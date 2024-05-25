package com.github.pedrodimoura.easyweather.today.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.github.pedrodimoura.easyweather.today.ui.composable.TodayScreen
import com.github.pedrodimoura.easyweather.today.vm.TodayWeatherViewModel
import com.github.pedrodimoura.ui.theme.EasyWeatherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodayActivity : ComponentActivity() {

    private val viewModel: TodayWeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EasyWeatherTheme {
                val uiState by viewModel.uiState.collectAsState()
                TodayScreen(uiState = uiState)
            }
        }
    }
}
