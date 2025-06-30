package compiler.semantic

import tools.ID

////注解
//data class Annotation(
//    val name : ID,
//    val value : AnnotationValue? = null
//)
////注解值枚举
//enum class AnnotationValue(
//    val text : ID
//) {
//    INT("整数"),DEC("小数"),STR("文本");
//    override fun toString() = text
//}
////注解处理器
//fun interface AnnotationProcessor : (ProjectTree)->ProjectTree {
//    override fun invoke(p1 : ProjectTree) : ProjectTree = p1.process()
//    fun ProjectTree.process() : ProjectTree
//}
////寻找具有对应注解名称的结构并将其应用给指定函数(暂不支持局部成员)
//inline fun <reified T : TopTree> ProjectTree.visitWithAnnotation(
//    annName : ID,action : T.() -> T
//) : ProjectTree = copy(
//    files = files.map { file ->
//        file.copy(
//            tops = file.tops.map { top ->
//                if(top is ClassTree)
//                    top.copy(
//                        members = top.members.map { member ->
//                            if (member is T && member.annotations.any { it.name == annName })
//                                member.action() as CallableTree
//                            else
//                                member
//                        }
//                    )
//                else
//                    if (top is T && top.annotations.any { it.name == annName })
//                        top.action()
//                    else
//                        top
//            }
//        )
//    }
//)