package com.example.mytestapplication.screen.common

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

//Взаимная блокировка c Mutex
class DeadlockWithMutex {
    private val lock1 = Mutex()
    private val lock2 = Mutex()

    private fun method1(onResult: (String) -> Unit) {
        CoroutineScope(Dispatchers.Default).launch {
            lock1.withLock {
                onResult("Корутина 1 замок 1")
                Log.d("MyLog", "Корутина 1 пытается захватить замок 2")
                delay(100)
                lock2.withLock {
                    onResult("Корутина 1 замок 2")
                }
            }
        }
    }

    private fun method2(onResult: (String) -> Unit) {
        CoroutineScope(Dispatchers.Default).launch {
            lock2.withLock {
                onResult("Корутина 2 замок 2")
                Log.d("MyLog", "Корутина 2 пытается захватить замок 1")
                delay(100)
                lock1.withLock {
                    onResult("Корутина 2 замок 1")
                }
            }
        }
    }

    fun runExample(onResult: (String) -> Unit) {
        method1(onResult)
        method2(onResult)
    }
}