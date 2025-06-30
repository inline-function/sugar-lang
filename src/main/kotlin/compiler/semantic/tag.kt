@file:Suppress("FunctionName")

package compiler.semantic

import compiler.parser.*
import tools.ID

typealias ResultBuilder<T> = context(MutableInformation,SymbolTable<Tag>) ()->T
sealed interface Tag {
    val name : ID
}
context(info : MutableInformation,symbols : SymbolTable<Tag>)
fun Tag(tree : TopTree) : Pair<Tag,ResultBuilder<TopAST>> = when(tree){
    is FunctionTree     -> FunctionTag(tree)
    is VariableTree     -> VariableTag(tree)
    is ClassTree        -> ClassTag(tree)
    is TypeVariableTree -> TODO()
}
sealed interface CallableTag : Tag {
    val returnType: TypeAST?
    override val name: ID
    val aboveContext: List<TypeAST>
    val belowContext: List<TypeAST>
    val annotations: List<AnnotationAST>
}
context(info : MutableInformation,symbols : SymbolTable<Tag>)
fun CallableTag(callable : CallableTree) = when(callable){
    is FunctionTree -> FunctionTag(callable)
    is VariableTree -> VariableTag(callable)
}
interface VariableTag : CallableTag {
    val receiver: TypeAST?
    val getter: FunctionTag?
    val setter: FunctionTag?
}
context(info : MutableInformation,symbols : SymbolTable<Tag>)
fun VariableTag(variable : VariableTree) : Pair<VariableTag,ResultBuilder<VariableAST>> =
    object : VariableTag {
        override val receiver : TypeAST?
            get() = variable.receiver?.transform()
        override val getter : FunctionTag?
            get() = variable.getter?.let { FunctionTag(it).first }
        override val setter : FunctionTag?
            get() = variable.setter?.let { FunctionTag(it).first }
        override val returnType : TypeAST?
            get() = variable.returnType?.transform()
        override val name : ID
            get() = variable.name
        override val aboveContext : List<TypeAST>
            get() = variable.aboveContext.map { it.transform() }
        override val belowContext : List<TypeAST>
            get() = variable.belowContext.map { it.transform() }
        override val annotations : List<AnnotationAST>
            get() = variable.annotations.map { it.transform() }
    } to { variable.transform() }
context(info : MutableInformation,symbols : SymbolTable<Tag>)
fun VariableTag(variable : VariableAST) : Pair<VariableTag,ResultBuilder<VariableAST>> =
    object : VariableTag {
        override val receiver : TypeAST?
            get() = variable.receiver
        override val getter : FunctionTag?
            get() = variable.getter?.let { FunctionTag(it).first }
        override val setter : FunctionTag?
            get() = variable.setter?.let { FunctionTag(it).first }
        override val returnType : TypeAST?
            get() = variable.returnType
        override val name : ID
            get() = variable.name
        override val aboveContext : List<TypeAST>
            get() = variable.aboveContext
        override val belowContext : List<TypeAST>
            get() = variable.belowContext
        override val annotations : List<AnnotationAST>
            get() = variable.annotations
    } to { variable }
interface FunctionTag : CallableTag {
    val typeParameters: List<ID>
    val parameters: List<VariableTag>
}
context(info : MutableInformation,symbols : SymbolTable<Tag>)
fun FunctionTag(function : FunctionTree) : Pair<FunctionTag,ResultBuilder<FunctionAST>> =
    object : FunctionTag {
        override val returnType : TypeAST?
            get() = function.returnType?.transform()
        override val name : ID
            get() = function.name
        override val aboveContext : List<TypeAST>
            get() = function.aboveContext.map { it.transform() }
        override val belowContext : List<TypeAST>
            get() = function.belowContext.map { it.transform() }
        override val annotations : List<AnnotationAST>
            get() = function.annotations.map { it.transform() }
        override val typeParameters : List<ID>
            get() = function.typeParameters
        override val parameters : List<VariableTag>
            get() = function.parameters.map { VariableTag(it).first }
    } to { function.transform() }
context(info : MutableInformation,symbols : SymbolTable<Tag>)
fun FunctionTag(function : FunctionAST) : Pair<FunctionTag,ResultBuilder<FunctionAST>> =
    object : FunctionTag {
        override val typeParameters : List<ID>
            get() = function.typeParameters
        override val parameters : List<VariableTag>
            get() = function.parameters.map { VariableTag(it).first }
        override val returnType : TypeAST?
            get() = function.returnType
        override val name : ID
            get() = function.name
        override val aboveContext : List<TypeAST>
            get() = function.aboveContext
        override val belowContext : List<TypeAST>
            get() = function.belowContext
        override val annotations : List<AnnotationAST>
            get() = function.annotations
    } to { function }
interface ClassTag : Tag {
    override val name: ID
    val annotations: List<AnnotationAST>
    val typeParameters: List<ID>
    val parents: List<TypeAST>
    val members: List<CallableTag>
}
context(info : MutableInformation,symbols : SymbolTable<Tag>)
fun ClassTag(`class` : ClassTree) : Pair<ClassTag,ResultBuilder<ClassAST>> =
    object : ClassTag {
        override val name : ID
            get() = `class`.name
        override val annotations : List<AnnotationAST>
            get() = `class`.annotations.map { it.transform() }
        override val typeParameters : List<ID>
            get() = `class`.typeParameters
        override val parents : List<TypeAST>
            get() = `class`.parents.map { it.transform() }
        override val members : List<CallableTag>
            get() = `class`.members.map {
                when(it) {
                    is VariableTree -> VariableTag(it).first
                    is FunctionTree -> FunctionTag(it).first
                }
            }
    } to { `class`.transform() }
context(info : MutableInformation,symbols : SymbolTable<Tag>)
fun ClassTag(`class` : ClassAST) : Pair<ClassTag,ResultBuilder<ClassAST>> =
    object : ClassTag {
        override val name : ID
            get() = `class`.name
        override val annotations : List<AnnotationAST>
            get() = `class`.annotations
        override val typeParameters : List<ID>
            get() = `class`.typeParameters
        override val parents : List<TypeAST>
            get() = `class`.parents
        override val members : List<CallableTag>
            get() = `class`.members.map {
                when(it) {
                    is VariableAST -> VariableTag(it).first
                    is FunctionAST -> FunctionTag(it).first
                }
            }
    } to { `class` }
interface TypeVarTag : Tag {
    override val name : ID
}
interface ContextTag : Tag {
    val type : TypeTree
}