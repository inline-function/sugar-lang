/**
 * 本文件定义一些用到的工具常量
 * 编写时kt版本 2.1.0
 * 编写时时间   2025.4.16
 * 创建者      语法糖味函子酱(sugared functor)
 */
package tools

/**
 * 绝对不可能被求值的变量
 * @throws Error 被求值
 */
inline val never : Nothing
    @SideEffect
    get() = throw Error()
/**
 * 标准缩进
 * @return 缩进字符串
 */
const val standardIndent = "    "
