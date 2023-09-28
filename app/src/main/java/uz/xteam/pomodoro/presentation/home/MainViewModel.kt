package uz.xteam.pomodoro.presentation.home

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.xteam.pomodoro.R
import uz.xteam.pomodoro.directions.HomeScreenDirections
import uz.xteam.pomodoro.preferences.LocalStorage
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

@HiltViewModel
class MainViewModel @Inject constructor(
    private val directions: HomeScreenDirections,
    private val localStorage: LocalStorage
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Focus)
    val uiState = _uiState.asStateFlow()

    private val _timerState = MutableStateFlow(TimerState.START)
    val timerState = _timerState.asStateFlow()

    private val _timerFlow = MutableStateFlow(stateTime(_uiState.value))
    val timerFlow = _timerFlow.asStateFlow()

    private val _soundFlow = MutableSharedFlow<Int>()
    val soundFlow = _soundFlow.asSharedFlow()


    private lateinit var timer: CountDownTimer


    fun nextState(): UiState {
        val nextUiState = when (_uiState.value) {
            UiState.Focus -> UiState.ShBreak
            UiState.ShBreak -> UiState.LBreak
            UiState.LBreak -> UiState.Focus
        }
        _timerFlow.value = stateTime(nextUiState)
        _uiState.value = nextUiState
        return nextUiState
    }

    fun onPlayClicked(state: TimerState) = viewModelScope.launch {
        when (state) {
            TimerState.START, TimerState.PAUSE -> {
                startTimer()
                _timerState.emit(TimerState.RUNNING)
            }
            TimerState.RUNNING -> {
                timer.cancel()
                _timerState.emit(TimerState.PAUSE)
            }
            TimerState.END -> {

            }
        }
    }


    private fun onTimerFinished() = viewModelScope.launch{
        _timerFlow.tryEmit("00\n00")
        _soundFlow.emit(R.raw.timer_finished_sound)
        _timerState.emit(TimerState.END)
    }



    private fun startTimer() {
        val time = _timerFlow.value
        val minute = time.substring(0, 2).toLong()
        val second = time.substring(3).toLong() + minute * 60
        timer = object : CountDownTimer(second * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _timerFlow.tryEmit(uiTime(millisUntilFinished))
            }

            override fun onFinish() {
                onTimerFinished()
            }
        }.start()
    }

    private fun uiTime(mill: Long): String {
        val seconds = (mill / 1000).toInt()
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60

        val minString = minutes.toString().padStart(2, '0')
        val secString = remainingSeconds.toString().padStart(2, '0')

        return "$minString\n$secString"
    }

    private fun stateTime(state: UiState): String {
        val length = when (state) {
            UiState.Focus -> localStorage.focus_length
            UiState.ShBreak -> localStorage.shbreak_length
            UiState.LBreak -> localStorage.lbreak_length
        }
        return "${length.toString().padStart(2, '0')}\n00"
    }

    fun openSettings() = viewModelScope.launch {
        directions.navigateToSettingsScreen()
    }
}
