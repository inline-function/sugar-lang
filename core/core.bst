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
class Function2<A,B,R> : Function {
    fun invoke(a : A,b : B) : R
}
class Function3<A, B, C, R> : Function {
    fun invoke(a: A, b: B, c: C): R
}
class Function4<A, B, C, D, R> : Function {
    fun invoke(a: A, b: B, c: C, d: D): R
}
class Function5<A, B, C, D, E, R> : Function {
    fun invoke(a: A, b: B, c: C, d: D, e: E): R
}
class Function6<A, B, C, D, E, F, R> : Function {
    fun invoke(a: A, b: B, c: C, d: D, e: E, f: F): R
}
class Function7<A, B, C, D, E, F, G, R> : Function {
    fun invoke(a: A, b: B, c: C, d: D, e: E, f: F, g: G): R
}
class Function8<A, B, C, D, E, F, G, H, R> : Function {
    fun invoke(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H): R
}
class Function9<A, B, C, D, E, F, G, H, I, R> : Function {
    fun invoke(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I): R
}
class Function10<A, B, C, D, E, F, G, H, I, J, R> : Function {
    fun invoke(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J): R
}
class Function11<A, B, C, D, E, F, G, H, I, J, K, R> : Function {
    fun invoke(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K): R
}
class Function12<A, B, C, D, E, F, G, H, I, J, K, L, R> : Function {
    fun invoke(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L): R
}
class Function13<A, B, C, D, E, F, G, H, I, J, K, L, M, R> : Function {
    fun invoke(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M): R
}
class Function14<A, B, C, D, E, F, G, H, I, J, K, L, M, N, R> : Function {
    fun invoke(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N): R
}
class Function15<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, R> : Function {
    fun invoke(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O): R
}
class Function16<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, R> : Function {
    fun invoke(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P): R
}
class Function17<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R> : Function {
    fun invoke(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q): R
}
@jvm_name("println")
fun print(text : Str)