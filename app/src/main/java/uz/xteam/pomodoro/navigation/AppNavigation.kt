package uz.xteam.pomodoro.navigation

import cafe.adriel.voyager.androidx.AndroidScreen


typealias AppScreen = AndroidScreen

interface AppNavigation {

    suspend fun back()

    suspend fun backAll()

    suspend fun backToRoot()

    suspend fun navigateTo(screen: AndroidScreen)

}