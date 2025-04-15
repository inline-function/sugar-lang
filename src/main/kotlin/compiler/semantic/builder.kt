/**
 * 本文件定义抽象语法树构建器相关事宜
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
interface Information{
    val sugar    : Messages
    val tips     : Messages
    val warnings : Messages
    val errors   : Messages
    /**
     * 合并两个语义分析信息
     * @param other 另一个语义分析信息
     * @return 合并后的语义分析信息
     */
    operator fun plus(other : Information) = Information(
        sugar    = sugar    + other.sugar,
        tips     = tips     + other.tips,
        warnings = warnings + other.warnings,
        errors   = errors   + other.errors
    )
    companion object : (Messages,Messages,Messages,Messages)->Information{
        /**
         * 直接构造一个信息实例
         * @param sugar 语法糖级别信息列表
         * @param tips 提示级别信息列表
         * @param warnings 警告级别信息列表
         * @param errors 错误级别信息列表
         * @return 信息实例
         */
        override operator fun invoke(
            sugar    : Messages,
            tips     : Messages,
            warnings : Messages,
            errors   : Messages
        ) : Information {
            data class InformationImplementation(
                override val sugar    : Messages,
                override val tips     : Messages,
                override val warnings : Messages,
                override val errors   : Messages
            ) : Information
            return InformationImplementation(sugar,tips,warnings,errors)
        }
    }
}
/**
 * 语义分析信息
 * @property line 行号
 * @property column 列号
 * @property context 信息内容
 */
