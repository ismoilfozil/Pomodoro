package uz.xteam.pomodoro.directions.impl

import uz.xteam.pomodoro.directions.SettingsScreenDirections
import uz.xteam.pomodoro.navigation.AppNavigation
import javax.inject.Inject

class SettingsScreenDirectionsImpl @Inject constructor(
    private val navigation: AppNavigation
): SettingsScreenDirections {
    override suspend fun back() {
        navigation.back()
    }
}