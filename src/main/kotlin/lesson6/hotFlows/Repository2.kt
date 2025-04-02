package org.example.lesson6.hotFlows

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

object Repository2 {
    private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
    private val scope = CoroutineScope(dispatcher)

//    private val timer = MutableSharedFlow<Int>().apply {
//        scope.launch {
//            getTimerFlor().collect {
//                emit(it)
//            }
//        }
//    }.asSharedFlow()

    val timer = getTimerFlor().shareIn(
        scope = scope,
        started = SharingStarted.Eagerly
    )

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