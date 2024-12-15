package com.example.mytestapplication.screen.common

import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class RaceConditionWithMutex {
    private var counter = 0
    private val mutex = Mutex()

    private suspend fun increment(onCounterUpdated: (Int) -> Unit) {
        repeat(1_000) {
            mutex.withLock {
                counter++
                onCounterUpdated(counter)
            }
        }
    }

    fun runExample(onCounterUpdated: (Int) -> Unit) = runBlocking {
        val job1 = launch { increment(onCounterUpdated) }
        val job2 = launch { increment(onCounterUpdated) }

        joinAll(job1, job2)
    }
}