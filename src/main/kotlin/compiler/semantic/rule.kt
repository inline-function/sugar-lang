/**
 * 本文件定义语义分析过程及规则
 * 编写时kt版本 2.1.0
 * 编写时时间   2025.4.16
 * 创建者      语法糖味函子酱(sugared functor)
 */
@file:Suppress("NOTHING_TO_INLINE","NestedLambdaShadowedImplicitParameter","NAME_SHADOWING")

package compiler.semantic
import compiler.parser.Body
import compiler.parser.ClassTree
import compiler.parser.FunctionTree
import compiler.parser.VariableTree
import tools.ID
import tools.never
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
sealed interface Tag{
    val name : ID
}
@JvmInline
value class VariableTag(val prototype : VariableSyntaxTree) : Tag{
    inline val line   get() = prototype.line
    inline val column get() = prototype.column
    override val name get() = prototype.name
    inline val type   get() = prototype.returnType?.toAst()
    /**
     * 正式把标签构建成抽象语法树
     *
     * 当构建失败时返回null
     * @param info 编译期信息
     * @param scope 当前作用域
     * @return 构建后的抽象语法树
     */
    inline fun build(info : MutableInformation,scope : LexicalScope) =
        type?.let {
            buildVariableTree(
                line       = line,
                column     = column,
                name       = name,
                returnType = type,
                value      = prototype.value?.toAst(info,scope)
            ).result
        }
}
@JvmInline
value class FunctionTag(val prototype : FunctionSyntaxTree) : Tag{
    inline val line       get() = prototype.line
    inline val column     get() = prototype.column
    override val name     get() = prototype.name
    inline val type       get() = prototype.returnType?.toAst()
    inline val parameters get() = prototype.parameters.map(::VariableTag)
    inline fun build(info : MutableInformation,scope : LexicalScope) =
        buildFunctionTree(
            line       = line,
            column     = column,
            name       = name,
            returnType = type ?: CommonTypeTree(line,column,"Void"),
            parameters = parameters.mapNotNull { it.build(info,scope) }.toMutableList()
        ) {
            body = prototype.body?.let {
                val scope = scope.subScope
                BlockTree(
                    stmts = it.mapNotNull {
                        it.toAst(info,scope).also {
                            (it as? Tag)?.also {
                                scope += it
                            }
                        }
                    },
                    line = line!!,
                    column = column!!
                )
            }
        }.result
}
@JvmInline
value class ClassTag(val prototype : ClassSyntaxTree) : Tag{
    inline val line    get() = prototype.line
    inline val column  get() = prototype.column
    override val name  get() = prototype.name
    inline val parents get() = prototype.parents.map(TypeSyntaxTree::toAst)
    inline val members get() = prototype.members.map {
        when(it){
            is FunctionSyntaxTree -> FunctionTag(it)
            is VariableSyntaxTree -> VariableTag(it)
        }
    }
    inline fun build(info : MutableInformation,scope : LexicalScope) =
        buildClassTree(
            line    = line,
            column  = column,
            name    = name,
            parents = parents.toMutableList(),
            members = members.mapNotNull {
                when(it){
                    is ClassTag    -> never
                    is FunctionTag -> it.build(info,scope)
                    is VariableTag -> it.build(info,scope)
                }
            }.toMutableList()
        ).result
}
//寻找符号
inline fun <T : Any> MutableScope<T>.findSymbol(bool : (Tag)->Boolean) : T?{
    parents.forEach {
        it.symbols.forEach {
            if (bool(it)) return it
        }
    }
}
/**
 * 把具体语法树进行语义分析,转换为抽象语法树
 */
@Suppress("NestedLambdaShadowedImplicitParameter","NAME_SHADOWING","RedundantSuppression","KotlinRedundantDiagnosticSuppress")
fun ProjectSyntaxTree.semanticAnalysis() : SemanticResult = let {
    //开始收集编译期信息
    val info = MutableInformation()
    //判断是否有重复文件
    fun hasDuplicateFile() = it.files
        .groupingBy { it.name }
        .eachCount()
        .forEach { (fileName,count) ->
            if (count > 1) info.errors += Message(-1,-1,fileName.duplicateFileError)
        }
    //开始构建工程树
    buildProjectTree(name = name) {
        //全局作用域
        val scope = rootScope
        //判断是否有重复文件
        hasDuplicateFile()
        //为每个文件构建文件树
        it.files.forEach { file ->
            //当前文件
            val fileBuilder = buildFileTree(name = file.name)
            //遍历文件,收集标签到顶层作用域
            file.tops.forEach { top ->
                scope += when(top){
                    is ClassSyntaxTree    -> ClassTag(top)
                    is FunctionSyntaxTree -> FunctionTag(top)
                    is VariableSyntaxTree -> VariableTag(top)
                }
            }
            //遍历顶层作用域,递归构造抽象语法树
            fileBuilder.tops.addAll(
                scope.mapNotNull {
                    when(it){
                        is ClassTag    -> it.build(info,scope)
                        is FunctionTag -> it.build(info,scope)
                        is VariableTag -> it.build(info,scope)
                    }
                }
            )
            //把文件添加给工程树构建器
            files += fileBuilder.result
        }
    }.result to info.result
}
//处理平凡类型
fun CommonTypeSyntaxTree.toAst() =
    buildCommonTypeTree(line,column,name).result
