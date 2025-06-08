/**
 * 本文件定义可运行的中间表示代码
 * 编写时kt版本 2.1.0
 * 编写时时间   2025.5.25
 * 创建者      语法糖味函子酱(sugared functor)
 */
package compiler.ir.sugar

import tools.ID

/**
 * 密封接口，表示所有可运行的中间表示（IR）结构的基类型。
 * 所有的 IR 结构都必须实现此接口。
 */
sealed interface SugarRunnableIR

/**
 * 表示变量赋值操作的 IR。
 *
 * @property name 目标变量的寄存器索引。
 * @property value 源变量的寄存器索引。
 * @property isLocal 是否目标变量是局部变量。
 * @property formLocal 是否源变量是局部变量。
 */
data class AssignIR(
    val name: Int,
    val value: Int,
    val isLocal: Boolean,
    val formLocal: Boolean,
) : SugarRunnableIR

/**
 * 表示常量赋值操作的 IR。
 *
 * @property name 目标变量的寄存器索引。
 * @property value 要赋值的常量值。
 * @property isLocal 是否目标变量是局部变量。
 */
data class AssignConstantIR(
    val name: Int,
    val value: ConstantValue<*>,
    val isLocal: Boolean,
) : SugarRunnableIR

/**
 * 表示标签的 IR。
 *
 * @property line 标签所在的行号。
 */
data class TagIR(
    val line: Int,
) : SugarRunnableIR

/**
 * 表示跳转到指定标签的 IR。
 *
 * @property tag 要跳转到的标签索引。
 */
data class GoToIR(
    val tag: Int,
) : SugarRunnableIR

/**
 * 表示返回操作的 IR。
 *
 * @property value 返回值的寄存器索引。
 * @property isLocal 是否返回值是局部变量。
 */
data class ReturnIR(
    val value: Int,
    val isLocal: Boolean,
) : SugarRunnableIR

/**
 * 表示成员变量赋值操作的 IR。
 *
 * @property name 对象实例的寄存器索引。
 * @property member 成员变量的名称。
 * @property result 结果存储的寄存器索引。
 * @property isLocal 是否对象实例是局部变量。
 * @property resultIsLocal 是否结果存储为局部变量。
 */
data class AssignMemberIR(
    val name: Int,
    val member: ID,
    val result: Int,
    val isLocal: Boolean,
    val resultIsLocal: Boolean,
) : SugarRunnableIR

/**
 * 表示成员变量赋值操作的 IR。
 *
 * @property name 对象实例的寄存器索引。
 * @property member 成员变量的名称。
 * @property form 源变量的寄存器索引。
 * @property isLocal 是否对象实例是局部变量。
 * @property formLocal 是否源变量是局部变量。
 */
data class MemberAssignIR(
    val name: Int,
    val member: ID,
    val form: Int,
    val isLocal: Boolean,
    val formLocal: Boolean,
) : SugarRunnableIR

/**
 * 表示函数调用操作的 IR。
 *
 * @property name 调用的目标函数的寄存器索引。
 * @property args 参数列表的寄存器索引。
 * @property result 结果存储的寄存器索引（可选）。
 * @property isLocal 是否目标函数是局部变量。
 * @property resultIsLocal 是否结果存储为局部变量。
 */
data class InvokeIR(
    val name: Int,
    val args: List<Int>,
    val result: Int? = null,
    val isLocal: Boolean,
    val resultIsLocal: Boolean,
) : SugarRunnableIR