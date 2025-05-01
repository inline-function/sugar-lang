/**
 * 本文件定义语义分析过程上下文相关事宜
 * 编写时kt版本 2.1.0
 * 编写时时间   2025.4.19
 * 创建者      语法糖味函子酱(sugared functor)
 */
package compiler.semantic

import tools.*
import kotlin.time.times

typealias Messages = List<Message>
typealias MutableMessages = MutableList<Message>
/**
 * 一条消息,包含消息行列与内容
 * @property line       消息所在行
 * @property column     消息所在列
 * @property content    消息内容
 */
data class Message(
    val line    : Int,
    val column  : Int,
    val content : String
)
/**
 * 语义分析过程中产生的信息
 *
 * ps:该类是信息的只读视图,并非不可变
 * @property sugar      语法糖警告
 * @property tips       提示
 * @property warnings   警告
 * @property errors     错误
 */
data class Information(
    val sugar    : Messages = listOf(),
    val tips     : Messages = listOf(),
    val warnings : Messages = listOf(),
    val errors   : Messages = listOf(),
){
    override fun toString() =
        sugar.joinToString(
            prefix = "建议:\n",
            separator = "\n",
        ) + tips.joinToString(
            prefix = "提示:\n",
            separator = "\n",
        ) + warnings.joinToString(
            prefix = "警告:\n",
            separator = "\n",
        ) + errors.joinToString(
            prefix = "错误:\n",
            separator = "\n",
        )
}
/**
 * 语义分析过程中用于构建只读信息的可变信息
 *
 * ps:请在构建完毕后确保不会再发生改变
 * @property sugar      建议
 * @property tips       提示
 * @property warnings   警告
 * @property errors     错误
 */
@Builder(name = "MutInfo")
data class MutableInformation(
    var sugar    : MutableMessages,
    var tips     : MutableMessages,
    var warnings : MutableMessages,
    var errors   : MutableMessages,
){
    override fun toString() = result.toString()
}
val MutableInformation.result : Information
    get() = Information(sugar, tips, warnings, errors)
/**
 * 作用域中的符号
 */
typealias Symbols<T> = List<T>
typealias MutableSymbols<T> = MutableList<T>

/**
 * 作用域（只读视图），提供符号、子作用域和父作用域
 *
 * ps:该类是作用域的只读视图,并非不可变
 * @property symbols    符号列表
 * @property children   子作用域列表
 * @property parent     父作用域
 */
data class Scope<T : Any>(
    val symbols  : Symbols<T>,
    val children : List<Scope<*>>,
    val parent   : Scope<*>?,
) {
    override fun toString() =
        parents.joinToString(
            separator = ">",
            prefix = "省略了如上父作用域[",
            postfix = "]"
        ) + depth.let { depth ->
            children.joinToString(
                prefix = "${depth * standardIndent}{\n",
                postfix = "${depth * standardIndent}\n}",
            ) { "${(depth + 1) * standardIndent}$it\n" }
        }
}

/**
 * 可变作用域，用于构建作用域层次结构
 *
 * ps:请在构建完毕后确保不会再发生改变
 * @property symbols    符号列表
 * @property children   子作用域列表
 * @property parent     父作用域
 */
@Builder(
    name   = "MutScope",
    parent = [MutableScope::class],
    code   = """
        children += it
        it.parent = this
    """
)
data class MutableScope<T : Any>(
    var symbols: MutableSymbols<T>,
    var children: MutableList<MutableScope<*>>,
    var parent: MutableScope<*>?,
) {
    override fun toString() = result.toString()
}

val <T : Any> MutableScope<T>.result: Scope<T>
    get() = Scope(symbols, children.map { it.result }, parent?.result)

operator fun <T : Any> MutableScope<T>.plusAssign(symbol: T) {
    symbols += symbol
}

val Scope<*>.depth: Int
    get() = generateSequence(this) { it.parent }.count() - 1

val Scope<*>.parents: List<Scope<*>>
    get() = generateSequence(this) { it.parent }.toList()

/**
 * 解构函数
 */
operator fun <T : Any> Scope<T>.component1() = symbols
operator fun <T : Any> Scope<T>.component2() = children
operator fun <T : Any> Scope<T>.component3() = parent
