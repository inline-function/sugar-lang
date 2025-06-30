/**
 * 本文件定义语义分析过程及规则
 * 编写时kt版本 2.1.0
 * 编写时时间   2025.4.16
 * 创建者      语法糖味函子酱(sugared functor)
 */
@file:Suppress("NestedLambdaShadowedImplicitParameter","NAME_SHADOWING","RedundantModalityModifier")

package compiler.semantic

import compiler.parser.*
import compiler.parser.AssignTree
import tools.context
typealias SemanticResult = Pair<Information,ProjectAST>
fun ProjectTree.transform() : SemanticResult =
    context(MutableInformation(),SymbolTable<Tag>()){ info,symbols ->
        //检查文件是否有重复
        files
            .groupBy { it.name }
            .forEach { (name,files) ->
                if(files.size > 1) info += DuplicateFilesException(name)
            }
        //收集所有顶层标签添加给符号表
        symbols.addAll(files.flatMap { it.tops }.map { Tag(it).first })
        //构建工程树
        info.result to ProjectAST(
            name  = name,
            files = files.map {
                FileAST(
                    name = it.name,
                    tops = it.tops.map { Tag(it).second() }
                )
            }
        )
    }
context(info : MutableInformation,symbols : SymbolTable<Tag>)
inline fun VariableTree.transform(top : Boolean = false,typeGetter : ()->TypeAST = { null!! }) : VariableAST {
    //变量值
    val value = value?.transform(returnType as? FunctionTypeTree)
    //变量返回类型
    val returnType = returnType?.transform()
    //检查变量必须有声明返回类型或值中的一个
    if(value == null && returnType == null)
        info += NoVariableTypeAndValueException(line,column,name)
    //如果变量声明有值,检查值的类型是否可以转换为变量类型
    if(value != null && returnType != null && !value.type.isCastableTo(returnType))
        info += CannotCastToTypeException(line,column,value.type,returnType)
    //构建变量树
    return VariableAST(
        line = line,
        column = column,
        name = name,
        returnType = returnType ?: value?.type ?: typeGetter(),
        annotations = annotations.map { it.transform() },
        aboveContext = aboveContext.map { it.transform() },
        belowContext = belowContext.map { it.transform() },
        receiver = receiver?.transform(),
        getter = getter?.transform(),
        setter = setter?.transform(),
        value = value,
    ).also { if(top) symbols += VariableTag(it).first }
}
context(info : MutableInformation,symbols : SymbolTable<Tag>)
fun FunctionTree.transform(top : Boolean = false) : FunctionAST {
    //分析类型,类型为空则使用Unit类型
    val type = returnType?.transform() ?: Unit
    //创建子作用域
    val body = SymbolTable(parent = symbols).run {
        //分析函数体
        body?.transform().also {
            //函数的最后一个表达式是函数的返回类型,检查返回类型是否匹配函数的声明返回类型
            (body?.stmts?.lastOrNull() as? ExpressionAST)?.type.let {
                if((it ?: Unit) isNotCastableTo type)
                    info += CannotCastToTypeException(line,column,it ?: Unit,type)
            }
        }
    }
    //构建函数树
    return FunctionAST(
        line = line,
        returnType = type,
        name = name,
        column = column,
        typeParameters = typeParameters,
        body = body,
        parameters = parameters.map { it.transform() },
        aboveContext = aboveContext.map { it.transform() },
        belowContext = belowContext.map { it.transform() },
        annotations = annotations.map { it.transform() },
    ).also { if(top) symbols += FunctionTag(it).first }
}
context(info : MutableInformation,symbols : SymbolTable<Tag>)
fun ClassTree.transform(top : Boolean = false) : ClassAST {
    //创建子作用域
    val members = SymbolTable(parent = symbols).run {
        //收集标签
        addAll(members.map { CallableTag(it).first })
        //分析成员
        members.map { it.transform() }.also {
            it.forEach { member ->
                //检查成员是否重复
                symbols.symbols.groupBy { it.name }
                    .filter { (_,members) -> members.size > 1 }
                    .forEach { (name,_) ->
                        info += DuplicateMemberException(line,column,name)
                    }
            }
        }
    }
    //构造类树
    return ClassAST(
        line = line,
        column = column,
        name = name,
        annotations = annotations.map { it.transform() },
        typeParameters = typeParameters,
        parents = parents.map { it.transform() },
        members = members
    ).also { if(top) symbols += ClassTag(it).first }
}
context(info : MutableInformation,symbols : SymbolTable<Tag>)
fun TopTree.transform(top : Boolean = false) : TopAST = when(this){
    is CallableTree     -> transform(top)
    is ClassTree        -> transform(top)
    is TypeVariableTree -> TODO()
}
context(info : MutableInformation,symbols : SymbolTable<Tag>)
fun CallableTree.transform(top : Boolean = false) : CallableAST = when(this){
    is FunctionTree     -> transform(top)
    is VariableTree     -> transform(top)
}
context(info : MutableInformation,symbols : SymbolTable<Tag>)
fun StatementTree.transform(need : FunctionTypeTree? = null) : StatementAST? = when(this){
    is TopTree        -> transform(true)
    is ExpressionTree -> transform(need)
}
context(info : MutableInformation,symbols : SymbolTable<Tag>)
fun TypeTree.transform() : TypeAST = when(this){
    is ApplyTypeTree    -> transform()
    is CommonTypeTree   -> transform()
    is FunctionTypeTree -> transform()
    is NullableTypeTree -> transform()
    is TupleTypeTree    -> transform()
}
context(info : MutableInformation,symbols : SymbolTable<Tag>)
fun CommonTypeTree.transform() : CommonTypeAST =
    CommonTypeAST(
        line = line,
        column = column,
        name = name,
        annotations = annotations.map { it.transform() }
    )
