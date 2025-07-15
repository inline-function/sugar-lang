package compiler.semantic
external infix operator fun InnerAST?.rem(type : InnerAST?) : Boolean
infix operator fun TypeAST?.rem(type : InnerAST?) : Boolean = when(this){
    is ApplyTypeAST?      -> this % type
    is CommonTypeAST?     -> this % type
    is FunctionTypeAST?   -> this % type
    is NullableTypeAST?   -> this % type
    is TupleTypeAST?      -> this % type
}
infix operator fun AnnotationAST?.rem(type : InnerAST?) : Boolean =
    this == null && type == null ||
    type is AnnotationAST &&
    this is AnnotationAST &&
    type.name == name &&
    type.arguments % arguments
infix operator fun CommonTypeAST?.rem(type : InnerAST?) : Boolean =
    this == null && type == null ||
    type is CommonTypeAST &&
    this is CommonTypeAST &&
    type.name == name &&
    type.annotations.size == annotations.size &&
    (type.annotations zip annotations).all { (p1,p2) -> p1 % p2 }
infix operator fun TupleTypeAST?.rem(type : InnerAST?) : Boolean =
    this == null && type == null ||
    type is TupleTypeAST &&
    this is TupleTypeAST &&
    type.arguments.size == arguments.size &&
    type.annotations.size == annotations.size &&
    (type.annotations zip annotations).all { (p1,p2) -> p1 % p2 } &&
    (type.arguments zip arguments).all { (p1,p2) -> p1 % p2 }
infix operator fun ApplyTypeAST?.rem(type : InnerAST?) : Boolean =
    this == null && type == null ||
    type is ApplyTypeAST &&
    this is ApplyTypeAST &&
    type.name == name &&
    type.arguments.size == arguments.size &&
    type.annotations.size == annotations.size &&
    (type.annotations zip annotations).all { (p1,p2) -> p1 % p2 } &&
    (type.arguments zip arguments).all { (p1,p2) -> p1 % p2 }
infix operator fun FunctionTypeAST?.rem(type : InnerAST?) : Boolean =
    this == null && type == null ||
    type is FunctionTypeAST &&
    this is FunctionTypeAST &&
    type.returnType % returnType &&
    type.parameters.size == parameters.size &&
    type.annotations.size == annotations.size &&
    (type.annotations zip annotations).all { (p1,p2) -> p1 % p2 } &&
    (type.parameters zip parameters).all { (p1,p2) -> p1 % p2 }
infix operator fun NullableTypeAST?.rem(type : InnerAST?) : Boolean =
    this == null && type == null ||
    type is NullableTypeAST &&
    this is NullableTypeAST &&
    type.type % type.type

infix operator fun ClassAST?.rem(ast: InnerAST?) : Boolean =
    this == null && ast == null ||
            ast is ClassAST &&
            this is ClassAST &&
            ast.name == name &&
            ast.annotations.size == annotations.size &&
            (ast.annotations zip annotations).all { (a1, a2) -> a1 % a2 } &&
            ast.typeParameters.size == typeParameters.size &&
            (ast.typeParameters zip typeParameters).all { (t1, t2) -> t1 % t2 } &&
            ast.parents.size == parents.size &&
            (ast.parents zip parents).all { (p1, p2) -> p1 % p2 } &&
            ast.members.size == members.size &&
            (ast.members zip members).all { (m1, m2) -> m1 % m2 }

infix operator fun TypeVariableAST?.rem(ast: InnerAST?) : Boolean =
    this == null && ast == null ||
            ast is TypeVariableAST &&
            this is TypeVariableAST &&
            ast.name == name &&
            ast.bound % bound &&
            ast.annotations.size == annotations.size &&
            (ast.annotations zip annotations).all { (a1, a2) -> a1 % a2 }

infix operator fun NameAST?.rem(ast: InnerAST?) : Boolean =
    this == null && ast == null ||
            ast is NameAST &&
            this is NameAST &&
            ast.expression % expression &&
            ast.name == name &&
            ast.type % type

