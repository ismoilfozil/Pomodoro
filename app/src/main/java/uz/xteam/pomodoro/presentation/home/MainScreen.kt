package uz.xteam.pomodoro.presentation.home

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.xteam.pomodoro.R
import uz.xteam.pomodoro.ThemeViewModel

class MainScreen(
    val themeViewModel: ThemeViewModel
) : AndroidScreen() {


    private lateinit var mediaPlayer: MediaPlayer

    @Composable
    override fun Content() {
        val viewModel = getViewModel<MainViewModel>()
        val context = LocalContext.current
        MainScreenContent(viewModel)

        LaunchedEffect(key1 = Unit){
            viewModel.soundFlow.collectLatest {
                playMedia(context)
            }
        }
    }

    @Composable
    private fun MainScreenContent(
        viewModel: MainViewModel
    ) {

        val timerState by viewModel.timerState.collectAsState()
        val uiState by viewModel.uiState.collectAsState()
        val timer by viewModel.timerFlow.collectAsState()



        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(size = 9999.dp)
                    )
                    .background(
                        color = uiState.color15, shape = RoundedCornerShape(size = 9999.dp)
                    )
                    .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = uiState.icon),
                    contentDescription = "state",
                    tint = MaterialTheme.colorScheme.primary
                )

                Spacer(
                    modifier = Modifier.width(4.dp)
                )

                Text(
                    text = uiState.name,
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Text(
                modifier = Modifier.wrapContentHeight(), text = timer, style = TextStyle(
                    fontSize = 228.sp,
                    color = MaterialTheme.colorScheme.primary,
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
                    .background(uiState.color15, shape = RoundedCornerShape(size = 24.dp))
                    .padding(24.dp), onClick = { viewModel.openSettings() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ph_dots_three_outline_fill),
                        contentDescription = "Settings",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                IconButton(
                    modifier = Modifier
                        .width(128.dp)
                        .height(96.dp)
                        .background(uiState.color70, shape = RoundedCornerShape(size = 32.dp))
                        .padding(24.dp),
                    onClick = { viewModel.onPlayClicked(timerState) },
                ) {

                    val playIconId =
                        if (timerState == TimerState.RUNNING) R.drawable.ph_pause_fill else R.drawable.ph_play_fill

                    Icon(
                        painter = painterResource(id = playIconId),
                        contentDescription = "Play",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                IconButton(modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
                    .background(uiState.color15, shape = RoundedCornerShape(size = 24.dp))
                    .padding(24.dp), onClick = {
                    val value = viewModel.nextState()
                    themeViewModel.changeUi(value)
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ph_fast_forward_fill),
                        contentDescription = "Forward",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }


    private fun playMedia(context: Context) {
        mediaPlayer = MediaPlayer.create(context, R.raw.timer_finished_sound)
        mediaPlayer.start()
    }


}