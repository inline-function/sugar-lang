/**
 * 本文件定义了关于ANTLR相关事宜
 * 编写时kt版本 2.1.0
 * 编写时时间   2025.4.4
 * 创建者      语法糖味函子酱(sugared functor)
 */
package compiler.antlr

import compiler.antlr.SugarParser.*
import compiler.parser.AnnotationTree
import compiler.parser.Body
import compiler.parser.ClassTree
import compiler.parser.CommonTypeTree
import compiler.parser.DecimalConstantTree
import compiler.parser.ExpressionTree
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import compiler.parser.FileTree
import compiler.parser.FunctionTree
import compiler.parser.FunctionTypeTree
import compiler.parser.IntegerConstantTree
import compiler.parser.InvokeTree
import compiler.parser.LambdaTree
import compiler.parser.NameTree
import compiler.parser.NullableTypeTree
import compiler.parser.ProjectTree
import compiler.parser.StringConstantTree
import compiler.parser.TypeTree
import compiler.parser.VariableTree
import tools.ID
import tools.input

/**
 * 将字符串转成antlr树
 * @receiver 代码
 * @return antlr语法树
 */
@Suppress("DEPRECATION")
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
                    tops = top().map(TopContext::toSugarTree)
                )
            )
        )
    }
/**
 * 关于文件树的转换函数
 * @receiver antlr语法树
 * @return 白砂糖语法树
 */
fun FileContext.toSugarTree() = FileTree(
    name = ID(),
    tops = top().map(TopContext::toSugarTree)
)
/**
 * 关于顶层语句树的转换函数
 * @receiver antlr语法树
 * @return 白砂糖语法树
 */
fun TopContext.toSugarTree() =
    variable()?.toSugarTree() ?:
    function()?.toSugarTree() ?:
    class_  () .toSugarTree()
/**
 * 关于变量树的转换函数
 * @receiver antlr语法树
 * @return 白砂糖语法树
 */
fun VariableContext.toSugarTree() = VariableTree(
    line = getStart().line,
    column = getStart().charPositionInLine,
    name = ID().text,
    value = expr()?.toSugarTree(),
    returnType = type()?.toSugarTree(),
    isMutable = VAL()?.let { false } ?: true,
    annotations = modifier().run {
        annotation().map(AnnotationContext::toSugarTree) +
        ID().map {
            AnnotationTree(
                line = getStart().line,
                column = getStart().charPositionInLine,
                name = it.text,
                arguments = null
            )
        }
    },
)
/**
 * 关于函数树的转换函数
 * @receiver antlr语法树
 * @return 白砂糖语法树
 */
fun FunctionContext.toSugarTree() = FunctionTree(
    line = getStart().line,
    returnType = type()?.toSugarTree(),
    name = ID().text,
    column = getStart().charPositionInLine,
    body = body()?.toSugarTree(),
    parameters = parameter().map(ParameterContext::toSugarTree),
    typeParameters = typeParamList()?.ID()?.map { text } ?: emptyList(),
    annotations = modifier().run {
        annotation().map(AnnotationContext::toSugarTree) +
        ID().map {
            AnnotationTree(
                line = getStart().line,
                column = getStart().charPositionInLine,
                name = it.text,
                arguments = null
            )
        }
    },
)
/**
 * 关于参数树的转换函数
 * @receiver antlr语法树
 * @return 白砂糖语法树
 */
fun ParameterContext.toSugarTree() = VariableTree(
    line = getStart().line,
    column = getStart().charPositionInLine,
    name = ID().text,
    value = expr()?.toSugarTree(),
    returnType = type().toSugarTree(),
    isMutable = false,
    annotations = listOf(),
)
fun FunctionTypeContext.toSugarTree() = FunctionTypeTree(
    line       = getStart().line,
    column     = getStart().charPositionInLine,
    returnType = type().last().toSugarTree(),
    parameters = type().dropLast(1).map(TypeContext::toSugarTree)
)
fun NullableTypeContext.toSugarTree(it : TypeTree) = NullableTypeTree(
    line   = getStart().line,
    column = getStart().charPositionInLine,
    type   = it
)
/**
 * 关于函数体的转换函数
 * @receiver antlr语法树
 * @return 白砂糖语法树
 */
fun BodyContext.toSugarTree() : Body = stmt().map(StmtContext::toSugarTree)
/**
 * 关于语句树的转换函数
 * @receiver antlr语法树
 * @return 白砂糖语法树
 */
