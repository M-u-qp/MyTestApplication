package com.example.mytestapplication.core.common

import android.util.Log

//Вставляет код функции в место вызова для улучшения производительности
inline fun inlineFunc(block: () -> Unit) {
    Log.d("MyLog", "Before inlined")
    block()
    Log.d("MyLog", "After inlined")
}

//Если нужно сохранить лямбда-выражение в переменной
inline fun noInlineFunc(noinline block: () -> Unit) {
    Log.d("MyLog", "Inside noInlined")
    block()
}

//Лямбда-выражение не может содержать неявные возвраты из функции
inline fun crossInlineFunc(crossinline block: () -> Unit) {
    Log.d("MyLog", "Before calling crossInlined")
    val runnable = Runnable { block() }
    runnable.run()
    Log.d("MyLog", "After calling crossInlined")
}

//Reified продемонстрирован в MyGenericLinkedList
//Позволяет получать доступ к типам параметров во время выполнения