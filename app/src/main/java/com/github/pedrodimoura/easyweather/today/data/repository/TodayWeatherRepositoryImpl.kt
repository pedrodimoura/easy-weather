package com.github.pedrodimoura.easyweather.today.data.repository

import android.util.Log
import com.github.pedrodimoura.easyweather.common.network.ktor.KtorNetworkClient
import com.github.pedrodimoura.easyweather.today.data.model.TodayWeatherResponse
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

internal class TodayWeatherRepositoryImpl @Inject constructor(
    private val networkClient: KtorNetworkClient,
) : TodayWeatherRepository {

    override suspend fun getTodayWeather(location: String): TodayWeatherResponse {
        return runCatching {
            networkClient.httpClient.get("current.json?q=$location")
        }.fold(
            onSuccess = { it.body() },
            onFailure = {
                Log.e("TodayWeatherRepositoryImpl", "Failed to fetch today's weather", it)
                throw it
            }
        )
    }
}