context(info : MutableInformation,symbols : SymbolTable<Tag>)
fun ApplyTypeTree.transform() : ApplyTypeAST =
    ApplyTypeAST(
        line = line,
        column = column,
        name = name,
        arguments = arguments.map { it.transform() },
        annotations = annotations.map { it.transform() }
    )
context(info : MutableInformation,symbols : SymbolTable<Tag>)
fun FunctionTypeTree.transform() : FunctionTypeAST =
    FunctionTypeAST(
        line = line,
        column = column,
        parameters = parameters.map { it.transform() },
        returnType = returnType.transform(),
        annotations = annotations.map { it.transform() }
    )
context(info : MutableInformation,symbols : SymbolTable<Tag>)
fun NullableTypeTree.transform() : NullableTypeAST =
    NullableTypeAST(
        line = line,
        column = column,
        type = type.transform(),
        annotations = annotations.map { it.transform() }
    )
context(info : MutableInformation,symbols : SymbolTable<Tag>)
fun TupleTypeTree.transform() : TupleTypeAST =
    TupleTypeAST(
        line = line,
        column = column,
        arguments = arguments.map { it.transform() },
        annotations = annotations.map { it.transform() }
    )
context(info : MutableInformation,symbols : SymbolTable<Tag>)
fun BodyTree.transform(need : FunctionTypeTree? = null) : BodyAST? = when(this){
    is ScopeTree      -> transform(need)
    is ExpressionTree -> transform(need)
}
context(info : MutableInformation,symbols : SymbolTable<Tag>)
fun ScopeTree.transform(need : FunctionTypeTree? = null) : ScopeAST =
    ScopeAST(
        line = line,
        column = column,
        stmts = stmts.dropLast(1).mapNotNull { it.transform() }.let {
            stmts.lastOrNull()?.transform(need)?.let { stmt ->
                it + stmt
            } ?: it
        }
    )
