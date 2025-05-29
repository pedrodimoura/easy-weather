package com.github.pedrodimoura.easyweather.common.di

import com.github.pedrodimoura.easyweather.common.network.ktor.KtorNetworkClient
import com.github.pedrodimoura.easyweather.common.network.ktor.KtorNetworkClientImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface HiltNetworkModule {

    @Binds
    @Singleton
    fun bindNetworkClient(networkClient: KtorNetworkClientImpl): KtorNetworkClient
}
