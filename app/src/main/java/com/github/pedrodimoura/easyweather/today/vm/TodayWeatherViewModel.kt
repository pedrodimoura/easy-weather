package com.github.pedrodimoura.easyweather.today.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.pedrodimoura.easyweather.common.formatter.location.LocationFormatter
import com.github.pedrodimoura.easyweather.common.formatter.time.DateAndTimeFormatter
import com.github.pedrodimoura.easyweather.common.formatter.weather.TemperatureUnit
import com.github.pedrodimoura.easyweather.common.formatter.weather.WeatherFormatter
import com.github.pedrodimoura.easyweather.common.network.ktor.KtorNetworkClient
import com.github.pedrodimoura.easyweather.today.data.model.TodayWeatherResponse
import com.github.pedrodimoura.easyweather.today.ui.model.TodayWeatherInformation
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface TodayWeatherUiState {
    data object Loading : TodayWeatherUiState
    data class Success(val todayWeatherInformation: TodayWeatherInformation) : TodayWeatherUiState
}

sealed interface TodayWeatherActivityAction {
    data object Idle : TodayWeatherActivityAction
    data object ShowErrorMessage : TodayWeatherActivityAction
}

@HiltViewModel
class TodayWeatherViewModel @Inject constructor(
    private val ktorNetworkClient: KtorNetworkClient,
    private val dateAndTimeFormatter: DateAndTimeFormatter,
    private val weatherFormatter: WeatherFormatter,
    private val locationFormatter: LocationFormatter,
) : ViewModel() {

    private val _uiState = MutableStateFlow<TodayWeatherUiState>(TodayWeatherUiState.Loading)
    val uiState: StateFlow<TodayWeatherUiState> = _uiState

    private val _activityAction =
        MutableStateFlow<TodayWeatherActivityAction>(TodayWeatherActivityAction.Idle)
    val activityAction: StateFlow<TodayWeatherActivityAction> = _activityAction

    init {
        viewModelScope.launch {
            runCatching {
                ktorNetworkClient
                    .httpClient
                    .get("current.json?q=Haselhorst")
                    .body<TodayWeatherResponse>()
            }.onSuccess { todayWeatherResponse ->
                _uiState.update {
                    TodayWeatherUiState.Success(
                        TodayWeatherInformation(
                            location = locationFormatter.format(
                                location = todayWeatherResponse.location.name,
                                region = todayWeatherResponse.location.region,
                                country = todayWeatherResponse.location.country,
                            ),
                            temperature = weatherFormatter.getTemperature(
                                temperature = todayWeatherResponse.current.tempC,
                                temperatureUnit = TemperatureUnit.Celsius,
                            ),
                            rawTemperature = todayWeatherResponse.current.tempC,
                            dateTime = dateAndTimeFormatter.format(
                                todayWeatherResponse.location.localtime,
                            ),
                        )
                    )
                }
            }.onFailure {
                _activityAction.update { TodayWeatherActivityAction.ShowErrorMessage }
            }
        }
    }
}
