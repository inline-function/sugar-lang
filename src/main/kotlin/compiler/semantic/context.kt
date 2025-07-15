/**
 * 本文件定义语义分析过程上下文相关事宜
 * 编写时kt版本 2.1.0
 * 编写时时间   2025.4.19
 * 创建者      语法糖味函子酱(sugared functor)
 */
package compiler.semantic

import compiler.parser.InnerTree
import compiler.semantic.MutableInformation
import tools.*
import java.util.function.IntFunction

/**
 * 语义分析过程中产生的信息
 *
 * @property suggestions 建议
 * @property hints       提示
 * @property warnings    警告
 * @property errors      错误
 */
data class Information(
    val suggestions : List<Suggestion> = emptyList(),
    val hints       : List<Hint>       = emptyList(),
    val warnings    : List<Warning>    = emptyList(),
    val errors      : List<Error>      = emptyList(),
){
    override fun toString() =
        suggestions.joinToString(
            prefix = "建议:\n",
            separator = "\n",
        ) + hints.joinToString(
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
 * @property suggestions 建议
 * @property hints       提示
 * @property warnings    警告
 * @property errors      错误
 */
data class MutableInformation(
    val suggestions : MutableList<Suggestion> = mutableListOf(),
    val hints       : MutableList<Hint>       = mutableListOf(),
    val warnings    : MutableList<Warning>    = mutableListOf(),
    val errors      : MutableList<Error>      = mutableListOf(),
) {
    operator fun plusAssign(exception : SemanticAnalysisException) = when(exception){
        is Suggestion -> suggestions += exception
        is Hint       -> hints += exception
        is Warning    -> warnings += exception
        is Error      -> errors += exception
    }
    operator fun plusAssign(exception : MutableInformation) {
        suggestions += exception.suggestions
        hints += exception.hints
        warnings += exception.warnings
        errors += exception.errors
    }
}
val MutableInformation.result : Information
    get() = Information(suggestions.toList(), hints.toList(), warnings.toList(), errors.toList())
/**
 * 语义分析过程中使用的符号表
 *
 * @property symbols  符号表
 * @property parent  父符号表
 */
@Suppress("JavaDefaultMethodsNotOverriddenByDelegation")
data class SymbolTable<T : Any>(
    val symbols  : MutableList<T> = mutableListOf(),
    val parent   : SymbolTable<T>? = null,
) : MutableList<T> by symbols

val <T : Any> SymbolTable<T>.parents : List<SymbolTable<T>>
    get() = generateSequence(this){it.parent}.toList()