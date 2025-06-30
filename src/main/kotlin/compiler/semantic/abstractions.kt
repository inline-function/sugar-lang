package compiler.semantic

import compiler.parser.StatementTree
import tools.ID
sealed interface AST
sealed interface InnerAST : AST {
    val line: Int
    val column: Int
}
sealed interface StatementAST : InnerAST
sealed interface TopAST : StatementAST {
    val name: ID
    val annotations: List<AnnotationAST>
}
sealed interface CallableAST : TopAST {
    val returnType: TypeAST?
    val aboveContext: List<TypeAST>
    val belowContext: List<TypeAST>
}
sealed interface FaceConstantAST : ExpressionAST {
    val value: Any
}
sealed interface NumberConstantAST : FaceConstantAST
sealed interface TypeAST : InnerAST {
    val annotations: List<AnnotationAST>
}
sealed interface BodyAST : InnerAST {
    val stmts : List<StatementAST>
}
sealed interface ExpressionAST : StatementAST, BodyAST {
    val type : TypeAST
    override val stmts : List<StatementAST>
        get() = listOf(this)
}
