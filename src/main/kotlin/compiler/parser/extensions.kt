package compiler.parser

fun ProjectTree.asString() =
    files.joinToString("\n", "/* 工程$name */\n")

fun FileTree.asString() =
    tops.joinToString("\n", "//文件名:$name\n")

fun NameTree.asString() =
    expression?.let { "($it).$name" } ?: name

fun ClassTree.asString() = buildString {
    append("class $name")
    if(parents.isNotEmpty()) append(" : ${parents.joinToString(",")}")
    append(" {\n${members.joinToString("\n")}\n}")
}

fun VariableTree.asString() = buildString {
    append("var")
    append(" $name")
    returnType?.let { append(" : $it") }
    value?.let { append(" = $it") }
}

fun FunctionTree.asString() = buildString {
    append("fun $name(${parameters.joinToString(",")})")
    returnType?.let { append(" : $it") }
    //body?.joinTo(this, "\n", "{\n", "\n}")
}

fun AnnotationTree.asString() =
    "@$name(${arguments?.let{""}})"

fun InvokeTree.asString() =
    "($invoker(${arguments.joinToString(",")}))"

fun StringConstantTree.asString() = "\"$value\""

fun IntegerConstantTree.asString() = value.toString()

fun DecimalConstantTree.asString() = value.toString()

fun CommonTypeTree.asString() = name

fun NullableTypeTree.asString() = "($type)?"

fun FunctionTypeTree.asString() = "(${parameters.joinToString(",")})=>$returnType"

fun ApplyTypeTree.asString() = "$name<${arguments.joinToString(",")}>"