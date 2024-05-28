package com.github.pedrodimoura.easyweather.today.di

import com.github.pedrodimoura.easyweather.common.formatter.location.LocationFormatter
import com.github.pedrodimoura.easyweather.common.formatter.time.DateAndTimeFormatter
import com.github.pedrodimoura.easyweather.common.formatter.weather.WeatherFormatter
import com.github.pedrodimoura.easyweather.common.network.ktor.KtorNetworkClient
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface WidgetEntryPoint {
    fun networkClient(): KtorNetworkClient
    fun weatherFormatter(): WeatherFormatter
    fun locationFormatter(): LocationFormatter
    fun dateAndTimeFormatter(): DateAndTimeFormatter
}
