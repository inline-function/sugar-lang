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
/**
 * 表示一个注解的中间表示。
 * 
 * @property name 注解的名称。
 * @property value 注解的值。
 * @property line 注解在源代码中的行号。
 */
data class AnnotationIR(
    val name: ID,
    val value: AnnotationValue,
    val argument : String?,
    val line: Int,
) : DeclarationIR
/**
 * 表示类型的中间表示。
 * 
 * @property name 类型的名称。
 * @property typeParams 类型参数列表。
 */
data class TypeIR(
    val name: ID,
    val typeParams: List<TypeIR> = emptyList(),
)
/**
 * 表示变量声明的中间表示。
 *
 * @property name 变量的名称。
 * @property type 变量的类型。
 * @property mutable 指示变量是否可变。
 * @property line 变量声明在源代码中的行号。
 * @property host 宿主类。
 * @property file 声明该变量的文件。
 * @property anns 应用到该变量上的注解列表。
 */
data class VariableDecIR(
    val name: ID,
    val type: TypeIR,
    val mutable: Boolean,
    val line: Int,
    val host: ID?,
    val file: ID,
    val anns: List<AnnotationIR>,
) : DeclarationIR
/**
 * 表示函数声明的中间表示。
 *
 * @property name 函数的名称。
 * @property anns 应用于该函数的注解列表。
 * @property type 函数的返回类型。
 * @property host 宿主类。
 * @property typeParams 函数的类型参数列表。
 * @property file 声明该函数的文件。
 * @property line 函数声明在源代码中的行号。
 * @property params 函数的参数类型列表。
 */
data class FunctionDecIR(
    val name: ID,
    val anns: List<AnnotationIR>,
    val type: TypeIR,
    val host: ID?,
    val typeParams: List<ID>,
    val file: ID,
    val line: Int,
    val params: Map<ID,TypeIR>,
    val body : List<SugarRunnableIR>
) : DeclarationIR
/**
 * 表示类声明的中间表示。
 *
 * @property name 类的名称。
 * @property anns 应用于该类的注解列表。
 * @property file 声明该类的文件。
 * @property line 类声明在源代码中的行号。
 * @property typeParams 类的类型参数列表。
 * @property parents 该类继承或实现的父类和接口列表。
 */
data class ClassDecIR(
    val name: ID,
    val anns: List<AnnotationIR>,
    val file: ID,
    val line: Int,
    val typeParams: List<ID>,
    val parents: List<TypeIR>,
) : DeclarationIR