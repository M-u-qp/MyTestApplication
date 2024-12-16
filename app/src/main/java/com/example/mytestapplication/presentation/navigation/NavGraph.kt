package com.example.mytestapplication.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.mytestapplication.presentation.screen.DeadlockScreen
import com.example.mytestapplication.presentation.screen.GenericLinkedList
import com.example.mytestapplication.presentation.screen.HomeScreen
import com.example.mytestapplication.presentation.screen.SingleLinkedList

@Composable
fun NavGraph(navController: NavHostController) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        NavHost(
            navController = navController,
            startDestination = Route.AppStartNavigation.route
        ) {
            navigation(
                route = Route.AppStartNavigation.route,
                startDestination = Route.HomeScreen.route
            ) {
                composable(route = Route.HomeScreen.route) {
                    HomeScreen(
                        navigateToCollectionScreen = {
                            navController.navigate(Route.CollectionScreen.route)
                        },
                        navigateToDeadlockScreen = {
                            navController.navigate(Route.DeadlockScreen.route)
                        },
                        navigateToGenericCollectionScreen = {
                            navController.navigate(Route.GenericCollectionScreen.route)
                        },
                    )
                }
                composable(route = Route.CollectionScreen.route) {
                    SingleLinkedList()
                }
                composable(route = Route.DeadlockScreen.route) {
                    DeadlockScreen()
                }
                composable(route = Route.GenericCollectionScreen.route) {
                    GenericLinkedList()
                }
            }
        }
    }
}