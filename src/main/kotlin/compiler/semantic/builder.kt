/**
 * 本文件定义抽象语法树的构建器
 * 编写时kt版本 2.1.0
 * 编写时时间   2025.4.19
 * 创建者      语法糖味函子酱(sugared functor)
 */
package compiler.semantic

import tools.*

typealias Children<T> = MutableList<InnerTreeBuilder<T>>
typealias ParentGetter = (()->Tree)
/**
 * 工程抽象语法树构建器
 * @property name 工程名
 * @property files 文件列表
 */
@Builder(name = "Project")
data class ProjectTreeBuilder(
    var name : ID = "unnamed",
    var files : MutableList<FileTreeBuilder> = mutableListOf(),
)
/**
 * 文件抽象语法树构建器
 * @property name 文件名
 * @property tops 顶层声明列表
 */
@Builder(
    parent = [ProjectTreeBuilder::class],
    name   = "File",
    code   = """
       files += it
       it.parent = this
    """
)
data class FileTreeBuilder(
    var name : ID,
    var tops : Children<TopTree>,
    var parent : ProjectTreeBuilder?,
)
/**
 * 文件内抽象语法树构建器
 * @property line 行号
 * @property column 列号
 * @property parent 父树
 * @property result 构建结果
 */
sealed interface InnerTreeBuilder<out R : InnerTree> : ()->R{
    var line : Int
    var column : Int
    var parent : ParentGetter?
    val result : R
    override fun invoke() = result
}
/**
 * 名称抽象语法树构建器
 * @property expression 表达式
 * @property name 名称
 */
