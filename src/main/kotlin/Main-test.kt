
import compiler.semantic.*
import tools.*
context(s : String)
fun p() = println(s)
fun main() {
    with("1"){
        with("2"){
            p()
        }
        p()
    }
}