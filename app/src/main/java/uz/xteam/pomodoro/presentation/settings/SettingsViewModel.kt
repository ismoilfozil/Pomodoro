package uz.xteam.pomodoro.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.xteam.pomodoro.directions.SettingsScreenDirections
import uz.xteam.pomodoro.preferences.LocalStorage
import javax.inject.Inject

const val MIN_FOCUS_LENGTH = 10
const val MAX_FOCUS_LENGTH = 99
const val MIN_SHORT_BREAK_LENGTH = 1
const val MAX_SHORT_BREAK_LENGTH = 10
const val MIN_LONG_BREAK_LENGTH = 10
const val MAX_LONG_BREAK_LENGTH = 30
const val MIN_POMODOROS_NUMBER = 3
const val MAX_POMODOROS_NUMBER = 10

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val directions: SettingsScreenDirections,
    private val localStorage: LocalStorage
)  : ViewModel(){



    private val _darkMode = MutableStateFlow(localStorage.darkTheme)
    val darkMode = _darkMode.asStateFlow()


    private val _focusLength = MutableStateFlow(localStorage.focus_length)
    val focusLength = _focusLength.asStateFlow()

    private val _untilLong = MutableStateFlow(localStorage.until_long)
    val untilLong = _untilLong.asStateFlow()

    private val _shBreakLength = MutableStateFlow(localStorage.shbreak_length)
    val shBreakLength = _shBreakLength.asStateFlow()


    private val _lBreakLength = MutableStateFlow(localStorage.lbreak_length)
    val lBreakLength = _lBreakLength.asStateFlow()

    private val _autoResume = MutableStateFlow(localStorage.autoResume)
    val autoResume = _autoResume.asStateFlow()

    private val _sound = MutableStateFlow(localStorage.sound)
    val sound = _sound.asStateFlow()

    private val _notifications = MutableStateFlow(localStorage.notifications)
    val notifications = _notifications.asStateFlow()


    fun changeDarkMode(isDark:Boolean){
        _darkMode.value = isDark
        localStorage.darkTheme = isDark
    }


    fun increaseFocusLength(){
        if (_focusLength.value == MAX_FOCUS_LENGTH) return

        val newValue = _focusLength.value.plus(1)
        _focusLength.value = newValue
        localStorage.focus_length = newValue
    }


    fun decreaseFocusLength(){

        if (_focusLength.value == MIN_FOCUS_LENGTH) return


        val newValue = _focusLength.value.minus(1)
        _focusLength.value = newValue
        localStorage.focus_length = newValue
    }


    fun increaseUntilLong(){

        if(_untilLong.value == MAX_POMODOROS_NUMBER) return

        val newValue = _untilLong.value.plus(1)
        _untilLong.value = newValue
        localStorage.until_long = newValue
    }


    fun decreaseUntilLong(){
        if (_untilLong.value == MIN_POMODOROS_NUMBER) return

        val newValue = _untilLong.value.minus(1)
        _untilLong.value = newValue
        localStorage.until_long = newValue
    }


    fun increaseShBreakLength(){

        if (_shBreakLength.value == MAX_SHORT_BREAK_LENGTH) return

        val newValue = _shBreakLength.value.plus(1)
        _shBreakLength.value = newValue
        localStorage.shbreak_length = newValue
    }


    fun decreaseShBreakLength(){

        if (_shBreakLength.value == MIN_SHORT_BREAK_LENGTH) return

        val newValue = _shBreakLength.value.minus(1)
        _shBreakLength.value = newValue
        localStorage.shbreak_length = newValue
    }

    fun increaseLBreakLength(){

        if (_lBreakLength.value == MAX_LONG_BREAK_LENGTH) return


        val newValue = _lBreakLength.value.plus(1)
        _lBreakLength.value = newValue
        localStorage.lbreak_length = newValue
    }


    fun decreaseLBreakLength(){

        if (_lBreakLength.value == MIN_LONG_BREAK_LENGTH) return


        val newValue = _lBreakLength.value.minus(1)
        _lBreakLength.value = newValue
        localStorage.lbreak_length = newValue
    }


    fun changeAutoResume(isResume:Boolean){
        _autoResume.value  = isResume
        localStorage.autoResume = isResume
    }

    fun changeSound(isSound:Boolean){
        _sound.value  = isSound
        localStorage.sound = isSound
    }

    fun changeNotifications(isNotification:Boolean){
        _notifications.value  = isNotification
        localStorage.notifications = isNotification
    }

    fun back() = viewModelScope.launch {
        directions.back()
    }
}