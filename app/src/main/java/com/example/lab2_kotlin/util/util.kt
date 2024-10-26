package com.example.lab2_kotlin.util

fun calculate(epsilon: Float): Triple<Float, Float, Int> {
    var sum = 0f
    var lastTerm = 0f
    var iterations = 0

    for (n in 1..100) {
        lastTerm = 1f / (n * n)
        sum += lastTerm
        iterations++

        if (lastTerm <= epsilon) {
            break
        }
    }

    return Triple(sum, lastTerm, iterations)
}