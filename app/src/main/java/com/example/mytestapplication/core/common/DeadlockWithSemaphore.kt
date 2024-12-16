package com.example.mytestapplication.core.common

import java.util.concurrent.CountDownLatch
import kotlin.concurrent.thread

//Взаимная блокировка с Semaphore
class DeadlockWithSemaphore {
    private val latch1 = CountDownLatch(1)
    private val latch2 = CountDownLatch(1)

    private fun method1(onResult: (String) -> Unit) {
        thread {
            onResult("Поток 1 отправляет сообщение в канал 1")
            latch1.await()
            onResult("Поток 1: захватил замок 1")
            Thread.sleep(100)
            onResult("Поток 1: пытается захватить замок 2")
            latch2.await()
            onResult("Поток 1: захватил замок 2")
        }
    }

    private fun method2(onResult: (String) -> Unit) {
        thread {
            onResult("Поток 2: ожидает захвата замка 1")
            latch1.countDown()
            onResult("Поток 2: захватил замок 1")
            Thread.sleep(100)
            onResult("Поток 2: пытается захватить замок 2")
            latch2.countDown()
            onResult("Поток 2: захватил замок 2")
        }
    }

    fun runExample(onResult: (String) -> Unit) {
        method1(onResult)
        method2(onResult)
    }
}