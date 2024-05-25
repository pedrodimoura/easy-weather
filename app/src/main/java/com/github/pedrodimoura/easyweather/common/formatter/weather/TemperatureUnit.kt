package com.github.pedrodimoura.easyweather.common.formatter.weather


sealed class TemperatureUnit(
    val unitSymbol: String,
) {
    data object Celsius : TemperatureUnit("°C")
    data object Fahrenheit : TemperatureUnit("°F")
}
