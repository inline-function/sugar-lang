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
import tools.ID
typealias SemanticResult = Pair<ProjectAST,Information>
fun ProjectTree.transform() : SemanticResult =
    context(MutableInformation(),SymbolTable<Tag>()){ info,symbols ->
        //检查文件是否有重复
        files
            .groupBy { it.name }
            .forEach { (name,files) ->
                if(files.size > 1) info += DuplicateFilesException(name)
            }
        //收集所有顶层标签添加给符号表
        files.forEach { file ->
            with(file.name) {
                file.tops.forEach {
                    symbols += Tag(it).first
                }
            }
        }
        //构建工程树
        ProjectAST(
            name  = name,
            files = files.map {
                with(it.name) {
                    FileAST(
                        name = it.name,
                        tops = it.tops.map { Tag(it).second() }
                    )
                }
            }
        ) to info.result
    }
context(info : MutableInformation,symbols : SymbolTable<Tag>,file : ID)
inline fun VariableTree.transform(top : Boolean = false,isParameter : Boolean = false,typeGetter : ()->TypeAST = { null!! }) : VariableAST {
    //变量值
    val value = value?.transform(returnType as? FunctionTypeTree)
    //变量返回类型
    val returnType = returnType?.transform()
    //检查变量必须有声明返回类型或值中的一个
    if(!isParameter && value == null && returnType == null)
        info += NoVariableTypeAndValueException(file,line,column,name)
    //如果变量声明有值,检查值的类型是否可以转换为变量类型
    if(value != null && returnType != null && !value.type.isCastableTo(returnType))
        info += CannotCastToTypeException(file,line,column,value.type,returnType)
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
context(info : MutableInformation,symbols : SymbolTable<Tag>,file : ID)
fun FunctionTree.transform(top : Boolean = false) : FunctionAST {
    //特化结果
    val result = FunctionTag(this).first.specialization(
        typeParameters
            .map { it.transform() }
            .associateWith { it.bound }
    ) as FunctionTag
    //分析参数
    val parameters = result.parameters
    //分析类型,类型为空则使用Unit类型
    val type = result.returnType ?: Unit
    //创建子作用域
    val body = SymbolTable(parent = symbols).run {
        //将类型参数添加入符号表
        addAll(typeParameters.map { TypeVarTag(it) })
        //将参数添加入符号表
        addAll(parameters.map { it })
        //分析函数体
        body?.transform()?.also {
            //函数的最后一个表达式是函数的返回类型,检查返回类型是否匹配函数的声明返回类型
            (it.stmts.lastOrNull() as? ExpressionAST)?.type.let {
                val it = it ?: Unit
                val typeVars = symbols.parents
                    .map { it.symbols }
                    .filterIsInstance<TypeVarTag>()
                val type = if(it is CommonTypeAST && it.name in typeVars.map { it.name })
                    typeVars.find { v -> v.name == it.name }!!.bound ?: Any
                else it
                if(type isNotCastableTo type)
                    info += CannotCastToTypeException(file,line,column,it,type)
            }
        }
    }
    //构建函数树
    return FunctionAST(
        line = line,
        returnType = this.returnType?.transform() ?: Unit,
        name = name,
        column = column,
        typeParameters = typeParameters.map { it.transform() },
        body = body,
        parameters = this.parameters.map { it.transform() },
        aboveContext = aboveContext.map { it.transform() },
        belowContext = belowContext.map { it.transform() },
        annotations = annotations.map { it.transform() },
    ).also { if(top) symbols += FunctionTag(it).first }
}
context(info : MutableInformation,symbols : SymbolTable<Tag>,file : ID)
fun TypeVariableTree.transform() : TypeVariableAST = TypeVariableAST(
    line = line,
    column = column,
    name = name,
    bound = bound?.transform() ?: NullableTypeAST(
        line = line,
        column = column,
        type = Any,
        annotations = emptyList()
    ),
    annotations = annotations.map { it.transform() },
)
context(info : MutableInformation,symbols : SymbolTable<Tag>,file : ID)
fun ClassTree.transform(top : Boolean = false) : ClassAST {
    //创建子作用域
    val members = SymbolTable(parent = symbols).run {
        //收集标签
        addAll(members.map { CallableTag(it).first })
        //分析成员
        members
            .map { it.transform() }
            .also {
                it.forEach { member ->
                    //检查成员是否重复
                    symbols.symbols
                        .groupBy { it.name }
                        .filter { (_,members) -> members.size > 1 }
                        .forEach { (name,_) ->
                            info += DuplicateMemberException(file,line,column,name)
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
        typeParameters = typeParameters.map { it.transform() },
        parents = parents.map { it.transform() },
        members = members
    ).also { if(top) symbols += ClassTag(it).first }
}
context(info : MutableInformation,symbols : SymbolTable<Tag>,file : ID)
fun TopTree.transform(top : Boolean = false) : TopAST = when(this){
    is CallableTree     -> transform(top)
    is ClassTree        -> transform(top)
    is TypeVariableTree -> TODO()
}
context(info : MutableInformation,symbols : SymbolTable<Tag>,file : ID)
fun CallableTree.transform(top : Boolean = false) : CallableAST = when(this){
    is FunctionTree     -> transform(top)
    is VariableTree     -> transform(top)
}
context(info : MutableInformation,symbols : SymbolTable<Tag>,file : ID)
fun StatementTree.transform(need : FunctionTypeTree? = null) : StatementAST? = when(this){
    is TopTree        -> transform(true)
    is ExpressionTree -> transform(need)
}
context(info : MutableInformation,symbols : SymbolTable<Tag>,file : ID)
fun StatementTree.transform(need : FunctionTypeAST?) : StatementAST? = when(this){
    is TopTree        -> transform(true)
    is ExpressionTree -> transform(need)
}
context(info : MutableInformation,symbols : SymbolTable<Tag>,file : ID)
fun TypeTree.transform() : TypeAST = when(this){
    is ApplyTypeTree    -> transform()
    is CommonTypeTree   -> transform()
    is FunctionTypeTree -> transform()
    is NullableTypeTree -> transform()
    is TupleTypeTree    -> transform()
}
context(info : MutableInformation,symbols : SymbolTable<Tag>,file : ID)
fun CommonTypeTree.transform() : CommonTypeAST =
    CommonTypeAST(
        line = line,
        column = column,
        name = name,
        annotations = annotations.map { it.transform() }
    )
context(info : MutableInformation,symbols : SymbolTable<Tag>,file : ID)
fun ApplyTypeTree.transform() : ApplyTypeAST =
    ApplyTypeAST(
        line = line,
        column = column,
        name = name,
        arguments = arguments.map { it.transform() },
        annotations = annotations.map { it.transform() }
    )
context(info : MutableInformation,symbols : SymbolTable<Tag>,file : ID)
fun FunctionTypeTree.transform() : FunctionTypeAST =
    FunctionTypeAST(
        line = line,
        column = column,
        parameters = parameters.map { it.transform() },
        returnType = returnType.transform(),
        annotations = annotations.map { it.transform() }
    )
context(info : MutableInformation,symbols : SymbolTable<Tag>,file : ID)
fun NullableTypeTree.transform() : NullableTypeAST =
    NullableTypeAST(
        line = line,
        column = column,
        type = type.transform(),
        annotations = annotations.map { it.transform() }
    )
context(info : MutableInformation,symbols : SymbolTable<Tag>,file : ID)
fun TupleTypeTree.transform() : TupleTypeAST =
    TupleTypeAST(
        line = line,
        column = column,
        arguments = arguments.map { it.transform() },
        annotations = annotations.map { it.transform() }
    )
context(info : MutableInformation,symbols : SymbolTable<Tag>,file : ID)
fun BodyTree.transform(need : FunctionTypeTree? = null) : BodyAST? = when(this){
    is ScopeTree      -> transform(need)
    is ExpressionTree -> transform(need)
}
context(info : MutableInformation,symbols : SymbolTable<Tag>,file : ID)
fun ScopeTree.transform(need : FunctionTypeTree? = null) : ScopeAST =
    ScopeAST(
        line = line,
        column = column,
        stmts = stmts
            .dropLast(1)
            .mapNotNull { it.transform() }
            .let {
                stmts
                    .lastOrNull()
                    ?.transform(need)
                    ?.let { stmt -> it + stmt }
                    ?: it
            }
    )
context(info : MutableInformation,symbols : SymbolTable<Tag>,file : ID)
fun BodyTree.transform(need : FunctionTypeAST?) : BodyAST? = when(this){
    is ScopeTree      -> transform(need)
    is ExpressionTree -> transform(need)
}
context(info : MutableInformation,symbols : SymbolTable<Tag>,file : ID)
fun ScopeTree.transform(need : FunctionTypeAST?) : ScopeAST =
    ScopeAST(
        line = line,
        column = column,
        stmts = stmts
            .dropLast(1)
            .mapNotNull { it.transform() }
            .let {
                stmts
                    .lastOrNull()
                    ?.transform(need)
                    ?.let { stmt -> it + stmt }
                    ?: it
            }
    )
context(info : MutableInformation,symbols : SymbolTable<Tag>,file : ID)
fun AnnotationTree.transform() = AnnotationAST(
    line = line,
    column = column,
    name = name,
    arguments = arguments?.transform()
)
context(info : MutableInformation,symbols : SymbolTable<Tag>,file : ID)
fun ExpressionTree.transform(need : FunctionTypeTree? = null) : ExpressionAST? = when(this){
    is DecimalConstantTree -> transform()
    is IntegerConstantTree -> transform()
    is StringConstantTree  -> transform()
    is InvokeTree          -> transform()
    is LambdaTree          -> transform(need)
    is NameTree            -> transform()
    is AssignTree          -> transform()
}
context(info : MutableInformation,symbols : SymbolTable<Tag>,file : ID)
fun ExpressionTree.transform(need : FunctionTypeAST?) : ExpressionAST? = when(this){
    is DecimalConstantTree -> transform()
    is IntegerConstantTree -> transform()
    is StringConstantTree  -> transform()
    is InvokeTree          -> transform()
    is LambdaTree          -> transform(need)
    is NameTree            -> transform()
    is AssignTree          -> transform()
}
context(info : MutableInformation,symbols : SymbolTable<Tag>,file : ID)
fun AssignTree.transform() : AssignAST? {
    if(name !is NameTree) {
        info += CannotAssignValueException(file,line,column)
        return null
    }
    symbols.parents
        .flatMap { it }
        .filterIsInstance<VariableTag>()
        .find { it.name == name.name }
        ?.let {
            if(it.annotations.find { it.name == "mut" } == null) {
                info += ImmutableVariableCannotBeAssignedException(file,line,column,name.name)
                return null
            }
        }
    return AssignAST(
        line = line,
        column = column,
        name = name.transform() ?: return null,
        value = value.transform() ?: return null,
    )
}
context(info : MutableInformation,symbols : SymbolTable<Tag>,file : ID)
fun NameTree.transform() : NameAST? {
    //分析表达式
    val expression = expression?.transform()
    //获取表达式类型的定义
    val exprType = expression?.type?.definition
    //获取名称树类型
    val type = if(exprType != null) exprType.members
        .find { it.name == name }
        ?.let {
            when(it){
                is FunctionTag -> FunctionTypeAST(
                    line = line,
                    column = column,
                    parameters = it.parameters.map { it.returnType!! },
                    returnType = it.returnType ?: Unit,
                    annotations = it.annotations,
                    typeParameters = it.typeParameters,
                )
                is VariableTag -> it.returnType!!
            }
        }
    else symbols.parents
        .flatMap { it }
        .find { it.name == name }
        ?.let {
            when(it){
                is FunctionTag -> FunctionTypeAST(
                    line = line,
                    column = column,
                    parameters = it.parameters.map { it.returnType!! },
                    returnType = it.returnType ?: Unit,
                    annotations = it.annotations,
                    typeParameters = it.typeParameters
                )
                is VariableTag -> it.returnType!!
                is ClassTag    -> TODO()
                is ContextTag  -> TODO()
                is TypeVarTag  -> TODO()
            }
        }
    //判断类型是否为空,为空则指示找不到成员
    type ?: run {
        info += CannotFoundException(file,line,column,name)
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
context(info : MutableInformation,symbols : SymbolTable<Tag>,file : ID)
fun DecimalConstantTree.transform() : DecimalConstantAST =
    DecimalConstantAST(
        line = line,
        column = column,
        value = value,
        type = Dec
    )
context(info : MutableInformation,symbols : SymbolTable<Tag>,file : ID)
fun IntegerConstantTree.transform() : IntegerConstantAST =
    IntegerConstantAST(
        line = line,
        column = column,
        value = value,
        type = Int
    )
context(info : MutableInformation,symbols : SymbolTable<Tag>,file : ID)
fun StringConstantTree.transform() : StringConstantAST =
    StringConstantAST(
        line = line,
        column = column,
        value = value,
        type = Str
    )
context(info : MutableInformation,symbols : SymbolTable<Tag>,file : ID)
fun InvokeTree.transform() : InvokeAST?{
    //分析调用者
    val invoker = invoker.transform() ?: return null
    //获取调用者的类型定义
    val invokerType = invoker.type.definition ?: return null
    //遍历类型成员,查找invoke函数
    val invokeFunction = invokerType.members.find {
        it.name  == "invoke"
    } ?: run {
        //找不到
        info += CannotFoundException(file,line,column,"invoke")
        return null
    }
    //获取invoke函数的返回类型,那个就是调用树的类型
    val invokeType = when(invokeFunction){
        is FunctionTag -> invokeFunction.returnType?.specialization(
            (invoker.type as? FunctionTypeAST)
                ?.typeParameters
                ?.zip(typeArguments.map { it.transform() })
                ?.toMap()
                ?: emptyMap()
        ) ?: Unit
        is VariableTag -> TODO("变量当成函数调用")
    }
    //分析参数
    val arguments = arguments.mapIndexedNotNull {
        index,it -> it.transform(
            when(invokeFunction){
                is FunctionTag -> invokeFunction.parameters[index].returnType
                is VariableTag -> TODO("变量当成函数")
            } as? FunctionTypeAST
        )
    }
    //判断函数的参数是否匹配
    invokeFunction.parameters.forEachIndexed { index,it ->
        //实参类型
        val arg = arguments.getOrNull(index)?.type
        //形参类型
        val param = run {
            val param = it.returnType!!
            val typeVars = symbols.parents
                .flatMap { it }
                .filterIsInstance<TypeVarTag>() +
                    ((invoker.type as? FunctionTypeAST)?.typeParameters?.map { TypeVarTag(it) } ?: emptyList())
            if(param is CommonTypeAST && param.name in typeVars.map { it.name })
                typeVars.find { it.name == param.name }!!.bound ?: Any
            else param
        }
        arg?.apply {
            //实参不能转换为形参
            if(arg isNotCastableTo param)
                info += ArgumentTypeException(file,arg.line,arg.column,arg,param)
        } ?: run {
            //没有实参
            info += ArgumentTypeException(file,line,column,null,param)
        }
    }
    //判断函数的类型参数是否匹配
    ((invoker.type as? FunctionTypeAST)?.typeParameters ?: invokeFunction.typeParameters)
        .forEachIndexed { index,it ->
            //实参类型
            val arg = typeArguments.getOrNull(index)?.transform()
            //形参类型
            val param = it.bound
            arg?.apply {
                //实参不能转换为形参
                if(arg isNotCastableTo param)
                    info += ArgumentTypeException(file, arg.line, arg.column, arg, param, true)
            } ?: run {
                //没有实参
                info += ArgumentTypeException(file,line,column,null,param,true)
            }
        }
    //构造调用树
    return InvokeAST(
        line = line,
        column = column,
        invoker = invoker,
        arguments = arguments,
        typeArguments = typeArguments.map { it.transform() },
        type = invokeType,
    )
}
context(info : MutableInformation,symbols : SymbolTable<Tag>,file : ID)
fun LambdaTree.transform(need : FunctionTypeTree? = null) : LambdaAST? {
    //检查参数
    need?.parameters
        ?.let { parameters zip it }
        ?.forEach { (parameter,wanting) ->
            parameter.returnType?.transform()?.also {
                if(it isNotCastableTo wanting.transform())
                    info += CannotCastToTypeException(file,line,column,it,wanting.transform())
            }
        }
    //分析参数
    val parameters = parameters.mapIndexed { index,parameter ->
        parameter.transform(isParameter = true) parameter@{
            need?.parameters[index]?.transform() ?: return@transform null
        }
    }
    //匿名函数的身体
    val body = with(SymbolTable(parent = symbols)){
        this.symbols.addAll(parameters.map { VariableTag(it).first })
        body.transform()
    }
    //身体最后一个表达式是匿名函数的返回值
    val returnType = (body.stmts.lastOrNull() as? ExpressionAST)?.type ?: Unit
    //检查返回值是否与预期相同
    need?.returnType?.let {
        if(returnType isNotCastableTo it.transform())
            info += CannotCastToTypeException(file,line,column,returnType,it.transform())
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
context(info : MutableInformation,symbols : SymbolTable<Tag>,file : ID)
fun LambdaTree.transform(need : FunctionTypeAST?) : LambdaAST? {
    //检查参数
    need?.parameters
        ?.let { parameters zip it }
        ?.forEach { (parameter,wanting) ->
            parameter.returnType?.transform()?.also {
                if(it isNotCastableTo wanting)
                    info += CannotCastToTypeException(file,line,column,it,wanting)
            }
        }
    //分析参数
    val parameters = parameters.mapIndexed { index,parameter ->
        parameter.transform(isParameter = true) parameter@{
            need?.parameters[index] ?: return@transform null
        }
    }
    //匿名函数的身体
    val body = with(SymbolTable(parent = symbols)){
        this.symbols.addAll(parameters.map { VariableTag(it).first })
        body.transform()
    }
    //身体最后一个表达式是匿名函数的返回值
    val returnType = (body.stmts.lastOrNull() as? ExpressionAST)?.type ?: Unit
    //检查返回值是否与预期相同
    need?.returnType?.let {
        if(returnType isNotCastableTo it)
            info += CannotCastToTypeException(file,line,column,returnType,it)
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