/**
 * 本文件定义语义分析过程及规则
 * 编写时kt版本 2.1.0
 * 编写时时间   2025.4.16
 * 创建者      语法糖味函子酱(sugared functor)
 */
@file:Suppress("NestedLambdaShadowedImplicitParameter","NAME_SHADOWING","RedundantModalityModifier")

package compiler.semantic
import compiler.parser.LambdaTree as LambdaSyntaxTree
import compiler.parser.ApplyTypeTree as ApplyTypeSyntaxTree
import compiler.parser.FunctionTypeTree as FunctionTypeSyntaxTree
import compiler.parser.NullableTypeTree as NullableTypeSyntaxTree
import compiler.parser.CallableTree as CallableSyntaxTree
import compiler.parser.TopTree as TopSyntaxTree
import tools.ID
import tools.input
import tools.never
import java.io.Closeable
import compiler.parser.StatementTree as StatementSyntaxTree
import compiler.parser.ExpressionTree as ExpressionSyntaxTree
import compiler.parser.DecimalConstantTree as DecimalConstantSyntaxTree
import compiler.parser.IntegerConstantTree as IntegerConstantSyntaxTree
import compiler.parser.InvokeTree as InvokeSyntaxTree
import compiler.parser.NameTree as NameSyntaxTree
import compiler.parser.StringConstantTree as StringConstantSyntaxTree
import compiler.parser.CommonTypeTree as CommonTypeSyntaxTree
import compiler.parser.FunctionTree as FunctionSyntaxTree
import compiler.parser.ProjectTree as ProjectSyntaxTree
import compiler.parser.ClassTree as ClassSyntaxTree
import compiler.parser.VariableTree as VariableSyntaxTree
import compiler.parser.TypeTree as TypeSyntaxTree
typealias SemanticResult = Pair<ProjectTree,Information>
typealias LexicalScope = MutableScope<Tag>
inline val rootScope : LexicalScope
    get() = MutableScope()
inline val LexicalScope.subScope : LexicalScope
    get() = MutableScope(parent = this)
context(info : MutableInformation,scope : LexicalScope)
val TypeTree.definition : ClassTag?
    get() = scope.findClassSymbol(name)
