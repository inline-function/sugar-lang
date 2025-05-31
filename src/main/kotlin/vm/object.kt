package vm

import compiler.ir.sugar.TypeIR
import tools.ID
interface SugarObject{
    val type : TypeIR
    fun getField(field : ID) : SugarObject?
    override fun toString() : String
}
data class CommonSugarObject(
    override val type : TypeIR,
    val fields : MutableMap<ID,SugarObject>,
) : SugarObject{
    override fun getField(field : ID) : SugarObject? = fields[field]
}