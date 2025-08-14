/**
 * 本文件定义关于注释形式注解相关事宜
 * 编写时kt版本 2.1.0
 * 编写时时间   2025.4.4
 * 创建者      语法糖味函子酱(sugared functor)
 */
package tools

import kotlin.annotation.AnnotationRetention.SOURCE
import kotlin.annotation.AnnotationTarget.*

/**
 * 该注解警示该函数具有副作用
 */
@Retention(SOURCE)
@Target(FUNCTION,PROPERTY_GETTER,PROPERTY_SETTER)
annotation class SideEffect(
    val about : Type
) {
    enum class Type {
        //函数会对文件进行读写
        IO,
        //函数会打印信息
        PRINT,
        //函数会改变外部变量
        CHANGE
    }
}
/**
 * 该注解警示该参数将会被污染
 */
@Retention(SOURCE)
@Target(VALUE_PARAMETER)
annotation class Pollutant