sealed interface Tag{
    val line   : Int
    val column : Int
    val name   : ID
    context(info : MutableInformation,scope : LexicalScope)
    val result : TopTree
}
fun Tag(it : TopSyntaxTree) = when(it){
    is ClassSyntaxTree    -> ClassHead(it)
    is FunctionSyntaxTree -> FunctionHead(it)
    is VariableSyntaxTree -> VariableHead(it)
}
sealed interface CallableTag : Tag{
    context(info : MutableInformation,scope : LexicalScope)
    override val result : CallableTree
    context(info : MutableInformation,scope : LexicalScope)
    val type : TypeTree?
}
sealed interface HeadTag : Tag{
    val prototype : TopSyntaxTree
}
sealed interface DefTag : Tag{
    val prototype : TopTree
}
sealed interface FunctionTag : CallableTag{
    val typeParaments : List<ID>
    val parameters : List<VariableTag>
    context(info : MutableInformation,scope : LexicalScope)
    val typeAsVariable : FunctionTypeTree
}
sealed interface VariableTag : CallableTag
sealed interface ClassTag : Tag{
    val typeParaments : List<ID>
    context(info : MutableInformation,scope : LexicalScope)
    val parents : List<TypeTree>
    val members : List<CallableTag>
}
fun CallableTag(it : CallableSyntaxTree) : CallableTag = when(it){
    is FunctionSyntaxTree -> FunctionHead(it)
    is VariableSyntaxTree -> VariableHead(it)
}
fun CallableTag(it : CallableTree) : CallableTag = when(it){
    is FunctionTree -> FunctionDef(it)
    is VariableTree -> VariableDef(it)
}
@JvmInline value class VariableHead(override val prototype : VariableSyntaxTree) : VariableTag,HeadTag{
    override val line      : Int             get() = prototype.line
    override val column    : Int             get() = prototype.column
    override val name      : ID              get() = prototype.name
    final    val isMutable : Boolean         get() = prototype.isMutable
    context(info : MutableInformation,scope : LexicalScope)
    override val type      : TypeTree?       get() = prototype.returnType?.toAst()
    context(info : MutableInformation,scope : LexicalScope)
    override val result : VariableTree
        get() = prototype.value?.toAst(type).let {
            VariableTree(
                line       = line,
                column     = column,
                name       = name,
                returnType = type ?: it?.type,
                value      = it,
                isMutable  = isMutable
            ).also {
                check(it.returnType != null) {
                    no_such_type(it.returnType!!)
                    it.value?.type?.apply {
                        no_such_castable_type(it.returnType,this)
                    }
                }
            }
        }
}
@JvmInline value class FunctionHead(override val prototype : FunctionSyntaxTree) : FunctionTag,HeadTag{
    override val line   get() = prototype.line
    override val column get() = prototype.column
    override val name   get() = prototype.name
    context(info : MutableInformation,scope : LexicalScope)
    override val type  get() = prototype.returnType?.toAst() ?: unit
    override val typeParaments  get() = prototype.typeParameters
    override val parameters     get() = prototype.parameters.map(::VariableHead)
    context(info : MutableInformation,scope : LexicalScope)
    override val typeAsVariable get() = FunctionTypeTree(
        line       = line,
        column     = column,
        parameters = parameters.mapNotNull { it.type },
        returnType = type
    )
    context(info : MutableInformation,scope : LexicalScope)
    override val result : FunctionTree
        get() = FunctionTree(
            line = line,
            returnType = type,
            name = name,
            column = column,
            body = prototype.body?.let {
                scope.subScope.apply {
                    typeParaments.map {
                        ClassSyntaxTree(
                            line           = line,
                            column         = column,
                            name           = it,
                            typeParameters = emptyList(),
                            parents        = emptyList(), //TODO(类型参数边界)
                            members        = emptyList()
                        ) input ::ClassHead
                    } input ::addAll
                }.run {
                    BlockTree(it.mapNotNull { it.toAst() },line,column)
                }
            },
            parameters = parameters.map { it.result },
            typeParameters = typeParaments
        ).also {
            check {
                no_such_type(it.returnType)
            }
        }
}
@JvmInline value class ClassHead(override val prototype : ClassSyntaxTree) : ClassTag,HeadTag{
    override val line   get() = prototype.line
    override val column get() = prototype.column
    override val name   get() = prototype.name
    override val typeParaments get() = prototype.typeParameters
    context(info : MutableInformation,scope : LexicalScope)
    override val parents       get() = prototype.parents.map { it.toAst() }
    override val members       get() = prototype.members.map(::CallableTag)
    context(info : MutableInformation,scope : LexicalScope)
    override val result : ClassTree
        get() = ClassTree(
            line    = line,
            column  = column,
            name    = name,
            parents = parents,
            typeParameters = typeParaments,
            members = scope.subScope.apply {
                addAll(
                    typeParaments.map {
                        ClassTree(
                            line           = line,
                            column         = column,
                            name           = it,
                            typeParameters = emptyList(),
                            parents        = emptyList(), //TODO(类型参数边界)
                            members        = emptyList()
                        ) input ::ClassDef
                    }
                )
            }.run {
                members.map { it.result }
            }
        ).also {
            it.parents.forEach {
                check {
                    no_such_type(it)
                }
            }
        }
}
@JvmInline value class VariableDef(override val prototype : VariableTree) : VariableTag,DefTag{
    context(info : MutableInformation,scope : LexicalScope)
    override val result : CallableTree
        get() = prototype.also {
            check(it.returnType != null) {
                no_such_type(it.returnType!!)
                it.value?.type?.apply {
                    no_such_castable_type(it.returnType,this)
                }
            }
        }
    override val line : Int
        get() = prototype.line
    override val column : Int
        get() = prototype.column
    override val name : ID
        get() = prototype.name
    context(info : MutableInformation,scope : LexicalScope)
    override val type : TypeTree?
        get() = prototype.returnType
}
@JvmInline value class FunctionDef(override val prototype : FunctionTree) : FunctionTag,DefTag{
    context(info : MutableInformation,scope : LexicalScope)
    override val result : CallableTree
        get() = prototype.also {
            check {
                no_such_type(it.returnType)
            }
        }
    override val line : Int
        get() = prototype.line
    override val column : Int
        get() = prototype.column
    override val name : ID
        get() = prototype.name
    override val parameters : List<VariableTag>
        get() = prototype.parameters.map(::VariableDef)
    override val typeParaments : List<ID>
        get() = prototype.typeParameters
    context(info : MutableInformation,scope : LexicalScope)
    override val type : TypeTree?
        get() = prototype.returnType
    context(info : MutableInformation,scope : LexicalScope)
    override val typeAsVariable get() = FunctionTypeTree(
        line       = line,
        column     = column,
        parameters = prototype.parameters.mapNotNull { it.returnType },
        returnType = prototype.returnType
    )
}
@JvmInline value class ClassDef(override val prototype : ClassTree) : ClassTag,DefTag{
    override val line : Int
        get() = prototype.line
    override val column : Int
        get() = prototype.column
    override val members : List<CallableTag>
        get() = prototype.members.map(::CallableTag)
    context(info : MutableInformation,scope : LexicalScope)
    override val parents : List<TypeTree>
        get() = prototype.parents
    override val typeParaments : List<ID>
        get() = prototype.typeParameters
    override val name : ID
        get() = prototype.name
    context(info : MutableInformation,scope : LexicalScope)
    override val result : TopTree
        get() = prototype.also {
            it.parents.forEach {
                check {
                    no_such_type(it)
                }
            }
        }
}
inline fun LexicalScope.findSymbol(bool : (Tag)->Boolean) : Tag?{
    parents.forEach {
        it.symbols.forEach {
            if (bool(it)) return it
        }
    }
    return null
}
fun LexicalScope.findClassSymbol(name : ID) : ClassTag?{
    parents.forEach {
        it.symbols.forEach {
            if (it is ClassTag && it.name == name)
                return it
        }
    }
    return null
}
fun LexicalScope.findVariableSymbol(name : String) : VariableTag?{
    parents.forEach {
        it.symbols.forEach {
            if ((it is VariableTag || it is VariableDef) && it.name == name) return it
        }
    }
    return null
}
fun LexicalScope.findFunctionSymbol(name : String) : FunctionTag?{
    parents.forEach {
        it.symbols.forEach {
            if ((it is FunctionTag || it is FunctionDef) && it.name == name) return it
        }
    }
    return null
}
inline fun <T> compilation(
    info : MutableInformation,scope : LexicalScope,
    block : context(MutableInformation,LexicalScope) (MutableInformation,LexicalScope)->T
) = with(info) { with(scope) { block(info,scope) } }
fun ProjectSyntaxTree.semanticAnalysis() : SemanticResult=
    compilation(MutableInformation(),rootScope){ info,scope ->
        //创建项目构建器
        val projectBuilder = buildProjectTree(name = name)
        //遍历每一个文件,转换成一个匿名函数,然后遍历匿名函数获取所有文件的抽象语法树
        val files = files.map { file ->
            //创建文件构建器
            val fileBuilder = buildFileTree(name = file.name)
            //遍历文件的所有顶层成员,收集它们的标签
            val tags = file.tops.map(::Tag)
            //检查是否有标签重名
            check {
                duplicate_tags(tags)
            }
            //将标签全部添加到根作用域
            scope.addAll(tags)
            //返回一个匿名函数:遍历文件每一个标签,依次构造它们的抽象语法树,
            //将它们全部添加到文件构建器中后构建文件的抽象语法树
            return@map {
                tags.map {
                    //检查顶层变量是否有类型,是否初始化
                    check(it is VariableHead) {
                        root_scope_variable_no_type((it as VariableHead).prototype)
                    }
                    it.result.apply {
                        check(this is VariableTree) {
                            no_such_variable_init(this as VariableTree)
                        }
                    }
                } input fileBuilder.tops::addAll
                fileBuilder.result
            }
        }.map { it() }
        //把所有文件的抽象语法树添加为工程构建器
        projectBuilder.files.addAll(files)
        //检查文件是否重复
        check {
            duplicate_files(projectBuilder.files)
        }
        //语义分析告一段落,且听下回分解
        projectBuilder.result to info.result
    }
