package com.github.pedrodimoura.easyweather.today.vm

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.pedrodimoura.easyweather.today.ui.model.TodayWeatherInformation
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface TodayWeatherUiState {
    data object Loading : TodayWeatherUiState
    data class Success(val todayWeatherInformation: TodayWeatherInformation) : TodayWeatherUiState
}

class TodayWeatherViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<TodayWeatherUiState>(TodayWeatherUiState.Loading)
    val uiState: StateFlow<TodayWeatherUiState> = _uiState

    private val _todayWeatherInformation = mutableStateOf(
        TodayWeatherInformation(
            temperature = "20°C",
            rawTemperature = 20,
            dateTime = "Friday, 20 January",
            location = "Recife, PE - Brazil",
        )
    )

    init {
        viewModelScope.launch {
            delay(2000)
            _uiState.update { TodayWeatherUiState.Success(_todayWeatherInformation.value) }
        }
    }
}
