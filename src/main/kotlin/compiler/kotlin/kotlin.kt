@file:Suppress("NestedLambdaShadowedImplicitParameter")

package compiler.kotlin

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import compiler.semantic.*
import tools.SideEffect
import java.io.File
import java.io.FileOutputStream
import kotlin.collections.forEach

context(nameMap : NameMap)
fun ClassAST.check() = when(name) {
    int,dec,str,unit -> null
    else if Regex("Function\\d+").matches(name) -> null
    else -> this
}
context(nameMap : NameMap)
fun FunctionAST.check() = when {
    annotations.any { it.name == "jvm_name" } -> null
    else -> this
}
context(nameMap : NameMap)
fun VariableAST.check() : VariableAST? = this

@SideEffect("io")
fun ProjectAST.generateKotlinProject(path : String) = with(nameMap) nameMap@{
    this@nameMap.forEach { (ast,name) ->
        this@nameMap[ast] = name
            .takeUnless { ast.annotations.any { it.name == "jvm_name" } }
            ?: when(val argument = ast.annotations.find { it.name == "jvm_name" }!!.arguments){
                is StringConstantAST if argument.value.split(":").size == 1 -> argument.value
                is StringConstantAST -> argument.value.split(":").let { "${it[0]}.${it[1]}" }
                else -> TODO()
            }
    }
    val src = "$path/src"
    files.forEach { file ->
        FileOutputStream(File("$src/${file.name}.kts").also(File::createNewFile)).use { stream ->
            file.tops.forEach {
                it.toKotlinCode()
                    .toByteArray()
                    .takeUnless { it.isEmpty() }
                    ?.let(stream::write)
            }
        }
    }
}
context(nameMap : NameMap)
fun ClassAST.toKotlinCode() = TypeSpec
    .interfaceBuilder(id)
    .let {
        members.fold(it) { acc, member ->
            when (member) {
                is FunctionAST -> acc.addFunction(member.toKotlinCode())
                is VariableAST -> acc.addProperty(member.toKotlinCode())
            }
        }
    }
    .build()
context(nameMap : NameMap)
fun FunctionAST.toKotlinCode() = FunSpec
    .builder(id)
    .let { builder ->
        parameters.fold(builder) { acc,it ->
            acc.addParameter(
                ParameterSpec(
                    name = it.name,
                    type = it.returnType.toKotlinCode(),
                )
            )
        }
    }
    .addCode(body?.stmts?.joinToString("\n") { it.toKotlinCode() } ?: "")
    .returns(returnType.toKotlinCode())
    .build()
context(nameMap : NameMap)
fun VariableAST.toKotlinCode() = PropertySpec
    .builder(
        name = id,
        type = returnType.toKotlinCode(),
    )
    .initializer(value?.toKotlinCode()?.let { CodeBlock.of(it) })
    .mutable(annotations.any { it.name == "mut" })
    .build()
context(nameMap : NameMap)
fun TypeAST.toKotlinCode() : TypeName = when(this) {
    is ApplyTypeAST    -> ClassName("",name.cid)
        .parameterizedBy(arguments.map { it.toKotlinCode() })
    is CommonTypeAST   -> ClassName("",name.cid)
    is FunctionTypeAST -> LambdaTypeName.get(
        receiver   = null,
        parameters = parameters.map { it.toKotlinCode() }.toTypedArray(),
        returnType = returnType.toKotlinCode(),
    )
    is NullableTypeAST -> type.toKotlinCode().copy(nullable = true)
    is TupleTypeAST    -> ClassName("","Tuple${arguments.size}")
}
context(nameMap : NameMap)
fun StatementAST.toKotlinCode() : String = when(this) {
    is ExpressionAST -> toKotlinCode()
    is TopAST        -> toKotlinCode()
}
context(nameMap : NameMap)
fun ExpressionAST.toKotlinCode() : String = when(this) {
    is LambdaAST if parameters.isEmpty() -> "{\n${body.stmts.joinToString("\n") { it.toKotlinCode() }}\n}"
    is NameAST   if expression != null   -> "${expression.toKotlinCode()}.${name.fvid}"
    is DecimalConstantAST -> value
    is IntegerConstantAST -> value
    is StringConstantAST  -> "\"$value\""
    is InvokeAST          -> "(${invoker.toKotlinCode()}(${arguments.joinToString(",") { it.toKotlinCode() }}))"
    is LambdaAST          -> "${parameters.joinToString(",") { "${it.name} : ${it.returnType.toKotlinCode()}" }} -> {\n${body.stmts.joinToString("\n") { it.toKotlinCode() }}\n}"
    is NameAST            -> name.fvid
    is AssignAST          -> "${name.toKotlinCode()} = ${value.toKotlinCode()}"
}.toString()
context(nameMap : NameMap)
fun TopAST.toKotlinCode() : String = when(this) {
    is FunctionAST     -> check()?.toKotlinCode()
    is VariableAST     -> check()?.toKotlinCode()
    is ClassAST        -> check()?.toKotlinCode()
    is TypeVariableAST -> TODO()
}?.toString() ?: ""