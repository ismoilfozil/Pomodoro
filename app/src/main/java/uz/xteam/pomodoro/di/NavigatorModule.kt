package uz.xteam.pomodoro.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.xteam.pomodoro.navigation.AppNavigation
import uz.xteam.pomodoro.navigation.NavigationDispatcher
import uz.xteam.pomodoro.navigation.NavigationHandler

@Module
@InstallIn(SingletonComponent::class)
interface NavigatorModule {

    @Binds
    fun appNavigator(dispatcher: NavigationDispatcher): AppNavigation

    @Binds
    fun navigationHandler(dispatcher: NavigationDispatcher): NavigationHandler
}