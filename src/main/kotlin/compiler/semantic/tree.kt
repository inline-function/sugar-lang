/**
 * 本文件定义语义分析完毕的抽象语法树
 * 编写时kt版本 2.1.0
 * 编写时时间   2025.4.13
 * 创建者      语法糖味函子酱(sugared functor)
 */
package compiler.semantic

import tools.*

/**
 * 语义分析阶段的语法树共同父类
 */
sealed interface Tree
/**
 * 语义分析阶段的文件内语法树共同父类
 * @property line 行号
 * @property column 列号
 */
sealed interface InnerTree : Tree {
    val line : Int
    val column : Int
}
/**
 * 描述一个工程
 * @param name 工程名
 * @param files 工程文件
 */
@Builder
data class ProjectTree(
    val name : ID,
    val files : List<FileTree>
) : Tree {
    override fun toString() =
        files.joinToString(
            separator = "\n",
            prefix = "/* 工程$name */\n"
        )
}
/**
 * 描述一个文件
 * @param name 文件 名
 */
@Builder
data class FileTree(
    val name : ID,
    val tops : List<TopTree>,
) : Tree {
    override fun toString() =
        tops.joinToString(
            separator = "\n",
            prefix = "//文件名:$name\n"
        )
}
/**
 * 描述一个名称
 * @property expression 调用者表达式
 */
@Builder
data class NameTree(
    override val line : Int,
    override val column : Int,
    val expression : ExpressionTree?,
    val name : ID,
    override val type : TypeTree,
) : ExpressionTree {
    override fun toString() = function<ExpressionTree,ID> {
        if (it is NameTree)
            "${this(it)}.$name"
        else
            "($it)"
    }(this)
}
/**
 * 描述一个词法作用域
 * @property stmts 作用域内的语句
 */
@Builder
data class BlockTree(
    val stmts : List<StatementTree>,
    override val line : Int,
    override val column : Int,
) : InnerTree,List<StatementTree> by stmts
/**
 * 描述一个语句
 */
sealed interface StatementTree : InnerTree
/**
 * 顶层成员
 * @property name 成员名
 */
sealed interface TopTree : StatementTree {
    val name : ID
}
/**
 * 描述一个类,值得一提的是,相比kt的类,它更接近接口
 * @property members 成员
 */
@Builder
data class ClassTree(
    override val line : Int,
    override val column : Int,
    override val name : ID,
    val parents : List<TypeTree>,
    val members : List<CallableTree>,
) : TopTree {
    override fun toString() =
        "$name |> ${parents.joinToString(" |> ")} {\n${members.joinToString("\n")}\n}"
}
/**
 * 可调用的成员,包括变量和函数
 * @property returnType 函数返回类型/变量类型
 */
sealed interface CallableTree : TopTree {
    val returnType : TypeTree?
}
/**
 * 描述一个变量
 * @property value 变量初始值
 */
@Builder
data class VariableTree(
    override val line : Int,
    override val column : Int,
    override val name : ID,
    val value : ExpressionTree?,
    override val returnType : TypeTree?,
) : CallableTree {
    override fun toString() =
        "$name${returnType?.let{" : $it"} ?: ""}${value?.let{" = $value"}?:""}"
}
/**
 * 描述一个函数
 * @property parameters 函数参数
 * @property body 函数体
 */
@Builder
data class FunctionTree(
    override val line : Int,
    override val returnType : TypeTree?,
    override val name : ID,
    override val column : Int,
    val body : BlockTree?,
    val parameters : List<VariableTree>,
) : CallableTree {
    override fun toString() =
        ("$name(${parameters.joinToString(",")})")+
                (returnType?.let{" : $it"} ?: "")+
                (body?.stmts?.joinToString(
                    prefix = "{\n",
                    postfix = "\n}",
                    separator = "\n"
                )?:"")
}
/**
 * 描述一个表达式
 * @property type 表达式类型
 */
sealed interface ExpressionTree : StatementTree{
    val type : TypeTree
}
/**
 * 描述一个赋值语句
 * @property invoker 被调用函数的表达式
 * @property arguments 调用时传入参数
 */
@Builder
data class InvokeTree(
    override val line : Int,
    override val column : Int,
    val invoker : ExpressionTree,
    val arguments : List<ExpressionTree>,
    override val type : TypeTree,
) : ExpressionTree{
    override fun toString() =
        "($invoker(${arguments.joinToString(",")}))"
}
/**
 * 描述一个字面值常量
 * @property value 字面值
 */
sealed interface FaceConstantTree : ExpressionTree{
    val value : Any
}
/**
 * 描述一个字符串字面值
 */
@Builder
data class StringConstantTree(
    override val line : Int,
    override val column : Int,
    override val value : String,
    override val type : TypeTree,
) : FaceConstantTree{
    override fun toString() = "\"$value\""
}
/**
 * 描述一个数字字面值
 */
sealed interface NumberConstantTree : FaceConstantTree
/**
 * 描述一个整数字面值
 */
@Builder
data class IntegerConstantTree(
    override val line : Int,
    override val column : Int,
    override val value : Int,
    override val type : TypeTree,
) : NumberConstantTree{
    override fun toString() = value.toString()
}
/**
 * 描述一个小数字面值
 */
@Builder
data class DecimalConstantTree(
    override val line : Int,
    override val column : Int,
    override val value : Double,
    override val type : TypeTree,
) : NumberConstantTree{
    override fun toString() = value.toString()
}
/**
 * 描述一个类型表达式
 */
sealed interface TypeTree : InnerTree
/**
 * 描述一个平凡类型
 * @property name 类型名
 */
@Builder
data class CommonTypeTree(
    override val line : Int,
    override val column : Int,
    val name : ID,
) : TypeTree{
    override fun toString() = name
}