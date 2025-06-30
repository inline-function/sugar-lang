@file:Suppress("NOTHING_TO_INLINE","FunctionName")

package compiler.semantic

import tools.ID
sealed interface SemanticAnalysisException
sealed interface Suggestion : SemanticAnalysisException
sealed interface Hint : SemanticAnalysisException
sealed interface Warning : SemanticAnalysisException
sealed interface Error : SemanticAnalysisException
sealed interface Inner : SemanticAnalysisException{
    val line : Int
    val column : Int
}
val Inner.prefix : String
    get() = "[第${line}行,第${column}列]"
data class DuplicateFilesException(
    val name : ID,
) : Error {
    override fun toString() = "含有多个`$name`文件"
}

data class NoVariableTypeAndValueException(
    override val line : Int,
    override val column : Int,
    val variableName : ID,
) : Error,Inner {
    override fun toString() = "${prefix}变量`$variableName`至少得指定类型或值中的一个"
}

data class CannotCastToTypeException(
    override val line : Int,
    override val column : Int,
    val from : TypeAST,
    val to : TypeAST,
) : Inner,Error {
    override fun toString() = "${prefix}无法将类型`$from`转换为类型`$to`"
}

data class CannotFoundException(
    override val line : Int,
    override val column : Int,
    val name : ID,
) : Inner,Error {
    override fun toString() = "${prefix}找不到`$name`}"
}

data class DuplicateMemberException(
    override val line : Int,
    override val column : Int,
    val name : ID,
) : Inner,Error {
    override fun toString() = "${prefix}重复存在的成员`$name`"
}