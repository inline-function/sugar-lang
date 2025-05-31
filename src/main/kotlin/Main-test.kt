import compiler.ir.sugar.AnnotationIR
import compiler.ir.sugar.FunctionDecIR
import compiler.ir.sugar.InvokeFunctionIR
import compiler.ir.sugar.PutStringConstantValueIR
import compiler.ir.sugar.TypeIR
import compiler.ir.sugar.buildRIR
import compiler.ir.sugar.goto
import compiler.ir.sugar.invoke
import compiler.ir.sugar.put
import compiler.ir.sugar.tag
import compiler.semantic.AnnotationValue
import vm.Context
import vm.Environment
import vm.SugarObject
import vm.VMThread
import vm.VirtualMachine
import vm.start
object Core {
    @JvmStatic
    fun print(text : SugarObject) {
        println(text)
    }
}
val mainFunctionBody = buildRIR {
    goto(1)
    "text" put "World!"
    "print"("text")
    tag(1)
    "text" put "Hello!"
    "print"("text")
}
fun main() {
    val mainFunction = FunctionDecIR(
        name = "main",
        anns = emptyList(),
        type = TypeIR("Unit"),
        host = null,
        typeParams = emptyList(),
        file = "say hello to the world",
        line = 10,
        params = emptyList()
    ) to mainFunctionBody
    val vm = VirtualMachine(
        environment = Environment(),
        context = Context(
            functions = mutableListOf(
                FunctionDecIR(
                    name = "print",
                    anns = listOf(
                        AnnotationIR(
                            name = "JvmFunction",
                            value = AnnotationValue.STR,
                            argument = "Core::print::1",
                            line = 1
                        )
                    ),
                    type = TypeIR("Unit"),
                    host = null,
                    typeParams = emptyList(),
                    file = "say hello to the world",
                    line = 2,
                    params = listOf(
                        TypeIR(
                            name = "Str",
                            typeParams = emptyList()
                        )
                    ),
                ) to emptyList(),
                mainFunction
            )
        ),
        mainThread = VMThread(
            command = mainFunction.second,
        )
    )
    vm.start()
    vm.mainThread.thread.join()
}