/**
 * 本文件定义语义分析DSL相关事宜
 * 编写时kt版本 2.1.0
 * 编写时时间   2025.4.13
 * 创建者      语法糖味函子酱(sugared functor)
 */
package compiler.semantic

import compiler.parser.ID
import kotlin.reflect.KClass

/**
 * 标记类为语法树(参数)的DSL构建器
 * @param target 构建结果语法树
 */
@DslMarker
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class TreeBuilderDsl(val target : KClass<*>)
/**
 * 语义分析结果信息
 * @property sugar 语法糖级别
 * 可使用更简洁的语法书写
 * 一般不会改变代码语义
 * @property tips 提示级别
 * 使用了一些可能误解概念的东西
 * 或者使用了不建议使用的东西
 * @property warnings 警告级别
 * 使用了很大概率有问题的语法
 * @property errors 错误级别
 * 出现了语法错误
 */
data class Information(
    val sugar : List<Message>,
    val tips : List<Message>,
    val warnings : List<Message>,
    val errors : List<Message>
){
    /**
     * 合并两个语义分析信息
     * @param other 另一个语义分析信息
     * @return 合并后的语义分析信息
     */
    operator fun plus(other : Information) = Information(
        sugar = sugar + other.sugar,
        tips = tips + other.tips,
        warnings = warnings + other.warnings,
        errors = errors + other.errors
    )
}
/**
 * 语义分析信息
 * @property line 行号
 * @property column 列号
 * @property context 信息内容
 */
data class Message(
    val line : Int,
    val column : Int,
    val context : String
)
/**
 * 语义化的语法树
 * @param T 语义化结果类型
 */
typealias Semantic<T> = Pair<T,Information>
/**
 * DSL的作用域
 * @param T DSL类
 */
typealias Block<T> = T.() -> Unit
/**
 * 语法树构建器DSL的共同父类
 * @property sugar 语法糖级别信息
 * @property tips 提示级别信息
 * @property warnings 警告级别信息
 * @property errors 错误级别信息
 */
abstract class Builder<out T>{
    private val sugar = mutableListOf<Message>()
    private val tips = mutableListOf<Message>()
    private val warnings = mutableListOf<Message>()
    private val errors = mutableListOf<Message>()
    /**
     * 将语法糖级别信息添加到语义分析信息中
     * @param context 信息内容
     * @receiver 信息所在语法树
     * @return 空
     */
    fun Tree.sugar(context : String) = template(context,sugar)
    /**
     * 将提示级别信息添加到语义分析信息中
     * @param context 信息内容
     * @receiver 信息所在语法树
     * @return 空
     */
    fun Tree.tip(context : String) = template(context,tips)
    /**
     * 将警告级别信息添加到语义分析信息中
     * @param context 信息内容
     * @receiver 信息所在语法树
     * @return 空
     */
    fun Tree.warning(context : String) = template(context,warnings)
    /**
     * 将错误级别信息添加到语义分析信息中
     * @param context 信息内容
     * @receiver 信息所在语法树
     * @return 空
     */
    fun Tree.error(context : String) = template(context,errors)
    /**
     * 一个模板内联函数,用于依照接收者的信息为list添加context的信息
     * @param context 信息内容
     * @param list 信息列表
     * @receiver 信息所在语法树
     * @return 空,用上该返回值则表达语法树构建失败
     */
    @Suppress("NOTHING_TO_INLINE")
    private inline fun Tree.template(context : String,list : MutableList<Message>) = null.also{
        if (this is InnerTree)
            list.add(Message(line,column,context))
        else
            list.add(Message(-1,-1,context))
    }
    /**
     * 获取当前的语义分析信息
     * @return 语义分析信息
     */
    val information get() = Information(
        sugar    = sugar.toList(),
        tips     = tips.toList(),
        warnings = warnings.toList(),
        errors   = errors.toList()
    )
    /**
     * 调用该函数以构建最终的只读语法树
     * @return 最终结果
     */
    fun build() : Semantic<T> = building() to information
    /**
     * 构建最终语法树,该函数要求子类实现
     * @return 最终语法树
     */
    protected abstract fun building() : T
}
/**
 * 工程语法树构建器
 * @property name 工程名
 * @property files 工程文件
 */
@TreeBuilderDsl(ProjectTree::class)
class ProjectTreeBuilder : Builder<ProjectTree>(){
    var name = "unnamed"
    val files = mutableListOf<FileTree>()
    override fun building() = ProjectTree(
        name  = name,
        files = files
    )
}
/**
 * 工程树的DSL函数
 * @param name 工程名
 * @param body DSL体
 * @return 只读工程树
 */
fun project(name : ID,body : Block<ProjectTreeBuilder>) =
    ProjectTreeBuilder().also(body).build()