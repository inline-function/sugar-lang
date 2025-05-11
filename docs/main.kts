interface A{
    val aaa : Int
    fun copy(a : Int = aaa) : B
}
data class B(
    override val aaa : Int
) : A
val b = B(1)
b.copy()