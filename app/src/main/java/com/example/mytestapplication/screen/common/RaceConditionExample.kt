package com.example.mytestapplication.screen.common

import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//Состояние гонки
class RaceConditionExample {
    private var counter = 0

    private fun increment(onCounterUpdated: (Int) -> Unit) {
        repeat(1_000) {
            onCounterUpdated(counter++)
        }
    }

    fun runExample(onCounterUpdated: (Int) -> Unit) = runBlocking {
        val job1 = launch { increment(onCounterUpdated) }
        val job2 = launch { increment(onCounterUpdated) }

        joinAll(job1, job2)
    }
}