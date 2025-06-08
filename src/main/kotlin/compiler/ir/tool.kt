@file:OptIn(ExperimentalUnsignedTypes::class)

package compiler.ir

fun String.toUByteArray() = toByteArray().asUByteArray()

val UByteArray.string : String get() = String(toByteArray())

inline fun <T1,R> context(p1 : T1,block : context(T1) (T1)->R) = p1.run { block(p1) }
inline fun <T1,T2,R> context(p1 : T1,p2 : T2,block : context(T1,T2) (T1,T2)->R) = p1.run { p2.run { block(p1,p2) } }
inline fun <T1,T2,T3,R> context(p1 : T1,p2 : T2,p3 : T3,block : context(T1,T2,T3) (T1,T2,T3)->R) = p1.run { p2.run { p3.run { block(p1,p2,p3) } } }
inline fun <T1,T2,T3,T4,R> context(p1 : T1,p2 : T2,p3 : T3,p4 : T4,block : context(T1,T2,T3,T4) (T1,T2,T3,T4)->R) = p1.run { p2.run { p3.run { p4.run { block(p1,p2,p3,p4) } } } }
inline fun <T1,T2,T3,T4,T5,R> context(p1 : T1,p2 : T2,p3 : T3,p4 : T4,p5 : T5,block : context(T1,T2,T3,T4,T5) (T1,T2,T3,T4,T5)->R) = p1.run { p2.run { p3.run { p4.run { p5.run { block(p1,p2,p3,p4,p5) } } } } }