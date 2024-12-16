package com.example.mytestapplication.core.common

import android.util.Log
import java.util.concurrent.CountDownLatch

//Взаимная блокировка с Semaphore
class DeadlockWithSemaphore {
    private val latch1 = CountDownLatch(1)
    private val latch2 = CountDownLatch(1)

    private fun method1(onResult: (String) -> Unit) {
        onResult("Поток 1 отправляет сообщение в канал 1")
        latch1.await()
        onResult("Поток 1: захватил замок 1")
        Log.d("MyLog", "Поток 1 пытается захватить замок 2")
        Thread.sleep(100)
        onResult("Поток 1: пытается захватить замок 2")
        latch2.await()
        onResult("Поток 1: захватил замок 2")
    }

    private fun method2(onResult: (String) -> Unit) {
        onResult("Поток 2: ожидает захвата замка 1")
        latch1.countDown()
        onResult("Поток 2: захватил замок 1")
        Log.d("MyLog", "Поток 2 пытается захватить замок 2")
        Thread.sleep(100)
        onResult("Поток 2: пытается захватить замок 2")
        latch2.countDown()
        onResult("Поток 2: захватил замок 2")
    }

    fun runExample(onResult: (String) -> Unit) {
        val thread1 = Thread { method1(onResult) }
        val thread2 = Thread { method2(onResult) }
        thread1.start()
        thread2.start()
        thread1.join()
        thread2.join()
    }
}