context(info : MutableInformation,scope : LexicalScope)
fun CommonTypeSyntaxTree.toAst() : CommonTypeTree =
    CommonTypeTree(line,column,name)
context(info : MutableInformation,scope : LexicalScope)
fun TypeSyntaxTree.toAst() : TypeTree = when(this){
    is CommonTypeSyntaxTree   -> toAst()
    is ApplyTypeSyntaxTree    -> toAst()
    is FunctionTypeSyntaxTree -> toAst()
    is NullableTypeSyntaxTree -> toAst()
}
context(info : MutableInformation,scope : LexicalScope)
fun ApplyTypeSyntaxTree.toAst() = ApplyTypeTree(
    line      = line,
    column    = column,
    name      = name,
    arguments = arguments.map { it.toAst() }
)
context(info : MutableInformation,scope : LexicalScope)
fun FunctionTypeSyntaxTree.toAst() = FunctionTypeTree(
    line       = line,
    column     = column,
    parameters = parameters.map { it.toAst() },
    returnType = returnType.toAst()
)
context(info : MutableInformation,scope : LexicalScope)
fun NullableTypeSyntaxTree.toAst() = NullableTypeTree(
    line       = line,
    column     = column,
    type       = type.toAst()
)
context(info : MutableInformation,scope : LexicalScope)
fun StatementSyntaxTree.toAst() : StatementTree? = when(this){
    is ExpressionSyntaxTree -> toAst()
    is FunctionSyntaxTree   -> FunctionHead(this).result.apply { scope.symbols.add(FunctionDef(this)) }
    is VariableSyntaxTree   -> VariableHead(this).result.apply { no_such_variable_init(this) ; scope.symbols.add(VariableDef(this))  }
    is ClassSyntaxTree      -> ClassHead(this).result.apply { scope.symbols.add(ClassDef(this)) }
}
context(info : MutableInformation,scope : LexicalScope)
fun ExpressionSyntaxTree.toAst(requirementType : TypeTree? = null) : ExpressionTree? = when(this){
    is DecimalConstantSyntaxTree -> toAst()
    is IntegerConstantSyntaxTree -> toAst()
    is StringConstantSyntaxTree  -> toAst()
    is InvokeSyntaxTree          -> toAst()
    is NameSyntaxTree            -> toAst()
    is LambdaSyntaxTree          -> toAst(requirementType)
}
context(info : MutableInformation,scope : LexicalScope)
fun LambdaSyntaxTree.toAst(
    requirementType : TypeTree?
) : LambdaTree? = scope.subScope.run {
    buildLambdaTree(
        line       = line,
        column     = column,
    ) {
        //判断是否有需求
        if(requirementType != null){
            //判断需求类型是否是函数类型
            check {
                type_is_not_function_type(requirementType) { return@toAst null }
            }
            requirementType as FunctionTypeTree
            //匿名函数参数列表
            parameters = (
                this@toAst.parameters.firstOrNull()?.returnType?.let {
                    this@toAst.parameters.map {
                        VariableHead(it).result
                    }
                } ?: (this@toAst.parameters zip requirementType.parameters)
                    .map { (param,type) ->
                        VariableHead(param).result.copy(returnType = type)
                    }
            ).toMutableList()
            //添加参数
            scope.symbols.addAll(parameters.map { VariableDef(it) })
        }else{
            //匿名函数参数列表
            parameters = this@toAst.parameters.map { VariableHead(it).result }.toMutableList()
            //添加参数
            scope.symbols.addAll(parameters.map { VariableDef(it) })
            //没有需求类型,检查每一个参数是否声明类型
            check {
                no_such_lambda_parameter_type(parameters) { return@toAst null }
            }
            //根据参数类型,附加匿名函数的类型
            type = FunctionTypeTree(
                line       = line!!,
                column     = column!!,
                parameters = parameters.mapNotNull { it.returnType },
                returnType = when(val last = body!!.lastOrNull()){
                    is ExpressionTree -> last.type
                    else              -> unit
                }
            )
        }
        //求值函数体
        body = BlockTree(
            stmts  = this@toAst.body.mapNotNull { it.toAst() },
            line   = this@toAst.line,
            column = this@toAst.column
        )
        //匿名函数的返回类型
        val returnType = when(val last = body?.lastOrNull()){
            is ExpressionTree -> last.type
            else              -> unit
        }
        //检查匿名函数的返回类型是否和需求类型一致
        check(requirementType != null && requirementType.returnType != unit) {
            no_such_castable_type(returnType,requirementType!!.returnType) { return@toAst null }
        }
        //根据需求参数类型,附加匿名函数的类型
        type = FunctionTypeTree(
            line       = line!!,
            column     = column!!,
            parameters = parameters.mapNotNull { it.returnType },
            returnType = returnType,
        )
    }.result
}
context(info : MutableInformation,scope : LexicalScope)
fun NameSyntaxTree.toAst() : NameTree? =
    buildNameTree(
        line = line,
        column = column,
        expression = expression?.toAst(),
        name = name,
    ) {
        //上表达式不为空
        expression?.let {
            //先取上表达式的类型
            val exprType = it.type
            //检查是否能找到类型定义
            check {
                no_such_type(exprType) { return@toAst null }
            }
            //从作用域中获取上表达式类型的实际类定义
            val realType = exprType.definition!!
            //检查是否能找到成员
            check {
                no_such_member(realType,name!!) { return@toAst null }
            }
            //寻找类定义中名字为name的成员
            val member = when(realType){
                is ClassDef  -> realType.prototype.members.find { it.name == name }!!.let(::CallableTag)
                is ClassHead -> realType.members.find { it.name == name }!!
            }
            //判断成员标签类型
            type = when(member){
                is FunctionTag -> TODO("暂不支持直接引用函数成员")
                is VariableHead -> member.type ?: return@toAst null
                is VariableDef -> member.prototype.returnType ?: return@toAst null
            }
        } ?: run {
            //检查作用域是否能找到这个名字
            check {
                no_such_variable(name!!,line!!,column!!) { return@toAst null }
            }
            //从作用域获取变量或者函数
            type = scope.findVariableSymbol(name!!)?.run {
                when(this){
                    is VariableDef  -> prototype.returnType
                    is VariableHead -> type
                }!!
            } ?: scope.findFunctionSymbol(name!!)!!.run {
                when(this){
                    is FunctionDef  -> typeAsVariable
                    is FunctionHead -> typeAsVariable
                }
            }
        }
    }.result
