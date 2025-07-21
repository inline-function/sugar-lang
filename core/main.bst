fun main() {
    var i = 0
    var result : Str = self<Str>("ok")
    var t = {1}
    var f = {2}
    if<Int>(false,t,f)
}
fun self<T : Str>(arg : T) : T {
    var a = arg
    a
}
fun a(f : (Int)=>(Str)=>Any) {
    var b : (Int)=>Any = f
}
fun if<R>(bool : (()=>R,()=>R)=>R,t : ()=>R,f : ()=>R) : R {
    bool(t,f)
}
fun false<R>(t : ()=>R,f : ()=>R) : R {
    f()
}
fun true<R>(t : ()=>R,f : ()=>R) : R {
    t()
}
class A<out T : Str>