@file:Suppress("NOTHING_TO_INLINE","FunctionName")

package compiler.semantic

import compiler.parser.VariableTree
import compiler.semantic.AnnotationValue.*
import compiler.semantic.beErasedBy
import tools.ID
import tools.input

//检查函数
context(info : MutableInformation,scope : LexicalScope,annotations : List<Annotation>)
inline fun check(
    bool : Boolean = true,
    block : context(MutableInformation,LexicalScope) ()->Unit
) {
    if(bool) block()
}
//重复文件
context(info : MutableInformation,scope : LexicalScope,annotations : List<Annotation>)
fun duplicate_files(files : List<FileTree>){
    files.groupBy { it.name }
         .filter  { it.value.size > 1 }
         .forEach { (_, duplicates) ->
             info.errors += Message("一个工程里不能有两个同名文件,编译器发现你的工程中有多个'${duplicates[0].name}'文件")
         }
}
//顶层变量没有类型
context(info : MutableInformation,scope : LexicalScope,annotations : List<Annotation>)
fun root_scope_variable_no_type(v : VariableTree){
    v.returnType ?: run {
        info.errors += v.Message("顶层变量必须指定类型,但是变量'${v.name}'没有指定类型")
    }
}
//重复标签
context(info : MutableInformation,scope : LexicalScope,annotations : List<Annotation>)
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
context(info : MutableInformation,scope : LexicalScope,annotations : List<Annotation>)
inline fun no_such_type(type : TypeTree,block : ()->Unit = {}){
    scope.findClassSymbol(type.name) ?: run {
        info.errors += type.Message("编译器没有在这儿找到'$type'类型,可能是因为你忘记写这个类型了,打错字了,忘记导入库了,或者写在其他作用域了")
        block()
    }
}
//找不到成员
context(info : MutableInformation,scope : LexicalScope,annotations : List<Annotation>)
inline fun no_such_member(type : ClassTag,member : String,block : ()->Unit = {}){
    when(type){
        is ClassDef  -> type.prototype.members.find {
            it.name == member
        }
        is ClassHead -> type.members.find {
            it.name == member
        }
    } ?: run {
        info.errors += type.Message("编译器没有在'${type.name}'中找到'$member',你是不是忘记写这个成员?还是拼错了?")
        block()
    }
}
//找不到变量以及函数
context(info : MutableInformation,scope : LexicalScope,annotations : List<Annotation>)
inline fun no_such_variable(variable : ID,line : Int,column : Int,block : ()->Unit = {}){
    scope.findSymbol {
        it is CallableTag && it.name == variable
    } ?: run {
        info.errors += Message("编译器没有在这儿找到'$variable',可能是因为你忘记写它了,打错字了,忘记导入库了,或者写在其他作用域了",line,column)
        block()
    }
}
//不可调用
context(info : MutableInformation,scope : LexicalScope,annotations : List<Annotation>)
inline fun no_invoke_function(classTag : ClassTag,block : ()->Unit = {}){
    when(classTag){
        is ClassDef  -> classTag.prototype.members.find {
            it is FunctionTag && it.name == invokeFunctionName
        }
        is ClassHead -> classTag.members.find {
            it is FunctionTag && it.name == invokeFunctionName
        }
    } ?: run{
        info.errors += classTag.Message("类型'${classTag.name}'不能当成函数进行调用,因为它没有invoke函数")
        block()
    }
}
//参数类型不匹配
context(info : MutableInformation,scope : LexicalScope,annotations : List<Annotation>)
inline fun parameter_type_error(
    func : FunctionTag,
    by : TypeTree,
    arg : List<ExpressionTree>,
    line : Int,
    column : Int,
    block : ()->Unit = {}
){
    val real = func paramTypeBeErasedBy by
    when(func){
        is FunctionDef  -> func.prototype.parameters.forEachIndexed { index, tag ->
            if(real[index] isNotCastableTo arg[index].type){
                info.errors += Message("函数的参数'${tag.name}'类型是'${real[index]}',但是你填的值的类型是'${arg[index].type}'",line,column)
                block()
            }
        }
        is FunctionHead -> func.parameters.forEachIndexed { index, tag ->
            if(real[index] isNotCastableTo arg[index].type){
                info.errors += Message("函数的参数'${tag.name}'类型是'${real[index]}',但是你填的值的类型是'${arg[index].type}'",line,column)
                block()
            }
        }
    }
}
//变量没有初始化
context(info : MutableInformation,scope : LexicalScope,annotations : List<Annotation>)
inline fun no_such_variable_init(variable : compiler.semantic.VariableTree,block : ()->Unit = {}){
    variable.value ?: run {
        info.errors += variable.Message("变量'${variable.name}'没有初始化,如果你希望暂时不赋值,请在变量后面加'? = null'")
        block()
    }
}
//无法转换类型
context(info : MutableInformation,scope : LexicalScope,annotations : List<Annotation>)
inline fun no_such_castable_type(
    from : TypeTree,to : TypeTree,
    block : ()->Unit = {}
){
    if(from isNotCastableTo to){
        info.errors += to.Message("编译器无法将类型'$from'转换为类型'$to'")
        block()
    }
}
//无需求lambda的参数没有声明参数类型
context(info : MutableInformation,scope : LexicalScope,annotations : List<Annotation>)
inline fun no_such_lambda_parameter_type(params : List<compiler.semantic.VariableTree>, block : ()->Unit = {}){
    params.forEach {
        it.returnType ?: run {
            info.errors += it.Message("不知道你的匿名函数参数类型是什么,请你自己在参数名字后面加': 类型'进行指定")
            block()
        }
    }
}
//类型不是函数类型
context(info : MutableInformation,scope : LexicalScope,annotations : List<Annotation>)
inline fun type_is_not_function_type(type : TypeTree,block : ()->Unit = {}){
    if(type !is FunctionTypeTree){
        info.errors += type.Message("类型'$type'不是一个函数类型,此处需要函数类型")
        block()
    }
}
//没有该注解
context(info : MutableInformation,scope : LexicalScope,annotations : List<Annotation>)
inline fun no_such_annotation(ann : AnnotationTree,block : ()->Unit = {}){
    annotations.find {
        it.name == ann.name
    } ?: run {
        info.errors += ann.Message("编译器没有找到叫做'${ann.name}'的注解,请检查是否写错或是否存在")
        block()
    }
}
//注解参数不正确
context(info : MutableInformation,scope : LexicalScope,annotations : List<Annotation>)
inline fun annotation_parameter_error(
    annotation : AnnotationTree,
    block : ()->Unit = {}
){
    val ann = annotations.find { annotation.name == it.name } ?: return
    when(annotation.arguments){
        is DecimalConstantTree if ann.value == DEC                     -> Unit
        is IntegerConstantTree if ann.value == INT || ann.value == DEC -> Unit
        is StringConstantTree  if ann.value == STR                     -> Unit
        else                                                            -> run {
            info.errors += annotation.Message("注解'${ann.name}'的参数应该是'${ann.value ?: "空"}',请检查你的注解参数")
            block()
        }
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