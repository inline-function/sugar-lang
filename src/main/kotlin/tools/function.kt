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