package com.example.mytestapplication.core.common

import android.util.Log

//Взаимная блокировка
class DeadlockExample {
    private val lock1 = Any()
    private val lock2 = Any()

    private fun method1(onResult: (String) -> Unit) {
        Thread {
            synchronized(lock1) {
                onResult("Поток 1 замок 1")
                Log.d("MyLog", "Поток 1 пытается захватить замок 2")
                synchronized(lock2) {
                    onResult("Поток 1 замок 2")
                }
            }
        }.start()
    }

    private fun method2(onResult: (String) -> Unit) {
        Thread {
            synchronized(lock2) {
                onResult("Поток 2 замок 2")
                Log.d("MyLog", "Поток 2 пытается захватить замок 1")
                synchronized(lock1) {
                    onResult("Поток 2 замок 1")
                }
            }
        }.start()
    }

    fun runExample(onResult: (String) -> Unit) {
        method1(onResult)
        method2(onResult)
    }
}