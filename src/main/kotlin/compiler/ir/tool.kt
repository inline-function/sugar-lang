@file:OptIn(ExperimentalUnsignedTypes::class)

package compiler.ir

fun String.toUByteArray() = toByteArray().asUByteArray()

val UByteArray.string : String get() = String(toByteArray())