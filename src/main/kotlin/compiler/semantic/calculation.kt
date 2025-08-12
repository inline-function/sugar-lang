@file:Suppress("NestedLambdaShadowedImplicitParameter")

package compiler.semantic

import tools.*

context(symbols : SymbolTable<Tag>)
val ClassTag.allParents : List<ClassTag>
    get() = parents
        .map { it.definition!! }
        .let { it + it.flatMap { it.allParents } }
context(symbols : SymbolTable<Tag>)
infix fun CallableTag.overrideIn(def : ClassTag) : Boolean = def.allParents
    .any { this in it.members }
context(symbols : SymbolTable<Tag>)
val CommonTypeAST.definition : ClassTag?
    get() = symbols.parents
        .flatMap { it.symbols }
        .find { it is ClassTag && it.name == name }
        .let { it as? ClassTag }
        ?: symbols.parents
            .flatMap { it }
            .find { it is TypeVarTag && it.name == name }
            .let { (it as? TypeVarTag)?.bound as? ClassTag }

context(symbols : SymbolTable<Tag>)
val TupleTypeAST.definition : ClassTag?
    get() = symbols.parents
        .flatMap { it.symbols }
        .find { it is ClassTag && it.name == "${tuple}${arguments.size}" }
        .let { it as? ClassTag }
        ?.let {
            val typeVars = (it.typeParameters zip arguments).toMap()
            object : ClassTag by it {
                override val parents : List<TypeAST> = it.parents.map { it.specialization(typeVars) }
                override val members : List<CallableTag> = it.members.map { it.specialization(typeVars) }
            }
        }

context(symbols : SymbolTable<Tag>)
val FunctionTypeAST.definition : ClassTag?
    get() = symbols.parents
        .flatMap { it.symbols }
        .find { it is ClassTag && it.name == "${function}${parameters.size}" }
        .let { it as? ClassTag }
        ?.let {
            val typeVars = (it.typeParameters zip (parameters + returnType)).toMap()
            object : ClassTag by it {
                override val parents : List<TypeAST> = it.parents.map { it.specialization(typeVars) }
                override val members : List<CallableTag> = it.members.map { it.specialization(typeVars) }
            }
        }

context(symbols : SymbolTable<Tag>)
val NullableTypeAST.definition : ClassTag?
    get() = type.definition

context(symbols : SymbolTable<Tag>)
val ApplyTypeAST.definition : ClassTag?
    get() = symbols.parents
        .flatMap { it.symbols }
        .find { it is ClassTag && it.name == name }
        .let { it as? ClassTag }
        ?.let {
            val typeVars = (it.typeParameters zip arguments).toMap()
            object : ClassTag by it {
                override val parents : List<TypeAST> = it.parents.map { it.specialization(typeVars) }
                override val members : List<CallableTag> = it.members.map { it.specialization(typeVars) }
            }
        }
context(symbols : SymbolTable<Tag>)
val TypeAST.definition : ClassTag?
    get() = when(this){
        is ApplyTypeAST     -> definition
        is CommonTypeAST    -> definition
        is FunctionTypeAST  -> definition
        is NullableTypeAST  -> definition
        is TupleTypeAST     -> definition
        is IntersectionType -> object : ClassTag {
            override val name : ID
                get() = never
            override val annotations : List<AnnotationAST>
                get() = annotations
            override val typeParameters : List<TypeVariableAST>
                get() = emptyList()
            override val parents : List<TypeAST>
                get() = types
            override val members : List<CallableTag>
                get() = types.flatMap { it.definition!!.members }
        }
    }
