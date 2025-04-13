/**
 * 本文件定义关于注释形式注解相关事宜
 * 编写时kt版本 2.1.0
 * 编写时时间   2025.4.4
 * 创建者      语法糖味函子酱(sugared functor)
 */
package tools
/**
 * 该注解警示该函数具有副作用
 */
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.FUNCTION)
annotation class SideEffect