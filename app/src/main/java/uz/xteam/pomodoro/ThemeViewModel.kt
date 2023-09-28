package uz.xteam.pomodoro

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.xteam.pomodoro.presentation.home.UiState
import uz.xteam.pomodoro.ui.theme.StatusColoScheme

class ThemeViewModel : ViewModel() {

    private val _status = MutableStateFlow<StatusColoScheme>(StatusColoScheme.Focus)
    val status = _status.asStateFlow()


    fun changeUi(state:UiState)  = viewModelScope.launch{

       val status =  when(state){
           UiState.Focus -> StatusColoScheme.Focus
           UiState.ShBreak -> StatusColoScheme.ShortBreak
           UiState.LBreak -> StatusColoScheme.LongBreak
       }
        Log.d("TTT", "changeUi: $status")
        _status.emit(status)
    }


}