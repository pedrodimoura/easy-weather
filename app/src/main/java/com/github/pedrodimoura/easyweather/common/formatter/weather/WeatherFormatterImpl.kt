package com.github.pedrodimoura.easyweather.common.formatter.weather

import javax.inject.Inject
import kotlin.math.roundToInt

class WeatherFormatterImpl @Inject constructor() : WeatherFormatter {
    override fun getTemperature(temperature: Double, temperatureUnit: TemperatureUnit): String {
        return "${temperature.roundToInt()}${temperatureUnit.unitSymbol}"
    }
}
