/**
 * 本文件定义"要么"类型相关事宜
 * 编写时kt版本 2.1.0
 * 编写时时间   2025.4.13
 * 创建者      语法糖味函子酱(sugared functor)
 */
package tools

/**
 * 要么类型，用于表示一个值可能是左值也可能是右值
 */
sealed interface Either<out L, out R>
/**
 * 要么类型的左值
 */
@JvmInline
value class Left<out L>(val value : L) : Either<L, Nothing>
/**
 * 要么类型的右值
 */
@JvmInline
value class Right<out R>(val value : R) : Either<Nothing, R>