//TODO:支持高阶类型
@JvmName("s1")
fun TypeAST.specialization(typeVars : Map<TypeVariableAST,TypeAST>) : TypeAST = when(this){
    is ApplyTypeAST     -> copy(arguments = arguments.map { it.specialization(typeVars) })
    is CommonTypeAST    -> takeUnless { it.name in typeVars.keys.map { it.name } } ?: typeVars.toList().find { (k,_) -> k.name == name }!!.second
    is FunctionTypeAST  -> copy(parameters = parameters.map { it.specialization(typeVars) },returnType = returnType.specialization(typeVars))
    is NullableTypeAST  -> copy(type = type.specialization(typeVars))
    is TupleTypeAST     -> copy(arguments = arguments.map { it.specialization(typeVars) })
    is IntersectionType -> copy(types = types.map { it.specialization(typeVars) })
}
@JvmName("s2")
fun TypeAST.specialization(typeVars : Map<TypeVarTag,TypeAST>) : TypeAST = when(this){
    is ApplyTypeAST     -> copy(arguments = arguments.map { it.specialization(typeVars) })
    is CommonTypeAST    -> takeUnless { it.name in typeVars.keys.map { it.name } } ?: typeVars.toList().find { (k,_) -> k.name == name }!!.second
    is FunctionTypeAST  -> copy(parameters = parameters.map { it.specialization(typeVars) },returnType = returnType.specialization(typeVars))
    is NullableTypeAST  -> copy(type = type.specialization(typeVars))
    is TupleTypeAST     -> copy(arguments = arguments.map { it.specialization(typeVars) })
    is IntersectionType -> copy(types = types.map { it.specialization(typeVars) })
}
fun CallableTag.specialization(typeVars : Map<TypeVariableAST,TypeAST>) : CallableTag = when(this){
    is FunctionTag -> typeVars
        .filter { it.key !in typeParameters }
        .let { typeVars ->
            object : FunctionTag by this {
                override val returnType : TypeAST? = this@specialization.returnType?.specialization(typeVars)
                override val parameters : List<VariableTag> = this@specialization.parameters.map {
                    object : VariableTag by it {
                        override val returnType : TypeAST? = it.returnType?.specialization(typeVars)
                    }
                }
            }
        }
    is VariableTag -> this
}
val FunctionAST.asVariableType : FunctionTypeAST
    get() = FunctionTypeAST(
        line = line,
        column = column,
        parameters = parameters.map { it.returnType },
        returnType = returnType,
        annotations = annotations,
    )
/*
TODO 暂时放弃泛型推导,只支持显式指定
具有如下规则:
T = Str  =>  T : Str
Out<T> = Out<Str>  =>  T : Str
In<T> = In<Str>  =>  Str : T
Mut<T> = Mut<Str>  =>  T = Str

设未知类型T
列方程组:{
    T <: Any
    List<Str> <: List<T>
}
因为List的E是协变的,因此
Str <: T <: Any
 */
context(info : MutableInformation,symbols : SymbolTable<Tag>)
fun FunctionTag.solve(types : List<TypeAST?>) : FunctionTag {
    //收集每个类型参数的约束
    val constraints : Map<TypeVariableAST,List<ClassTag.()->Boolean>> = typeParameters
        .associateWith { typeVariable ->
            buildList {
                //类型必须符合边界约束
                add { asType isCastableTo typeVariable.bound }
                //直接约束
                types
                    .filterIsInstance<CommonTypeAST>()
                    .filter { it.name == typeVariable.name }
                    .forEach { add { asType isCastableTo it } }
                /*
                //判断是否引用了类型变量
                fun isUsingTypeVariable(type : TypeAST) : Boolean = when(type){
                    is ApplyTypeAST    -> type.arguments.any { isUsingTypeVariable(it) }
                    is CommonTypeAST   -> type.name == typeVariable.name
                    is FunctionTypeAST -> type.parameters.any { isUsingTypeVariable(it) } || isUsingTypeVariable(type.returnType)
                    is NullableTypeAST -> isUsingTypeVariable(type.type)
                    is TupleTypeAST    -> type.arguments.any { isUsingTypeVariable(it) }
                }
                //求与类型实参有关的类型形参
                fun ApplyTypeAST.definition() : TypeVariableAST = definition!!.typeParameters[arguments.indexOfFirst { isUsingTypeVariable(it) }]
                //TODO 化简约束 List<List<T>> List<List<Int>> List<T> TList
                tailrec fun simplify(supertype : TypeAST, subtype : TypeAST) : TypeAST = when(supertype){
                    is ApplyTypeAST if supertype.definition().isCovariant     -> simplify(
                        supertype.arguments.first { isUsingTypeVariable(it) },
                        when(subtype){
                            is ApplyTypeAST    -> TODO()
                            is CommonTypeAST   -> TODO()
                            is FunctionTypeAST -> TODO()
                            is NullableTypeAST -> TODO()
                            is TupleTypeAST    -> TODO()
                        }
                    )
                    is ApplyTypeAST if supertype.definition().isContravariant -> TODO()
                    is ApplyTypeAST    -> TODO()
                    is CommonTypeAST   -> subtype
                    is FunctionTypeAST -> TODO()
                    is NullableTypeAST -> TODO()
                    is TupleTypeAST    -> TODO()
                }
                //间接约束
                types
                    .filterNot { it is CommonTypeAST }
                    .filter { isUsingTypeVariable(it) }
                    .forEach { add { asType isCastableTo it } }
                 */
            }
        }
    //求解
    val results = constraints.map { (typeVariable, constraints) ->
        typeVariable to symbols.parents
            .flatMap { it }
            .filterIsInstance<ClassTag>()
            .find { constraints.fold(true) { acc, constraint -> acc && it.constraint() } }
    }.toMap()
    //构造函数标签
    return object : FunctionTag by this {
        override val parameters : List<VariableTag>
            get() = TODO("Not yet implemented")
        override val returnType : TypeAST?
            get() = TODO("Not yet implemented")
    }
}
//协变
val TypeVariableAST.isCovariant : Boolean
    get() = annotations.any { it.name == "out" }
