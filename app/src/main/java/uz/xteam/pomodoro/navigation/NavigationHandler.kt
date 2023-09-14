package uz.xteam.pomodoro.navigation

import cafe.adriel.voyager.navigator.Navigator
import kotlinx.coroutines.flow.Flow

typealias NavigationArgs = Navigator.() -> Unit

interface NavigationHandler {
    val navStack: Flow<NavigationArgs>
}