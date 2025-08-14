@file:Suppress("NestedLambdaShadowedImplicitParameter")

package compiler.kotlin

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import compiler.semantic.*
import compiler.semantic.FileAST
import compiler.semantic.ProjectAST
import tools.Box
import tools.ID
import tools.Pollutant
import tools.SideEffect
import tools.SideEffect.Type.*
import tools.context
import tools.never
import tools.plusAssign
import java.io.File
import java.io.FileOutputStream
import kotlin.collections.forEach
/*
 * 你将在下面见到很多上下文参数,以下是对这些上下文参数的解释:
 *  tree    : ProjectAST    : 表示当前操作的工程抽象语法树
 *  file    : FileAST       : 表示当前操作的文件抽象语法树
 *  map     : SourceMap     : 表示整个工程的本源地图
 *  symbols : KtSymbolTable : 表示符号表
 *  index   : LineIndex     : 表示当前文件的行索引
 *  indexer : Indexer       : 表示当前代码块的索引器
 * 其中可能会有有些上下文参数标注为@Pollutant,这表示上下文参数会被函数污染
 */
typealias LineIndex = Box<Int>
typealias KtSymbolTable = SymbolTable<Pair<FileAST,TopAST>>
//生成kt工程
@SideEffect(IO)
fun ProjectAST.generateKotlinProject(path : String) : ResultSourceMap {
    //符号表
    val symbols = KtSymbolTable()
    //本源地图
    val map = map
    //生成路径
    val src = "$path/src"
    //正式名称地图
    val nameMap = mutableMapOf<Name,Pair<ID,Name>>()
    //为符号表添加所有顶层成员
    symbols.addAll(
        this@generateKotlinProject.files
            .associateWith { it.tops }
            .flatMap { (file,tops) -> tops.map { file to it } }
    )
    //为每个文件生成文件
    files.forEach { file ->
        //获取文件的Kt文件
        val file = context(map,this,symbols,nameMap){ _,_,_,_ ->
            file.toKotlinCode()
        }
        //写出到文件
        FileOutputStream(
            File("$src/${file.name}.kts").also(File::createNewFile)
        ).use { stream -> file
            .toString()
            .toByteArray()
            .takeUnless { it.isEmpty() }
            ?.let(stream::write)
        }
    }
    //合并为正式本源地图
    return map.result(
        nameMap = nameMap,
        lineMap = ,
        fileMap = TODO(),
    )
}
context(
    tree : ProjectAST,
    @Pollutant map : SourceMap,
    @Pollutant names : MutableMap<Name,Pair<ID,Name>>,
    @Pollutant lines : MutableMap<Pair<ID,Int>,Pair<ID,Int>>,
    @Pollutant symbols : KtSymbolTable,
)
fun FileAST.toKotlinCode() : FileSpec {
    val index = LineIndex(0)
    val file = FileSpec.builder(
        packageName = "sugar.lang",
        fileName = name
    )
    index += file
    index += 1
    val map = mutableMapOf<Name,Name>()
    val result = tops.fold(file) { acc,ast ->
        context(index,this,map){ _,_,_ ->
            when(ast) {
                is FunctionAST     -> acc.addFunction(ast.toKotlinCode())
                is VariableAST     -> acc.addProperty(
                    ast.toKotlinCode().let { (variable,map) ->
                        map.forEach { (old,new) ->
                            lines[name to old] = name to new
                        }
                        variable
                    }
                )
                is ClassAST        -> acc.addType(ast.toKotlinCode())
                is TypeVariableAST -> never
            }
        }
    }.build()
    map.forEach { (old,new) ->
        names[old] = name to new
    }
    return result
}
context(
    tree : ProjectAST,
    file : FileAST,
    @Pollutant map : SourceMap,
    @Pollutant names : MutableMap<Name,Name>,
    @Pollutant symbols : KtSymbolTable,
    @Pollutant index : LineIndex,
)
fun ClassAST.toKotlinCode() : TypeSpec = context(KtSymbolTable(parent = symbols)){ symbols ->
    symbols.addAll(members.map { file to it })
    val cls = TypeSpec.interfaceBuilder(
        name = name
    )
    index += 1
    val clsWithMember = members
        .fold(cls) { acc,member ->
            when(member) {
                is FunctionAST -> acc.addFunction(member.toKotlinCode())
                is VariableAST -> acc.addProperty(member.toKotlinCode())
            }
        }
    val clsWithTypeArg = typeParameters
        .fold(clsWithMember) { acc,par ->
            acc.addTypeVariable(par.toKotlinCode())
        }
    val clsWithParents = parents
        .fold(clsWithTypeArg) { acc,parent ->
            acc.addSuperinterface(parent.toKotlinCode())
        }
    index += clsWithParents
    clsWithParents.build()
}
context(
    tree : ProjectAST,
    file : FileAST,
    @Pollutant map : SourceMap,
    @Pollutant names : MutableMap<Name,Name>,
    @Pollutant symbols : KtSymbolTable,
    @Pollutant index : LineIndex,
)
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
context(
    tree : ProjectAST,
    file : FileAST,
    @Pollutant map : SourceMap,
    @Pollutant names : MutableMap<Name,Name>,
    @Pollutant symbols : KtSymbolTable,
    @Pollutant index : LineIndex,
)
fun TypeVariableAST.toKotlinCode() : TypeVariableName =
    TypeVariableName(
        name = name,
        variance = when {
            isCovariant     -> KModifier.OUT
            isContravariant -> KModifier.IN
            else            -> null
        },
        bounds = listOf(bound.toKotlinCode())
    )
