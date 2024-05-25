package com.github.pedrodimoura.easyweather.today.ui.model

data class TodayWeatherInformation(
    val temperature: String,
    val rawTemperature: Double,
    val dateTime: String,
    val location: String,
)
