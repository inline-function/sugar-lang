package tools

data class Box<T>(
    var value : T
)
operator fun Box<Int>.plusAssign(value : Any) {
    this.value += value.toString().lines().size
}
operator fun Box<Int>.plusAssign(value : Int) {
    this.value += value
}