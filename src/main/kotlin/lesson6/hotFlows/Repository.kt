package org.example.lesson6.hotFlows

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

object Repository {
    private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
    private val scope = CoroutineScope(dispatcher)

    private val _timer = MutableSharedFlow<Int>()
    val timer = _timer.asSharedFlow()

    init {
        scope.launch {
            var second = 0
            while (true) {
                println("Emitted $second")
                _timer.emit(second++)
                delay(1000)
            }
        }
    }

    private fun getTimerFlor() : Flow<Int> {
        return flow {
            var second = 0
            while (true) {
                emit(second++)
                delay(1000)
            }
        }
    }
}