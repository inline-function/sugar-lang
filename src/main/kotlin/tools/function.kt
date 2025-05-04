/**
 * 本文件定义杂七杂八的工具函数
 * 编写时kt版本 2.1.0
 * 编写时时间   2025.4.19
 * 创建者      语法糖味函子酱(sugared functor)
 */
package tools

/**
 * 将字符串重复n次
 * @param n 重复次数
 * @receiver 需要重复的字符串
 * @return 重复n次的字符串
 */
operator fun String.times(n: Int) =
    buildString {
        repeat(n) {
            append(this@times)
        }
    }
/**
 * 将字符串重复n次
 * @param s 需要重复的字符串
 * @receiver 重复次数
 * @return 重复n次的字符串
 */
operator fun Int.times(s: String) =
    buildString {
        repeat(this@times) {
            append(s)
        }
    }
/**
 * 抛出异常
 * @param message 异常信息
 */
@Suppress("NOTHING_TO_INLINE")
@SideEffect
inline fun exception(message: String) : Nothing =
    throw Exception(message)
/**
 * 省略if
 * @receiver 条件
 * @param then 条件为真时的执行体
 * @return 条件为真时返回then的执行结果，否则返回null
 */
inline operator fun <R> Boolean.invoke(then : ()->R) =
    if (this) then() else null
/**
 * 省略if
 * @receiver 条件
 * @param then 条件为真时的执行体
 * @return 条件为真时返回then的执行结果，否则返回else的执行结果
 */
inline operator fun <R> Boolean.invoke(then : ()->R,`else` : ()->R) =
    if (this) then() else `else`()