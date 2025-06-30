/**
 * 本文件定义了关于ANTLR相关事宜
 * 编写时kt版本 2.1.0
 * 编写时时间   2025.4.4
 * 创建者      语法糖味函子酱(sugared functor)
 */
@file:Suppress("DEPRECATION","NestedLambdaShadowedImplicitParameter")

package compiler.parser

import compiler.antlr.SugarLexer
import compiler.antlr.SugarParser
import compiler.antlr.SugarParser.*
import org.antlr.v4.kotlinruntime.ANTLRInputStream
import org.antlr.v4.kotlinruntime.CommonTokenStream
import org.antlr.v4.kotlinruntime.ParserRuleContext
import tools.ID

/**
 * 将字符串转成antlr树
 * @receiver 代码
 * @return antlr语法树
 */
fun String.toAntlrTree(showTree : Boolean = false) : FileContext{
    val chars = ANTLRInputStream(this)
    val lexer = SugarLexer(chars)
    val tokens = CommonTokenStream(lexer)
    val parser = SugarParser(tokens)
    val tree = parser.file()
    if (showTree) println("ANTLR解析的具体语法树 : " + tree.toStringTree(parser))
    return tree
}
/**
 * 将字符串文件转换为只有单文件的语法阶段语法树
 * @param fileName 文件名
 * @param projectName 工程名
 * @receiver 代码
 * @return 单文件的工程语法树
 */
fun String.toSugarTree(showTree : Boolean = false,fileName : ID = "默认",projectName : ID = "默认") =
    toAntlrTree(showTree).run {
        ProjectTree(
            name  = projectName,
            files = listOf(
                FileTree(
                    name = fileName,
                    tops = top().map { it.toSugarTree() }
                )
            )
        )
    }
val ParserRuleContext.line get() = start!!.line
val ParserRuleContext.column get() = start!!.charPositionInLine
fun TopContext.toSugarTree() : TopTree =
    variable()?.toSugarTree() ?:
    function()?.toSugarTree() ?:
    class_()!!.toSugarTree()
fun VariableContext.toSugarTree() = VariableTree(
    line = line,
    column = column,
    name = ID().text,
    returnType = type()?.toSugarTree(),
    annotations = modifier()?.toSugarTree() ?: emptyList(),
    aboveContext = aboveList()?.toSugarTree() ?: emptyList(),
    belowContext = belowList()?.toSugarTree() ?: emptyList(),
    getter = getter()?.toSugarTree(),
    setter = setter()?.toSugarTree(),
    value = expr()?.toSugarTree(),
    receiver = receiver()?.toSugarTree()
)
fun FunctionContext.toSugarTree() = FunctionTree(
    line = line,
    returnType = type()?.toSugarTree(),
    name = ID().text,
    column = column,
    typeParameters = typeParamList()?.toSugarTree() ?: emptyList(),
    body = body()?.toSugarTree(),
    parameters = parameter().map { it.toSugarTree() },
    aboveContext = aboveList()?.toSugarTree() ?: emptyList(),
    belowContext = belowList()?.toSugarTree() ?: emptyList(),
    annotations = modifier()?.toSugarTree() ?: emptyList()
)
fun ClassContext.toSugarTree() = ClassTree(
    line = line,
    column = column,
    name = ID().text,
    annotations = modifier()?.toSugarTree() ?: emptyList(),
    typeParameters = typeParamList()?.toSugarTree() ?: emptyList(),
    parents = type().map { it.toSugarTree() },
    members = variable().map { it.toSugarTree() } + function().map { it.toSugarTree() },
)
fun TypeContext.toSugarTree() : TypeTree = (
    type()?.toSugarTree() ?:
    tupleType()?.run {
        TupleTypeTree(
            line = line,
            column = column,
            arguments = type().map { it.toSugarTree() },
            annotations = modifier()?.toSugarTree() ?: emptyList(),
        )
    } ?: applyType()?.run {
        ApplyTypeTree(
            line = line,
            column = column,
            name = ID().text,
            arguments = typeArgList().type().map { it.toSugarTree() },
            annotations = modifier()?.toSugarTree() ?: emptyList(),
        )
    } ?: functionType()?.run {
        FunctionTypeTree(
            line = line,
            column = column,
            parameters = type().dropLast(1).map { it.toSugarTree() },
            returnType = type().last().toSugarTree(),
            annotations = modifier()?.toSugarTree() ?: emptyList(),
        )
    } ?: CommonTypeTree(
        line = line,
        column = column,
        name = commonType()!!.ID().text,
        annotations = commonType()!!.modifier()?.toSugarTree() ?: emptyList(),
    )
).let {
    nullableType()?.run {
        NullableTypeTree(
            line = line,
            column = column,
            type = it,
            annotations = modifier()?.toSugarTree() ?: emptyList(),
        )
    } ?: it
}
fun ModifierContext.toSugarTree() : List<AnnotationTree> =
    annotation().map {
        AnnotationTree(
            line = line,
            column = column,
            name = it.ID().text,
            arguments = it.expr()?.toSugarTree()
        )
    } + ID().map {
        AnnotationTree(
            line = line,
            column = column,
            name = it.text,
            arguments = null
        )
    }
