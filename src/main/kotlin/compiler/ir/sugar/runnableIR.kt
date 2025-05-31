/**
 * 本文件定义可运行的中间表示代码
 * 编写时kt版本 2.1.0
 * 编写时时间   2025.5.25
 * 创建者      语法糖味函子酱(sugared functor)
 */
package compiler.ir.sugar

/**
 * 表示一个标识符的值类。
 *
 * @property text 标识符的实际文本内容。
 */
@JvmInline
value class ID(val text: String) {
    override fun toString() = "`$text`"
}
sealed interface ConstantValue
@JvmInline
value class IntegerConstant(val value : Int) : ConstantValue
@JvmInline
value class DecimalConstant(val value : Double) : ConstantValue
@JvmInline
value class StringConstant(val value : String) : ConstantValue
/**
 * 密封接口，表示所有可运行的中间表示（IR）结构的基类型。
 */
sealed interface SugarRunnableIR
/**
 * 表示赋值操作的中间表示。
 *
 * @property name 被赋值变量的标识符。
 * @property value 变量被赋予的值的标识符。
 * @property type 赋值操作的数据类型的标识符。
 * @property isLocal 是否为局部变量
 */
data class AssignIR(
    val name: ID,
    val value: ID,
    val type: ID,
    val isLocal : Boolean,
) : SugarRunnableIR
/**
 * 表示常量赋值操作的中间表示。
 *
 * @property name 被赋值变量的标识符。
 * @property value 常量值。
 * @property isLocal 是否为局部变量
 */
data class AssignConstantIR(
    val name: ID,
    val value: ConstantValue,
    val isLocal : Boolean,
) : SugarRunnableIR
/**
 * 表示标签的中间表示。
 *
 * @property line 标签所在的行号。
 */
data class TagIR(
    val line: Int,
) : SugarRunnableIR

/**
 * 表示跳转到指定标签的操作的中间表示。
 *
 * @property tag 目标标签的行号。
 */
data class GoToIR(
    val tag: Int,
) : SugarRunnableIR
/**
 * 表示返回操作的中间表示。
 *
 * @property value 返回值的标识符。
 * @property type 返回值的数据类型的标识符。
 */
data class ReturnIR(
    val value: ID,
    val type: ID,
) : SugarRunnableIR
/**
 * 表示成员赋值操作的中间表示。
 *
 * @property name 对象实例的标识符。
 * @property member 成员变量的标识符。
 * @property result 赋值结果的标识符。
 */
data class AssignMemberIR(
    val name: ID,
    val member: ID,
    val result: ID,
) : SugarRunnableIR
/**
 * 表示函数调用操作的中间表示。
 *
 * @property name 被调用函数的标识符。
 * @property args 函数调用传递的参数列表。
 * @property result 函数调用的结果标识符，如果不需要则可写null
 */
data class InvokeIR(
    val name: ID,
    val args: List<ID>,
    val result: ID? = null,
) : SugarRunnableIR