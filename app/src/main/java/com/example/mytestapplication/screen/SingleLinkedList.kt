package com.example.mytestapplication.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.mytestapplication.screen.common.MyLinkedList

@Composable
fun SingleLinkedList() {
    val linkedList = remember { MyLinkedList() }
    val inputValue = remember { mutableStateOf("") }
    val listItems = remember { mutableStateOf(listOf<Int>()) }

    Box(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = inputValue.value,
                onValueChange = { inputValue.value = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = { Text("Введите число") },
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    inputValue.value.toIntOrNull()?.let {
                        linkedList.add(it)
                        listItems.value = linkedList.toList()
                        inputValue.value = ""
                    }
                }
            ) {
                Text("Добавить")
            }
            Button(
                onClick = {
                    linkedList.reverse()
                    listItems.value = linkedList.toList()
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
}