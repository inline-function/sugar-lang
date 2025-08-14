@file:Suppress("NestedLambdaShadowedImplicitParameter")

package compiler.kotlin

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FunSpec
import compiler.semantic.ClassAST
import compiler.semantic.FunctionAST
import compiler.semantic.ProjectAST
import compiler.semantic.TopAST
import compiler.semantic.TypeVariableAST
import compiler.semantic.VariableAST
import tools.Block
import tools.ID
import tools.never
import compiler.kotlin.Name.NameType.*
import compiler.semantic.FileAST
import compiler.semantic.SymbolTable
import compiler.semantic.parents
import compiler.semantic.visit
import tools.exception

//生成本源地图
val ProjectAST.map : SourceMap
    get() = SourceMap(
        tree = this,
        nameMap = buildList {
            files.forEach { file ->
                file.visit {
                    val type = when(it) {
                        is ClassAST    -> CLASS
                        is VariableAST -> VARIABLE
                        is FunctionAST -> FUNCTION
                        else           -> null
                    }
                    type?.also { _ ->
                        val it = it as TopAST
                        this += file to Name(
                            tagType = type,
                            name = it.name,
                            line = it.line,
                            column = it.column,
                            file = file.name
                        )
                    }
                }
            }
        }.associate { (left,right) ->
            right to (left.name to right.name)
        }.toMutableMap(),
        fileMap = files
            .associate { it.name to it.name }
            .toMutableMap()
    )
//本源地图
data class SourceMap(
    val tree : ProjectAST,
    val nameMap : MutableMap<Name,Pair<ID,ID>>,
    val fileMap : MutableMap<ID,ID>,
) : MutableMap<Name,Pair<ID,ID>> by nameMap
//结果本源地图
data class ResultSourceMap(
    val tree : ProjectAST,
    val nameMap : Map<Name,Pair<ID,Name>>,
    val lineMap : Map<Pair<ID,Int>,Pair<ID,Int>>,
    val fileMap : Map<ID,ID>,
)
fun SourceMap.result(
    nameMap : Map<Name,Pair<ID,Name>>,
    lineMap : Map<Pair<ID,Int>,Pair<ID,Int>>,
    fileMap : Map<ID,ID>
) = ResultSourceMap(
    tree = tree,
    nameMap = nameMap,
    lineMap = lineMap,
    fileMap = fileMap,
)
//索引器
data class Indexer(
    var index : Int,
    var codeBlock : CodeBlock.Builder = CodeBlock.builder(),
    val map : MutableMap<Int,Int> = mutableMapOf(),
)
fun Indexer.begin(line : Int,code : Any,vararg args : Any) = apply {
    codeBlock = codeBlock.beginControlFlow(code.toString(),*args)
    index += 1
    map[line] = index
}
fun Indexer.end(line : Int) = apply {
    codeBlock.endControlFlow()
    index += 1
    map[line] = index
}
fun Indexer.add(line : Int,code : Any,vararg args : Any) = apply {
    codeBlock = codeBlock.addStatement(code.toString(),*args)
    index += code.toString().lines().size
    map[line] = index
}
val Indexer.result : CodeBlock
    get() = codeBlock.build()
//名字
data class Name(
    val tagType : NameType,
    val name : ID,
    val line : Int,
    val column : Int,
    val file : ID,
) {
    enum class NameType {
        CLASS,
        FUNCTION,
        VARIABLE,
    }
}
//从名称获取实际语法树
context(project : ProjectAST)
val Name.ast : TopAST?
    get() = project.files
        .find { it.name == file }
        ?.tops
        ?.find { it.line == line && it.column == column }
//从抽象语法树获取名称
@get:JvmName("theName1")
val Pair<FileAST,VariableAST>.theName : Name
    get() = second.run{
        Name(
            tagType = VARIABLE,
            name = name,
            line = line,
            column = column,
            file = first.name
        )
    }
@get:JvmName("theName2")
val Pair<FileAST,FunctionAST>.theName : Name
    get() = second.run{
        Name(
            tagType = FUNCTION,
            name = name,
            line = line,
            column = column,
            file = first.name
        )
    }
@get:JvmName("theName3")
val Pair<FileAST,ClassAST>.theName : Name
    get() = second.run{
        Name(
            tagType = CLASS,
            name = name,
            line = line,
            column = column,
            file = first.name
        )
    }
context(nameMap : SourceMap,symbols : SymbolTable<Pair<FileAST,TopAST>>)
val Pair<FileAST,TopAST>.mapName : ID
    get() = nameMap[theName]?.second ?: exception(theName)
//context(nameMap : SourceMap,symbols : SymbolTable<Pair<FileAST,TopAST>>)
//val TopAST.mapName : ID
//    get() = symbols.parents
//        .flatMap { it.symbols }
//        .firstOrNull { (_,ast) -> ast == this }
//        .let { it ?:
//        TODO()}
//        .run { first to second }
//        .mapName
val Pair<FileAST,TopAST>.theName : Name
    get() = when(val second = second){
        is FunctionAST     -> (first to second).theName
        is VariableAST     -> (first to second).theName
        is ClassAST        -> (first to second).theName
        is TypeVariableAST -> never
    }