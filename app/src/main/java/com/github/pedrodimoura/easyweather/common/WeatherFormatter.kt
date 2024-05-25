package com.github.pedrodimoura.easyweather.common

interface WeatherFormatter {
    fun getTemperature(temperature: String, temperatureUnit: String): String
}
