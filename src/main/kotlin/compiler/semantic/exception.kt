@file:Suppress("NOTHING_TO_INLINE","FunctionName")

package compiler.semantic

import compiler.parser.VariableTree
import tools.ID

//检查函数
context(info : MutableInformation,scope : LexicalScope)
inline fun check(
    bool : Boolean = true,
    block : context(MutableInformation,LexicalScope) ()->Unit
) {
    if(bool) block()
}
//重复文件
context(info : MutableInformation,scope : LexicalScope)
fun duplicate_files(files : List<FileTree>){
    files.groupBy { it.name }
         .filter  { it.value.size > 1 }
         .forEach { (_, duplicates) ->
             info.errors += Message("一个工程里不能有两个同名文件,编译器发现你的工程中有多个'${duplicates[0].name}'文件")
         }
}
//顶层变量没有类型
context(info : MutableInformation,scope : LexicalScope)
fun root_scope_variable_no_type(v : VariableTree){
    v.returnType ?: run {
        info.errors += v.Message("顶层变量必须指定类型,但是变量'${v.name}'没有指定类型")
    }
}
//重复标签
context(info : MutableInformation,scope : LexicalScope)
fun duplicate_tags(tags : List<Tag>){
    tags.groupBy { it.name }
        .filter { it.value.size > 1 }
        .forEach { (_, duplicates) ->
            duplicates.forEach {
                info.errors += it.Message("一个作用域里不能有多个同名'${duplicates[0].name}'标签,哪怕其中一个是变量,另一个是函数,或者另一个是类都不行")
            }
        }
}
//找不到类型
context(info : MutableInformation,scope : LexicalScope)
inline fun no_such_type(type : TypeTree,block : ()->Unit = {}){
    scope.findSymbol {
        it is ClassTag && it.name == type.name
    } ?: run {
        info.errors += type.Message("编译器没有在这儿找到'$type'类型,可能是因为你忘记写这个类型了,打错字了,忘记导入库了,或者写在其他作用域了")
        block()
    }
}
//找不到成员
context(info : MutableInformation,scope : LexicalScope)
inline fun no_such_member(type : ClassTag,member : String,block : ()->Unit = {}){
    type.members.find {
        it.name == member
    } ?: run {
        info.errors += type.Message("编译器没有在'${type.name}'中找到'$member',你是不是忘记写这个成员?还是拼错了?")
        block()
    }
}
//找不到变量
context(info : MutableInformation,scope : LexicalScope)
inline fun no_such_variable(variable : ID,line : Int,column : Int,block : ()->Unit = {}){
    scope.findSymbol {
        it is VariableTag && it.name == variable
    } ?: run {
        info.errors += Message("编译器没有在这儿找到变量'$variable',可能是因为你忘记写这个变量了,打错字了,忘记导入库了,或者写在其他作用域了",line,column)
        block()
    }
}
//不可调用
context(info : MutableInformation,scope : LexicalScope)
inline fun no_invoke_function(classTag : ClassTag,block : ()->Unit = {}){
    classTag.members.find {
        it is FunctionTag && it.name == invokeFunctionName
    } ?: run{
        info.errors += classTag.Message("类型'${classTag.name}'不能当成函数进行调用,因为它没有invoke函数")
        block()
    }
}
//参数类型不匹配
context(info : MutableInformation,scope : LexicalScope)
inline fun parameter_type_error(func : FunctionTag,arg : List<ExpressionTree>,block : ()->Unit = {}){
    func.parameters.forEachIndexed { index, tag ->
        if(tag.type!! isNotCastableTo arg[index].type)
            info.errors += tag.Message("函数的'${tag.name}'的类型是'${tag.type!!.name}',但是你填的值的类型是'${arg[index].type.name}'")
    }
}
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