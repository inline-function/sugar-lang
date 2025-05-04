/**
 * 本文件定义"结果"类型相关事宜
 * 编写时kt版本 2.1.0
 * 编写时时间   2025.5.4
 * 创建者      语法糖味函子酱(sugared functor)
 */
package tools

data class Result<out V,out E>(val value : V?,val error1 : E?)
fun <V : R,E,R> Result<V,E>.catch(action : E.()->R) : R = value ?: error1!!.action()