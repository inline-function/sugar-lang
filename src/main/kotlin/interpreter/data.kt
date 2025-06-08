package interpreter

import compiler.semantic.*
import tools.ID

data class SugarProgram(
    val tree : ProjectTree,
    val info : ProgramInformation,
    val runtime : ProgramRuntime
)
data class ProgramInformation(
    val classes : List<ClassTree> = emptyList(),
    val functions : List<FunctionTree> = emptyList(),
    val variables : List<VariableTree> = emptyList(),
    val parent : ProgramInformation? = null,
)
val ProgramInformation.flatFunctions : List<FunctionTree>
    get() = functions + (parent?.flatFunctions ?: emptyList())
val ProgramInformation.flatVariables : List<VariableTree>
    get() = variables + (parent?.flatVariables ?: emptyList())
val ProgramInformation.flatClasses : List<ClassTree>
    get() = classes + (parent?.flatClasses ?: emptyList())
fun ProgramInformation.findFunction(name : ID) : FunctionTree? =
    parent?.findFunction(name) ?: functions.find { it.name == name }
fun ProgramInformation.findClass(name : ID) : ClassTree? =
    parent?.findClass(name) ?: classes.find { it.name == name }
fun ProgramInformation.findVariable(name : ID) : VariableTree? =
    parent?.findVariable(name) ?: variables.find { it.name == name }
data class SugarPointer(
    val address : UInt,
)
val SugarProgram.allocateHeap : SugarPointer
    get() = SugarPointer(runtime.heap.keys.maxOfOrNull { it.address }?.plus(1u) ?: 1u)
val StackFrame.allocate : SugarPointer
    get() = SugarPointer(variables.keys.maxOfOrNull { it.address }?.plus(1u) ?: 1u)
data class StackFrame(
    val variables : MutableMap<SugarPointer,SugarObject> = mutableMapOf(),
)
data class SugarObject(
    val type : ClassTree,
    val fields : MutableMap<ID,SugarPointer> = mutableMapOf(),
    val methods : MutableMap<ID,List<FunctionTree>> = mutableMapOf(),
)
data class ProgramRuntime(
    val stack : MutableMap<ID,MutableList<StackFrame>> = mutableMapOf(),
    val heap : MutableMap<SugarPointer,SugarObject> = mutableMapOf(),
    val metaData : MutableList<MetaData> = mutableListOf(),
)
sealed interface MetaData
data class Initialized(
    val variable : VariableTree,
    val pointer : SugarPointer,
) : MetaData