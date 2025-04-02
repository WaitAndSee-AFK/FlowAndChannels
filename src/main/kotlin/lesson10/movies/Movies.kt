package org.example.lesson10.movies

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toSet
import java.util.concurrent.Executors
import kotlin.time.measureTime

private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher)

fun main() {
    scope.launch {
        val time = measureTime {
            loadMovies()
        }
        println("Time: $time")
    }
}

private suspend fun loadMoviesIds(): List<Int> {
    delay(3000)
    return (0..100).toList()
}

private suspend fun loadMovieById(id: Int): String {
    delay(100)
    return "Movie: $id"
}

private suspend fun loadMovies(): List<String> {
    return supervisorScope {
        loadMoviesIds().map {
            scope.async {
                loadMovieById(it)
            }
        }.awaitAll()
    }
}