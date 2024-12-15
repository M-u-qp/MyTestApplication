package com.example.mytestapplication.screen.common

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//Взаимная блокировка
class DeadlockExample {
    private val lock1 = Any()
    private val lock2 = Any()

    private fun method1(onResult: (String) -> Unit) {
        CoroutineScope(Dispatchers.Default).launch {
            synchronized(lock1) {
                onResult("Корутина 1 замок 1")
                Log.d("MyLog", "Корутина 1 пытается захватить замок 2")
                synchronized(lock2) {
                    onResult("Корутина 1 замок 2")
                }
            }
        }
    }

    private fun method2(onResult: (String) -> Unit) {
        CoroutineScope(Dispatchers.Default).launch {
            synchronized(lock2) {
                onResult("Корутина 2 замок 2")
                Log.d("MyLog", "Корутина 2 пытается захватить замок 1")
                synchronized(lock1) {
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