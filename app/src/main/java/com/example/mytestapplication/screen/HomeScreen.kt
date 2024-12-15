package com.example.mytestapplication.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    navigateToCollectionScreen: () -> Unit,
    navigateToDeadlockScreen: () -> Unit,
    navigateToGenericCollectionScreen: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(
                onClick = navigateToCollectionScreen
            ) {
                Text("Односвязный список")
            }
            Button(
                onClick = navigateToDeadlockScreen
            ) {
                Text("Состояние гонки - взаимная блокировка")
            }
            Button(
                onClick = navigateToGenericCollectionScreen
            ) {
                Text("Параметризированный односвязный список")
            }
        }
    }
}