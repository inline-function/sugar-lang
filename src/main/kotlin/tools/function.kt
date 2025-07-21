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
infix fun <E,R,T> List<Pair<E,T>>.zip(list : List<R>) : List<Triple<E,T,R>> =
    mapIndexed { index,(e,t) -> Triple(e,t,list[index]) }
/**
 * 抛出异常
 * @param message 异常信息
 */
@Suppress("NOTHING_TO_INLINE")
@SideEffect
inline fun exception(message: String) : Nothing =
    throw Exception(message)
fun <A,R> context(a : A,block : context(A) (A)->R) : R = with(a) { block(a) }
fun <A,B,R> context(a : A,b : B,block : context(A,B) (A,B)->R) : R = with(a) { with(b) { block(a,b) } }
fun <A,B,C,R> context(a : A,b : B,c : C,block : context(A,B,C) (A,B,C)->R) : R = with(a) { with(b) { with(c) { block(a,b,c) } } }
fun <A,B,C,D,R> context(a : A,b : B,c : C,d : D,block : context(A,B,C,D) (A,B,C,D)->R) : R = with(a) { with(b) { with(c) { with(d) { block(a,b,c,d) } } } }
fun <A,B,C,D,E,R> context(a : A,b : B,c : C,d : D,e : E,block : context(A,B,C,D,E) (A,B,C,D,E)->R) : R = with(a) { with(b) { with(c) { with(d) { with(e) { block(a,b,c,d,e) } } } } }