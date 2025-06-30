/**
 * 本文件定义语义分析完毕的抽象语法树
 * 编写时kt版本 2.1.0
 * 编写时时间   2025.4.13
 * 创建者      语法糖味函子酱(sugared functor)
 */
package compiler.semantic

import tools.ID
data class ProjectAST(
    val name : ID,
    val files : List<FileAST>,
) : AST

data class FileAST(
    val name: ID,
    val tops: List<TopAST>
) : AST

data class NameAST(
    override val line: Int,
    override val column: Int,
    val expression: ExpressionAST?,
    val name: ID,
    override val type : TypeAST,
) : ExpressionAST

data class TypeVariableAST(
    override val line: Int,
    override val column: Int,
    override val name: ID,
    val bound: TypeAST,
    override val annotations : List<AnnotationAST>
) : TopAST

data class ClassAST(
    override val line  : Int,
    override val column: Int,
    override val name  : ID,
    override val annotations: List<AnnotationAST>,
    val typeParameters : List<ID>,
    val parents: List<TypeAST>,
    val members: List<CallableAST>,
) : TopAST

data class VariableAST(
    override val line: Int,
    override val column: Int,
    override val name: ID,
    override val returnType: TypeAST,
    override val annotations: List<AnnotationAST>,
    override val aboveContext: List<TypeAST>,
    override val belowContext: List<TypeAST>,
    val receiver : TypeAST?,
    val getter: FunctionAST?,
    val setter: FunctionAST?,
    val value: ExpressionAST?,
) : CallableAST

data class FunctionAST(
    override val line: Int,
    override val returnType: TypeAST,
    override val name: ID,
    override val column: Int,
    val typeParameters: List<ID>,
    val body: BodyAST?,
    val parameters: List<VariableAST>,
    override val aboveContext: List<TypeAST>,
    override val belowContext: List<TypeAST>,
    override val annotations: List<AnnotationAST>,
) : CallableAST

data class ScopeAST(
    override val line : Int,
    override val column : Int,
    override val stmts : List<StatementAST>,
) : BodyAST

data class LambdaAST(
    override val line: Int,
    override val column: Int,
    val parameters: List<VariableAST>,
    val body: ScopeAST,
    val returnType : TypeAST,
    override val type : TypeAST,
) : ExpressionAST

data class AnnotationAST(
    override val line : Int,
    override val column : Int,
    val name : ID,
    val arguments : ExpressionAST?,
) : InnerAST

data class InvokeAST(
    override val line: Int,
    override val column: Int,
    val invoker: ExpressionAST,
    val arguments: List<ExpressionAST>,
    val typeArguments: List<TypeAST>,
    override val type : TypeAST,
) : ExpressionAST

data class AssignAST(
    override val line: Int,
    override val column: Int,
    val name: ExpressionAST,
    val value: ExpressionAST,
) : ExpressionAST {
    override val type : TypeAST
        get() = value.type
}

data class StringConstantAST(
    override val line: Int,
    override val column: Int,
    override val value: String,
    override val type : TypeAST,
) : FaceConstantAST

data class IntegerConstantAST(
    override val line: Int,
    override val column: Int,
    override val value: Int,
    override val type : TypeAST,
) : NumberConstantAST

data class DecimalConstantAST(
    override val line: Int,
    override val column: Int,
    override val value: Double,
    override val type : TypeAST,
) : NumberConstantAST

data class CommonTypeAST(
    override val line: Int,
    override val column: Int,
    val name: ID,
    override val annotations : List<AnnotationAST>,
) : TypeAST

data class NullableTypeAST(
    override val line: Int,
    override val column: Int,
    val type: TypeAST,
    override val annotations : List<AnnotationAST>,
) : TypeAST

data class TupleTypeAST(
    override val line: Int,
    override val column: Int,
    val arguments: List<TypeAST>,
    override val annotations : List<AnnotationAST>,
) : TypeAST

data class FunctionTypeAST(
    override val line: Int,
    override val column: Int,
    val parameters: List<TypeAST>,
    val returnType: TypeAST,
    override val annotations : List<AnnotationAST>,
) : TypeAST

data class ApplyTypeAST(
    override val line: Int,
    override val column: Int,
    val name: ID,
    val arguments: List<TypeAST>,
    override val annotations : List<AnnotationAST>,
) : TypeAST
