package com.example.weatherapp.datalibrary.mapper

import com.example.weatherapp.datalibrary.utils.HourlyWeatherDetails
import com.example.weatherapp.datalibrary.utils.WeatherInfo
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

fun List<HourlyWeatherDetails>.trimList(): List<HourlyWeatherDetails> =
    if (this.size >= 10) this.subList(0, 10) else this

fun String.convertToLocalTime(): String {
    return dateTime(this.toInt(), "America/Detroit")    //Todo: To be improved to use timezone value from response and converting it to proper local time using offset
}

fun List<WeatherInfo>.toWeatherUi(): List<WeatherInfo> {
    return listOf(
        WeatherInfo(
            weatherCondition = this[0].weatherCondition,
            description = this[0].description.capitalizeFirstLetters(),
            icon = this[0].icon.toIconUrl()
        )
    )
}

fun Double.toPercentage(): String = "${(this * 100).toInt()}%"

private fun String.capitalizeFirstLetters(): String {
    val words = this.lowercase().split(" ").toMutableList()
    var output = ""
    words.forEach { word ->
        output += word.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() } + " "
    }
    return output.trimEnd()
}

private fun dateTime(time: Int, zone: String, format: String = "K:mm a"): String {
    // parse the time zone
    val zoneId = ZoneId.of(zone)
    // create a moment in time from the given timestamp (in seconds!)
    val instant = Instant.ofEpochSecond(time.toLong())
    // define a formatter using the given pattern and a Locale
    val formatter = DateTimeFormatter.ofPattern(format, Locale.ENGLISH)
    // then make the moment in time consider the zone and return the formatted String
    return instant.atZone(zoneId).format(formatter)
}

private fun String.toIconUrl(): String {
    return "https://openweathermap.org/img/wn/$this@2x.png"
}