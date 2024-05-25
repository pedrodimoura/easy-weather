package com.github.pedrodimoura.easyweather.today.data

import com.github.pedrodimoura.easyweather.today.data.model.TodayWeatherResponse

interface WeatherRepository {
    fun getTodayWeather(): TodayWeatherResponse
}
