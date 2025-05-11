/**
 * 本文件定义语义分析过程及规则
 * 编写时kt版本 2.1.0
 * 编写时时间   2025.4.16
 * 创建者      语法糖味函子酱(sugared functor)
 */
@file:Suppress("NestedLambdaShadowedImplicitParameter","NAME_SHADOWING","RedundantModalityModifier")

package compiler.semantic
import compiler.semantic.LexicalScope
import compiler.semantic.MutableInformation
import compiler.parser.CallableTree as CallableSyntaxTree
import compiler.parser.TopTree as TopSyntaxTree
import tools.ID
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
    is ClassSyntaxTree    -> ClassTag(it)
    is FunctionSyntaxTree -> FunctionTag(it)
    is VariableSyntaxTree -> VariableTag(it)
}
sealed interface CallableTag : Tag{
    context(info : MutableInformation,scope : LexicalScope)
    override val result : CallableTree
}
fun CallableTag(it : CallableSyntaxTree) = when(it){
    is FunctionSyntaxTree -> FunctionTag(it)
    is VariableSyntaxTree -> VariableTag(it)
}
@JvmInline value class VariableTag(val prototype : VariableSyntaxTree) : CallableTag{
    override val line      : Int             get() = prototype.line
    override val column    : Int             get() = prototype.column
    override val name      : ID              get() = prototype.name
    final    val isMutable : Boolean         get() = prototype.isMutable
    context(info : MutableInformation,scope : LexicalScope)
    final    val type      : TypeTree?       get() = prototype.returnType?.toAst()
    context(info : MutableInformation,scope : LexicalScope)
    override val result : VariableTree
        get() = VariableTree(
            line       = line,
            column     = column,
            name       = name,
            returnType = type,
            value      = prototype.value?.toAst(),
            isMutable  = isMutable
        ).also {
            check(it.returnType != null) {
                no_such_type(it.returnType!!)
            }
        }
}
@JvmInline value class FunctionTag(val prototype : FunctionSyntaxTree) : CallableTag{
    override val line       : Int               get() = prototype.line
    override val column     : Int               get() = prototype.column
    override val name       : ID                get() = prototype.name
    context(info : MutableInformation,scope : LexicalScope)
    final    val type       : TypeTree?         get() = prototype.returnType?.toAst()
    final    val parameters : List<VariableTag> get() = prototype.parameters.map(::VariableTag)
    context(info : MutableInformation,scope : LexicalScope)
    override val result : FunctionTree
        get() = FunctionTree(
            line = line,
            returnType = type,
            name = name,
            column = column,
            body = prototype.body?.let {
                BlockTree(it.mapNotNull { it.toAst() },line,column)
            },
            parameters = parameters.map { it.result }
        ).also {
            check(it.returnType != null) {
                no_such_type(it.returnType!!)
            }
        }
}
@JvmInline value class ClassTag(val prototype : ClassSyntaxTree) : Tag{
    override val line    get() = prototype.line
    override val column  get() = prototype.column
    override val name    get() = prototype.name
    final    val parents get() = prototype.parents
    final    val members get() = prototype.members.map {
        when(it){
            is FunctionSyntaxTree -> FunctionTag(it)
            is VariableSyntaxTree -> VariableTag(it)
        }
    }
    context(info : MutableInformation,scope : LexicalScope)
    override val result : ClassTree
        get() = ClassTree(
            line    = line,
            column  = column,
            name    = name,
            parents = parents.map { it.toAst() },
            members = with(scope.subScope){
                members.map {
                    it.result
                }
            }
        ).also {
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
fun LexicalScope.findClassSymbol(name : String) : ClassTag?{
    parents.forEach {
        it.symbols.forEach {
            if (it is ClassTag && it.name == name) return it
        }
    }
    return null
}
fun LexicalScope.findVariableSymbol(name : String) : VariableTag?{
    parents.forEach {
        it.symbols.forEach {
            if (it is VariableTag && it.name == name) return it
        }
    }
    return null
}
fun LexicalScope.findFunctionSymbol(name : String) : FunctionTag?{
    parents.forEach {
        it.symbols.forEach {
            if (it is FunctionTag && it.name == name) return it
        }
    }
    return null
}
inline fun <T> compilation(
    info : MutableInformation,scope : LexicalScope,
    block : context(MutableInformation,LexicalScope) (MutableInformation,LexicalScope)->T
) = with(info) { with(scope) { block(info,scope) } }
fun ProjectSyntaxTree.semanticAnalysis() : SemanticResult =
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
                fileBuilder.apply {
                    tops.addAll(
                        tags.map {
                            //检查顶层变量是否有类型
                            check(it is VariableTag) {
                                root_scope_variable_no_type((it as VariableTag).prototype)
                            }
                            it.result
                        }
                    )
                }.result
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
    is CommonTypeSyntaxTree -> toAst()
}
context(info : MutableInformation,scope : LexicalScope)
fun StatementSyntaxTree.toAst() : StatementTree? = when(this){
    is ExpressionSyntaxTree -> toAst()
    is FunctionSyntaxTree   -> FunctionTag(this).result
    is VariableSyntaxTree   -> VariableTag(this).result
    is ClassSyntaxTree      -> ClassTag(this).result
}
context(info : MutableInformation,scope : LexicalScope)
fun ExpressionSyntaxTree.toAst() : ExpressionTree? = when(this){
    is DecimalConstantSyntaxTree -> toAst()
    is IntegerConstantSyntaxTree -> toAst()
    is StringConstantSyntaxTree  -> toAst()
    is InvokeSyntaxTree          -> toAst()
    is NameSyntaxTree            -> toAst()
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
            val member = realType.members.find {
                it.name == name
            }!!
            //判断成员标签类型
            when(member){
                is FunctionTag -> TODO("暂不支持直接引用函数成员")
                is VariableTag -> {
                    type = member.type ?: return@toAst null
                }
            }
        } ?: run {
            //检查作用域是否能找到这个名字
            check {
                no_such_variable(name!!,line!!,column!!)
            }
            //从作用域获取变量
            val variable = scope.findVariableSymbol(name!!)
            //取变量的类型
            type = variable?.type ?: return@toAst null
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
        arguments = arguments.mapNotNull { it.toAst() }.toMutableList(),
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
        val invokeFunction = invokerType.members.find {
            it is FunctionTag && it.name == invokeFunctionName
        } as FunctionTag
        //
    }.result



