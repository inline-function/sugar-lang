@file:Suppress("NestedLambdaShadowedImplicitParameter")

package interpreter

import compiler.ir.context
import compiler.semantic.*
import interpreter.SugarProgram
import tools.exception

fun ProjectTree.execute() = context(
    SugarProgram(
        info = ProgramInformation(
            classes = files.flatMap { it.tops.filterIsInstance<ClassTree>() },
            functions = files.flatMap { it.tops.filterIsInstance<FunctionTree>() },
            variables = files.flatMap { it.tops.filterIsInstance<VariableTree>() },
        ),
        runtime = ProgramRuntime(
            stack = mutableMapOf("main" to mutableListOf()),
            heap = mutableMapOf()
        ),
        tree = this
    )
){ program ->
    val mainFunction = program.info.findFunction("main") ?: exception("主函数不存在")
    context(StackFrame()){ topLevelFrame ->
        program.runtime.stack["main"]!!.add(topLevelFrame)
        mainFunction()
    }
}
context(pi : SugarProgram,frame : StackFrame)
operator fun FunctionTree.invoke(vararg args : SugarPointer) : SugarPointer{
    body?.map { it.execute() }?.lastOrNull()
    TODO()
}
context(pi : SugarProgram)
fun getTopValue(variable : VariableTree) : SugarPointer = pi.runtime.metaData
    .find { it is Initialized && it.variable == variable }
    ?.let { (it as Initialized).pointer }
    ?: TODO()
context(pi : SugarProgram,frame : StackFrame)
fun StatementTree.execute() : SugarPointer = when(this){
    is ExpressionTree -> execute()
    is TopTree        -> execute()
}
context(pi : SugarProgram,frame : StackFrame)
fun ExpressionTree.execute() : SugarPointer = when(this){
    is DecimalConstantTree -> TODO()
    is IntegerConstantTree -> TODO()
    is StringConstantTree  -> TODO()
    is InvokeTree          -> TODO()
    is LambdaTree          -> TODO()
    is NameTree            -> TODO()
}