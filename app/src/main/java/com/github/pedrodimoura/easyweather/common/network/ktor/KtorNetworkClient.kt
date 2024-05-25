package com.github.pedrodimoura.easyweather.common.network.ktor

import com.github.pedrodimoura.easyweather.common.network.NetworkClient
import io.ktor.client.HttpClient

interface KtorNetworkClient : NetworkClient {
    val httpClient: HttpClient
}
