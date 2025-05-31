package vm

import compiler.ir.sugar.FunctionDecIR
import compiler.ir.sugar.MoveIR
import compiler.ir.sugar.PutDecConstantValueIR
import compiler.ir.sugar.PutIntConstantValueIR
import compiler.ir.sugar.PutStringConstantValueIR
import compiler.ir.sugar.ReturnIR
import compiler.ir.sugar.SugarRunnableIR
import compiler.ir.sugar.TagIR
import tools.ID

data class VirtualMachine(
    val mainThread      : VMThread,
    val environment     : Environment = Environment(),
    val context         : Context = Context(),
    val childrenThreads : MutableList<VMThread> = mutableListOf(),
) {
    companion object {
        fun start(vararg ir : SugarRunnableIR) =
            start(ir.toList())
        fun start(ir : List<SugarRunnableIR>) =
            VirtualMachine(
                mainThread = VMThread(
                    command = ir
                )
            ).also { it.mainThread.start(it) }
    }
}
