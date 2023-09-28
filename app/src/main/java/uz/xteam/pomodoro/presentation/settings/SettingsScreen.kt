package uz.xteam.pomodoro.presentation.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import uz.xteam.pomodoro.R

class SettingsScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel = getViewModel<SettingsViewModel>()
        SettingsScreenContent(viewModel)
    }

    @Composable
    private fun SettingsScreenContent(
        viewModel: SettingsViewModel
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f), text = "Settings", style = TextStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 24.sp,
                        fontWeight = FontWeight(700)
                    )
                )

                IconButton(onClick = {
                    viewModel.back()
                }) {
                    Icon(
                        modifier = Modifier.padding(8.dp),
                        painter = painterResource(id = R.drawable.ph_vector),
                        contentDescription = "Close",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            val darkMode = viewModel.darkMode.collectAsState()
            val focusLength = viewModel.focusLength.collectAsState()
            val untilLong = viewModel.untilLong.collectAsState()
            val shBreakLength = viewModel.shBreakLength.collectAsState()
            val lBreakLength = viewModel.lBreakLength.collectAsState()
            val autoResume = viewModel.autoResume.collectAsState()
            val sound = viewModel.sound.collectAsState()
            val notification = viewModel.notifications.collectAsState()

            SwitchComponent(text = "Dark mode", darkMode.value) { isChecked ->
                viewModel.changeDarkMode(isChecked)
            }


            InputComponent(text = "Focus length", value = focusLength.value.toString(),
                onUpClick = {
                    viewModel.increaseFocusLength()
                },
                onDownClick = {
                    viewModel.decreaseFocusLength()
                }

            )
            InputComponent(text = "Pomodoros until long break", value = untilLong.value.toString(),
                onDownClick = {
                    viewModel.decreaseUntilLong()
                },
                onUpClick = {
                    viewModel.increaseUntilLong()
                })
            InputComponent(text = "Short break length", value = shBreakLength.value.toString(),
                onUpClick = {
                    viewModel.increaseShBreakLength()
                },
                onDownClick = {
                    viewModel.decreaseShBreakLength()
                })
            InputComponent(text = "Long break length", value = lBreakLength.value.toString(),
                onUpClick = {
                    viewModel.increaseLBreakLength()
                },
                onDownClick = {
                    viewModel.decreaseLBreakLength()
                })

            SwitchComponent(text = "Auto resume timer", autoResume.value) { isCheked ->
                viewModel.changeAutoResume(isCheked)
            }
            SwitchComponent(text = "Sound", sound.value) {isCheked ->
                viewModel.changeSound(isCheked)
            }
            SwitchComponent(text = "Notifications", notification.value) {isCheked ->
                viewModel.changeNotifications(isCheked)
            }

        }
    }


    @Composable
    fun SwitchComponent(
        text: String,
        checked: Boolean,
        onCheckedChange: (Boolean) -> Unit
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f), text = text, style = TextStyle(
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400)
                )
            )

            Switch(checked = checked, onCheckedChange = onCheckedChange)

        }
    }

    @Composable
    fun InputComponent(
        text: String,
        value: String,
        onUpClick: () -> Unit,
        onDownClick: () -> Unit,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f), text = text, style = TextStyle(
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400)
                )
            )

            CarbonInput(
                text = value,
                onUpClick = onUpClick,
                onDownClick = onDownClick
            )
        }
    }


    @Composable
    fun CarbonInput(
        modifier: Modifier = Modifier,
        text: String,
        onUpClick: () -> Unit,
        onDownClick: () -> Unit,
    ) {
        Row(
            modifier = modifier
                .width(96.dp)
                .height(40.dp)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(size = 8.dp)
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = text,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
            )
            Column {
                IconButton(
                    modifier = Modifier.weight(1f),
                    onClick = onUpClick
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.frame_15),
                        contentDescription = "Up",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                IconButton(
                    modifier = Modifier.weight(1f),
                    onClick = onDownClick
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.frame_16),
                        contentDescription = "Down",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }


}