package uz.xteam.pomodoro.presentation.home

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.xteam.pomodoro.directions.HomeScreenDirections
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val directions: HomeScreenDirections
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Focus)
    val uiState = _uiState.asStateFlow()

    private val _timerState = MutableStateFlow(TimerState.START)
    val timerState = _timerState.asStateFlow()

    private val _timer = MutableStateFlow(stateTime(_uiState.value))
    val timer = _timer.asStateFlow()


    fun nextState() {
        when (_uiState.value) {
            UiState.Focus -> {
                _timer.value = stateTime(UiState.ShBreak)
                _uiState.value = UiState.ShBreak
            }

            UiState.ShBreak -> {
                _timer.value = stateTime(UiState.LBreak)
                _uiState.value = UiState.LBreak
            }

            UiState.LBreak -> {
                _timer.value = stateTime(UiState.Focus)
                _uiState.value = UiState.Focus
            }
        }
    }

    fun onPlayClicked(state: TimerState) = viewModelScope.launch {
        when (state) {
            TimerState.PAUSE -> {
                _timerState.emit(TimerState.RUNNING)
            }
            TimerState.RUNNING ->{
                _timerState.emit(TimerState.PAUSE)
            }
            else -> _timerState.emit(TimerState.PAUSE)
        }
    }


    fun stateTime(state: UiState):String{
       return when(state){
            UiState.Focus -> "25\n00"
            UiState.ShBreak -> "05\n00"
            UiState.LBreak -> "15\n00"
        }
    }


    fun startTimer(time: String){
        val minute = time.substring(0, 2).toLong()
        val second = time.substring(3).toLong() + minute * 60
        object  : CountDownTimer(second * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val value = uiTime(millisUntilFinished)
                _timer.tryEmit(value)
            }
            override fun onFinish() {
                _timer.tryEmit("00\n00")
            }
        }
    }

    private fun uiTime(mill:Long):String{
        var second = (mill / 1000).toInt()
        val minute: Int = (second / 60)
        second %= 60
        val minString = if (minute in 0..9) "0$minute" else minute.toString()
        val secString = if (second in 0 .. 9) "0$second" else second.toString()
        return "$minString\n$secString"
    }

    fun openSettings() = viewModelScope.launch{
        directions.navigateToSettingsScreen()
    }


}