data class Message(
    val line    : Int,
    val column  : Int,
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
 * 可变信息
 */
typealias MutableInformation = MessageCollector
/**
 * 多条消息
 */
typealias Messages = List<Message>
/**
 * 可变多条消息
 */
typealias MutableMessages = MutableList<Message>
/**
 * 语法树DSL接口
 *
 * 信息收集器 + 语法树构建器 = 语法树DSL
 * @param T 所构建的语法树类型
 * @property tree 所构建的语法树
 */
interface TreeDsl<out T : Tree> : TreeBuilder<T>,MessageCollector
/**
 * ps:等同于语法树构建器的onBuilding函数
 *
 * 该属性获取最终的只读语法树
 * @param T 语法树构建器所构建的语法树类型
 * @return 最终的只读语法树
 * @receiver 语法树构建器
 */
inline val <T : Tree> TreeDsl<T>.tree get() = onBuilding()
/**
 * 语法树构建器接口
 * @param T 所构建的语法树类型
 */
fun interface TreeBuilder<out T : Tree> {
    /**
     * 构建语法树过程
     * @return 最终的只读语法树,如果构建过程失败则返回null
     */
    fun onBuilding(): T?
}
/**
 * 构建最终结果
 * @param T 所构建的语法树类型
 * @receiver DSL体
 * @return 语义分析结果
 */
val <T : Tree> TreeDsl<T>.result : Semantic<T?> get() = tree to information
/**
 * 消息收集器接口
 * @property sugar 语法糖级别信息
 * @property tips 提示级别信息
 * @property warnings 警告级别信息
 * @property errors 错误级别信息
 * @property information 只读信息副本 调用该属性获取本信息收集器的只读信息副本
 */
interface MessageCollector : Information {
    override val sugar       : MutableMessages
    override val tips        : MutableMessages
    override val warnings    : MutableMessages
    override val errors      : MutableMessages
    val information : Information
    /**
     * 为列表添加语法糖级别信息
     * @param context 信息内容
     * @receiver 信息回收器
     * @return null
     */
    infix fun Tree.sugar(context: String) = template(context, sugar)
    /**
     * 为列表添加提示级别信息
     * @param context 信息内容
     * @receiver 信息回收器
     * @return null
     */
    infix fun Tree.tip(context: String) = template(context, tips)
    /**
     * 为列表添加语警告级别信息
     * @param context 信息内容
     * @receiver 信息回收器
     * @return null
     */
    infix fun Tree.warning(context: String) = template(context, warnings)
    /**
     * 为列表添加错误级别信息
     * @param context 信息内容
     * @receiver 信息回收器
     * @return null
     */
    infix fun Tree.error(context: String) = template(context, errors)
    /**
     * 该函数用于解构,返回语法糖级别消息列表
     * @return 语法糖级别消息列表
     */
    operator fun component1() : MutableMessages
    /**
     * 该函数用于解构,返回提示级别消息列表
     * @return 提示级别消息列表
     */
    operator fun component2() : MutableMessages
    /**
     * 该函数用于解构,返回警告级别消息列表
     * @return 警告级别消息列表
     */
    operator fun component3() : MutableMessages
    /**
     * 该函数用于解构,返回错误级别消息列表
     * @return 错误级别消息列表
     */
    operator fun component4() : MutableMessages
    /**
     * 将目标信息的所有消息追加到自身
     * @param other 目标信息
     */
    operator fun plusAssign(other : Information) {
        sugar.addAll(other.sugar)
        tips.addAll(other.tips)
        warnings.addAll(other.warnings)
        errors.addAll(other.errors)
    }
    companion object : ()->MessageCollector{
        /**
         * 创建一个默认的信息收集器
         * @return 默认信息收集器
         */
        override fun invoke() : MessageCollector {
            /**
             * 信息收集器的实现,不使用匿名对象的原因有两点
             *
             * 1.匿名对象没有类名,打印出来不是很好看
             *
             * 2.数据类会自动生成大量的模板函数
             * @property sugar 语法糖级别信息
             * @property tips 提示级别信息
             * @property warnings 警告级别信息
             * @property errors 错误级别信息
             * @property information 只读信息副本 调用该属性获取本信息收集器的只读信息副本
             */
            data class MessageCollectorImplementation(
                override val sugar    : MutableMessages = mutableListOf(),
                override val tips     : MutableMessages = mutableListOf(),
                override val warnings : MutableMessages = mutableListOf(),
                override val errors   : MutableMessages = mutableListOf(),
            ) : MessageCollector {
                override val information get() = copy()
            }
            return MessageCollectorImplementation()
        }
    }
}
/**
 * ps:这个是一个模板函数,因此必须内联
 * 为列表添加信息
 * @param context 信息内容
 * @receiver 信息回收器
 * @return null
 */
@Suppress("NOTHING_TO_INLINE")
private inline fun Tree.template(context: String,list: MutableMessages) = null.also {
    if (this is InnerTree)
        list.add(Message(line, column, context))
    else
        list.add(Message(-1, -1, context))
}
/**
 * 工程树构建器
 * @property name 工程名
 * @property files 工程文件列表
 */
@TreeBuilderDsl(ProjectTree::class)
class ProjectTreeBuilder:TreeDsl<ProjectTree>,MessageCollector by MessageCollector(){
    var name : ID = "未命名"
    val files = mutableListOf<FileTree>()
    override fun onBuilding() = ProjectTree(name,files)
}

/**
 * 文件树构建器
 * @property name 文件名
 * @property tops 文件顶层语法树列表
 */
@TreeBuilderDsl(FileTree::class)
class FileTreeBuilder:TreeDsl<FileTree>,MessageCollector by MessageCollector(){
    var name : ID = "未命名"
    val tops = mutableListOf<TopTree>()
    override fun onBuilding() = FileTree(name,tops)
}

/**
 * 变量语法树构建器
 * @property name 变量名
 * @property type 变量类型
 * @property value 变量值
 * @property line 变量所在行
 * @property column 变量所在列
 */
@TreeBuilderDsl(VariableTree::class)
class VariableTreeBuilder:TreeDsl<VariableTree>,MessageCollector by MessageCollector(){
    var name : ID = "未命名"
    var type : TypeTree? = null
    var value : ExpressionTree? = null
    var line = -1
    var column = -1
    override fun onBuilding() = VariableTree(
        line = line,
        column = column,
        name = name,
        returnType = type,
        value = value,
    )
}
