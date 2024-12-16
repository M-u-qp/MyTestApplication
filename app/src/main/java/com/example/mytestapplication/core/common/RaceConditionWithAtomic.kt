package com.example.mytestapplication.core.common

import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

//Решение гонки с атомарными переменными
class RaceConditionWithAtomic {
    private var counter = AtomicInteger(0)

    @Synchronized
    private fun increment(): Int {
        return counter.incrementAndGet()
    }

    fun runExample(onCounterUpdated: (Int) -> Unit) {
        val thread1 = thread { repeat(1000) { onCounterUpdated(increment()) } }
        val thread2 = thread { repeat(1000) { onCounterUpdated(increment()) } }

        thread1.join()
        thread2.join()
    }
}