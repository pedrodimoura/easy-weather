package com.github.pedrodimoura.easyweather.today.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TodayWeatherResponse(
    @SerialName("location")
    val location: WeatherLocation,
    @SerialName("current")
    val current: WeatherCurrent,
)

@Serializable
data class WeatherLocation(
    @SerialName("name")
    val name: String,
    @SerialName("region")
    val region: String,
    @SerialName("country")
    val country: String,
    @SerialName("localtime_epoch")
    val localtimeEpoch: Int,
    @SerialName("localtime")
    val localtime: String,
)

@Serializable
data class WeatherCurrent(
    @SerialName("temp_c")
    val tempC: Double,
    @SerialName("temp_f")
    val tempF: Double,
    @SerialName("condition")
    val condition: WeatherCondition,
    @SerialName("feelslike_c")
    val feelsLikeC: Double,
    @SerialName("feelslike_f")
    val feelsLikeF: Double,
)

@Serializable
data class WeatherCondition(
    val text: String,
    val icon: String,
)
