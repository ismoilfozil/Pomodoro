package uz.xteam.pomodoro.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.xteam.pomodoro.directions.HomeScreenDirections
import uz.xteam.pomodoro.directions.SettingsScreenDirections
import uz.xteam.pomodoro.directions.impl.HomeScreenDirectionsImpl
import uz.xteam.pomodoro.directions.impl.SettingsScreenDirectionsImpl

@Module
@InstallIn(ViewModelComponent::class)
interface DirectionsModule {

    @Binds
    fun bindMainScreenDirections(impl: HomeScreenDirectionsImpl): HomeScreenDirections

    @Binds
    fun bindSettingsScreenDirections(impl: SettingsScreenDirectionsImpl): SettingsScreenDirections

}