context(
    tree : ProjectAST,
    file : FileAST,
    @Pollutant map : SourceMap,
    @Pollutant names : MutableMap<Name,Name>,
    @Pollutant symbols : KtSymbolTable,
    @Pollutant index : LineIndex,
)
fun VariableAST.toKotlinCode() : Pair<PropertySpec,Map<Int,Int>> {
    val variable = PropertySpec.builder(
        name = name,
        type = returnType.toKotlinCode(),
    )
    val varWithReceiver = receiver
        ?.let { variable.receiver(it.toKotlinCode()) }
        ?: variable
    val varWithSetter = setter
        ?.let { varWithReceiver.setter(it.toKotlinCode()) }
        ?: varWithReceiver
    val varWithGetter = getter
        ?.let { varWithSetter.getter(it.toKotlinCode()) }
        ?: varWithSetter
    val (varWithValue,lineMap) = value?.let {
        Indexer(index.value)
            .apply { it.mapToKotlin() }
            .run { varWithGetter.initializer(result) to this.map }
    } ?: (varWithGetter to emptyMap<Int,Int>())
    return varWithValue.build() to lineMap
}
context(
    tree : ProjectAST,
    file : FileAST,
    @Pollutant map : SourceMap,
    @Pollutant names : MutableMap<Name,Name>,
    @Pollutant symbols : KtSymbolTable,
    @Pollutant index : LineIndex,
)
fun VariableAST.toParameter() : ParameterSpec {
    val parameter = ParameterSpec.builder(
        name = name,
        type = returnType.toKotlinCode(),
    )
    val parWithValue = value?.let {
        parameter.defaultValue(
            Indexer(index.value)
                .apply { it.mapToKotlin() }
                .also { index += it }
                .result
        )
    } ?: parameter
    return parWithValue.build()
}
context(
    tree : ProjectAST,
    file : FileAST,
    @Pollutant map : SourceMap,
    @Pollutant names : MutableMap<Name,Name>,
    @Pollutant symbols : KtSymbolTable,
    @Pollutant index : LineIndex,
)
fun FunctionAST.toKotlinCode() : FunSpec {
    val function = FunSpec.builder(
        name = name
    )
    val funWithReturnType = function.returns(returnType.toKotlinCode())
    val funWithParameters = parameters
        .fold(funWithReturnType) { acc,par -> acc.addParameter(par.toParameter()) }
    val funWithTypeParameters = typeParameters
        .fold(funWithParameters) { acc,par -> acc.addTypeVariable(par.toKotlinCode()) }
    index += 1
    val funWithBody = body?.let {
        funWithTypeParameters.addCode(
            Indexer(index.value)
                .apply { it.mapToKotlin() }
                .also { index += it }
                .result
        )
    } ?: funWithTypeParameters
    return funWithBody.build()
}
context(
    tree : ProjectAST,
    file : FileAST,
    @Pollutant map : SourceMap,
    @Pollutant names : MutableMap<Name,Name>,
    @Pollutant symbols : KtSymbolTable,
    index : LineIndex,
    @Pollutant indexer : Indexer,
)
fun BodyAST.mapToKotlin() : Unit = context(KtSymbolTable(parent = symbols)){ symbols ->
    stmts.forEach {
        when(it) {
            is AnonymousObjectAST -> it.mapToKotlin()
            is AssignAST          -> it.mapToKotlin()
            is DecimalConstantAST -> it.mapToKotlin()
            is IntegerConstantAST -> it.mapToKotlin()
            is StringConstantAST  -> it.mapToKotlin()
            is InvokeAST          -> it.mapToKotlin()
            is LambdaAST          -> it.mapToKotlin()
            is NameAST            -> it.mapToKotlin()
            is TopAST             -> when(it){
                is FunctionAST     -> {
                    val ast = it.toKotlinCode()
                    symbols.add(file to it)
                    indexer.add(it.line,"%L",ast)
                }
                is VariableAST     -> {
                    val ast = it.toKotlinCode()
                    indexer.add(it.line,"%L",ast)
                    symbols.add(file to it)
                }
                is ClassAST        -> {
                    val ast = it.toKotlinCode()
                    symbols.add(file to it)
                    indexer.add(it.line,"%L",ast)
                }
                is TypeVariableAST -> never
            }
        }
    }
}
context(
    tree : ProjectAST,
    file : FileAST,
    @Pollutant map : SourceMap,
    @Pollutant names : MutableMap<Name,Name>,
    @Pollutant symbols : KtSymbolTable,
    index : LineIndex,
    indexer : Indexer,
)
fun ExpressionAST.toKotlinCode() : String = when(this) {
    is AnonymousObjectAST -> toKotlinCode()
    is AssignAST          -> toKotlinCode()
    is DecimalConstantAST -> toKotlinCode()
    is IntegerConstantAST -> toKotlinCode()
    is StringConstantAST  -> toKotlinCode()
    is InvokeAST          -> toKotlinCode()
    is LambdaAST          -> toKotlinCode()
    is NameAST            -> toKotlinCode()
}.toString()
context(
    tree : ProjectAST,
    file : FileAST,
    @Pollutant map : SourceMap,
    @Pollutant names : MutableMap<Name,Name>,
    @Pollutant symbols : KtSymbolTable,
    index : LineIndex,
    @Pollutant indexer : Indexer,
)
fun AnonymousObjectAST.mapToKotlin() {
    val anonymous = TypeSpec.anonymousClassBuilder()
    val anonymousWithParents = parents.fold(anonymous) { acc,parent ->
        acc.addSuperinterface(parent.toKotlinCode())
    }
    val anonymousWithMembers = members.fold(anonymousWithParents) { acc,member ->
        when(member) {
            is FunctionAST -> acc.addFunction(member.toKotlinCode())
            is VariableAST -> acc.addProperty(member.toKotlinCode())
        }
    }
    indexer.add(line,"%L",anonymousWithMembers.build())
}
context(
    tree : ProjectAST,
    file : FileAST,
    @Pollutant map : SourceMap,
    @Pollutant names : MutableMap<Name,Name>,
    @Pollutant symbols : KtSymbolTable,
    index : LineIndex,
    indexer : Indexer,
)
fun AnonymousObjectAST.toKotlinCode() : TypeSpec {
    val anonymous = TypeSpec.anonymousClassBuilder()
    val anonymousWithParents = parents.fold(anonymous) { acc,parent ->
        acc.addSuperinterface(parent.toKotlinCode())
    }
    index += 1
    val anonymousWithMembers = members.fold(anonymousWithParents) { acc,member ->
        when(member) {
            is FunctionAST -> acc.addFunction(member.toKotlinCode())
            is VariableAST -> acc.addProperty(member.toKotlinCode())
        }
    }
    return anonymousWithMembers.build()
}
context(
    tree : ProjectAST,
    file : FileAST,
    @Pollutant map : SourceMap,
    @Pollutant names : MutableMap<Name,Name>,
    @Pollutant symbols : KtSymbolTable,
    index : LineIndex,
    @Pollutant indexer : Indexer,
)
fun InvokeAST.mapToKotlin() {
    val invoker = invoker.toKotlinCode()
    val args = arguments.joinToString(
        separator = ", ",
        prefix = "(",
        postfix = ")",
    ) { it.toKotlinCode() }
    val typeArgs = typeArguments
        .takeIf { it.isNotEmpty() }
        ?.joinToString(
            separator = ", ",
            prefix = "<",
            postfix = ">",
        ) { it.toKotlinCode().toString() }
        ?: ""
    indexer.add(line,"$invoker$typeArgs$args")
}
context(
    tree : ProjectAST,
    file : FileAST,
    @Pollutant map : SourceMap,
    @Pollutant names : MutableMap<Name,Name>,
    @Pollutant symbols : KtSymbolTable,
    index : LineIndex,
    indexer : Indexer,
)
fun InvokeAST.toKotlinCode() : String {
    val invoker = invoker.toKotlinCode()
    val args = arguments.joinToString(
        separator = ", ",
        prefix = "(",
        postfix = ")",
    ) { it.toKotlinCode() }
    val typeArgs = typeArguments
        .takeIf { it.isNotEmpty() }
        ?.joinToString(
            separator = ", ",
            prefix = "<",
            postfix = ">",
        ) { it.toKotlinCode().toString() }
        ?: ""
    return "$invoker$typeArgs$args"
}
context(
    tree : ProjectAST,
    file : FileAST,
    @Pollutant map : SourceMap,
    @Pollutant names : MutableMap<Name,Name>,
    @Pollutant symbols : KtSymbolTable,
    index : LineIndex,
    @Pollutant indexer : Indexer,
)
fun NameAST.mapToKotlin() {
    val expr = expression?.toKotlinCode()
    val name = expr
        ?.let { "$it.$name" }
        ?: name
    indexer.add(line,name)
}
context(
    tree : ProjectAST,
    file : FileAST,
    @Pollutant map : SourceMap,
    @Pollutant names : MutableMap<Name,Name>,
    @Pollutant symbols : KtSymbolTable,
    index : LineIndex,
    indexer : Indexer,
)
fun NameAST.toKotlinCode() : String {
    val expr = expression?.toKotlinCode()
    val name = expr
        ?.let { "$it.$name" }
        ?: name
    return name
}
/*
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


 */