package com.github.pedrodimoura.easyweather.today.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModelProvider
import com.github.pedrodimoura.easyweather.today.ui.composable.TodayScreen
import com.github.pedrodimoura.easyweather.today.vm.TodayWeatherViewModel
import com.github.pedrodimoura.ui.theme.EasyWeatherTheme

class TodayActivity : ComponentActivity() {

    private val viewModel: TodayWeatherViewModel by lazy {
        ViewModelProvider(this)[TodayWeatherViewModel::class.java]
    }

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
