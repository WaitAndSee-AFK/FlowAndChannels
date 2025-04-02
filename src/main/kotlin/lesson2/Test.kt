package org.example.lesson2

import kotlin.random.Random

fun main() {
    var filterCount = 0
    var mapCount = 0


//    Example №1
//    generateSequence {
//        Random.nextInt(1000)
//    }.filter {
//        filterCount++
//        it % 2 == 0
//    }.map {
//        mapCount++
//        it * 10
//    }.take(10).forEach(::println)


//    Example №2
//    generateSequence(0) {
//        it + 1
//    }.filter {
//        filterCount++
//        it % 2 == 0
//    }.map {
//        mapCount++
//        it * 10
//    }.take(10).forEach(::println)


//    Example №3
    sequence<Int> {
        println("Start generation")
        yield(0)
        println("Random generation")
        repeat(1000) {
            yield(Random.nextInt(1000))
        }
    }.filter {
        filterCount++
        it % 2 == 0
    }.map {
        mapCount++
        it * 10
    }.take(10).forEach(::println)
    println("Filtered $filterCount")
    println("Mapped $mapCount")
}