package com.github.pedrodimoura.easyweather.common.network.ktor

import com.github.pedrodimoura.easyweather.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Inject

class KtorNetworkClientImpl @Inject constructor() : KtorNetworkClient {

    override val httpClient: HttpClient by lazy {
        HttpClient(Android) {
            defaultRequest {
                header(HttpHeaders.ContentType, "application/json")
                url {
                    protocol = URLProtocol.HTTPS
                    host = "api.weatherapi.com"
                    path("v1/")
                    parameters.append("key", BuildConfig.API_KEY)
                }
            }
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        this.ignoreUnknownKeys = true
                    }
                )
            }
            install(Logging) {
                logger = Logger.ANDROID
                level = LogLevel.BODY
                sanitizeHeader { header -> header == HttpHeaders.Authorization }
            }
            engine {
                connectTimeout = 30_000
                socketTimeout = 30_000
            }
        }
    }
}