fun StmtContext.toSugarTree() = top()?.toSugarTree() ?: expr().toSugarTree()
/**
 * 关于类树的转换函数
 * @receiver antlr语法树
 * @return 白砂糖语法树
 */
fun ClassContext.toSugarTree() = ClassTree(
    line = getStart().line,
    column = getStart().charPositionInLine,
    name = ID().text,
    parents = type().map(TypeContext::toSugarTree),
    typeParameters = typeParamList()?.ID()?.map { it.text } ?: emptyList(),
    members = function().map(FunctionContext::toSugarTree) +
            variable().map(VariableContext::toSugarTree),
    annotations = modifier().run {
        annotation().map(AnnotationContext::toSugarTree) +
        ID().map {
            AnnotationTree(
                line = getStart().line,
                column = getStart().charPositionInLine,
                name = it.text,
                arguments = null
            )
        }
    }
)
/**
 * 关于表达式树的转换函数
 * @receiver antlr语法树
 * @return 白砂糖语法树
 * TODO invoke
 */
fun ExprContext.toSugarTree() : ExpressionTree =(
    number()?.toSugarTree()?:
    expr()  ?.toSugarTree()?:
    lambda()?.toSugarTree()?:
    ID()    ?.run { NameTree(
        line = getStart().line,
        column = getStart().charPositionInLine,
        expression = null,
        name = text
    ) } ?: StringConstantTree(
        line   = getStart().line,
        column = getStart().charPositionInLine,
        value  = STRING().text.drop(1).dropLast(1)
    )
).run{
    invoke()?.toSugarTree(
        name()?.toSugarTree(this) ?: this
    ) ?: (name()?.toSugarTree(this) ?: this)
}
fun LambdaContext.toSugarTree() : LambdaTree =
    LambdaTree(
        line = getStart().line,
        column = getStart().charPositionInLine,
        parameters = if(parameter().isNotEmpty())
            parameter().map(ParameterContext::toSugarTree)
        else
            ID().map {
                VariableTree(
                    line = getStart().line,
                    column = getStart().charPositionInLine,
                    name = it.text,
                    value = null,
                    returnType = null,
                    isMutable = false,
                    annotations = listOf(),
                )
            },
        body = stmt().map(StmtContext::toSugarTree),
    )
fun AnnotationContext.toSugarTree() : AnnotationTree =
    AnnotationTree(
        line = getStart().line,
        column = getStart().charPositionInLine,
        name = ID().text,
        arguments = expr()?.toSugarTree()
    )
/**
 * 关于调用树的转换函数
 * @receiver antlr语法树
 * @return 白砂糖语法树
 */
fun InvokeContext.toSugarTree(it:ExpressionTree) : ExpressionTree =
    InvokeTree(
        line = getStart().line,
        column = getStart().charPositionInLine,
        invoker = it,
        arguments = expr().map(ExprContext::toSugarTree),
        typeArguments = typeArgList()?.type()?.map(TypeContext::toSugarTree) ?: emptyList()
    ).run {
        invoke()?.toSugarTree(
            name()?.toSugarTree(this) ?: this
        ) ?: (name()?.toSugarTree(this) ?: this)
    }
fun NameContext.toSugarTree(it:ExpressionTree) : ExpressionTree =
    NameTree(
        line = getStart().line,
        column = getStart().charPositionInLine,
        name = ID().text,
        expression = it
    ).run {
        invoke()?.toSugarTree(
            name()?.toSugarTree(this) ?: this
        ) ?: (name()?.toSugarTree(this) ?: this)
    }
/**
 * 关于类型树的转换函数
 * @receiver antlr语法树
 * @return 白砂糖语法树
 */
fun TypeContext.toSugarTree() : TypeTree =
    (ID()?.let {
        CommonTypeTree(
            line   = getStart().line,
            column = getStart().charPositionInLine,
            name   = it.text
        )
    } ?: functionType()?.toSugarTree()
    ?:type().toSugarTree()).run {
        nullableType()?.toSugarTree(this) ?: this
    }
fun NumberContext.toSugarTree() =
    INT()?.let {
        IntegerConstantTree(
            line   = getStart().line,
            column = getStart().charPositionInLine,
            value  = it.text.toInt()
        )
    }?:DecimalConstantTree(
        line   = getStart().line,
        column = getStart().charPositionInLine,
        value  = DEC().text.toDouble()
    )