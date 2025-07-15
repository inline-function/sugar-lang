@file:Suppress("unused","ClassName","NonAsciiCharacters","PropertyName","LocalVariableName","FunctionName","SpellCheckingInspection","UnusedReceiverParameter","CAST_NEVER_SUCCEEDS")
//例子：具有类型参数的函数
fun <T> self(arg : T) : T = arg
object lambda {
    operator fun <T> invoke(arg : T) : T = arg
}
lambda(1)