@Builder(name = "Name")
data class NameTreeBuilder(
    override var line : Int,
    override var column : Int = -1,
    override var parent : ParentGetter? = null,
    var expression : InnerTreeBuilder<ExpressionTree>? = null,
    var name : ID = "unnamed",
) : InnerTreeBuilder<NameTree> {
    /**
     * 获取名称树构建器的只读抽象语法树结果
     * @return 只读抽象语法树
     * @receiver 名称树构建器
     * @throws Exception 如果父语法树为空则抛出异常
     */
    override val result : NameTree
        get() = NameTree(
            line       = line,
            column     = column,
            expression = expression?.result,
            name       = name,
            parent     = parent.check
        )
    /**
     * 将名称树构建器转换为字符串表示
     * @return 代码字符串
     */
    override fun toString() = result.toString()
}
@Builder(
    parent = [FunctionTreeBuilder::class],
    name = "Block",
    code = """
        body = it
        it.parent = this
    """
)
data class BlockTreeBuilder(
    override var line : Int = -1,
    override var column : Int = -1,
    override var parent : ParentGetter? = null,
    var stmts : Children<StatementTree> = mutableListOf(),
) : InnerTreeBuilder<BlockTree> {
    override val result : BlockTree
        get() = BlockTree(
            line       = line,
            column     = column,
            stmts      = stmts.map { it.result },
            parent     = parent.check
        )
    override fun toString() = result.toString()
}
@Builder
data class IfTreeBuilder(
    override var line : Int = -1,
    override var column : Int = -1,
    override var parent : ParentGetter? = null,
    var condition : InnerTreeBuilder<ExpressionTree>? = null,
    var then : InnerTreeBuilder<BlockTree>? = null,
    var `else` : InnerTreeBuilder<BlockTree>? = null,
) : InnerTreeBuilder<IfTree> {
    override val result : IfTree
        get() = IfTree(
            line       = line,
            column     = column,
            condition  = condition?.result ?: exception("条件为空,语法树构建失败"),
            then       = then?.result ?: exception("then块为空,语法树构建失败"),
            `else`     = `else`?.result ?: exception("else块为空,语法树构建失败"),
            parent     = parent.check
        )
    override fun toString() = result.toString()
}
@Builder
data class ClassTreeBuilder(
    override var line : Int = -1,
    override var column : Int = -1,
    override var parent : ParentGetter? = null,
    var name : ID = "unnamed",
    var parents : Children<TypeTree> = mutableListOf(),
    var members : Children<CallableTree> = mutableListOf(),
) : InnerTreeBuilder<ClassTree> {
    override val result : ClassTree
        get() = ClassTree(
            line       = line,
            column     = column,
            name       = name,
            parents    = parents.map { it.result },
            members    = members.map { it.result },
            parent     = parent.check
        )
    override fun toString() = result.toString()
}
@Builder
data class FunctionTreeBuilder(
    override var line : Int = -1,
    override var column : Int = -1,
    override var parent : ParentGetter? = null,
    var name : ID = "unnamed",
    var parameters : Children<VariableTree> = mutableListOf(),
    var returnType : InnerTreeBuilder<TypeTree>? = null,
    var body : InnerTreeBuilder<BlockTree>? = null,
) : InnerTreeBuilder<FunctionTree> {
    override val result : FunctionTree
        get() = FunctionTree(
            line       = line,
            column     = column,
            name       = name,
            parameters = parameters.map { it.result },
            returnType = returnType?.result ?: exception("返回类型为空,语法树构建失败"),
            body       = body?.result ?: exception("函数体为空,语法树构建失败"),
            parent     = parent.check
        )
    override fun toString() = result.toString()
}
@Builder
data class VariableTreeBuilder(
    override var line : Int = -1,
    override var column : Int = -1,
    override var parent : ParentGetter? = null,
    var name : ID = "unnamed",
    var type : InnerTreeBuilder<TypeTree>? = null,
    var value : InnerTreeBuilder<ExpressionTree>? = null,
) : InnerTreeBuilder<VariableTree> {
    override val result : VariableTree
        get() = VariableTree(
            line       = line,
            column     = column,
            name       = name,
            returnType = type?.result ?: exception("类型为空,语法树构建失败"),
            value      = value?.result ?: exception("值表达式为空,语法树构建失败"),
            parent     = parent.check
        )
    override fun toString() = result.toString()
}
@Builder
data class InvokeTreeBuilder(
    override var line : Int = -1,
    override var column : Int = -1,
    override var parent : ParentGetter? = null,
    var invoker : InnerTreeBuilder<ExpressionTree>? = null,
    var arguments : Children<ExpressionTree> = mutableListOf(),
) : InnerTreeBuilder<InvokeTree> {
    override val result : InvokeTree
        get() = InvokeTree(
            line       = line,
            column     = column,
            invoker    = invoker?.result ?: exception("调用者表达式为空,语法树构建失败"),
            arguments  = arguments.map { it.result },
            parent     = parent.check
        )
    override fun toString() = result.toString()
}
@Builder
data class StringConstantTreeBuilder(
    override var line : Int = -1,
    override var column : Int = -1,
    override var parent : ParentGetter? = null,
    var value : String? = null,
) : InnerTreeBuilder<StringConstantTree> {
    override val result : StringConstantTree
        get() = StringConstantTree(
            line       = line,
            column     = column,
            value      = value ?: exception("字面值为空,语法树构建失败"),
            parent     = parent.check
        )
    override fun toString() = result.toString()
}
@Builder
data class IntegerConstantTreeBuilder(
    override var line : Int = -1,
    override var column : Int = -1,
    override var parent : ParentGetter? = null,
    var value : Int? = null,
) : InnerTreeBuilder<IntegerConstantTree> {
    override val result : IntegerConstantTree
        get() = IntegerConstantTree(
            line       = line,
            column     = column,
            value      = value ?: exception("字面值为空,语法树构建失败"),
            parent     = parent.check
        )
    override fun toString() = result.toString()
}
@Builder
data class DecimalConstantTreeBuilder(
    override var line : Int = -1,
    override var column : Int = -1,
    override var parent : ParentGetter? = null,
    var value : Double? = null,
) : InnerTreeBuilder<DecimalConstantTree> {
    override val result : DecimalConstantTree
        get() = DecimalConstantTree(
            line       = line,
            column     = column,
            value      = value ?: exception("字面值为空,语法树构建失败"),
            parent     = parent.check
        )
    override fun toString() =result.toString()
}
@Builder
data class CommonTypeTreeBuilder(
    override var line : Int = -1,
    override var column : Int = -1,
    override var parent : ParentGetter? = null,
    var name : ID = "unnamed",
) : InnerTreeBuilder<CommonTypeTree> {
    override val result : CommonTypeTree
        get() = CommonTypeTree(
            line       = line,
            column     = column,
            name       = name,
            parent     = parent.check
        )
    override fun toString() = result.toString()
}