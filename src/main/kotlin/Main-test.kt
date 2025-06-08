import org.jetbrains.kotlin.cli.common.ExitCode
import org.jetbrains.kotlin.cli.jvm.K2JVMCompiler
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinToJVMBytecodeCompiler
import org.jetbrains.kotlin.config.CompilerConfiguration

fun main() {
    val compiler = K2JVMCompiler()
    // 2. 配置编译器参数
    val args = mutableListOf(
        "-d", "F:\\JavaProjrct\\sugerLang\\docs\\clses",            // 输出目录
        "-classpath", "F:\\JavaProjrct\\sugerLang\\libs\\kotlin-stdlib.jar", // 依赖路径
        "F:\\JavaProjrct\\sugerLang\\docs\\test.kt"                  // 要编译的 Kotlin 文件
    )
    // 3. 执行编译
    val exitCode = compiler.exec(
        errStream = System.err,
        args = args.toTypedArray()
    )
    if (exitCode.code == 0) {
        println("编译成功！")
    } else {
        println("编译失败（Exit Code: $exitCode）")
    }
}