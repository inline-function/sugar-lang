@file:Suppress("NestedLambdaShadowedImplicitParameter")

package compiler.semantic

import tools.input
import tools.never
import tools.nullThen
import tools.safeCast

//允许接收者安全转换为某类型
context(info : MutableInformation,scope : LexicalScope,annotations : List<Annotation>)
infix fun TypeTree.isCastableTo(type : TypeTree) : Boolean{
    //如果类型是类型本身，则返回true
    if(type.fullName == fullName) return true
    //查找接收者的定义
    val left = scope.findClassSymbol(name)
    //获取所有父类
    val parents : List<TypeTree>? = when(left){
        is ClassDef  -> left.prototype.parents
        is ClassHead -> left.parents
        null         -> null
    }
    parents?.forEach {
        //如果父类中有类型，则返回true
        if(it == type) return true
        //如果已经是最高父类,则返回假
        if(it.name == highestParentClass) return false
        //如果接收者可以转换为父类，则返回true
        if(this isCastableTo it) return true
    }
    return false
}
//上面函数的相反版本
context(info : MutableInformation,scope : LexicalScope,annotations : List<Annotation>)
infix fun TypeTree.isNotCastableTo(type : TypeTree) = !(this isCastableTo type)
/**
 * 返回泛型参数的擦除版本(其实擦除这个词不太准确)
 * @receiver 泛型参数 T
 * @param type 擦除依据类型 List<Int>
 * @return 擦除版本 Int
 */
context(info : MutableInformation,scope : LexicalScope,annotations : List<Annotation>)
infix fun TypeTree.beErasedBy(type : TypeTree) : TypeTree = type.definition.let {
    it ?: throw Error("应当确保type的definition非空再调用beErasedBy函数")
    when(type) {
        is CommonTypeTree -> this
        is ApplyTypeTree if it is ClassHead -> it.typeParaments
            .indexOfFirst { it == name }
            .takeIf { it != -1 }
            ?.let { type.arguments[it] }
            ?: this
        is ApplyTypeTree if it is ClassDef -> it.prototype.typeParameters
            .indexOfFirst { it == name }
            .takeIf { it != -1 }
            ?.let { type.arguments[it] }
            ?: this
        is FunctionTypeTree if it is ClassHead -> it.typeParaments
            .indexOfFirst { it == name }
            .takeIf { it != -1 }
            ?.let { type.parameters.getOrNull(it) ?: type.returnType }
            ?: this
        is FunctionTypeTree if it is ClassDef -> it.prototype.typeParameters
            .indexOfFirst { it == name }
            .takeIf { it != -1 }
            ?.let { type.parameters.getOrNull(it) ?: type.returnType }
            ?: this
        is NullableTypeTree if it is ClassHead -> it.typeParaments
            .indexOfFirst { it == name }
            .takeIf { it != -1 }
            ?.let { type.type }
            ?: this
        is NullableTypeTree if it is ClassDef -> it.prototype.typeParameters
            .indexOfFirst { it == name }
            .takeIf { it != -1 }
            ?.let { type.type }
            ?: this
        else -> never
    }
}
/**
 * 返回变量标签的擦除版本
 * @receiver 变量抽象语法树 val value : T
 * @param type 擦除依据类型 ValueBox<Int>
 * @return 擦除版本 Int
 */
context(info : MutableInformation,scope : LexicalScope,annotations : List<Annotation>)
infix fun VariableTree.beErasedBy(type : TypeTree) : TypeTree? = type.definition.let {
    it ?: throw Error("应当确保type的definition非空再调用beErasedBy函数")
    val typeParaments = it.typeParaments
    val any = typeParaments.any { it == name }
    when(type){
        is ApplyTypeTree if any -> typeParaments
            .indexOfFirst { it == name }
            .let { type.arguments[it] }
        is FunctionTypeTree if any -> typeParaments
            .indexOfFirst { it == name }
            .let { type.parameters[it] }
        is NullableTypeTree if any -> type.type
        else -> returnType
    }
}
/**
 * 返回变量标签的擦除版本
 * @receiver 变量标签 val value : T
 * @param type 擦除依据类型 ValueBox<Int>
 * @return 擦除版本 Int
 */
