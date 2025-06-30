package compiler.parser

import tools.ID

sealed interface Tree
sealed interface InnerTree : Tree {
    val line : Int
    val column : Int
}
sealed interface StatementTree : InnerTree
sealed interface TopTree : StatementTree {
    val name : ID
    val annotations : List<AnnotationTree>
}
sealed interface CallableTree : TopTree {
    val returnType : TypeTree?
    val aboveContext: List<TypeTree>
    val belowContext: List<TypeTree>
}
sealed interface FaceConstantTree : ExpressionTree{
    val value : Any
}
sealed interface NumberConstantTree : FaceConstantTree
sealed interface TypeTree : InnerTree {
    val annotations : List<AnnotationTree>
}
sealed interface BodyTree : InnerTree {
    val stmts : List<StatementTree>
}
sealed interface ExpressionTree : StatementTree,BodyTree {
    override val stmts : List<StatementTree>
        get() = listOf(this)
}