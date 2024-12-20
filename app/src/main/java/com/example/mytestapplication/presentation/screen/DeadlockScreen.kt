package com.example.mytestapplication.presentation.screen

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
import com.example.mytestapplication.core.common.DeadlockExample
import com.example.mytestapplication.core.common.DeadlockWithSemaphore
import com.example.mytestapplication.core.common.DeadlockWithLock
import com.example.mytestapplication.core.common.RaceConditionExample
import com.example.mytestapplication.core.common.RaceConditionWithAtomic
import com.example.mytestapplication.core.common.RaceConditionWithLock

@Composable
fun DeadlockScreen() {
    var counterRaceCondition by remember { mutableIntStateOf(0) }
    var counterRaceConditionWithLock by remember { mutableIntStateOf(0) }
    var counterRaceConditionWithAtomic by remember { mutableIntStateOf(0) }
    var resultDeadlock by remember { mutableStateOf("Пока не заблокировано") }
    var resultDeadlockWithLock by remember { mutableStateOf("Пока не заблокировано") }
    var resultDeadlockWithSemaphore by remember { mutableStateOf("Пока не заблокировано") }

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
                    RaceConditionWithLock().runExample { newValue ->
                        counterRaceConditionWithLock = newValue
                    }
                },
                result = counterRaceConditionWithLock.toString(),
                text = "Cостояниe гонки с Lock"
            )
            DeadlockScreenElement(
                onClick = {
                    RaceConditionWithAtomic().runExample { newValue ->
                        counterRaceConditionWithAtomic = newValue
                    }
                },
                result = counterRaceConditionWithAtomic.toString(),
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
                    DeadlockWithLock().runExample { newResult ->
                        resultDeadlockWithLock = newResult
                    }
                },
                result = resultDeadlockWithLock,
                text = "Взаимная блокировка c Lock"
            )
            DeadlockScreenElement(
                onClick = {
                    DeadlockWithSemaphore().runExample { newResult ->
                        resultDeadlockWithSemaphore = newResult
                    }
                },
                result = resultDeadlockWithSemaphore,
                text = "Взаимная блокировка c Semaphore"
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
