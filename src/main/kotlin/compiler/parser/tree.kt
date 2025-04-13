/**
 * 本文件中定义了语法分析阶段的语法树及其相关事宜
 * 语法树抽象统一使用密封接口
 * 语法树具体统一使用数据类
 * 数据类必须重写toString来表示白砂糖代码
 * 编写时kt版本 2.1.0
 * 编写时时间   2025.4.4
 * 创建者      语法糖味函子酱(sugared functor)
 */
package compiler.parser
typealias ID = String
typealias Body = List<StatementTree>
/**
 * 语法分析阶段的语法树共同父类
 */
sealed interface Tree
/**
 * 语法分析阶段的文件内语法树共同父类
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
 * @param name 文件名
 */
data class FileTree(
    val name : ID,
    val tops : List<TopTree>
) : Tree {
    override fun toString() =
        tops.joinToString(
            separator = "\n",
            prefix = "//文件名:$name\n"
        )
}
data class NameTree(
    override val line : Int,
    override val column : Int,
    val chain : List<String>
) : ExpressionTree {
    override fun toString() = chain.joinToString(".")
}
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
data class ClassTree(
    override val line : Int,
    override val column : Int,
    override val name : ID,
    val parents : List<TypeTree>,
    val members : List<CallableTree>
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
data class FunctionTree(
    override val line : Int,
    override val returnType : TypeTree?,
    override val name : ID,
    override val column : Int,
    val body : Body?,
    val parameters : List<VariableTree>
) : CallableTree {
    override fun toString() =
        ("$name(${parameters.joinToString(",")})")+
        (returnType?.let{" : $it"} ?: "")+
        (body?.joinToString(
            prefix = "{\n",
            postfix = "\n}",
            separator = "\n"
        )?:"")
}
/**
 * 描述一个表达式
 * @property type 表达式类型
 */
sealed interface ExpressionTree : StatementTree
/**
 * 描述一个赋值语句
 * @property invoker 被调用函数的表达式
 * @property arguments 调用时传入参数
 */
data class InvokeTree(
    override val line : Int,
    override val column : Int,
    val invoker : ExpressionTree,
    val arguments : List<ExpressionTree>,
) : ExpressionTree{
    override fun toString() =
        "($invoker(${arguments.joinToString(",")}))"
}
/**
 * 描述一个如果语句
 * @property condition 条件表达式
 * @property then 条件为真时
 * @property else 条件为假时
 */
data class IfTree(
    override val line : Int,
    override val column : Int,
    val condition : ExpressionTree,
    val then : Body,
    val `else` : Body?
) : ExpressionTree
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
data class StringConstantTree(
    override val line : Int,
    override val column : Int,
    override val value : String
) : FaceConstantTree{
    override fun toString() = "\"$value\""
}
sealed interface NumberConstantTree : FaceConstantTree
/**
 * 描述一个整数字面值
 */
data class IntegerConstantTree(
    override val line : Int,
    override val column : Int,
    override val value : Int
) : NumberConstantTree{
    override fun toString() = value.toString()
}
/**
 * 描述一个小数字面值
 */
data class DecimalConstantTree(
    override val line : Int,
    override val column : Int,
    override val value : Double
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
data class CommonTypeTree(
    override val line : Int,
    override val column : Int,
    val name : ID,
) : TypeTree{
    override fun toString() = name
}
