package uz.xteam.pomodoro.presentation.home

import android.widget.ImageButton
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import uz.xteam.pomodoro.R
import uz.xteam.pomodoro.ui.theme.Red
import uz.xteam.pomodoro.ui.theme.Red15
import uz.xteam.pomodoro.ui.theme.Red50
import uz.xteam.pomodoro.ui.theme.Red70

class MainScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel = getViewModel<MainViewModel>()
        MainScreenContent(
            viewModel
        )
    }

    @Composable
    private fun MainScreenContent(
        viewModel: MainViewModel
    ) {

        val timerState = viewModel.timerState.collectAsState()
        val uiState = viewModel.uiState.collectAsState()
        val timer = viewModel.timer.collectAsState()


        Column(
            modifier = Modifier
                .background(color = uiState.value.color50)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .border(
                        width = 2.dp,
                        color = uiState.value.color,
                        shape = RoundedCornerShape(size = 9999.dp)
                    )
                    .background(
                        color = uiState.value.color15,
                        shape = RoundedCornerShape(size = 9999.dp)
                    )
                    .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painterResource(id = uiState.value.icon), contentDescription = "state"
                )

                Spacer(
                    modifier = Modifier.width(4.dp)
                )

                Text(
                    text = uiState.value.name,
                    fontSize = 24.sp,
                    color = uiState.value.color
                )
            }

            Text(
                modifier = Modifier.wrapContentHeight(), text = timer.value, style = TextStyle(
                    fontSize = 228.sp,
                    color = uiState.value.color,
                    textAlign = TextAlign.Center,
                    lineHeight = 190.sp,
                    platformStyle = PlatformTextStyle(includeFontPadding = false),
                    fontWeight = FontWeight.Medium
                )
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
                    .background(uiState.value.color15, shape = RoundedCornerShape(size = 24.dp))
                    .padding(24.dp),
                    onClick = { viewModel.openSettings() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ph_dots_three_outline_fill),
                        contentDescription = "Settings",
                        tint = uiState.value.color
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                IconButton(
                    modifier = Modifier
                        .width(128.dp)
                        .height(96.dp)
                        .background(uiState.value.color70, shape = RoundedCornerShape(size = 32.dp))
                        .padding(24.dp),
                    onClick = { viewModel.onPlayClicked(timerState.value) },
                ) {

                    val playIconId =
                        if (timerState.value == TimerState.PAUSE)
                            R.drawable.ph_pause_fill
                        else
                            R.drawable.ph_play_fill
                    Icon(
                        painter = painterResource(id = playIconId),
                        contentDescription = "Play",
                        tint = uiState.value.color
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                IconButton(modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
                    .background(uiState.value.color15, shape = RoundedCornerShape(size = 24.dp))
                    .padding(24.dp),
                    onClick = { viewModel.nextState() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ph_fast_forward_fill),
                        contentDescription = "Forward",
                        tint = uiState.value.color
                    )
                }

            }

            Spacer(modifier = Modifier.weight(1f))

        }
    }

    @Preview(showBackground = true)
    @Composable
    fun MainScreenPreview() {
        val viewModel = getViewModel<MainViewModel>()
        MainScreenContent(viewModel)
    }

}