import compiler.antlr.toSugarTree
import compiler.kotlin.generateKotlinProject
import compiler.semantic.Annotation
import compiler.semantic.AnnotationProcessor
import compiler.semantic.AnnotationValue
import compiler.semantic.ProjectTree
import compiler.semantic.semanticAnalysis
import tools.SideEffect
import java.nio.file.Files
import java.nio.file.Paths

/**
 * 对action进行指定次数的计时输出
 * @param times 循环的次数
 * @param action 所执行动作
 * @see SideEffect
 * @author 语法糖味函子酱(sugared functor)
 */
@SideEffect
inline fun time(times : Int = 1,action : (Int)->Unit){
    println("-------------开始执行-------------")
    val start = System.currentTimeMillis()
    repeat(times,action)
    println("------------耗时${(System.currentTimeMillis() - start) / 1000.0}秒------------")
}
/**
 * 主函数,程序的启动入口
 * @author 语法糖味函子酱(sugared functor)
 * @see SideEffect
 */
@SideEffect
fun main() = time{
    val path = "F:/JavaProjrct/sugerLang/docs/build"
    """
        |@jvm_function("sugar.Core:println")
        |fun print(text : Str)
        |fun main() {
        |    /*
        |    val lambda : (Int)=>Int = { a =>
        |       a
        |    }
        |    */
        |    print("Hello,World!")
        |}
    """
        .trimMargin()
        .toSugarTree(showTree = true,fileName = "main")
        .run {
            copy(
                files = files + Files.readString(
                    Paths.get("F:\\JavaProjrct\\sugerLang\\core\\core.bst")
                ).toSugarTree(fileName = "core").files
            )
        }
        .semanticAnalysis(annotations,apts)
        .apply {
            println("语义分析后的抽象语法树 : $first\n语义分析信息:\n$second\n生成kotlin项目至 : $path")
        }
        .first
        .generateKotlinProject(path)
//        .apply {
//            println("IR:\n$this")
//        }
//        .run { VirtualMachine.start(this) }
}
val annotations = listOf(
    Annotation("jvm_function",AnnotationValue.STR),
    Annotation("mut"),
)
val apts : List<AnnotationProcessor> = emptyList()