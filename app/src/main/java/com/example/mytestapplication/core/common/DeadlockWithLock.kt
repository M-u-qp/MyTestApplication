package com.example.mytestapplication.core.common

import android.util.Log
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.thread

//Взаимная блокировка c Lock
class DeadlockWithLock {
    private val lock1 = ReentrantLock()
    private val lock2 = ReentrantLock()

    private fun method1(onResult: (String) -> Unit) {
        thread {
            lock1.lock()
            try {
                onResult("Поток 1 замок 1")
                Log.d("MyLog", "Поток 1 пытается захватить замок 2")
                Thread.sleep(100)
                lock2.lock()
                try {
                    onResult("Поток 1 замок 2")
                } finally {
                    lock2.unlock()
                }
            } finally {
                lock1.unlock()
            }
        }
    }

    private fun method2(onResult: (String) -> Unit) {
        thread {
            lock1.lock()
            try {
                onResult("Поток 2 замок 2")
                Log.d("MyLog", "Поток 2 пытается захватить замок 1")
                Thread.sleep(100)
                lock2.lock()
                try {
                    onResult("Поток 2 замок 1")
                } finally {
                    lock2.unlock()
                }
            } finally {
                lock1.unlock()
            }
        }
    }

    fun runExample(onResult: (String) -> Unit) {
        method1(onResult)
        method2(onResult)
    }
}