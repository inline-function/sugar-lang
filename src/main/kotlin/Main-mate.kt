import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.runBlocking

class ResumableBody<E : Throwable,R>(
    val body : suspend ResumableBody<E,R>.() -> Unit,
){
    lateinit var resuming : ResumingBody<E,R>
    val riseChannel = Channel<E>(capacity = 1)
    val resumeChannel = Channel<R>(capacity = 1)
    suspend fun rise(exc : E) : R {
        riseChannel.send(exc)
        resuming()
        return resumeChannel.receive()
    }
}
class ResumingBody<E : Throwable,R>(
    val resumable : ResumableBody<E,R>,
    val body : suspend ResumingBody<E,R>.(E) -> Unit
) {
    init {
        resumable.resuming = this
    }
    suspend operator fun invoke() {
        body(resumable.riseChannel.receive())
    }
    suspend fun resume(value : R) {
        resumable.resumeChannel.send(value)
    }
}
fun <E : Throwable,R> trying(block : suspend ResumableBody<E,R>.() -> Unit) : ResumableBody<E,R> =
    ResumableBody(block)
infix fun <E : Throwable,R> ResumableBody<E,R>.catch(block : suspend ResumingBody<E,R>.(E) -> Unit){
    runBlocking {
        val body = ResumingBody(this@catch,block)
        body.resumable.body(body.resumable)
    }
}
//可恢复异常
fun main() {
    trying {
        println(1)
        repeat(rise(RuntimeException("4"))){
            println(114)
        }
        repeat(rise(RuntimeException("5"))){
            println(514)
        }
        println(2)
    } catch { err ->
        println(3)
        resume(err.message?.toInt() ?: 0)
        println(4)
    }
}
