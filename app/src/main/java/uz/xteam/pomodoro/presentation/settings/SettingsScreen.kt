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
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import uz.xteam.pomodoro.R
import uz.xteam.pomodoro.ui.theme.Red
import uz.xteam.pomodoro.ui.theme.Red50

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
                .background(Red50)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f), text = "Settings", style = TextStyle(
                        color = Red, fontSize = 24.sp, fontWeight = FontWeight(700)
                    )
                )

                IconButton(onClick = {
                    viewModel.back()
                }) {
                    Icon(
                        modifier = Modifier.padding(8.dp),
                        painter = painterResource(id = R.drawable.ph_vector),
                        contentDescription = "Close"
                    )
                }
            }

            var darkMode by remember { mutableStateOf(false) }
            var focusLength by remember { mutableIntStateOf(25) }

            SwitchComponent(text = "Dark mode", darkMode){ isChecked ->
                darkMode = isChecked
            }


            InputComponent(text ="Focus length", value = focusLength.toString(),
                onUpClick = {
                    focusLength++
                },
                onDownClick = {
                    focusLength--
                }

            )
            InputComponent(text ="Pomodoros until long break", value ="25",
                onDownClick = {

                },
                onUpClick = {

                })
            InputComponent(text ="Short break length", value = "25",
                onUpClick = {
                    
                },
                onDownClick = {

                })
            InputComponent(text ="Long break length", value ="25",
                onUpClick = {

                },
                onDownClick = {

                })

            SwitchComponent(text = "Auto resume timer", false){

            }
            SwitchComponent(text = "Sound", false) {

            }
            SwitchComponent(text = "Notifications", false){

            }

        }
    }


    @Composable
    fun SwitchComponent(
        text:String,
        checked:Boolean,
        onCheckedChange: (Boolean) -> Unit
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f), text = text, style = TextStyle(
                    color = Red, fontSize = 16.sp, fontWeight = FontWeight(400)
                )
            )

            Switch(checked = checked, onCheckedChange = onCheckedChange)

        }
    }

    @Composable
    fun InputComponent(
        text:String,
        value:String,
        onUpClick : () -> Unit,
        onDownClick : () -> Unit,
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f), text = text, style = TextStyle(
                    color = Red, fontSize = 16.sp, fontWeight = FontWeight(400)
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
        onUpClick : () -> Unit,
        onDownClick : () -> Unit,
    ) {
        Row(
            modifier = modifier
                .width(96.dp)
                .height(40.dp)
                .border(
                    width = 1.dp, color = Color(0x26000000), shape = RoundedCornerShape(size = 8.dp)
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = text,
                color = Red,
                textAlign = TextAlign.Center,
            )
            Column {
                IconButton(
                    modifier = Modifier.weight(1f),
                    onClick = onUpClick){
                    Icon(
                        painter = painterResource(id = R.drawable.frame_15),
                        contentDescription = "Up",
                    )
                }
                IconButton(
                    modifier = Modifier.weight(1f),
                    onClick = onDownClick){
                    Icon(
                        painter = painterResource(id = R.drawable.frame_16),
                        contentDescription = "Down",
                    )
                }
            }
        }
    }


}