//处理类型
fun TypeSyntaxTree.toAst() = when(this){
    is CommonTypeSyntaxTree -> toAst()
}
fun StatementSyntaxTree.toAst(info : MutableInformation,scope : LexicalScope) : StatementTree? =
    when(this){
        is ExpressionSyntaxTree -> toAst(info,scope)
        is FunctionTree         -> FunctionTag(this).build(info,scope)
        is VariableTree         -> VariableTag(this).build(info,scope)
        is ClassTree            -> ClassTag(this).build(info,scope)
    }
fun ExpressionSyntaxTree.toAst(info : MutableInformation,scope : LexicalScope) =
    when(this){
        is DecimalConstantSyntaxTree -> toAst()
        is IntegerConstantSyntaxTree -> toAst()
        is StringConstantSyntaxTree  -> toAst()
        is InvokeSyntaxTree          -> toAst(info,scope)
        is NameSyntaxTree            -> toAst(info,scope)
    }
fun NameSyntaxTree.toAst(info : MutableInformation,scope : LexicalScope) : NameTree? =
    buildNameTree(
        line       = line,
        column     = column,
        expression = expression?.toAst(info,scope),
        name       = name,
    ) {
        //判断调用者类型
        when(val invokerType = expression?.type){
            //是普通类型,遍历作用域查找此类型
            is CommonTypeTree -> scope.parents.forEach { scope ->
                scope.find { //查找类型
                    it is ClassTag && it.name == invokerType.name
                }?.let { it as ClassTag
                    //找到了,遍历其成员
                    val result = it.members.find {
                        it.name == name
                    }
                    //判断结果的类型
                    type = when(result){
                        is FunctionTag -> TODO("函数类型")
                        is VariableTag -> result.type
                        else           -> null
                    }
                    //如果类型依旧是空的,则报错
                    type ?: info.errors.add(
                        Message(line!!,column!!,noSuchMemberError(invokerType.name,name!!))
                    )
                } ?: info.errors.add(
                    //没找到，报错
                    Message(line!!,column!!,noSuchMemberError(invokerType.name,name!!))
                )
            }
            //只有单个名字
            null -> {
                scope.parents.forEach {
                    val result = when(
                        val result = it.find { (it.name == name) && (it !is ClassTag) }
                    ){
                        is FunctionTag -> TODO("函数类型")
                        is VariableTag -> result.type!!
                        else           -> null
                    }
                    result?.let { type = result } ?: info.errors.add(
                        Message(line!!,column!!,name!!.noSuchVariableOrFunctionError)
                    )
                }
            }
        }
    }.run {
        type?.let { result }
    }
fun StringConstantSyntaxTree.toAst() =
    buildStringConstantTree(
        line      = line,
        column    = column,
        value     = value,
        type      = CommonTypeTree(line,column,"Str")
    ).result
fun DecimalConstantSyntaxTree.toAst() =
    buildDecimalConstantTree(
        line      = line,
        column    = column,
        value     = value,
        type      = CommonTypeTree(line,column,"Dec")
    ).result
fun IntegerConstantSyntaxTree.toAst() =
    buildIntegerConstantTree(
        line      = line,
        column    = column,
        value     = value,
        type      = CommonTypeTree(line,column,"Int")
    ).result
fun InvokeSyntaxTree.toAst(info : MutableInformation,scope : LexicalScope) : InvokeTree =
    buildInvokeTree(
        line      = line,
        column    = column,
        arguments = arguments.mapNotNull { it.toAst(info,scope) }.toMutableList(),
        invoker   = invoker.toAst(info,scope)!!
    ) {
        //判断调用者类型
        when(val invokerType = invoker!!.type){
            //是平凡类型,遍历作用域查找此类型,然后判断是否类型有invoke函数
            is CommonTypeTree -> {
                //遍历作用域查找此类型
                val result = scope.findSymbol {
                    it is ClassTag && it.name == invokerType.name
                } as? ClassTag
                //如果找到了,遍历类型成员是否存在invoke函数
                val functionResult = result?.members?.find {
                    it is FunctionTag && it.name == invokeFunctionName
                } as? FunctionTag
                //如果result是空的,报错
                result ?: info.errors.add(
                    Message(line!!,column!!,invokerType.name.noSuchClassError)
                )
                //如果functionResult是空的,报错
                functionResult ?: info.errors.add(
                    Message(line!!,column!!,invokerType.name.noInvokeFunctionError)
                )
            }
            null              -> TODO()
        }
    }.result