fun AboveListContext.toSugarTree() : List<TypeTree> =
    type().map { it.toSugarTree() }
fun BelowListContext.toSugarTree() : List<TypeTree> =
    type().map { it.toSugarTree() }
fun GetterContext.toSugarTree() = FunctionTree(
    line = line,
    returnType = null,
    name = "getter",
    column = column,
    typeParameters = typeParamList()?.toSugarTree() ?: emptyList(),
    body = body()?.toSugarTree() ?: expr()?.toSugarTree(),
    parameters = emptyList(),
    aboveContext = aboveList()?.toSugarTree() ?: emptyList(),
    belowContext = belowList()?.toSugarTree() ?: emptyList(),
    annotations = modifier()?.toSugarTree() ?: emptyList(),
)
fun SetterContext.toSugarTree() = FunctionTree(
    line = line,
    returnType = null,
    name = "setter",
    column = column,
    typeParameters = typeParamList()?.toSugarTree() ?: emptyList(),
    body = body()?.toSugarTree() ?: expr()?.toSugarTree(),
    parameters = listOf(
        VariableTree(
            line = line,
            column = column,
            name = "value",
            returnType = null,
            annotations = modifier()?.toSugarTree() ?: emptyList(),
            aboveContext = emptyList(),
            belowContext = emptyList(),
            receiver = null,
            getter = null,
            setter = null,
            value = null,
        )
    ),
    aboveContext = aboveList()?.toSugarTree() ?: emptyList(),
    belowContext = belowList()?.toSugarTree() ?: emptyList(),
    annotations = modifier()?.toSugarTree() ?: emptyList(),
)
fun ReceiverContext.toSugarTree() : TypeTree? =
    type().toSugarTree()
fun ParameterContext.toSugarTree() = VariableTree(
    line = line,
    column = column,
    name = ID().text,
    returnType = type().toSugarTree(),
    annotations = modifier()?.toSugarTree() ?: emptyList(),
    aboveContext = emptyList(),
    belowContext = emptyList(),
    receiver = null,
    getter = null,
    setter = null,
    value = expr()?.toSugarTree(),
)
fun TypeParamListContext.toSugarTree() : List<ID> =
    ID().map { it.text }
fun BodyContext.toSugarTree() = ScopeTree(
    line = line,
    column = column,
    stmts = stmt().map { it.expr()?.toSugarTree() ?: it.top()!!.toSugarTree() }
)
fun ExprContext.toSugarTree() : ExpressionTree = (
    expr()?.toSugarTree() ?:
    number()?.run {
        INT()?.text?.toInt()?.let {
            IntegerConstantTree(
                line = line,
                column = column,
                value = it
            )
        } ?: DecimalConstantTree(
            line = line,
            column = column,
            value = DEC()!!.text.toDouble()
        )
    } ?: STRING()?.run {
        StringConstantTree(
            line = line,
            column = column,
            value = text.substring(1, text.length - 1)
        )
    } ?: ID()?.run {
        NameTree(
            line = line,
            column = column,
            expression = null,
            name = text
        )
    } ?: lambda()!!.run {
        LambdaTree(
            line = line,
            column = column,
            parameters = if(parameter().isNotEmpty())
                parameter().map { it.toSugarTree() }
            else ID().map {
                VariableTree(
                    line = line,
                    column = column,
                    name = it.text,
                    returnType = null,
                    annotations = emptyList(),
                    aboveContext = emptyList(),
                    belowContext = emptyList(),
                    receiver = null,
                    getter = null,
                    setter = null,
                    value = null
                )
            },
            body = ScopeTree(
                line = line,
                column = column,
                stmts = stmt().map { it.expr()?.toSugarTree() ?: it.top()!!.toSugarTree() }
            )
        )
    }
).let {
    name()?.run {
        NameTree(
            line = line,
            column = column,
            expression = it,
            name = ID().text,
        )
    } ?: it
}.let {
    invoke()?.run {
        InvokeTree(
            line = line,
            column = column,
            invoker = it,
            arguments = expr().map { it.toSugarTree() },
            typeArguments = typeArgList()?.type()?.map { it.toSugarTree() } ?: emptyList()
        )
    } ?: it
}.let {
    assign()?.run {
        AssignTree(
            line = line,
            column = column,
            name = it,
            value = expr().toSugarTree(),
        )
    } ?: it
}