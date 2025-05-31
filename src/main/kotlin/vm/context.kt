package vm

import compiler.ir.sugar.*

data class Context(
    val classes : MutableList<ClassDecIR> = mutableListOf(),
    val functions : MutableList<Pair<FunctionDecIR,List<SugarRunnableIR>>> = mutableListOf(),
    val variables : MutableList<VariableDecIR> = mutableListOf(),
)