infix operator fun VariableAST?.rem(ast: InnerAST?) : Boolean =
    this == null && ast == null ||
            ast is VariableAST &&
            this is VariableAST &&
            ast.name == name &&
            ast.returnType % returnType &&
            ast.annotations.size == annotations.size &&
            (ast.annotations zip annotations).all { (a1, a2) -> a1 % a2 } &&
            ast.aboveContext.size == aboveContext.size &&
            (ast.aboveContext zip aboveContext).all { (c1, c2) -> c1 % c2 } &&
            ast.belowContext.size == belowContext.size &&
            (ast.belowContext zip belowContext).all { (c1, c2) -> c1 % c2 } &&
            ast.receiver % receiver &&
            ast.getter % getter &&
            ast.setter % setter &&
            ast.value % value

infix operator fun FunctionAST?.rem(ast: InnerAST?) : Boolean =
    this == null && ast == null ||
            ast is FunctionAST &&
            this is FunctionAST &&
            ast.returnType % returnType &&
            ast.name == name &&
            ast.typeParameters.size == typeParameters.size &&
            (ast.typeParameters zip typeParameters).all { (t1, t2) -> t1 % t2 } &&
            ast.body % body &&
            ast.parameters.size == parameters.size &&
            (ast.parameters zip parameters).all { (p1, p2) -> p1 % p2 } &&
            ast.aboveContext.size == aboveContext.size &&
            (ast.aboveContext zip aboveContext).all { (c1, c2) -> c1 % c2 } &&
            ast.belowContext.size == belowContext.size &&
            (ast.belowContext zip belowContext).all { (c1, c2) -> c1 % c2 } &&
            ast.annotations.size == annotations.size &&
            (ast.annotations zip annotations).all { (a1, a2) -> a1 % a2 }

infix operator fun ScopeAST?.rem(ast: InnerAST?) : Boolean =
    this == null && ast == null ||
            ast is ScopeAST &&
            this is ScopeAST &&
            ast.stmts.size == stmts.size &&
            (ast.stmts zip stmts).all { (s1, s2) -> s1 % s2 }

infix operator fun LambdaAST?.rem(ast: InnerAST?) : Boolean =
    this == null && ast == null ||
            ast is LambdaAST &&
            this is LambdaAST &&
            ast.parameters.size == parameters.size &&
            (ast.parameters zip parameters).all { (p1, p2) -> p1 % p2 } &&
            ast.body % body &&
            ast.returnType % returnType &&
            ast.type % type

infix operator fun InvokeAST?.rem(ast: InnerAST?) : Boolean =
    this == null && ast == null ||
            ast is InvokeAST &&
            this is InvokeAST &&
            ast.invoker % invoker &&
            ast.arguments.size == arguments.size &&
            (ast.arguments zip arguments).all { (a1, a2) -> a1 % a2 } &&
            ast.typeArguments.size == typeArguments.size &&
            (ast.typeArguments zip typeArguments).all { (t1, t2) -> t1 % t2 } &&
            ast.type % type

infix operator fun AssignAST?.rem(ast: InnerAST?) : Boolean =
    this == null && ast == null ||
            ast is AssignAST &&
            this is AssignAST &&
            ast.name % name &&
            ast.value % value

infix operator fun StringConstantAST?.rem(ast: InnerAST?) : Boolean =
    this == null && ast == null ||
            ast is StringConstantAST &&
            this is StringConstantAST &&
            ast.value == value &&
            ast.type % type

infix operator fun IntegerConstantAST?.rem(ast: InnerAST?) : Boolean =
    this == null && ast == null ||
            ast is IntegerConstantAST &&
            this is IntegerConstantAST &&
            ast.value == value &&
            ast.type % type

infix operator fun DecimalConstantAST?.rem(ast: InnerAST?) : Boolean =
    this == null && ast == null ||
            ast is DecimalConstantAST &&
            this is DecimalConstantAST &&
            ast.value == value &&
            ast.type % type