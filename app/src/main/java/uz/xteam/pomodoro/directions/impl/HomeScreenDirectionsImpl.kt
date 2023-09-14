package uz.xteam.pomodoro.directions.impl

import uz.xteam.pomodoro.directions.HomeScreenDirections
import uz.xteam.pomodoro.navigation.AppNavigation
import uz.xteam.pomodoro.presentation.settings.SettingsScreen
import javax.inject.Inject

class HomeScreenDirectionsImpl @Inject constructor(
    private val navigation:AppNavigation
) : HomeScreenDirections{
    override suspend fun navigateToSettingsScreen() {
        navigation.navigateTo(SettingsScreen())
    }

}