package compiler.kotlin

import compiler.semantic.AnnotationAST
import compiler.semantic.ApplyTypeAST
import compiler.semantic.CallableTag
import compiler.semantic.ClassAST
import compiler.semantic.CommonTypeAST
import compiler.semantic.FileAST
import compiler.semantic.FunctionTypeAST
import compiler.semantic.IntersectionType
import compiler.semantic.NullableTypeAST
import compiler.semantic.SymbolTable
import compiler.semantic.TopAST
import compiler.semantic.TupleTypeAST
import compiler.semantic.TypeAST
import compiler.semantic.TypeVarTag
import compiler.semantic.TypeVariableAST
import compiler.semantic.function
import compiler.semantic.parents
import compiler.semantic.specialization
import compiler.semantic.tuple
import tools.ID
import tools.never

context(symbols : SymbolTable<Pair<FileAST,TopAST>>)
val CommonTypeAST.definition : Pair<FileAST,ClassAST>?
    get() = symbols.parents
        .flatMap { it.symbols }
        .find { (_,it) -> it is ClassAST && it.name == name }
        .run { (this?.second as? ClassAST)?.let { first to it } }

context(symbols : SymbolTable<Pair<FileAST,TopAST>>)
val TupleTypeAST.definition : Pair<FileAST,ClassAST>?
    get() = symbols.parents
        .flatMap { it.symbols }
        .find { (_,it) -> it is ClassAST && it.name == "${tuple}${arguments.size}" }
        .run { (this?.second as? ClassAST)?.let { first to it } }

context(symbols : SymbolTable<Pair<FileAST,TopAST>>)
val FunctionTypeAST.definition : Pair<FileAST,ClassAST>?
    get() = symbols.parents
        .flatMap { it.symbols }
        .find { (_,it) -> it is ClassAST && it.name == "${function}${parameters.size}" }
        .run { (this?.second as? ClassAST)?.let { first to it } }

context(symbols : SymbolTable<Pair<FileAST,TopAST>>)
val NullableTypeAST.definition : Pair<FileAST,ClassAST>?
    get() = type.definition

context(symbols : SymbolTable<Pair<FileAST,TopAST>>)
val ApplyTypeAST.definition : Pair<FileAST,ClassAST>?
    get() = symbols.parents
        .flatMap { it.symbols }
        .find { (_,it) -> it is ClassAST && it.name == name }
        .run { (this?.second as? ClassAST)?.let { first to it } }
context(symbols : SymbolTable<Pair<FileAST,TopAST>>)
val TypeAST.definition : Pair<FileAST,ClassAST>?
    get() = when(this){
        is ApplyTypeAST     -> definition
        is CommonTypeAST    -> definition
        is FunctionTypeAST  -> definition
        is NullableTypeAST  -> definition
        is TupleTypeAST     -> definition
        is IntersectionType -> TODO()
    }