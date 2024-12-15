package com.example.mytestapplication.navigation

sealed class Route(val route: String) {
    data object AppStartNavigation : Route(route = "appStartNavigation")
    data object HomeScreen : Route(route = "homeScreen")
    data object CollectionScreen : Route(route = "collectionScreen")
    data object GenericCollectionScreen : Route(route = "genericCollectionScreen")
    data object DeadlockScreen : Route(route = "deadlockScreen")
}