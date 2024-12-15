package com.example.mytestapplication.screen.common

import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.atomic.AtomicInteger

//Решение гонки с атомарными переменными
class RaceConditionWithAtomic {
    private var counter = AtomicInteger(0)

    private fun increment(onCounterUpdated: (Int) -> Unit) {
        repeat(1_000) {
            onCounterUpdated(counter.incrementAndGet())
        }
    }

    fun runExample(onCounterUpdated: (Int) -> Unit) = runBlocking {
        val job1 = launch { increment(onCounterUpdated) }
        val job2 = launch { increment(onCounterUpdated) }

        joinAll(job1, job2)
    }
}