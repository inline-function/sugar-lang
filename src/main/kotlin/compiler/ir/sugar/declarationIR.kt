/**
 * 本文件定义声明系中间表示代码
 * 编写时kt版本 2.1.0
 * 编写时时间   2025.5.25
 * 创建者      语法糖味函子酱(sugared functor)
 */
package compiler.ir.sugar

import compiler.semantic.AnnotationValue
import tools.ID

sealed interface DeclarationIR
sealed interface ConstantValue<out T : Any>
@JvmInline
value class IntegerConstant(val value : Int) : ConstantValue<Int>
@JvmInline
value class DecimalConstant(val value : Double) : ConstantValue<Double>
@JvmInline
value class StringConstant(val value : String) : ConstantValue<String>
data class AnnotationIR(
    val name: ID,
    val value: ConstantValue<*>?,
    val line: Int,
)
data class TypeIR(
    val name: ID,
    val typeParams: List<TypeIR> = emptyList(),
)
data class VariableDecIR(
    val name: ID,
    val type: TypeIR,
    val line: Int,
    val host: ID?,
    val file: ID,
    val anns: List<AnnotationIR>,
) : DeclarationIR
val VariableDecIR.runnableID : ID
    get() = $$"$${host?.let{"$it$"}?: ""}$$name$init"
data class FunctionDecIR(
    val name: ID,
    val anns: List<AnnotationIR>,
    val type: TypeIR,
    val host: ID?,
    val typeParams: List<ID>,
    val file: ID,
    val line: Int,
    val params: Map<ID,TypeIR>,
) : DeclarationIR
val FunctionDecIR.runnableID : ID
    get() = $$"$${host?.let{"$it$"}?: ""}$$name"
data class ClassDecIR(
    val name: ID,
    val anns: List<AnnotationIR>,
    val file: ID,
    val line: Int,
    val typeParams: List<ID>,
    val parents: List<TypeIR>,
) : DeclarationIR
typealias IRS = IntermediateRepresentation
data class IntermediateRepresentation(
    val classes : List<ClassDecIR>,
    val functions : List<FunctionDecIR>,
    val variables : List<VariableDecIR>,
    val commands : Map<ID,List<SugarRunnableIR>>,
)