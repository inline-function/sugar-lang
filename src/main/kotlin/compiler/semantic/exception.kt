@file:Suppress("NOTHING_TO_INLINE")

package compiler.semantic
//调用函数
const val invokeFunctionName = "invoke"
//重复文件
inline val String.duplicateFileError
    get() = "工程中有重复的文件:'$this'"
//顶层变量没有类型
inline val String.topLevelVariableNoTypeError
    get() = "顶层变量'$this'必须声明类型"
//顶层函数没有类型
inline val String.topLevelFunctionNoTypeError
    get() = "顶层函数'$this'必须声明返回类型"
//该类型没有该变量或函数
inline fun noSuchMemberError(type : String,attr : String) =
    "类型'$type'没有名字叫'$attr'的变量或函数"
//没有找到变量或函数
inline val String.noSuchVariableOrFunctionError
    get() = "这里没有找到名字叫'$this'的变量或函数"
//没有找到类
inline val String.noSuchClassError
    get() = "这里没有找到名字叫'$this'的类"
//没有invoke函数
inline val String.noInvokeFunctionError
    get() = "类型'$this'没有'$invokeFunctionName'函数"
//函数参数类型不匹配
inline fun functionParameterTypeError(
    type : String,func : String,parName : String,parType : String,arg : String
) = "'$type'类型的'$func'函数中'$parName'参数的类型是'$parType',但是你给的类型是'$arg'"