package uz.xteam.pomodoro.preferences

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import uz.xteam.pomodoro.helper.SharedPreferenceHelper
import javax.inject.Inject


const val DEFAULT_FOCUS_LENGTH = 30
const val DEFAULT_SHORT_BREAK_LENGTH = 5
const val DEFAULT_LONG_BREAK_LENGTH = 15
const val DEFAULT_POMDOROS_COUNT = 5

class LocalStorage @Inject constructor(@ApplicationContext  context: Context) : SharedPreferenceHelper(context, prefName = "SettingsPreferences") {

    var darkTheme: Boolean by booleans(false)
    var focus_length: Int by ints(DEFAULT_FOCUS_LENGTH)
    var until_long: Int by ints(DEFAULT_POMDOROS_COUNT)
    var shbreak_length: Int by ints(DEFAULT_SHORT_BREAK_LENGTH)
    var lbreak_length: Int by ints(DEFAULT_LONG_BREAK_LENGTH)
    var autoResume: Boolean by booleans(false)
    var sound: Boolean by booleans(false)
    var notifications: Boolean by booleans(false)
}