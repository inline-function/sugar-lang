/**
 * 本文件定义关于注释形式注解相关事宜
 * 编写时kt版本 2.1.0
 * 编写时时间   2025.4.4
 * 创建者      语法糖味函子酱(sugared functor)
 */
package tools

import kotlin.annotation.AnnotationRetention.SOURCE
import kotlin.annotation.AnnotationTarget.*
import kotlin.reflect.KClass

/**
 * 该注解警示该函数具有副作用
 */
@Retention(SOURCE)
@Target(FUNCTION,PROPERTY_GETTER,PROPERTY_SETTER)
annotation class SideEffect(
    val about : String = ""
)
/**
 * 该注解为类自动生成构建器模板代码
 */
@Retention(SOURCE)
@Target(CLASS)
@DslMarker
annotation class Builder