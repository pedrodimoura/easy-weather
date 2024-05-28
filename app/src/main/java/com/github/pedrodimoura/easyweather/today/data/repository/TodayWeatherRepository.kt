package com.github.pedrodimoura.easyweather.today.data.repository

import com.github.pedrodimoura.easyweather.today.data.model.TodayWeatherResponse

interface TodayWeatherRepository {
    suspend fun getTodayWeather(location: String): TodayWeatherResponse
}
