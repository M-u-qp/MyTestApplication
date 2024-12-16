package com.example.mytestapplication.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mytestapplication.core.common.MyGenericLinkedList

@Composable
fun GenericLinkedList() {
    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Spacer(modifier = Modifier.size(50.dp))
        GenericLinkedListElement(String::class.java)
        GenericLinkedListElement(Int::class.java)
        GenericLinkedListElement(Double::class.java)
        GenericLinkedListElement(Boolean::class.java)
    }
}

@Composable
private inline fun <reified T> GenericLinkedListElement(type: Class<T>) {
    val linkedList = remember { mutableStateOf(MyGenericLinkedList<T>()) }
    val inputValue = remember { mutableStateOf("") }
    val listItems = remember { mutableStateOf(listOf<T>()) }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = inputValue.value,
            onValueChange = { inputValue.value = it },
            label = { Text("Введите значение") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                when (type) {
                    Int::class.java -> {
                        inputValue.value.toIntOrNull()?.let {
                            linkedList.value = linkedList.value.add(it as T)
                        }
                    }
                    Double::class.java -> {
                        inputValue.value.toDoubleOrNull()?.let {
                            linkedList.value = linkedList.value.add(it as T)
                        }
                    }
                    Boolean::class.java -> {
                        val value = inputValue.value.lowercase()
                        if (value == "true" || value == "false") {
                            linkedList.value = linkedList.value.add(value.toBoolean() as T)
                        }
                    }
                    else -> {
                        if (inputValue.value.isNotBlank()) {
                            linkedList.value = linkedList.value.add(inputValue.value as T)
                        }
                    }
                }
                listItems.value = linkedList.value.toList()
                inputValue.value = ""
            }
        ) {
            Text("Добавить")
        }
        Button(
            onClick = {
                linkedList.value = linkedList.value.reverse()
                listItems.value = linkedList.value.toList()
                linkedList.value.forEach1 { println(it) }
            }
        ) {
            Text("Развернуть")
        }
        Text(
            if (listItems.value.isEmpty()) {
                "Список пуст"
            } else {
                listItems.value.joinToString(", ")
            }
        )
    }
}