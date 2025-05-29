package com.github.pedrodimoura.easyweather.today.di

import com.github.pedrodimoura.easyweather.today.data.repository.TodayWeatherRepository
import com.github.pedrodimoura.easyweather.today.data.repository.TodayWeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface HiltTodayWeatherModule {

    @Binds
    @Singleton
    fun bindsTodayWeatherRepository(
        todayWeatherRepositoryImpl: TodayWeatherRepositoryImpl,
    ): TodayWeatherRepository
}
