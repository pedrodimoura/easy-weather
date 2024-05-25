package com.github.pedrodimoura.easyweather.today.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.lifecycleScope
import com.github.pedrodimoura.easyweather.today.ui.composable.TodayScreen
import com.github.pedrodimoura.easyweather.today.vm.TodayWeatherActivityAction
import com.github.pedrodimoura.easyweather.today.vm.TodayWeatherViewModel
import com.github.pedrodimoura.ui.theme.EasyWeatherTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TodayActivity : ComponentActivity() {

    private val viewModel: TodayWeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeActivityActions()
        setContent {
            EasyWeatherTheme {
                val uiState by viewModel.uiState.collectAsState()
                TodayScreen(uiState = uiState)
            }
        }
    }

    private fun observeActivityActions() {
        lifecycleScope.launch {
            viewModel.activityAction.collect {
                when (it) {
                    is TodayWeatherActivityAction.ShowErrorMessage -> {
                        Toast.makeText(
                            this@TodayActivity,
                            "An error occurred",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }

                    else -> {
                        // Do nothing
                    }
                }
            }
        }
    }
}
