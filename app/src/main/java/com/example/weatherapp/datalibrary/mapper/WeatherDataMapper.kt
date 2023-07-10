package com.example.weatherapp.datalibrary.mapper

import com.example.weatherapp.datalibrary.utils.WeatherDetails
import com.example.weatherapp.datalibrary.utils.WeatherInfo
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

fun Double.toFahrenheit(): Double {
    return (((this - 273.15) * (9)) / 5) + 32
}

fun String.capitalizeFirstLetters(): String {
    val words = this.split(" ").toMutableList()
    var output = ""
    words.forEach {
        output += it.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() } + " "
    }
    return output
}

fun String.convertToLocalTime(): String {
    val parser = SimpleDateFormat("HH:mm", Locale.getDefault())
    parser.timeZone = TimeZone.getTimeZone("UTC")
    return dateTime(this.toInt(), "America/Detroit")

//    return LocalDateTime.parse(this, DateTimeFormatter.ISO_DATE_TIME).format(DateTimeFormatter.ofPattern("HH:mm"))
}

fun dateTime(time: Int, zone: String, format: String = "K:mm a"): String {
    // parse the time zone
    val zoneId = ZoneId.of(zone)
    // create a moment in time from the given timestamp (in seconds!)
    val instant = Instant.ofEpochSecond(time.toLong())
    // define a formatter using the given pattern and a Locale
    val formatter = DateTimeFormatter.ofPattern(format, Locale.ENGLISH)
    // then make the moment in time consider the zone and return the formatted String
    return instant.atZone(zoneId).format(formatter)
}

fun String.toIconUrl(): String {
    return "https://openweathermap.org/img/wn/$this@2x.png"
}

fun WeatherDetails.toWeatherDetailsUi(): WeatherDetails {
    return WeatherDetails(
        temperature = this.temperature.toFahrenheit(),
        feelsLike = this.feelsLike.toFahrenheit(),
        minimumTemperature = this.minimumTemperature.toFahrenheit(),
        maximumTemperature = this.maximumTemperature.toFahrenheit(),
        pressure = this.pressure,
        humidity = this.humidity,
        seaLevel = this.seaLevel,
        groundLevel = this.groundLevel
    )
}

fun List<WeatherInfo>.toWeatherUi(): List<WeatherInfo> {
    return listOf(
        WeatherInfo(weatherCondition = this[0].weatherCondition, description = this[0].description.capitalizeFirstLetters(), icon = this[0].icon.toIconUrl())
    )
}