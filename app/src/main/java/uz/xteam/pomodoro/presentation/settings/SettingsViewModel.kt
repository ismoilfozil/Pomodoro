package uz.xteam.pomodoro.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.xteam.pomodoro.directions.SettingsScreenDirections
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val directions: SettingsScreenDirections
)  : ViewModel(){



    fun back() = viewModelScope.launch {
        directions.back()
    }
}