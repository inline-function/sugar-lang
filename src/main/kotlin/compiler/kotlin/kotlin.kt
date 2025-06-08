package compiler.kotlin

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.LambdaTypeName
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.ParameterizedTypeName
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.TypeSpec
import compiler.semantic.*
import tools.SideEffect
import java.io.*

fun ClassTree.check() = when(name){
    "Int","Dec","Str","Unit","Bool","Function" -> null
    else if Regex("Function\\d+").matches(name) -> null
    else -> this
}
fun FunctionTree.check() = when {
    annotations.any { it.name == "jvm_function" } -> null
    else -> this
}
external fun VariableTree.check() : VariableTree?
@SideEffect("io")
fun ProjectTree.generateKotlinProject(path : String) {
    val src = "$path/src"
    files.forEach { file ->
        FileOutputStream(File("$src/${file.name}.kts").also(File::createNewFile)).use { stream ->
            file.tops.forEach {
                it.toKotlinCode()
                    .let(String::toByteArray)
                    .let(stream::write)
            }
        }
    }
}
fun ClassTree.toKotlinCode() = TypeSpec
    .interfaceBuilder(name)
    .let {
        members.fold(it) { acc, member ->
            when (member) {
                is FunctionTree -> acc.addFunction(member.toKotlinCode())
                is VariableTree -> acc.addProperty(member.toKotlinCode())
            }
        }
    }
    .build()
fun FunctionTree.toKotlinCode() = FunSpec
    .builder(name)
    .let { builder ->
        parameters.fold(builder) { acc,it ->
            acc.addParameter(
                ParameterSpec(
                    name = it.name,
                    type = it.type.toKotlinCode(),
                )
            )
        }
    }
    .addCode(body?.joinToString("\n") { it.toKotlinCode() } ?: "")
    .returns(returnType.toKotlinCode())
    .build()
fun VariableTree.toKotlinCode() = PropertySpec
    .builder(
        name = "",
        type = type.toKotlinCode(),
    )
    .mutable(isMutable)
    .build()
fun TypeTree.toKotlinCode() : TypeName = when(this){
    is ApplyTypeTree    -> ClassName("",name)
        .parameterizedBy(arguments.map(TypeTree::toKotlinCode))
    is CommonTypeTree   -> ClassName("",name)
    is FunctionTypeTree -> LambdaTypeName.get(
        receiver   = null,
        parameters = parameters.map(TypeTree::toKotlinCode).toTypedArray(),
        returnType = returnType.toKotlinCode(),
    )
    is NullableTypeTree -> type.toKotlinCode().copy(nullable = true)
}
fun StatementTree.toKotlinCode() : String = when(this){
    is ExpressionTree -> toKotlinCode()
    is TopTree        -> toKotlinCode()
}
fun ExpressionTree.toKotlinCode() : String = when(this){
    is LambdaTree if parameters.isEmpty() -> "{\n${body.joinToString("\n") { it.toKotlinCode() }}\n}"
    is NameTree   if expression != null   -> "${expression.toKotlinCode()}.$name"
    is DecimalConstantTree -> value
    is IntegerConstantTree -> value
    is StringConstantTree  -> "\"$value\""
    is InvokeTree          -> "(${invoker.toKotlinCode()}(${arguments.joinToString(",") { it.toKotlinCode() }}))"
    is LambdaTree          -> "${parameters.joinToString(",") { "${it.name} : ${it.type.toKotlinCode()}" }} -> {\n${body.joinToString("\n") { it.toKotlinCode() }}\n}"
    is NameTree            -> name
}.toString()
fun TopTree.toKotlinCode() : String = when(this){
    is FunctionTree -> check()?.toKotlinCode()
    is VariableTree -> check()?.toKotlinCode()
    is ClassTree    -> check()?.toKotlinCode()
}?.toString() ?: ""