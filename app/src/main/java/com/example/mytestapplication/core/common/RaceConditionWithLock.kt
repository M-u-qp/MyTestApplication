package com.example.mytestapplication.core.common

import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.thread

//Состояние гонки c Lock
class RaceConditionWithLock {
    private var counter = 0
    private val lock = ReentrantLock()

    private fun increment(): Int {
        lock.lock()
        try {
            counter += 1
            return counter
        } finally {
            lock.unlock()
        }
    }

    fun runExample(onCounterUpdated: (Int) -> Unit) {
        val thread1 = thread { repeat(1000) { onCounterUpdated(increment()) } }
        val thread2 = thread { repeat(1000) { onCounterUpdated(increment()) } }

        thread1.join()
        thread2.join()
    }
}