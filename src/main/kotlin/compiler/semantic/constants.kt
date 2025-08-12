package compiler.semantic

import compiler.parser.InnerTree
import compiler.semantic.ClassTag
import compiler.semantic.FunctionAST
import compiler.semantic.FunctionTypeAST
import compiler.semantic.TypeVariableAST

const val tuple = "Tuple"
const val function = "Function"
const val unit = "Unit"
const val dec = "Dec"
const val int = "Int"
const val str = "Str"
const val any = "Any"
const val nothing = "Nothing"
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
val InnerTree.Any
    get() = CommonTypeAST(
        line = line,
        column = column,
        name = any,
        annotations = emptyList()
    )
val InnerTree.Nothing
    get() = CommonTypeAST(
        line = line,
        column = column,
        name = nothing,
        annotations = emptyList()
    )
val InnerAST.Unit
    get() = CommonTypeAST(
        line = line,
        column = column,
        name = unit,
        annotations = emptyList()
    )
val InnerAST.Dec
    get() = CommonTypeAST(
        line = line,
        column = column,
        name = dec,
        annotations = emptyList()
    )
val InnerAST.Int
    get() = CommonTypeAST(
        line = line,
        column = column,
        name = int,
        annotations = emptyList()
    )
val InnerAST.Str
    get() = CommonTypeAST(
        line = line,
        column = column,
        name = str,
        annotations = emptyList()
    )
val InnerAST.Any
    get() = CommonTypeAST(
        line = line,
        column = column,
        name = any,
        annotations = emptyList()
    )
val InnerAST.Nothing
    get() = CommonTypeAST(
        line = line,
        column = column,
        name = nothing,
        annotations = emptyList()
    )