//逆变
val TypeVariableAST.isContravariant : Boolean
    get() = annotations.any { it.name == "in" }
//不变
val TypeVariableAST.isInvariant : Boolean
    get() = (!isCovariant) && (!isContravariant)
val ClassTag.asType : TypeAST
    get() = when { //TODO
        else -> CommonTypeAST(
            line = -1,
            column = -1,
            name = name,
            annotations = emptyList()
        )
    }
context(info : MutableInformation,symbols : SymbolTable<Tag>)
infix fun TypeAST.isCastableTo(type : TypeAST) : Boolean = try {
    //如果接收者是参数本身,可以转换
    (this % type) ||
    //如果参数是可空类型,且接收者可以转换为参数的非空版本,可以转换
    (type is NullableTypeAST && this isCastableTo type.type) ||
    //如果参数是接收者的父类,可以转换
    (definition!!.parents.any { it % type }) ||
    //如果参数是最高父类Any,可以转换
    (type is CommonTypeAST && this !is NullableTypeAST && type.name == any) ||
    //如果接收者是最低子类Nothing,可以转换
    (this is CommonTypeAST && name == nothing) ||
    //两个都是可空类型
    (this is NullableTypeAST && type is NullableTypeAST &&
            this.type isCastableTo type.type) ||
    //两个都是元组类型
    (this is TupleTypeAST && type is TupleTypeAST &&
            arguments.size == type.arguments.size &&
            arguments.zip(type.arguments).all { (a,b) -> a isCastableTo b }) ||
    //两个都是函数类型
    (this is FunctionTypeAST && type is FunctionTypeAST &&
            parameters.size == type.parameters.size &&
            parameters.zip(type.parameters).all { (a,b) -> b isCastableTo a } &&
            returnType isCastableTo type.returnType) ||
    //两个都是泛型类型
    (this is ApplyTypeAST && type is ApplyTypeAST &&
            name == type.name &&
            arguments.size == type.arguments.size &&
            (arguments zip type.arguments zip definition!!.typeParameters).all { (a,b,c) ->
                when {
                    c.isCovariant     -> a isCastableTo b
                    c.isContravariant -> b isCastableTo a
                    else              -> a % b
                }
            }) ||
    //上述均不符合,对接收者的父类再判断
    (definition!!.parents.any { it isCastableTo type })
} catch(err : NullPointerException) {
    throw NullPointerException(toString())
}
context(info : MutableInformation,symbols : SymbolTable<Tag>)
@Suppress("NOTHING_TO_INLINE")
inline infix fun TypeAST.isNotCastableTo(type : TypeAST) = !this.isCastableTo(type)