package uz.xteam.pomodoro.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat


private val DarkFocusColorScheme = darkColorScheme(
    primary = Red50, secondary = Red15, tertiary = Red70, background = DarkBackground
)

private val DarkShortBreakColorScheme = darkColorScheme(
    primary = Green50, secondary = Green15, tertiary = Green62, background = DarkBackground
)

private val DarkLongBreakColorScheme = darkColorScheme(
    primary = Blue50, secondary = Blue15, tertiary = Blue62, background = DarkBackground
)


private val LightFocusColorScheme = lightColorScheme(
    primary = Red, secondary = Red15, tertiary = Red70, background = Red50
)


private val LightShortBreakColorScheme = lightColorScheme(
    primary = Green, secondary = Green15, tertiary = Green62, background = Green50
)


private val LightLongBreakScheme = lightColorScheme(
    primary = Blue, secondary = Blue15, tertiary = Blue62, background = Blue50
)

enum class StatusColoScheme(
    val lightColorScheme: ColorScheme,
    val darkColorScheme: ColorScheme,
) {
    Focus(LightFocusColorScheme, DarkFocusColorScheme),
    ShortBreak(LightShortBreakColorScheme, DarkShortBreakColorScheme),
    LongBreak(LightLongBreakScheme, DarkLongBreakColorScheme)
}


@Composable
fun PomodoroTheme(
    themeColorStatus: StatusColoScheme,
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {

    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> themeColorStatus.darkColorScheme
        else -> themeColorStatus.lightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme, typography = Typography, content = content
    )
}