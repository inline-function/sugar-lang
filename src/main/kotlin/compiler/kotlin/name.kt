package compiler.kotlin

import compiler.semantic.*
import tools.ID

typealias NameMap = MutableMap<TopAST,ID>
val ProjectAST.nameMap : NameMap
    get() = files
        .flatMap { it.tops }
        .associateWith { it.name }
        .toMutableMap()
context(nameMap : NameMap)
val TopAST.id : ID
    get() = nameMap[this] ?: name
context(nameMap : NameMap)
val ID.fid : ID
    get() = nameMap
        .toList()
        .find { (ast,_) -> ast is FunctionAST && ast.name == this }
        ?.second ?: this
context(nameMap : NameMap)
val ID.vid : ID
    get() = nameMap
        .toList()
        .find { (ast,_) -> ast is VariableAST && ast.name == this }
        ?.second ?: this
context(nameMap : NameMap)
val ID.fvid : ID
    get() = nameMap
        .toList()
        .find { (ast,_) -> ast is CallableAST && ast.name == this }
        ?.second ?: this
context(nameMap : NameMap)
val ID.cid : ID
    get() = nameMap
        .toList()
        .find { (ast,_) -> ast is ClassAST && ast.name == this }
        ?.second ?: this