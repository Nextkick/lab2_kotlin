package com.example.lab2_kotlin.util

fun calculate(epsilon: Double): Triple<Double, Double, Int> {
    var sum = 0.0
    var lastTerm = 0.0
    var iterations = 0

    for (n in 1..1000) {
        lastTerm = 1.0 / (n * n)
        sum += lastTerm
        iterations++

        if (lastTerm < epsilon) {
            break
        }
    }

    return Triple(sum, lastTerm, iterations)
}