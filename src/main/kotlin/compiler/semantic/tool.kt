package compiler.semantic

import tools.exception

/**
 * 获取父语法树
 * @receiver 父语法树获取函数
 * @return 父语法树
 * @throws Exception 如果父语法树为空则抛出异常
 */
inline val (()->Tree)?.check : InnerTree
    get() = this?.invoke() as? InnerTree ?: exception(nullParentMessage)
/**
 * 当父树为空时使用的异常信息
 */
const val nullParentMessage = "父树为空,语法树构建失败"