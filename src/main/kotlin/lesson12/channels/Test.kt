package org.example.lesson12.channels

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import java.util.concurrent.Executors

private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher)

private val channel = Channel<Int>()

fun main() {
    scope.launch {
        repeat(100) {
            println("1 is setting")
            channel.send(1)
            println("1 was sent")
            delay(100)
        }
    }
    scope.launch {
        repeat(100) {
            println("2 is setting")
            channel.send(2)
            println("2 was sent")
            delay(100)
        }
    }

    scope.launch {
        channel.consumeEach {
            println("Costumer 1: $it")
        }
    }
    scope.launch {
        channel.consumeEach {
            println("Costumer 2: $it")
        }
    }
}