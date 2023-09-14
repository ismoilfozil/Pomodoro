package uz.xteam.pomodoro.presentation.home

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import uz.xteam.pomodoro.R
import uz.xteam.pomodoro.ui.theme.Blue
import uz.xteam.pomodoro.ui.theme.Blue15
import uz.xteam.pomodoro.ui.theme.Blue50
import uz.xteam.pomodoro.ui.theme.Blue62
import uz.xteam.pomodoro.ui.theme.Green
import uz.xteam.pomodoro.ui.theme.Green15
import uz.xteam.pomodoro.ui.theme.Green50
import uz.xteam.pomodoro.ui.theme.Green62
import uz.xteam.pomodoro.ui.theme.Red
import uz.xteam.pomodoro.ui.theme.Red15
import uz.xteam.pomodoro.ui.theme.Red50
import uz.xteam.pomodoro.ui.theme.Red70

//enum class UiState{
//    FOCUS, SHORT_BREAK, LONG_BREAK
//}

sealed class UiState(
    val name:String,
    @DrawableRes val icon:Int,
    val color:Color,
    val color15:Color,
    val color50:Color,
    val color70:Color
){
    object Focus:UiState(
        "Focus",
        R.drawable.ph_brain_fill,
        Red,
        Red15,
        Red50,
        Red70
    )

    object ShBreak:UiState(
        "Short Break",
        R.drawable.ph_coffee,
        Green,
        Green15,
        Green50,
        Green62
    )

    object LBreak:UiState(
        "Long Break",
        R.drawable.ph_coffee,
        Blue,
        Blue15,
        Blue50,
        Blue62
    )

}