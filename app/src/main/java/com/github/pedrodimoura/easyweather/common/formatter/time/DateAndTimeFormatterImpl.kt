package com.github.pedrodimoura.easyweather.common.formatter.time

import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class DateAndTimeFormatterImpl @Inject constructor() : DateAndTimeFormatter {
    override fun format(input: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        val outputFormat = SimpleDateFormat("EEEE, dd MMMM", Locale.getDefault())
        val date = inputFormat.parse(input)
        return date?.let { outputFormat.format(it) } ?: input
    }
}