package org.example.lesson1

import kotlin.random.Random

// Это я учусь создавать ветки для GitHUB

fun main() {
    var filterCount = 0
    var mapCount = 0

    val list = mutableListOf<Int>().apply {
        repeat(1000) {
            add(Random.nextInt(1000))
        }
    }.asSequence()
    list.filter {
        filterCount++
        it % 2 == 0
    }.map {
        mapCount++
        it * 10
    }.take(10)

    println("Filtered $filterCount")
    println("Mapped $mapCount")
}