package com.github.pedrodimoura.easyweather.today.receiver

import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import com.github.pedrodimoura.easyweather.today.ui.widget.TodayWeatherWidget

internal class TodayWeatherReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = TodayWeatherWidget()
}
