package com.github.pedrodimoura.easyweather.common.formatter.location

interface LocationFormatter {
    fun format(location: String, region: String, country: String): String
    fun getCountryCode(country: String): String
}
