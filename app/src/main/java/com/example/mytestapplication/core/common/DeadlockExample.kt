package com.example.mytestapplication.core.common

import android.util.Log

//Взаимная блокировка
class DeadlockExample {
    private val lock1 = Any()
    private val lock2 = Any()

    private fun method1(onResult: (String) -> Unit) {
        synchronized(lock1) {
            onResult("Поток 1 замок 1")
            Log.d("MyLog", "Поток 1 пытается захватить замок 2")
            Thread.sleep(1000)
            synchronized(lock2) {
                onResult("Поток 1 замок 2")
            }
        }
    }

    private fun method2(onResult: (String) -> Unit) {
        synchronized(lock2) {
            onResult("Поток 2 замок 2")
            Log.d("MyLog", "Поток 2 пытается захватить замок 1")
            Thread.sleep(1000)
            synchronized(lock1) {
                onResult("Поток 2 замок 1")
            }
        }
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