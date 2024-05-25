package com.github.pedrodimoura.easyweather.common.formatter

interface WeatherFormatter {
    fun getTemperature(temperature: String, temperatureUnit: String): String
}
