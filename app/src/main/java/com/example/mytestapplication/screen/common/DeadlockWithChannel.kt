package com.example.mytestapplication.screen.common

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//Взаимная блокировка с Channel
class DeadlockWithChannel {
    private val channel1 = Channel<String>()
    private val channel2 = Channel<String>()

    private fun method1(onResult: (String) -> Unit) {
        CoroutineScope(Dispatchers.Default).launch {
            onResult("Корутина 1 отправляет сообщение в канал 1")
            channel1.send("Сообщение от корутины 1")
            delay(1000)
            val message = channel2.receive()
            Log.d("MyLog", message)
            onResult("Корутина 1 получила: $message")
        }
    }

    private fun method2(onResult: (String) -> Unit) {
        CoroutineScope(Dispatchers.Default).launch {
            val message = channel1.receive()
            Log.d("MyLog", message)
            onResult("Корутина 2 получила: $message")
            onResult("Корутина 2 отправляет сообщение в канал 2")
            delay(1000)
            channel2.send("Сообщение от корутины 2")
        }
    }

    fun runExample(onResult: (String) -> Unit) {
        method1(onResult)
        method2(onResult)
    }
}