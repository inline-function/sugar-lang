package compiler.semantic

import compiler.parser.InnerTree

const val tuple = "Tuple"
const val function = "Function"
const val unit = "Unit"
const val dec = "Dec"
const val int = "Int"
const val str = "Str"
val InnerTree.Unit
    get() = CommonTypeAST(
        line = line,
        column = column,
        name = unit,
        annotations = emptyList()
    )
val InnerTree.Dec
    get() = CommonTypeAST(
        line = line,
        column = column,
        name = dec,
        annotations = emptyList()
    )
val InnerTree.Int
    get() = CommonTypeAST(
        line = line,
        column = column,
        name = int,
        annotations = emptyList()
    )
val InnerTree.Str
    get() = CommonTypeAST(
        line = line,
        column = column,
        name = str,
        annotations = emptyList()
    )