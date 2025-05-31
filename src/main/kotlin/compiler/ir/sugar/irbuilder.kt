@file:Suppress("FunctionName")

package compiler.ir.sugar

import tools.ID

@JvmInline
value class RunnableIRList(val list : MutableList<SugarRunnableIR> = mutableListOf())
fun buildRIR(block : RunnableIRList.() -> Unit) =
    RunnableIRList().also(block).list
context(irs : RunnableIRList)
infix fun ID.put(value : Double) =
    irs.list.add(PutDecConstantValueIR(value,this))
context(irs : RunnableIRList)
infix fun ID.put(value : Int) =
    irs.list.add(PutIntConstantValueIR(value,this))
context(irs : RunnableIRList)
infix fun ID.put(value : String) =
    irs.list.add(PutStringConstantValueIR(value,this))
context(irs : RunnableIRList)
infix fun ID.moveTo(to : ID) =
    irs.list.add(MoveIR(this,to))
context(irs : RunnableIRList)
infix fun ID.copyTo(to : ID) =
    irs.list.add(CopyIR(this,to))
context(irs : RunnableIRList)
fun tag(line : Int) =
    irs.list.add(TagIR(line))
context(irs : RunnableIRList)
fun goto(line : Int) =
    irs.list.add(GoToIR(line))
context(irs : RunnableIRList)
operator fun ID.invoke(vararg args : ID) =
    irs.list.add(InvokeFunctionIR(this,args.toList()))