context(info : MutableInformation,scope : LexicalScope,annotations : List<Annotation>)
infix fun VariableTag.beErasedBy(type : TypeTree) : TypeTree? = type.definition.let {
    it ?: throw Error("应当确保type的definition非空再调用beErasedBy函数")
    val typeParaments = it.typeParaments
    val any = typeParaments.any { it == name }
    when(type){
        is ApplyTypeTree if any -> typeParaments
            .indexOfFirst { it == name }
            .let { type.arguments[it] }
        is FunctionTypeTree if any -> typeParaments
            .indexOfFirst { it == name }
            .let { type.parameters[it] }
        is NullableTypeTree if any -> type.type
        else -> this.type
    }
}

/**
 * 函数标签的擦除版本
 * @receiver 函数标签 fun get() : R
 * @param type 擦除依据类型 Getter<Int>
 * @return 擦除版本 Int
 */
context(info : MutableInformation,scope : LexicalScope,annotations : List<Annotation>)
infix fun FunctionTag.returnTypeBeErasedBy(type : TypeTree) : TypeTree? = type.definition.let {
    it ?: throw Error("应当确保type的definition非空再调用beErasedBy函数")
    //函数自己带的泛型类型参数
    val funTypeParaments = when(this){
        is FunctionDef  -> prototype.typeParameters
        is FunctionHead -> typeParaments
    }
    //类型带的泛型类型参数
    val typeParaments = when(it){
        is ClassDef  -> it.prototype.typeParameters
        is ClassHead -> it.typeParaments
    }
    //函数的擦除返回类型
    //this : fun get() : R | type : Getter<Int> | it : class Getter<R>
    when(val typeName = this.type?.name) {
        in typeParaments if typeName !in funTypeParaments -> typeParaments
            .indexOfFirst { it == typeName }
            .takeIf { it != 0 }
            ?.let { index ->
                when(type){
                    is CommonTypeTree   -> this.type
                    is ApplyTypeTree    -> type.arguments[index]
                    is FunctionTypeTree -> type.parameters.getOrNull(index) ?: type.returnType
                    is NullableTypeTree -> type.type
                }
            } ?: this.type
        else -> this.type
    }
}
/**
 * 函数标签的擦除版本
 * @receiver 函数标签 fun set(arg : P)
 * @param type 擦除依据类型 Setter<Int>
 * @return 擦除版本 {Int}
 */
context(info : MutableInformation,scope : LexicalScope,annotations : List<Annotation>)
infix fun FunctionTag.paramTypeBeErasedBy(type : TypeTree) : List<TypeTree> = type.definition.let {
    it ?: throw Error("应当确保type的definition非空再调用beErasedBy函数")
    //函数自己带的泛型类型参数
    val funTypeParaments = when(this){
        is FunctionDef  -> prototype.typeParameters
        is FunctionHead -> typeParaments
    }
    //类型带的泛型类型参数
    val typeParaments = when(it){
        is ClassDef  -> it.prototype.typeParameters
        is ClassHead -> it.typeParaments
    }.filter { it !in funTypeParaments }
    //函数参数类型
    when(type) {
        is CommonTypeTree   -> this.parameters.map { it.type!! }
        is ApplyTypeTree    -> this.parameters.map { param ->
            param.type
                .takeUnless { it?.name in typeParaments }
                ?: typeParaments
                    .indexOfFirst { it == param.type?.name }
                    .let { type.arguments[it] }
        }
        is FunctionTypeTree -> this.parameters.map { param ->
            param.type
                .takeUnless { it?.name in typeParaments }
                ?: typeParaments
                    .indexOfFirst { it == param.type?.name }
                    .let { type.parameters[it] }
        }
        is NullableTypeTree -> this.parameters.map { param ->
            param.type
                .takeUnless { it?.name in typeParaments }
                ?: type.type
        }
    }
}
