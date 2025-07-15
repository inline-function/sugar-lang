package compiler.parser
import tools.ID
import java.util.Objects

data class ProjectTree(
    val name : ID,
    val files : List<FileTree>,
) : Tree

data class FileTree(
    val name: ID,
    val tops: List<TopTree>
) : Tree

data class NameTree(
    override val line: Int,
    override val column: Int,
    val expression: ExpressionTree?,
    val name: ID,
) : ExpressionTree

data class TypeVariableTree(
    override val line: Int,
    override val column: Int,
    override val name: ID,
    val bound: TypeTree?,
    override val annotations : List<AnnotationTree>
) : TopTree

data class ClassTree(
    override val line  : Int,
    override val column: Int,
    override val name  : ID,
    override val annotations: List<AnnotationTree>,
    val typeParameters : List<TypeVariableTree>,
    val parents: List<TypeTree>,
    val members: List<CallableTree>,
) : TopTree

data class VariableTree(
    override val line: Int,
    override val column: Int,
    override val name: ID,
    override val returnType: TypeTree?,
    override val annotations: List<AnnotationTree>,
    override val aboveContext: List<TypeTree>,
    override val belowContext: List<TypeTree>,
    val receiver : TypeTree?,
    val getter: FunctionTree?,
    val setter: FunctionTree?,
    val value: ExpressionTree?,
) : CallableTree

data class AssignTree(
    override val line: Int,
    override val column: Int,
    val name: ExpressionTree,
    val value: ExpressionTree,
) : ExpressionTree

data class FunctionTree(
    override val line: Int,
    override val returnType: TypeTree?,
    override val name: ID,
    override val column: Int,
    val typeParameters: List<TypeVariableTree>,
    val body: BodyTree?,
    val parameters: List<VariableTree>,
    override val aboveContext: List<TypeTree>,
    override val belowContext: List<TypeTree>,
    override val annotations: List<AnnotationTree>,
) : CallableTree

data class ScopeTree(
    override val line : Int,
    override val column : Int,
    override val stmts : List<StatementTree>,
) : BodyTree

data class LambdaTree(
    override val line: Int,
    override val column: Int,
    val parameters: List<VariableTree>,
    val body: ScopeTree,
) : ExpressionTree

data class AnnotationTree(
    override val line: Int,
    override val column: Int,
    val name: ID,
    val arguments: ExpressionTree?,
) : InnerTree

data class InvokeTree(
    override val line: Int,
    override val column: Int,
    val invoker: ExpressionTree,
    val arguments: List<ExpressionTree>,
    val typeArguments: List<TypeTree>,
) : ExpressionTree

data class StringConstantTree(
    override val line: Int,
    override val column: Int,
    override val value: String
) : FaceConstantTree

data class IntegerConstantTree(
    override val line: Int,
    override val column: Int,
    override val value: Int
) : NumberConstantTree

data class DecimalConstantTree(
    override val line: Int,
    override val column: Int,
    override val value: Double
) : NumberConstantTree

data class CommonTypeTree(
    override val line: Int,
    override val column: Int,
    val name: ID,
    override val annotations : List<AnnotationTree>,
) : TypeTree {
    override fun equals(other : Any?) =
        other is CommonTypeTree &&
        name == other.name &&
        annotations == other.annotations
}

data class NullableTypeTree(
    override val line: Int,
    override val column: Int,
    val type: TypeTree,
    override val annotations : List<AnnotationTree>,
) : TypeTree {
    override fun equals(other : Any?) =
        other is NullableTypeTree &&
        type == other.type &&
        annotations == other.annotations
}

data class TupleTypeTree(
    override val line: Int,
    override val column: Int,
    val arguments: List<TypeTree>,
    override val annotations : List<AnnotationTree>,
) : TypeTree {
    override fun equals(other : Any?) =
        other is TupleTypeTree &&
        arguments == other.arguments &&
        annotations == other.annotations
}

data class FunctionTypeTree(
    override val line: Int,
    override val column: Int,
    val parameters: List<TypeTree>,
    val returnType: TypeTree,
    override val annotations : List<AnnotationTree>,
) : TypeTree
data class ApplyTypeTree(
    override val line: Int,
    override val column: Int,
    val name: ID,
    val arguments: List<TypeTree>,
    override val annotations : List<AnnotationTree>,
) : TypeTree