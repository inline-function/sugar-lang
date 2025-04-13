/**
 * 本文件定义关于匿名函数与高阶函数相关工具
 * 编写时kt版本 2.1.0
 * 编写时时间   2025.4.4
 * 创建者      语法糖味函子酱(sugared functor)
 */
@file:Suppress("NOTHING_TO_INLINE","FunctionWithLambdaExpressionBody")

package tools
/**
 * 创建可以递归的匿名函数,返回值与参数不同,因此需要多指定一个类型参数
 * @param code 匿名函数体
 * @return 匿名函数
 */
fun <A,R> function(code : ((A)->R).(A)->R) : (A)->R {
    lateinit var fn : (A)->R
    fun f(a : A) : R = code(fn,a)
    fn = ::f
    return { code(fn,it) }
}
/**
 * 创建可以递归的匿名函数,返回值与参数相同,因此需要不需要指定类型参数
 * @param code 匿名函数体
 * @return 匿名函数
 */
fun <A> lambda(code : ((A)->A).(A)->A) : (A)->A {
    lateinit var fn : (A)->A
    fun f(a : A) : A = code(fn,a)
    fn = ::f
    return { code(fn,it) }
}
/**
 * 像管道运算符一样把后值传给前函数
 * @receiver 前函数
 * @param arg 后值
 * @return 被传参函数的返回值
 */
inline infix fun <A,R> ((A)->R).invoke(arg : A) : R = this(arg)
/**
 * 像管道运算符一样把前值传给后函数
 * @receiver 前值
 * @param function 被传参函数
 * @return 被传参函数的返回值
 */
inline infix fun <T,R> T.input(function : (T)->R) : R = function(this)
/**
 * 像管道运算符一样把前值传给后函数,如果值是空的话则不传递
 * @receiver 可空前值
 * @param function 被传参函数
 * @return 被传参函数的返回值,或者空
 */
inline infix fun <T,R> T?.mayInput(function : (T)->R) : R? = this?.let(function)
/**
 * 把前参数传给后函数,返回这个函数的部分应用函数格式,
 * 单参时返回无参函数,不会直接调用
 * @receiver 前函数
 * @param a 后参数
 * @return 部分应用后的函数
 */
inline operator fun <A,R> ((A)->R).minus(a : A): () -> R = { this(a) }
inline operator fun <A,B,R> ((A,B)->R).minus(a : A): (B) -> R = { b -> this(a, b) }
inline operator fun <A,B,C,R> ((A,B,C)->R).minus(a : A): (B, C) -> R = { b, c -> this(a, b, c) }
inline operator fun <A,B,C,D,R> ((A,B,C,D)->R).minus(a : A): (B, C, D) -> R = { b, c, d -> this(a, b, c, d) }
inline operator fun <A,B,C,D,E,R> ((A,B,C,D,E)->R).minus(a : A): (B, C, D, E) -> R = { b, c, d, e -> this(a, b, c, d, e) }
/**
 * 把两个函数组合成一个函数,丢弃前一个函数的值
 * @receiver 前函数
 * @param f 后函数
 * @return 组合后的函数
 */
inline operator fun <R1,R2> (()->R1).plus(crossinline f : ()->R2) = { this() ; f() }
/**
 * 把两个函数组合成一个函数,前一个函数的值为后一个的参数
 * @receiver 前函数
 * @param f 后函数
 * @return 组合后的函数
 */
inline operator fun <R1,R2> (()->R1).plus(crossinline f : (R1)->R2) = { f(this()) }
/**
 * 为前一个函数添加异常处理函数
 * @receiver 前函数
 * @param f 异常处理函数
 * @return 组合后的函数
 */
inline infix fun <R,reified E : Throwable> (()->R).catch(crossinline f : (E)->R) = {
    try {
        this()
    } catch (e : Throwable) {
        if (e is E) f(e) else throw e
    }
}
inline infix fun <A,R,reified E : Throwable> ((A)->R).catch(crossinline f : (E)->R) = {
    a : A ->
    try {
        this(a)
    } catch (e : Throwable) {
        if (e is E) f(e) else throw e
    }
}
inline infix fun <A,B,R,reified E : Throwable> ((A,B)->R).catch(crossinline f : (E)->R) = {
    a : A,b : B ->
    try {
        this(a,b)
    } catch (e : Throwable) {
        if (e is E) f(e) else throw e
    }
}
inline infix fun <A,B,C,R,reified E : Throwable> ((A,B,C)->R).catch(crossinline f : (E)->R) = {
    a : A,b : B,c : C ->
    try {
        this(a,b,c)
    } catch (e : Throwable) {
        if (e is E) f(e) else throw e
    }
}
/**
 * 为前一个函数添加始终处理函数
 * @receiver 前函数
 * @param f 始终处理函数
 * @return 组合后的函数
 */
inline infix fun <R> (()->R).finally(crossinline f : ()->R) = {
    try {
        this()
    } finally {
        f()
    }
}
inline infix fun <A,R> ((A)->R).finally(crossinline f : ()->R) = {
    a : A ->
    try {
        this(a)
    } finally {
        f()
    }
}
inline infix fun <A,B,R> ((A,B)->R).finally(crossinline f : ()->R) = {
    a : A,b : B ->
    try {
        this(a,b)
    } finally {
        f()
    }
}
inline infix fun <A,B,C,R> ((A,B,C)->R).finally(crossinline f : ()->R) = {
    a : A,b : B,c : C ->
    try {
        this(a,b,c)
    } finally {
        f()
    }
}