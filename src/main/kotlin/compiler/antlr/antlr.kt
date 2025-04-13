/**
 * 本文件定义了关于ANTLR相关事宜
 * 编写时kt版本 2.1.0
 * 编写时时间   2025.4.4
 * 创建者      语法糖味函子酱(sugared functor)
 */
package compiler.antlr

import compiler.antlr.SugarParser.*
import compiler.parser.Body
import compiler.parser.ClassTree
import compiler.parser.CommonTypeTree
import compiler.parser.DecimalConstantTree
import compiler.parser.ExpressionTree
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import compiler.parser.FileTree
import compiler.parser.FunctionTree
import compiler.parser.ID
import compiler.parser.IntegerConstantTree
import compiler.parser.InvokeTree
import compiler.parser.NameTree
import compiler.parser.ProjectTree
import compiler.parser.StringConstantTree
import compiler.parser.TypeTree
import compiler.parser.VariableTree
import tools.input

/**
 * 将字符串转成antlr树
 * @receiver 代码
 * @return antlr语法树
 */
@Suppress("DEPRECATION")
fun String.toAntlrTree() : FileContext = this input
    ::ANTLRInputStream  input
    ::SugarLexer        input
    ::CommonTokenStream input
    ::SugarParser       input
    SugarParser::file
/**
 * 将字符串文件转换为只有单文件的语法阶段语法树
 * @param fileName 文件名
 * @param projectName 工程名
 * @receiver 代码
 * @return 单文件的工程语法树
 */
fun String.toSugarTree(fileName : ID = "默认",projectName : ID = "默认") =
    toAntlrTree().run {
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
    line       = getStart().line,
    column     = getStart().charPositionInLine,
    name       = ID().text,
    value      = expr()?.toSugarTree(),
    returnType = type()?.toSugarTree()
)
/**
 * 关于函数树的转换函数
 * @receiver antlr语法树
 * @return 白砂糖语法树
 */
fun FunctionContext.toSugarTree() = FunctionTree(
    line       = getStart().line,
    returnType = type()?.toSugarTree(),
    name       = ID().text,
    column     = getStart().charPositionInLine,
    body       = body()?.toSugarTree(),
    parameters = variable().map(VariableContext::toSugarTree)
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
    line    = getStart().line,
    column  = getStart().charPositionInLine,
    name    = ID().text,
    parents = type().map(TypeContext::toSugarTree),
    members = function().map(FunctionContext::toSugarTree) +
              variable().map(VariableContext::toSugarTree)
)
/**
 * 关于表达式树的转换函数
 * @receiver antlr语法树
 * @return 白砂糖语法树
 * TODO invoke
 */
fun ExprContext.toSugarTree() : ExpressionTree =(
    number()?.toSugarTree()?:
    name()  ?.toSugarTree()?:
    expr()  ?.toSugarTree()?:
    StringConstantTree(
        line   = getStart().line,
        column = getStart().charPositionInLine,
        value  = STRING().text.drop(1).dropLast(1)
    )
).run{ invoke_()?.toSugarTree(this) ?: this }
/**
 * 关于调用树的转换函数
 * @receiver antlr语法树
 * @return 白砂糖语法树
 */
fun Invoke_Context.toSugarTree(it:ExpressionTree) : ExpressionTree =
    InvokeTree(
        line = getStart().line,
        column = getStart().charPositionInLine,
        invoker = it,
        arguments = expr().map(ExprContext::toSugarTree)
    ).run { invoke_()?.toSugarTree(this) ?: this }
/**
 * 关于类型树的转换函数
 * @receiver antlr语法树
 * @return 白砂糖语法树
 */
fun TypeContext.toSugarTree() : TypeTree =
    ID()?.let {
        CommonTypeTree(
            line   = getStart().line,
            column = getStart().charPositionInLine,
            name   = it.text
        )
    }?:type().toSugarTree()
fun NameContext.toSugarTree() = NameTree(
    line = getStart().line,
    column = getStart().charPositionInLine,
    chain = ID().map{ it.text }
)
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