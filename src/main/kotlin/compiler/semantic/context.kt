/**
 * 本文件定义语义分析过程上下文相关事宜
 * 编写时kt版本 2.1.0
 * 编写时时间   2025.4.19
 * 创建者      语法糖味函子酱(sugared functor)
 */
package compiler.semantic

import compiler.parser.InnerTree
import compiler.semantic.LexicalScope
import compiler.semantic.MutableInformation
import tools.*

typealias Messages = List<Message>
typealias MutableMessages = MutableList<Message>
/**
 * 一条消息,包含消息行列与内容
 * @property line       消息所在行
 * @property column     消息所在列
 * @property content    消息内容
 */
data class Message(
    val content : String,
    val line    : Int? = null,
    val column  : Int? = null,
) {
    override fun toString() =
        if(line != null && column != null)
          "[第${line}行,第${column}列]$content"
        else content
}
fun InnerTree.Message(content : String) = Message(content, line, column)
fun compiler.semantic.InnerTree.Message(content : String) = Message(content, line, column)
fun Tag.Message(content : String) = Message(content, line, column)
/**
 * 语义分析过程中产生的信息
 *
 * @property sugar      建议
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
 * @property sugar      建议
 * @property tips       提示
 * @property warnings   警告
 * @property errors     错误
 */
data class MutableInformation(
    var sugar    : MutableMessages = mutableListOf(),
    var tips     : MutableMessages = mutableListOf(),
    var warnings : MutableMessages = mutableListOf(),
    var errors   : MutableMessages = mutableListOf(),
)
val MutableInformation.result : Information
    get() = Information(sugar.toList(), tips.toList(), warnings.toList(), errors.toList())

typealias Symbols<T> = List<T>
typealias MutableSymbols<T> = MutableList<T>

/**
 * 作用域（只读视图），提供符号、子作用域和父作用域
 *
 * @property symbols    符号列表
 * @property parent     父作用域
 */
data class Scope<T : Any>(
    val symbols  : Symbols<T> = listOf(),
    val parent   : Scope<T>? = null,
) : Symbols<T> by symbols
/**
 * 可变作用域，用于构建作用域层次结构
 *
 * @property symbols    符号列表
 * @property parent     父作用域
 */
@Suppress("JavaDefaultMethodsNotOverriddenByDelegation")
data class MutableScope<T : Any>(
    @Suppress("DelegationToVarProperty")
    var symbols: MutableSymbols<T> = mutableListOf(),
    var parent: MutableScope<T>? = null,
) : MutableSymbols<T> by symbols
@Suppress("RecursivePropertyAccessor")
val <T : Any> MutableScope<T>.result: Scope<T>
    get() = Scope(symbols.toList(), parent?.result)

operator fun <T : Any> MutableScope<T>.plusAssign(symbol: T) {
    symbols += symbol
}

val <T : Any> Scope<T>.depth: Int
    get() = generateSequence(this) { it.parent }.count() - 1

val <T : Any> Scope<T>.parents: List<Scope<T>>
    get() = generateSequence(this) { it.parent }.toList()

val <T : Any> MutableScope<T>.depth: Int
    get() = generateSequence(this) { it.parent }.count() - 1

val <T : Any> MutableScope<T>.parents: List<MutableScope<T>>
    get() = generateSequence(this) { it.parent }.toList()
