@file:Suppress("NestedLambdaShadowedImplicitParameter")

package compiler.semantic

context(info : MutableInformation,symbols : SymbolTable<Tag>)
val CommonTypeAST.definition : ClassTag?
    get() = symbols.parents.flatMap { it.symbols }.find { it is ClassTag && it.name == name } as ClassTag?

context(info : MutableInformation,symbols : SymbolTable<Tag>)
val TupleTypeAST.definition : ClassTag?
    get() = symbols.parents.flatMap { it.symbols }.find { it is ClassTag && it.name == "${tuple}${arguments.size}" } as ClassTag?

context(info : MutableInformation,symbols : SymbolTable<Tag>)
val FunctionTypeAST.definition : ClassTag?
    get() = symbols.parents.flatMap { it.symbols }.find { it is ClassTag && it.name == "${function}${parameters.size}" } as ClassTag?

context(info : MutableInformation,symbols : SymbolTable<Tag>)
val NullableTypeAST.definition : ClassTag?
    get() = type.definition

context(info : MutableInformation,symbols : SymbolTable<Tag>)
val ApplyTypeAST.definition : ClassTag?
    get() = symbols.parents.flatMap { it.symbols }.find { it is ClassTag && it.name == name } as ClassTag?

context(info : MutableInformation,symbols : SymbolTable<Tag>)
val TypeAST.definition : ClassTag?
    get() = when(this){
        is ApplyTypeAST    -> definition
        is CommonTypeAST   -> definition
        is FunctionTypeAST -> definition
        is NullableTypeAST -> definition
        is TupleTypeAST    -> definition
    }
val FunctionAST.asVariableType : FunctionTypeAST
    get() = FunctionTypeAST(
        line = line,
        column = column,
        parameters = parameters.map { it.returnType },
        returnType = returnType,
        annotations = annotations
    )
context(info : MutableInformation,symbols : SymbolTable<Tag>)
infix fun TypeAST.isCastableTo(type : TypeAST) : Boolean = try {
    when {
        //如果接收者是参数本身,可以转换
        this == type                                           -> true
        //如果接收者是可空类型且接收者的非空版本可以转换为参数,可以转换
        this is NullableTypeAST && this.type isCastableTo type -> true
        //如果参数是接收者的父类,可以转换
        type in definition!!.parents                           -> true
        //其他情况不可以转换 TODO 添加形变
        else                                                   -> false
    }
} catch(_ : Throwable) {
    throw RuntimeException("$this")
}
context(info : MutableInformation,symbols : SymbolTable<Tag>)
@Suppress("NOTHING_TO_INLINE")
inline infix fun TypeAST.isNotCastableTo(type : TypeAST) = !this.isCastableTo(type)