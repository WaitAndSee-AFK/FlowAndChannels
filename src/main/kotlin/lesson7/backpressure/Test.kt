package org.example.lesson7.backpressure

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher)

fun main() {
    val flow = flow {
        repeat(10) {
            println("Emitted: $it")
            emit(it)
            println("After emit: $it")
            kotlinx.coroutines.delay(100)
        }
    }.buffer(capacity = 10, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    scope.launch {
        flow.collect {
            println("Collected: $it")
            delay(1000)
            println(it)
        }
    }
}