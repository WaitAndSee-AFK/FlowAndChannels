package org.example.lesson6.hotFlows

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.concurrent.Executors
import kotlin.concurrent.thread

private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher)

private val sharedFlow = MutableSharedFlow<Int>(replay = 1)
private val stateFlow = MutableStateFlow(0)

fun main() {
//    emitFromCoroutine()
//    emitFromStandardMethod()

    sharedFlow.onEach {
        delay(1000)
        println("Shared flow: $it")
    }.launchIn(scope)

    stateFlow.onEach {
        delay(1000)
        println("State flow: $it")
    }.launchIn(scope)

    emitFromStandardMethod()
}

fun emitFromStandardMethod() {
    Thread.sleep(2000)
    thread {
        repeat(100) {
            sharedFlow.tryEmit(it)
        }
    }
    thread {
        repeat(100) {
            stateFlow.tryEmit(it)
        }
    }
}

fun emitFromCoroutine() {
    scope.launch {
        repeat(100) {
            sharedFlow.emit(it)
        }
    }

    scope.launch {
        repeat(100) {
            stateFlow.emit(it)
        }
    }
}
