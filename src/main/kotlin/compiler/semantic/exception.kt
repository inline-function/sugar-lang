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
    val file : ID
}
val Inner.prefix : String
    get() = "[${file}:第${line}行,第${column}列]"
data class DuplicateFilesException(
    val name : ID,
) : Error {
    override fun toString() = "含有多个`$name`文件"
}

data class NoVariableTypeAndValueException(
    override val file : ID,
    override val line : Int,
    override val column : Int,
    val variableName : ID,
) : Error,Inner {
    override fun toString() = "${prefix}变量`$variableName`至少得指定类型或值中的一个"
}

data class CannotCastToTypeException(
    override val file : ID,
    override val line : Int,
    override val column : Int,
    val from : TypeAST,
    val to : TypeAST,
) : Inner,Error {
    override fun toString() = "${prefix}无法将类型`$from`转换为类型`$to`"
}

data class ArgumentTypeException(
    override val file : ID,
    override val line : Int,
    override val column : Int,
    val argument : TypeAST?,
    val parameter : TypeAST,
    val aboutType : Boolean = false
) : Inner,Error {
    override fun toString() = "${prefix}${if(aboutType) "类型" else ""}实参${argument?.let { "${if(aboutType) "" else "类型"}`$argument`" } ?: "没有类型,"}无法转换为${if(aboutType) "类型" else ""}形参${if(aboutType) "" else "类型"}`$parameter`"
}

data class CannotFoundException(
    override val file : ID,
    override val line : Int,
    override val column : Int,
    val name : ID,
) : Inner,Error {
    override fun toString() = "${prefix}找不到`$name`"
}

data class DuplicateMemberException(
    override val file : ID,
    override val line : Int,
    override val column : Int,
    val name : ID,
) : Inner,Error {
    override fun toString() = "${prefix}重复存在的成员`$name`"
}

data class ImmutableVariableCannotBeAssignedException(
    override val file : ID,
    override val line : Int,
    override val column : Int,
    val name : ID,
) : Inner,Error {
    override fun toString() = "${prefix}不可变变量`$name`不能进行初始化外的赋值"
}

data class CannotAssignValueException(
    override val file : ID,
    override val line : Int,
    override val column : Int,
) : Inner,Error {
    override fun toString() = "${prefix}无法向没有名字的表达式赋值"
}