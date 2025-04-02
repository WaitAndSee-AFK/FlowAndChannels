package org.example.lesson8.exceptions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher)

fun main() {
    val flow = getFlow()
    scope.launch {
        flow
            .retry(3)
            .catch { println("Exception caught") }
            .collect {
            println(it)
        }
    }
}

private fun getFlow() = flow {
    repeat(5) {
        emit(it)
    }
    error("Exception caught in flow")
}