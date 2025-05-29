package com.github.pedrodimoura.easyweather.common.formatter.location

import java.util.Locale
import javax.inject.Inject

internal class LocationFormatterImpl @Inject constructor() : LocationFormatter {
    override fun format(location: String, region: String, country: String): String {
        return "$location, $region - ${getCountryCode(country)}"
    }

    override fun getCountryCode(country: String): String {
        val locales = Locale.getAvailableLocales()
        for (locale in locales) {
            if (locale.getDisplayCountry(Locale.ENGLISH).equals(country, ignoreCase = true)) {
                return locale.country
            }
        }
        return country
    }
}
