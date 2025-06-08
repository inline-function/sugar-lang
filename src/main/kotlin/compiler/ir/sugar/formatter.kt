package compiler.ir.sugar

import tools.times
//
//fun IntermediateRepresentation.asString() = """
//    |classes :
//    |${classes.joinToString("\n") { it.asString(4) }}
//    |functions :
//    |${functions.joinToString("\n") { it.asString(4) }}
//    |variables :
//    |${variables.joinToString("\n") { it.asString(4) }}
//    |commands :
//    |${commands.toList().joinToString("\n") { it.toString() /* .asString(4) */ }}
//""".trimMargin()
//fun ClassDecIR.asString(indentation : Int = 0) = """
//    |class `$name` :
//    |    line = $line
//    |    file = `$file`
//    |    typeParams = $typeParams
//    |    parents :
//    |       ${parents.joinToString("\n") { it.asString() }}
//    |    annotations :
//    |       ${anns.joinToString("\n") { it.asString() }}
//""".trimMargin().prependIndent(indentation * " ")
//fun FunctionDecIR.asString(indentation : Int = 0) = """
//    |function `$name` :
//    |    host = `$host`
//    |    line = $line
//    |    file = `$file`
//    |    type = `${type.asString()}`
//    |    typeParams = $typeParams
//    |    params = $params
//    |    annotations :
//    |       ${anns.joinToString("\n") { it.asString() }}
//""".trimMargin().prependIndent(indentation * " ")
//fun VariableDecIR.asString(indentation : Int = 0) = """
//    |variable `$name` :
//    |    host = `$host`
//    |    line = $line
//    |    file = `$file`
//    |    type = `${type.asString()}`
//    |    annotations :
//    |       ${anns.joinToString("\n") { it.asString() }}
//""".trimMargin().prependIndent(indentation * " ")
//fun TypeIR.asString() : String = name + typeParams
//    .takeIf { it.isNotEmpty() }
//    ?.joinToString(",",transform = TypeIR::asString)
//    .let { it ?: "" }
//fun AnnotationIR.asString() = "@$name" + value
//    ?.let {
//        when(it){
//            is DecimalConstant -> "(${it.value})"
//            is IntegerConstant -> "(${it.value})"
//            is StringConstant  -> "(\"${it.value}\")"
//        }
//    }
//    .let { it ?: "" }
//    .let { "$it - $line" }