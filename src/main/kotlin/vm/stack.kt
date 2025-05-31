package vm

import compiler.ir.sugar.SugarRunnableIR
import tools.ID
import java.lang.RuntimeException

class ThreadFinishedException : RuntimeException("线程函数栈为空,因此线程结束")
data class VMThread private constructor(
    val stack : FunctionStack,
) {
    lateinit var thread : Thread
        private set
    fun start(vm : VirtualMachine) {
        thread = Thread {
            try{
                task(vm)
            }catch(e : ThreadFinishedException){
                if(this in vm.childrenThreads)
                    vm.childrenThreads.remove(this)
            }
        }
        thread.start()
    }
    constructor(command : List<SugarRunnableIR>) : this(
        stack = FunctionStack(
            frames = mutableListOf(
                StackFrame(
                    command = command
                )
            )
        )
    )
}
data class FunctionStack(
    val frames : MutableList<StackFrame> = mutableListOf(),
){
    fun push(command : List<SugarRunnableIR>,returnResultName : ID? = null) =
        frames.add(StackFrame(command,returnResultName))
    fun pop() = frames.removeLast()
    val top : StackFrame
        get() = frames.lastOrNull() ?: throw ThreadFinishedException()
}
data class StackFrame(
    val command : List<SugarRunnableIR>,
    val returnResultName : ID? = null,
    val variables : MutableMap<ID,SugarObject> = mutableMapOf(),
    var index : Int = 0,
)