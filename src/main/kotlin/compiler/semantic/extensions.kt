package compiler.semantic
external infix operator fun InnerAST?.rem(type : InnerAST?) : Boolean
infix operator fun TypeAST?.rem(type : InnerAST?) : Boolean = when(this){
    is ApplyTypeAST?      -> this % type
    is CommonTypeAST?     -> this % type
    is FunctionTypeAST?   -> this % type
    is NullableTypeAST?   -> this % type
    is TupleTypeAST?      -> this % type
    is IntersectionType   -> this % type
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
infix operator fun IntersectionType?.rem(type : InnerAST?) : Boolean =
    this == null && type == null ||
    type is IntersectionType &&
    this is IntersectionType &&
    types.size == type.types.size &&
    (types zip type.types).all { (p1,p2) -> p1 % p2 }
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
fun ProjectAST.visit(body : (AST)->Unit) {
    files.forEach(body)
    files.forEach { it.visit(body) }
}
fun FileAST.visit(body : (AST)->Unit) {
    tops.forEach(body)
    tops.forEach { it.visit(body) }
}
fun TopAST.visit(body : (AST)->Unit) {
    body(this)
    when(this){
        is FunctionAST     -> visit(body)
        is VariableAST     -> visit(body)
        is ClassAST        -> visit(body)
        is TypeVariableAST -> visit(body)
    }
}
fun FunctionAST.visit(body : (AST)->Unit) {
    body(returnType)
    typeParameters.forEach(body)
    parameters.forEach(body)
    parameters.forEach(body)
    aboveContext.forEach(body)
    belowContext.forEach(body)
    annotations.forEach(body)
    this.body?.let { body(it) }
    typeParameters.forEach { it.visit(body) }
    parameters.forEach { it.visit(body) }
    aboveContext.forEach { it.visit(body) }
    belowContext.forEach { it.visit(body) }
    annotations.forEach { it.visit(body) }
    this.body?.visit(body)
}
fun BodyAST.visit(body : (AST)->Unit) {
    stmts.forEach(body)
    stmts.forEach { it.visit(body) }
}
fun StatementAST.visit(body : (AST)->Unit) {
    body(this)
    when(this){
        is LambdaAST          -> visit(body)
        is InvokeAST          -> visit(body)
        is AssignAST          -> visit(body)
        is StringConstantAST  -> visit(body)
        is IntegerConstantAST -> visit(body)
        is AnonymousObjectAST -> visit(body)
        is DecimalConstantAST -> visit(body)
        is NameAST            -> visit(body)
        is FunctionAST        -> visit(body)
        is VariableAST        -> visit(body)
        is ClassAST           -> visit(body)
        is TypeVariableAST    -> visit(body)
    }
}
fun VariableAST.visit(body : (AST)->Unit) {
    body(returnType)
    receiver?.let { body(it) }
    getter?.let { body(it) }
    setter?.let { body(it) }
    value?.let { body(it) }
    aboveContext.forEach(body)
    belowContext.forEach(body)
    annotations.forEach(body)
    
    receiver?.visit(body)
    getter?.visit(body)
    setter?.visit(body)
    value?.visit(body)
    aboveContext.forEach { it.visit(body) }
    belowContext.forEach { it.visit(body) }
    annotations.forEach { it.visit(body) }
}

fun ClassAST.visit(body : (AST)->Unit) {
    typeParameters.forEach(body)
    parents.forEach(body)
    members.forEach(body)
    annotations.forEach(body)
    
    typeParameters.forEach { it.visit(body) }
    parents.forEach { it.visit(body) }
    members.forEach { it.visit(body) }
    annotations.forEach { it.visit(body) }
}

fun TypeVariableAST.visit(body : (AST)->Unit) {
    body(bound)
    annotations.forEach(body)
    
    bound.visit(body)
    annotations.forEach { it.visit(body) }
}

fun NameAST.visit(body : (AST)->Unit) {
    expression?.let { body(it) }
    body(type)
    
    expression?.visit(body)
    type.visit(body)
}

fun ScopeAST.visit(body : (AST)->Unit) {
    stmts.forEach(body)
    stmts.forEach { it.visit(body) }
}

fun LambdaAST.visit(body : (AST)->Unit) {
    parameters.forEach(body)
    body(this.body)
    body(returnType)
    body(type)
    
    parameters.forEach { it.visit(body) }
    this.body.visit(body)
    returnType.visit(body)
    type.visit(body)
}

fun AnnotationAST.visit(body : (AST)->Unit) {
    arguments?.let { body(it) }
    arguments?.visit(body)
}

fun InvokeAST.visit(body : (AST)->Unit) {
    body(invoker)
    arguments.forEach(body)
    typeArguments.forEach(body)
    body(type)
    
    invoker.visit(body)
    arguments.forEach { it.visit(body) }
    typeArguments.forEach { it.visit(body) }
    type.visit(body)
}

fun AnonymousObjectAST.visit(body : (AST)->Unit) {
    parents.forEach(body)
    members.forEach(body)
    body(type)
    
    parents.forEach { it.visit(body) }
    members.forEach { it.visit(body) }
    type.visit(body)
}

fun TypeAST.visit(body : (AST)->Unit) {
    body(this)
    when(this) {
        is CommonTypeAST -> visit(body)
        is NullableTypeAST -> visit(body)
        is TupleTypeAST -> visit(body)
        is FunctionTypeAST -> visit(body)
        is ApplyTypeAST -> visit(body)
        is IntersectionType -> visit(body)
    }
}

fun CommonTypeAST.visit(body : (AST)->Unit) {
    annotations.forEach(body)
    annotations.forEach { it.visit(body) }
}

fun NullableTypeAST.visit(body : (AST)->Unit) {
    body(type)
    annotations.forEach(body)
    
    type.visit(body)
    annotations.forEach { it.visit(body) }
}

fun TupleTypeAST.visit(body : (AST)->Unit) {
    arguments.forEach(body)
    annotations.forEach(body)
    
    arguments.forEach { it.visit(body) }
    annotations.forEach { it.visit(body) }
}

fun FunctionTypeAST.visit(body : (AST)->Unit) {
    parameters.forEach(body)
    body(returnType)
    annotations.forEach(body)
    typeParameters.forEach(body)
    
    parameters.forEach { it.visit(body) }
    returnType.visit(body)
    annotations.forEach { it.visit(body) }
    typeParameters.forEach { it.visit(body) }
}

fun ApplyTypeAST.visit(body : (AST)->Unit) {
    arguments.forEach(body)
    annotations.forEach(body)
    
    arguments.forEach { it.visit(body) }
    annotations.forEach { it.visit(body) }
}

fun IntersectionType.visit(body : (AST)->Unit) {
    types.forEach(body)
    annotations.forEach(body)
    
    types.forEach { it.visit(body) }
    annotations.forEach { it.visit(body) }
}

fun ExpressionAST.visit(body : (AST)->Unit) {
    body(this)
    when(this) {
        is NameAST -> visit(body)
        is LambdaAST -> visit(body)
        is InvokeAST -> visit(body)
        is AssignAST -> visit(body)
        is AnonymousObjectAST -> visit(body)
        is StringConstantAST -> visit(body)
        is IntegerConstantAST -> visit(body)
        is DecimalConstantAST -> visit(body)
    }
}

fun AssignAST.visit(body : (AST)->Unit) {
    body(name)
    body(value)
    
    name.visit(body)
    value.visit(body)
}

fun StringConstantAST.visit(body : (AST)->Unit) {
    body(type)
    type.visit(body)
}

fun IntegerConstantAST.visit(body : (AST)->Unit) {
    body(type)
    type.visit(body)
}

fun DecimalConstantAST.visit(body : (AST)->Unit) {
    body(type)
    type.visit(body)
}