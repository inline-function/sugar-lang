package vm

import compiler.ir.sugar.*
import compiler.semantic.Tag
import tools.ID
import vm.VMThread
import vm.VirtualMachine

val task : VMThread.(VirtualMachine)->Unit = {
    with(it) {
        generateSequence { stack.top.command.getOrNull(stack.top.index++) }
            .forEach { command ->
                when(command) {
                    is InvokeFunctionIR         -> command.execute()
                    is MoveIR                   -> command.execute()
                    is PutDecConstantValueIR    -> command.execute()
                    is PutIntConstantValueIR    -> command.execute()
                    is PutStringConstantValueIR -> command.execute()
                    is ReturnIR                 -> command.execute()
                    is TagIR                    -> command.execute()
                    is CopyIR                   -> command.execute()
                    is GetMemberIR              -> command.execute()
                    is EqualIR                  -> command.execute()
                    is GoToIR                   -> command.execute()
                }
            }
    }
}
context(vm : VirtualMachine,thread : VMThread)
fun InvokeFunctionIR.execute() {
    vm.context.functions
        .find { (f,_) -> f.name == name && f.host == null }
        .let { it ?: throw Error("没有该函数") }
        .let { (fn,com) ->
            fn.anns
                .find { it.name == "JvmFunction" }
                ?.let {
                    val (className,functionName,paramSize) = it.argument!!.split("::")
                    val jvmClass = Class.forName(className)
                    val jvmFunction = jvmClass.getDeclaredMethod(functionName,*buildList {
                        repeat(paramSize.toInt()) {
                            add(SugarObject::class.java)
                        }
                    }.toTypedArray())
                    jvmFunction.isAccessible = true
                    jvmFunction.invoke(null,
                        *args.map { arg ->
                            thread.stack.top.variables[arg]
                                ?: vm.environment.variables[arg]!!
                        }.toTypedArray()
                    ) ?: Unit
                } ?: run {
                    thread.stack.push(com,result)
                    //TODO()
                }
        }
}
context(vm : VirtualMachine,thread : VMThread)
fun PutStringConstantValueIR.execute() {
    thread.stack.top.variables[result] = object : SugarObject {
        override val type : TypeIR
            get() = str_type_ir
        override fun getField(field : ID) : SugarObject? {
            TODO("Not yet implemented")
        }
        override fun toString() = value
    }
}
context(vm : VirtualMachine,thread : VMThread)
fun PutDecConstantValueIR.execute() {
    thread.stack.top.variables[result] = object : SugarObject {
        override val type : TypeIR
            get() = dec_type_ir
        override fun getField(field : ID) : SugarObject? {
            TODO("Not yet implemented")
        }
        override fun toString() = value.toString()
    }
}
context(vm : VirtualMachine,thread : VMThread)
fun PutIntConstantValueIR.execute() {
    thread.stack.top.variables[result] = object : SugarObject {
        override val type : TypeIR
            get() = int_type_ir
        override fun getField(field : ID) : SugarObject? {
            TODO("Not yet implemented")
        }
        override fun toString() = value.toString()
    }
}
context(vm : VirtualMachine,thread : VMThread)
fun MoveIR.execute() {
    thread.stack.top.variables[to] = thread.stack.top.variables[from]!!
    thread.stack.top.variables.remove(from)
}
context(vm : VirtualMachine,thread : VMThread)
fun CopyIR.execute() {
    thread.stack.top.variables[to] = thread.stack.top.variables[from]!!
}
context(vm : VirtualMachine,thread : VMThread)
fun TagIR.execute() {}
context(vm : VirtualMachine,thread : VMThread)
fun GetMemberIR.execute() {
    thread.stack.top.variables[result] = thread.stack.top.variables[name]!!.getField(member)!!
}
context(vm : VirtualMachine,thread : VMThread)
fun ReturnIR.execute() {
    if(thread.stack.top.command.size == 1) {
        thread.stack.pop()
        return
    }
    val resultName = thread.stack.top.returnResultName
    val value = value.let {
        thread.stack.top.variables[it]
            ?: vm.environment.variables[it]!!
    }
    thread.stack.pop()
    resultName?.let {
        thread.stack.top.variables[it] = value
    }
}
context(vm : VirtualMachine,thread : VMThread)
fun EqualIR.execute() {
    val left = left.let {
        thread.stack.top.variables[it] ?: vm.environment.variables[it]!!
    }
    val right = right.let {
        thread.stack.top.variables[it] ?: vm.environment.variables[it]!!
    }
    thread.stack.top.variables[result] = object : SugarObject {
        override val type : TypeIR
            get() = bool_type_ir
        override fun getField(field : ID) =
            when(field){
                "invoke" -> object : SugarObject {
                    override val type : TypeIR
                        get() = TypeIR(
                            name = "Function1",
                            typeParams = listOf(
                                TypeIR(
                                    name = "Function0",
                                    typeParams = listOf(unit_type_ir)
                                ),
                                unit_type_ir
                            ),
                        )
                    override fun getField(field : ID) : SugarObject? {
                        TODO("Not yet implemented")
                    }
                    override fun toString() : String {
                        TODO("Not yet implemented")
                    }
                }
                else -> TODO()
            }
        override fun toString() = (left === right).toString()
    }
}
context(vm : VirtualMachine,thread : VMThread)
fun GoToIR.execute() {
    thread.stack.top.index = thread.stack.top.command
        .indexOfFirst { it is TagIR && it.line == tag }
        .takeIf { it != -1 }
        ?: throw Exception("没有此标签")
}