context(info : MutableInformation,scope : LexicalScope)
fun StringConstantSyntaxTree.toAst() : StringConstantTree =
    StringConstantTree(line,column,value,CommonTypeTree(line,column,"Str"))
context(info : MutableInformation,scope : LexicalScope)
fun DecimalConstantSyntaxTree.toAst() : DecimalConstantTree =
    DecimalConstantTree(line,column,value,CommonTypeTree(line,column,"Dec"))
context(info : MutableInformation,scope : LexicalScope)
fun IntegerConstantSyntaxTree.toAst() : IntegerConstantTree =
    IntegerConstantTree(line,column,value,CommonTypeTree(line,column,"Int"))
context(info : MutableInformation,scope : LexicalScope)
fun InvokeSyntaxTree.toAst() : InvokeTree? =
    buildInvokeTree(
        line = line,
        column = column,
        invoker = invoker.toAst(),
    ) {
        //调用者如果为空则表示下层出现异常,本层无力处理,因此返回null
        invoker ?: return@toAst null
        //确认类型可以找到
        check {
            no_such_type(invoker!!.type) { return@toAst null }
        }
        //获取调用者类型
        val invokerType = invoker!!.type.definition!!
        //判断是否可调用
        check {
            no_invoke_function(invokerType) { return@toAst null }
        }
        //在调用者类型中寻找名为invoke的函数
        val invokeFunction = when(invokerType){
            is ClassDef  -> invokerType.prototype.members.find {
                it is FunctionTree && it.name == invokeFunctionName
            } as FunctionTree input ::FunctionDef
            is ClassHead -> invokerType.members.find {
                it is FunctionTag && it.name == invokeFunctionName
            } as FunctionTag
        }
        //invoke表达式的类型就是invoke函数的返回值类型
        type = invokeFunction returnTypeBeErasedBy invoker!!.type
        //然后依次提供需求以构造实参
        arguments = when(invokeFunction){
            is FunctionDef  -> (this@toAst.arguments zip invokeFunction.prototype.parameters)
                .mapNotNull { (expr,tag) -> expr.toAst(tag.returnType) }
            is FunctionHead -> (this@toAst.arguments zip invokeFunction.parameters)
                .mapNotNull { (expr,tag) -> expr.toAst(tag.type) }
        }.toMutableList()
        //判断函数类型是否符合
        check {
            (invoker as? NameTree)?.let {
                scope.findFunctionSymbol(it.name)?.let {
                    parameter_type_error(it,invoker!!.type,arguments,line!!,column!!) { return@toAst null }
                }
            } ?: parameter_type_error(invokeFunction,invoker!!.type,arguments,line!!,column!!) { return@toAst null }
        }
    }.result



