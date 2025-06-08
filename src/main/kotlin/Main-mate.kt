import  E.*
enum class E {A,B,C}
val value : E get() = A
external fun E.doing()
fun main() {
    when(val value = value){
        A -> value.doing()
        B -> value.doing()
        C -> value.doing()
    }
}