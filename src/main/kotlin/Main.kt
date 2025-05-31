import compiler.antlr.toSugarTree
import compiler.ir.sugar.toIrs
import compiler.semantic.Annotation
import compiler.semantic.AnnotationProcessor
import compiler.semantic.AnnotationValue
import compiler.semantic.ProjectTree
import compiler.semantic.semanticAnalysis
import tools.SideEffect
import vm.VMThread
import vm.VirtualMachine

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
    """
        |class Unit
        |class Str
        |class Int {
        |    fun add(arg : Int) : Int
        |}
        |class Function
        |class Function0<R> : Function {
        |    fun invoke() : R
        |}
        |class Function1<A,R> : Function {
        |    fun invoke(a : A) : R
        |}
        |@jvm_function("sugar.Core.println")
        |fun print(text : Str)
        |fun main() {
        |    val lambda : (Int)=>Int = { a =>
        |       a
        |    }
        |}
    """
        .trimMargin()
        .toSugarTree(showTree = true)
        .semanticAnalysis(annotations,apts)
        .apply {
            println("语义分析后的抽象语法树:\n$first\n语义分析信息:\n$second")
        }
        .first
        .toIrs()
        .apply {
            println("IR:\n$this")
        }
        .run { VirtualMachine.start(this) }
}
val annotations = listOf(
    Annotation("jvm_function",AnnotationValue.STR)
)
val apts : List<AnnotationProcessor> = emptyList()