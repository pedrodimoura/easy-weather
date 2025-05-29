package com.github.pedrodimoura.easyweather.common.di

import com.github.pedrodimoura.easyweather.common.formatter.location.LocationFormatter
import com.github.pedrodimoura.easyweather.common.formatter.location.LocationFormatterImpl
import com.github.pedrodimoura.easyweather.common.formatter.time.DateAndTimeFormatter
import com.github.pedrodimoura.easyweather.common.formatter.time.DateAndTimeFormatterImpl
import com.github.pedrodimoura.easyweather.common.formatter.weather.WeatherFormatter
import com.github.pedrodimoura.easyweather.common.formatter.weather.WeatherFormatterImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface HiltFormatterModule {

    @Binds
    @Singleton
    fun bindsDateAndTimeFormatter(
        dateAndTimeFormatterImpl: DateAndTimeFormatterImpl,
    ): DateAndTimeFormatter

    @Binds
    @Singleton
    fun bindsWeatherFormatter(
        weatherFormatterImpl: WeatherFormatterImpl,
    ): WeatherFormatter

    @Binds
    @Singleton
    fun bindsLocationFormatter(
        locationFormatterImpl: LocationFormatterImpl,
    ): LocationFormatter
}
