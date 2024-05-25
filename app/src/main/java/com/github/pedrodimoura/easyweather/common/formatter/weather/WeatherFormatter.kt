package com.github.pedrodimoura.easyweather.common.formatter.weather

interface WeatherFormatter {
    fun getTemperature(temperature: Double, temperatureUnit: TemperatureUnit): String
}
