@file:Suppress("NestedLambdaShadowedImplicitParameter")

package compiler.kotlin

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import compiler.semantic.*
import tools.Box
import tools.SideEffect
import tools.never
import java.io.File
import java.io.FileOutputStream
import kotlin.collections.forEach
typealias LineIndex = Box<Int>
context(nameMap : SourceMap,tree : ProjectAST,file : FileAST,symbols : SymbolTable<Pair<FileAST,TopAST>>,lineIndex : LineIndex)
fun ClassAST.check() = when(name) {
    int,dec,str,unit,function -> null
    else if Regex("Function\\d+").matches(name) -> null
    else -> this
}
context(nameMap : SourceMap,tree : ProjectAST,file : FileAST,symbols : SymbolTable<Pair<FileAST,TopAST>>,lineIndex : LineIndex)
fun FunctionAST.check() = when {
    annotations.any { it.name == "jvm_name" } -> null
    else -> this
}
context(nameMap : SourceMap,tree : ProjectAST,file : FileAST,symbols : SymbolTable<Pair<FileAST,TopAST>>,lineIndex : LineIndex)
fun VariableAST.check() : VariableAST? = this

@SideEffect("io")
fun ProjectAST.generateKotlinProject(path : String) = with(map) nameMap@{
    with(SymbolTable<Pair<FileAST,TopAST>>()) symbols@ {
        with(false){
            with(LineIndex(1)){
                this@symbols.addAll(
                    this@generateKotlinProject.files
                        .associateWith { it.tops }
                        .flatMap { (file,tops) -> tops.map { file to it } }
                )
                val src = "$path/src"
                files.forEach { file ->
                    with(file) {
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
            }
        }
    }
    this
}
context(nameMap : SourceMap,tree : ProjectAST,file : FileAST,symbols : SymbolTable<Pair<FileAST,TopAST>>,lineIndex : LineIndex)
fun ClassAST.toKotlinCode() = TypeSpec
    .interfaceBuilder((file to this).mapName)
    .let {
        with(SymbolTable(parent = symbols)) symbols@{
            this@symbols.addAll(members.map { file to it })
            members.fold(it) { acc,member ->
                with(false){
                    when(member) {
                        is FunctionAST -> acc.addFunction(member.toKotlinCode())
                        is VariableAST -> acc.addProperty(member.toKotlinCode())
                    }.let {
                        if(member.annotations.any { it.name == "over" })
                            acc.addModifiers(KModifier.OVERRIDE)
                        else it
                    }
                }
            }
        }
    }.let {
        typeParameters.fold(it) { acc,par ->
            acc.addTypeVariable(par.toKotlinCode())
        }
    }
    .build()
context(nameMap : SourceMap,tree : ProjectAST,file : FileAST,symbols : SymbolTable<Pair<FileAST,TopAST>>,isLocal : Boolean,lineIndex : LineIndex)
fun FunctionAST.toKotlinCode() = run {
    if(isLocal) symbols += file to this
    with(SymbolTable(parent = symbols)) symbols@{
        FunSpec
            .builder((file to this@toKotlinCode).mapName)
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
            .let {
                typeParameters.fold(it) { it,ast ->
                    it.addTypeVariable(ast.toKotlinCode())
                }
            }
            .also { addAll(parameters.map { file to it }) }
            .let { Indexer(it,lineIndex.value) }
            .apply {
                body?.stmts
                    ?.dropLast(1)
                    ?.forEach { it.mapToKotlin() }
                body?.stmts
                    ?.lastOrNull()
                    ?.let { it.mapToKotlin(isReturn = true) }
            }
            .builder
            .returns(returnType.toKotlinCode())
            .build()
    }
}
context(nameMap : SourceMap,tree : ProjectAST,file : FileAST,symbols : SymbolTable<Pair<FileAST,TopAST>>,lineIndex : LineIndex)
fun TypeVariableAST.toKotlinCode() = TypeVariableName(
    name = name,
    bounds = listOf(bound.toKotlinCode())
)
context(nameMap : SourceMap,tree : ProjectAST,file : FileAST,symbols : SymbolTable<Pair<FileAST,TopAST>>,isLocal : Boolean,lineIndex : LineIndex,indexer : Indexer)
fun VariableAST.toKotlinCode() = PropertySpec
    .builder(
        name = (file to this).mapName,
        type = returnType.toKotlinCode(),
    )
    .initializer(value?.toKotlinCode()?.let { CodeBlock.of(it) })
    .mutable(annotations.any { it.name == "mut" })
    .build()
    .also { if(isLocal) symbols += file to this }
context(nameMap : SourceMap,tree : ProjectAST,file : FileAST,symbols : SymbolTable<Pair<FileAST,TopAST>>,lineIndex : LineIndex)
fun TypeAST.toKotlinCode() : TypeName = when(this) {
    is ApplyTypeAST     -> ClassName("",definition!!.mapName)
        .parameterizedBy(arguments.map { it.toKotlinCode() })
    is CommonTypeAST    -> ClassName("",definition!!.mapName)
    is FunctionTypeAST  -> LambdaTypeName.get(
        receiver   = null,
        parameters = parameters.map { it.toKotlinCode() }.toTypedArray(),
        returnType = returnType.toKotlinCode(),
    )
    is NullableTypeAST  -> type.toKotlinCode().copy(nullable = true)
    is TupleTypeAST     -> ClassName("","Tuple${arguments.size}")
    is IntersectionType -> never
}
context(nameMap : SourceMap,tree : ProjectAST,file : FileAST,symbols : SymbolTable<Pair<FileAST,TopAST>>,isLocal : Boolean,lineIndex : LineIndex,indexer : Indexer)
fun StatementAST.mapToKotlin(isReturn : Boolean = false) = when(this) {
    is ExpressionAST -> mapToKotlin(false)
    is TopAST        -> toKotlinCode()
}
context(nameMap : SourceMap,tree : ProjectAST,file : FileAST,symbols : SymbolTable<Pair<FileAST,TopAST>>,lineIndex : LineIndex,indexer : Indexer)
fun ExpressionAST.mapToKotlin(valuable : Boolean = true,isReturn : Boolean = false) = when(this) {
    is AnonymousObjectAST if parents.isEmpty() -> "object {${members.joinToString("\n") { (if(it.annotations.any { it.name == "over" }) "override " else "") + with(false){ it.toKotlinCode() } }}}"
    is DecimalConstantAST -> indexer.add(lineIndex.value,"$value")
    is IntegerConstantAST -> indexer.add(lineIndex.value,"$value")
    is StringConstantAST  -> indexer.add(lineIndex.value,"\"$value\"")
    is InvokeAST          -> mapToKotlin(isReturn)
    is LambdaAST          -> mapToKotlin(isReturn)
    is NameAST            -> mapToKotlin(isReturn)
    is AssignAST          -> mapToKotlin(isReturn)
    is AnonymousObjectAST -> "object : ${parents.joinToString(",") { it.toKotlinCode().toString() }} {\n${members.joinToString("\n") { (if(it.annotations.any { it.name == "over" }) "override " else "") + with(false){ it.toKotlinCode() } }}\n}"
}
context(nameMap : SourceMap,tree : ProjectAST,file : FileAST,symbols : SymbolTable<Pair<FileAST,TopAST>>,lineIndex : LineIndex,indexer : Indexer)
fun InvokeAST.mapToKotlin(isReturn : Boolean = false) {
    val `return` = if(isReturn) "return " else ""
    indexer.add(lineIndex.value,"(${invoker.mapToKotlin()}(${arguments.joinToString(",") { it.mapToKotlin() }}))")
}
context(nameMap : SourceMap,tree : ProjectAST,file : FileAST,symbols : SymbolTable<Pair<FileAST,TopAST>>,lineIndex : LineIndex,indexer : Indexer)
fun LambdaAST.mapToKotlin(isReturn : Boolean = false) {
    val `return` = if(isReturn) "return " else ""
    if(parameters.isEmpty())
        indexer
            .begin(lineIndex.value,`return`)
            .apply { with(true) { body.stmts.forEach { it.mapToKotlin(false) } } }
            .end(lineIndex.value)
    else
        indexer
            .begin(lineIndex.value,`return`)
            .add(lineIndex.value,parameters.joinToString(",") { "${it.name} : ${it.returnType.toKotlinCode()}" } + " ->")
            .apply { with(true) { body.stmts.forEach { it.mapToKotlin(false) } } }
            .end(lineIndex.value)
}
context(nameMap : SourceMap,tree : ProjectAST,file : FileAST,symbols : SymbolTable<Pair<FileAST,TopAST>>,lineIndex : LineIndex,indexer : Indexer)
fun AssignAST.mapToKotlin(isReturn : Boolean = false) {
    val `return` = if(isReturn) "return " else ""
    indexer.add(lineIndex.value,"$`return`${name.mapToKotlin()} = ${value.mapToKotlin()}")
}
context(nameMap : SourceMap,tree : ProjectAST,file : FileAST,symbols : SymbolTable<Pair<FileAST,TopAST>>,lineIndex : LineIndex,indexer : Indexer)
fun NameAST.mapToKotlin(isReturn : Boolean = false) {
    val `return` = if(isReturn) "return " else ""
    val main = if(expression != null)
        "${expression.mapToKotlin()}.${expression.type.definition!!.let { (file,ast) -> (file to ast.members.first { it.name == name }).mapName }}"
    else
        symbols.parents.flatMap { it }.filter { it.second !is ClassAST }.first { it.second.name == name }.mapName
    indexer.add(lineIndex.value,"$`return`$main")
}
context(nameMap : SourceMap,tree : ProjectAST,file : FileAST,symbols : SymbolTable<Pair<FileAST,TopAST>>,isLocal : Boolean)
fun TopAST.toKotlinCode() : String = when(this) {
    is FunctionAST     -> check()?.toKotlinCode()
    is VariableAST     -> check()?.toKotlinCode()
    is ClassAST        -> check()?.toKotlinCode()
    is TypeVariableAST -> TODO()
}?.toString() ?: ""