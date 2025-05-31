@file:Suppress("NestedLambdaShadowedImplicitParameter")

package compiler.ir.sugar

import compiler.semantic.*
import tools.ID
import tools.never
import java.util.function.IntFunction

typealias IRS = List<DeclarationIR>
@Suppress("JavaDefaultMethodsNotOverriddenByDelegation")
data class IRCollector(
    val list : MutableList<SugarRunnableIR> = mutableListOf(),
    var index : Int = 0,
) : MutableList<SugarRunnableIR> by list{
    val tempVarName : ID
        get() = "$${index++}"
}
fun ProjectTree.toIrs() : IRS =
    mutableListOf<DeclarationIR>().apply {
        files.forEach { file ->
            with(file) {
                file.tops.forEach { top ->
                    this@apply += when(top) {
                        is FunctionTree -> top.ir
                        is VariableTree -> top.ir
                        is ClassTree    -> top.ir
                    }
                }
            }
        }
    }
fun IRCollector.irOf(stmt : StringConstantTree) = tempVarName.also {
    add(PutStringConstantValueIR(stmt.value,it))
}
fun IRCollector.irOf(stmt : IntegerConstantTree) = tempVarName.also {
    add(PutIntConstantValueIR(stmt.value,it))
}
fun IRCollector.irOf(stmt : DecimalConstantTree) = tempVarName.also {
    add(PutDecConstantValueIR(stmt.value,it))
}
fun IRCollector.irOf(stmt : NameTree) : ID = stmt.expression?.run {
    tempVarName.also {
        add(GetMemberIR(irOf(this),stmt.name,it))
    }
} ?: stmt.name
fun IRCollector.irOf(stmt : InvokeTree) : ID = tempVarName.also {
    add(InvokeFunctionIR(irOf(stmt.invoker),stmt.arguments.map { irOf(it) },it))
}
fun IRCollector.irOf(stmt : ExpressionTree) : ID =
    when(stmt){
        is DecimalConstantTree -> irOf(stmt)
        is IntegerConstantTree -> irOf(stmt)
        is StringConstantTree  -> irOf(stmt)
        is InvokeTree          -> irOf(stmt)
        is LambdaTree          -> TODO()
        is NameTree            -> irOf(stmt)
    }
fun IRCollector.irOf(stmt : VariableTree) {
    add(CopyIR(irOf(stmt.value!!),stmt.name))
}
val BlockTree.ir : List<SugarRunnableIR>
    get() = IRCollector().apply {
        stmts.forEach { stmt ->
            when(stmt){
                is InvokeTree          -> irOf(stmt)
                is VariableTree        -> irOf(stmt)
                else -> TODO()
            }
        }
    }
context(irs : MutableList<DeclarationIR>,file : FileTree)
val ClassTree.ir : ClassDecIR
    get() = ClassDecIR(
        name = this.name,
        anns = this.annotations.ir,
        file = file.name,
        line = this.line,
        typeParams = this.typeParameters,
        parents = this.parents.map { it.ir }
    ).also {
        this@ir.members.forEach { member ->
            irs += when(member) {
                is FunctionTree -> with(member) {
                    FunctionDecIR(
                        name = this.name,
                        anns = this.annotations.ir,
                        type = this.returnType.ir,
                        host = this@ir.name,
                        typeParams = this.typeParameters,
                        file = file.name,
                        line = this.line,
                        params = this.parameters.associate { it.name to it.returnType!!.ir },
                        body = this.body?.ir ?: emptyList()
                    )
                }
                is VariableTree -> with(member){
                    VariableDecIR(
                        name = this.name,
                        type = this.returnType!!.ir,
                        mutable = this.isMutable,
                        line = this.line,
                        host = this@ir.name,
                        file = file.name,
                        anns = this.annotations.ir,
                    )
                }
            }
        }
    }
context(file : FileTree)
val FunctionTree.ir : FunctionDecIR
    get() = FunctionDecIR(
        name = this.name,
        anns = this.annotations.ir,
        type = this.returnType.ir,
        host = null,
        typeParams = this.typeParameters,
        file = file.name,
        line = this.line,
        params = this.parameters.associate { it.name to it.returnType!!.ir },
        body = this.body?.ir ?: emptyList()
    )
context(file : FileTree)
val VariableTree.ir : VariableDecIR
    get() = VariableDecIR(
        name = this.name,
        type = this.returnType?.ir ?: unit_type_ir,
        mutable = this.isMutable,
        line = this.line,
        host = null,
        file = file.name,
        anns = this.annotations.ir,
    )
val TypeTree.ir : TypeIR
    get() = when (this) {
        is CommonTypeTree   -> TODO()
        is NullableTypeTree -> TODO()
        is ApplyTypeTree    -> TODO()
        is FunctionTypeTree -> TODO()
    }
val List<AnnotationTree>.ir : List<AnnotationIR>
    get() = map {
        AnnotationIR(
            name = it.name,
            value = when(it.arguments){
                is DecimalConstantTree -> AnnotationValue.DEC
                is IntegerConstantTree -> AnnotationValue.INT
                is StringConstantTree  -> AnnotationValue.STR
                else -> never
            },
            argument = it.arguments.toString(),
            line = it.line
        )
    }