package com.github.pedrodimoura.easyweather.common.network.ktor

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.HttpHeaders
import javax.inject.Inject

class KtorNetworkClientImpl @Inject constructor() : KtorNetworkClient {

    override val httpClient: HttpClient by lazy {
        HttpClient(Android) {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
                sanitizeHeader { header -> header == HttpHeaders.Authorization }
                sanitizeHeader { header -> header == HttpHeaders.Cookie }
            }
            engine {
                connectTimeout = 30_000
                socketTimeout = 30_000
            }
        }
    }
}