/* 假设这是源代码
* class A {
*     var number
* }
* class B {
*     var number
* }
* fun main() {
*     var any = if(time % 2 == 0) {
*         A()
*     } else {
*         B()
*     }
*     any.number //此时程序不知道该调用A的number还是B的number
* }
* */
//假设这是编译后的代码
class A {
    val number: Any? = null
}
class B {
    val number: Any? = null
}
fun main() {
    val any: Any? = if(System.currentTimeMillis() % 2 == 0L) {
        A()
    } else {
        B()
    }
    //此时程序进行模式匹配
    when(any){
        is A -> any.number
        is B -> any.number
        else -> throw Error()
    }
}
