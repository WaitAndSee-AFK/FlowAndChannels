package org.example.lesson5.coldFlows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object Repository {
    val timer = getTimerFlor()
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