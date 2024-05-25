package com.github.pedrodimoura.easyweather.today.ui.model

data class TodayWeatherInformation(
    val temperature: String,
    val rawTemperature: Int,
    val dateTime: String,
    val location: String,
)