context(info : MutableInformation,symbols : SymbolTable<Tag>)
fun AnnotationTree.transform() = AnnotationAST(
    line = line,
    column = column,
    name = name,
    arguments = arguments?.transform()
)
context(info : MutableInformation,symbols : SymbolTable<Tag>)
fun ExpressionTree.transform(need : FunctionTypeTree? = null) : ExpressionAST? = when(this){
    is DecimalConstantTree -> transform()
    is IntegerConstantTree -> transform()
    is StringConstantTree  -> transform()
    is InvokeTree          -> transform()
    is LambdaTree          -> transform(need)
    is NameTree            -> transform()
    is AssignTree          -> transform()
}
context(info : MutableInformation,symbols : SymbolTable<Tag>)
fun AssignTree.transform() : AssignAST? {
    return AssignAST(
        line = line,
        column = column,
        name = name.transform() ?: return null,
        value = value.transform() ?: return null,
    )
}
context(info : MutableInformation,symbols : SymbolTable<Tag>)
fun NameTree.transform() : NameAST? {
    //分析表达式
    val expression = expression?.transform()
    //获取表达式类型的定义
    val exprType = expression?.type?.definition
    //获取名称树类型
    val type = if(exprType != null) exprType.members.find {
        it.name == name
    }?.let {
        when(it){
            is FunctionTag -> FunctionTypeAST(
                line = line,
                column = column,
                parameters = it.parameters.map { it.returnType!! },
                returnType = it.returnType ?: Unit,
                annotations = it.annotations.map { it }
            )
            is VariableTag -> it.returnType!!
        }
    } else symbols.parents.flatMap { it }.find {
        it.name == name
    }?.let {
        when(it){
            is FunctionTag -> FunctionTypeAST(
                line = line,
                column = column,
                parameters = it.parameters.map { it.returnType!! },
                returnType = it.returnType ?: Unit,
                annotations = it.annotations.map { it }
            )
            is VariableTag -> it.returnType!!
            is ClassTag    -> TODO()
            is ContextTag  -> TODO()
            is TypeVarTag  -> TODO()
        }
    }
    //判断类型是否为空,为空则指示找不到成员
    type ?: run {
        info += CannotFoundException(line,column,name)
        return null
    }
    //构造名称树
    return NameAST(
        line = line,
        column = column,
        expression = expression,
        name = name,
        type = type
    )
}
context(info : MutableInformation,symbols : SymbolTable<Tag>)
fun DecimalConstantTree.transform() : DecimalConstantAST =
    DecimalConstantAST(
        line = line,
        column = column,
        value = value,
        type = Dec
    )
context(info : MutableInformation,symbols : SymbolTable<Tag>)
fun IntegerConstantTree.transform() : IntegerConstantAST =
    IntegerConstantAST(
        line = line,
        column = column,
        value = value,
        type = Int
    )
context(info : MutableInformation,symbols : SymbolTable<Tag>)
fun StringConstantTree.transform() : StringConstantAST =
    StringConstantAST(
        line = line,
        column = column,
        value = value,
        type = Str
    )
context(info : MutableInformation,symbols : SymbolTable<Tag>)
fun InvokeTree.transform() : InvokeAST?{
    //分析调用者
    val invoker = invoker.transform() ?: return null
    //获取调用者的类型定义
    val invokerType = invoker.type.definition ?: return null
    //遍历类型成员,查找invoke函数
    val invokeFunction = invokerType.members.find {
        it.name == "invoke"
    } ?: run {
        //找不到
        info += CannotFoundException(line,column,"invoke")
        return null
    }
    //获取invoke函数的返回类型,那个就是调用树的类型
    val invokeType = when(invokeFunction){
        is FunctionTag -> invokeFunction.returnType ?: Unit
        is VariableTag -> TODO("变量当成函数调用")
    }
    //分析参数
    val arguments = arguments.mapIndexedNotNull {
        index,it -> it.transform(
            when(invokeFunction){
                is FunctionTag -> invokeFunction.parameters[index].returnType
                is VariableTag -> TODO("变量当成函数")
            } as? FunctionTypeTree
        )!!
    }
    //构造调用树
    return InvokeAST(
        line = line,
        column = column,
        invoker = invoker,
        arguments = arguments,
        typeArguments = typeArguments.map { it.transform() },
        type = invokeType
    )
}
context(info : MutableInformation,symbols : SymbolTable<Tag>)
fun LambdaTree.transform(need : FunctionTypeTree? = null) : LambdaAST? {
    //匿名函数的身体
    val body = body.transform()
    //身体最后一个表达式是匿名函数的返回值
    val returnType = (body.stmts.lastOrNull() as? ExpressionAST)?.type ?: Unit
    //检查返回值是否与预期相同
    need?.returnType?.let {
        if(returnType isNotCastableTo it.transform())
            info += CannotCastToTypeException(line,column,returnType,it.transform())
    }
    //检查参数
    need?.parameters
        ?.let { parameters zip it }
        ?.forEach { (parameter,wanting) ->
            parameter.returnType?.transform()?.also {
                if(it isNotCastableTo wanting.transform())
                    info += CannotCastToTypeException(line,column,it,wanting.transform())
            }
        }
    //分析参数
    val parameters = parameters.mapIndexed { index,parameter ->
        parameter.transform parameter@{
            need?.parameters[index]?.transform() ?: return@transform null
        }
    }
    //构造匿名函数树
    return LambdaAST(
        line = line,
        column = column,
        parameters = parameters,
        body = body,
        returnType = returnType,
        type = FunctionTypeAST(
            line = line,
            column = column,
            parameters = parameters.map { it.returnType },
            returnType = returnType,
            annotations = emptyList()
        ),
    )
}