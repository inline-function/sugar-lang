class Unit
@jvm_name("String")
class Str
@jvm_name("Double")
class Dec
class Int {
    fun add(arg : Int) : Int
}
class Function
class Function0<R> : Function {
    fun invoke() : R
}
class Function1<A,R> : Function {
    fun invoke(a : A) : R
}