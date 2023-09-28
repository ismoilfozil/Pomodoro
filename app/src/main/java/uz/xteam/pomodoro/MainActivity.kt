package uz.xteam.pomodoro

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.xteam.pomodoro.navigation.NavigationHandler
import uz.xteam.pomodoro.presentation.home.MainScreen
import uz.xteam.pomodoro.ui.theme.PomodoroTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationHandler:NavigationHandler

    private val viewModel:ThemeViewModel by viewModels()

    @SuppressLint("FlowOperatorInvokedInComposition", "CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val theme  = viewModel.status.collectAsState()

            PomodoroTheme(
                themeColorStatus = theme.value
            ){
                Navigator( screen = MainScreen(viewModel)){ navigator ->
                    navigationHandler.navStack
                        .onEach {
                            it.invoke(navigator)
                        }.launchIn(lifecycleScope)
                    SlideTransition(navigator)
                }
            }
        }
    }
}
