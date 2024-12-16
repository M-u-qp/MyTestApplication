package com.example.mytestapplication.core.common

import kotlin.concurrent.thread

//Состояние гонки
class RaceConditionExample {
    private var counter = 0

    private fun increment(onCounterUpdated: (Int) -> Unit) {
        repeat(1_000) {
            onCounterUpdated(counter++)
        }
    }

    fun runExample(onCounterUpdated: (Int) -> Unit) {
        val thread1 = thread { increment(onCounterUpdated) }
        val thread2 = thread { increment(onCounterUpdated) }

        thread1.join()
        thread2.join()
    }
}