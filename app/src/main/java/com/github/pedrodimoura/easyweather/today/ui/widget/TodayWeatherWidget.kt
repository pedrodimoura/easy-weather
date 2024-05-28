package com.github.pedrodimoura.easyweather.today.ui.widget

import android.content.Context
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.github.pedrodimoura.easyweather.common.formatter.location.LocationFormatter
import com.github.pedrodimoura.easyweather.common.formatter.time.DateAndTimeFormatter
import com.github.pedrodimoura.easyweather.common.formatter.weather.TemperatureUnit
import com.github.pedrodimoura.easyweather.common.formatter.weather.WeatherFormatter
import com.github.pedrodimoura.easyweather.common.network.ktor.KtorNetworkClient
import com.github.pedrodimoura.easyweather.today.data.model.TodayWeatherResponse
import com.github.pedrodimoura.easyweather.today.di.WidgetEntryPoint
import com.github.pedrodimoura.easyweather.today.ui.composable.getFontColor
import com.github.pedrodimoura.easyweather.today.ui.composable.getTemperatureColor
import com.github.pedrodimoura.easyweather.today.ui.model.TodayWeatherInformation
import com.github.pedrodimoura.ui.theme.EasyWeatherUI
import dagger.hilt.android.EntryPointAccessors
import io.ktor.client.call.body
import io.ktor.client.request.get

class TodayWeatherWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {

        val weatherFormatter: WeatherFormatter by lazy {
            EntryPointAccessors
                .fromApplication(context, WidgetEntryPoint::class.java)
                .weatherFormatter()
        }

        val locationFormatter: LocationFormatter by lazy {
            EntryPointAccessors
                .fromApplication(context, WidgetEntryPoint::class.java)
                .locationFormatter()
        }

        val dateAndTimeFormatter: DateAndTimeFormatter by lazy {
            EntryPointAccessors
                .fromApplication(context, WidgetEntryPoint::class.java)
                .dateAndTimeFormatter()
        }

        val networkClient: KtorNetworkClient by lazy {
            EntryPointAccessors
                .fromApplication(context, WidgetEntryPoint::class.java)
                .networkClient()
        }

        provideContent {
            GlanceTheme {
                var weatherInformation by remember {
                    mutableStateOf<TodayWeatherInformation?>(null)
                }
                var backgroundColor by remember {
                    mutableStateOf(EasyWeatherUI.Colors.Temperature.Cool)
                }
                var refresh by remember { mutableIntStateOf(0) }

                Column(
                    modifier = GlanceModifier
                        .height(100.dp)
                        .fillMaxWidth()
                        .background(backgroundColor)
                        .clickable {
                            refresh++
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    weatherInformation?.let {
                        backgroundColor = it.getTemperatureColor()
                        val fontColor = ColorProvider(it.getFontColor())
                        Text(
                            text = it.temperature,
                            style = TextStyle(color = fontColor)
                        )
                        Text(
                            text = it.location,
                            style = TextStyle(color = fontColor)
                        )
                    }
                }

                LaunchedEffect(key1 = refresh) {
                    val weather = networkClient
                        .httpClient
                        .get("current.json?q=Haselhorst")
                        .body<TodayWeatherResponse>()
                    weatherInformation = TodayWeatherInformation(
                        temperature = weatherFormatter.getTemperature(
                            weather.current.tempC,
                            TemperatureUnit.Celsius
                        ),
                        rawTemperature = weather.current.tempC,
                        dateTime = dateAndTimeFormatter.format(weather.location.localtime),
                        location = locationFormatter.format(
                            weather.location.name,
                            weather.location.region,
                            weather.location.country
                        )
                    )
                }
            }
        }
    }
}
