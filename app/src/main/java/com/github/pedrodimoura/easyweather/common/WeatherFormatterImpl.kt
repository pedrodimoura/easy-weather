package com.github.pedrodimoura.easyweather.common

class WeatherFormatterImpl : WeatherFormatter {
    override fun getTemperature(temperature: String, temperatureUnit: String): String {
        return "$temperature $temperatureUnit"
    }
}
