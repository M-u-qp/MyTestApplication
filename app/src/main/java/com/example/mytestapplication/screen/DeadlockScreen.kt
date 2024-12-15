package com.example.mytestapplication.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mytestapplication.screen.common.DeadlockExample
import com.example.mytestapplication.screen.common.DeadlockWithChannel
import com.example.mytestapplication.screen.common.DeadlockWithMutex
import com.example.mytestapplication.screen.common.RaceConditionExample
import com.example.mytestapplication.screen.common.RaceConditionWithAtomic
import com.example.mytestapplication.screen.common.RaceConditionWithMutex

@Composable
fun DeadlockScreen() {
    var counterRaceCondition by remember { mutableIntStateOf(0) }
    var counterRaceConditionMutex by remember { mutableIntStateOf(0) }
    var counterRaceConditionAtomic by remember { mutableIntStateOf(0) }
    var resultDeadlock by remember { mutableStateOf("Пока не заблокировано") }
    var resultDeadlockMutex by remember { mutableStateOf("Пока не заблокировано") }
    var resultDeadlockChannel by remember { mutableStateOf("Пока не заблокировано") }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DeadlockScreenElement(
                onClick = {
                    RaceConditionExample().runExample { newValue ->
                        counterRaceCondition = newValue
                    }
                },
                result = counterRaceCondition.toString(),
                text = "Состояние гонки"
            )
            DeadlockScreenElement(
                onClick = {
                    RaceConditionWithMutex().runExample { newValue ->
                        counterRaceConditionMutex = newValue
                    }
                },
                result = counterRaceConditionMutex.toString(),
                text = "Cостояниe гонки с Mutex"
            )
            DeadlockScreenElement(
                onClick = {
                    RaceConditionWithAtomic().runExample { newValue ->
                        counterRaceConditionAtomic = newValue
                    }
                },
                result = counterRaceConditionAtomic.toString(),
                text = "Cостояниe гонки с Atomic"
            )
            DeadlockScreenElement(
                onClick = {
                    DeadlockExample().runExample { newResult ->
                        resultDeadlock = newResult
                    }
                },
                result = resultDeadlock,
                text = "Взаимная блокировка"
            )
            DeadlockScreenElement(
                onClick = {
                    DeadlockWithMutex().runExample { newResult ->
                        resultDeadlockMutex = newResult
                    }
                },
                result = resultDeadlockMutex,
                text = "Взаимная блокировка c Mutex"
            )
            DeadlockScreenElement(
                onClick = {
                    DeadlockWithChannel().runExample { newResult ->
                        resultDeadlockChannel = newResult
                    }
                },
                result = resultDeadlockChannel,
                text = "Взаимная блокировка c Channel"
            )
        }
    }
}

@Composable
private fun DeadlockScreenElement(
    onClick: () -> Unit,
    text: String,
    result: String
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Button(
                onClick = onClick
            ) {
                Text(text = "Пуск")
            }
            Text(text = result)
        }
        HorizontalDivider()
    }
}
