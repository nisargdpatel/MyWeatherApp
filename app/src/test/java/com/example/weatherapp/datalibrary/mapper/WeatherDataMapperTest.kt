package com.example.weatherapp.datalibrary.mapper

import com.example.weatherapp.datalibrary.utils.HourlyWeatherDetails
import com.example.weatherapp.datalibrary.utils.WeatherInfo
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Test

class WeatherDataMapperTest {

    @Test
    fun trimList_givenListOfHourlyWeatherDetailsGreaterThan10_assertsTrimmedListOfSize10() {
        val hourlyWeatherDetails1: HourlyWeatherDetails = mockk()
        val hourlyWeatherDetails2: HourlyWeatherDetails = mockk()
        val hourlyWeatherDetails3: HourlyWeatherDetails = mockk()
        val hourlyWeatherDetails4: HourlyWeatherDetails = mockk()
        val hourlyWeatherDetails5: HourlyWeatherDetails = mockk()
        val hourlyWeatherDetails6: HourlyWeatherDetails = mockk()
        val hourlyWeatherDetails7: HourlyWeatherDetails = mockk()
        val hourlyWeatherDetails8: HourlyWeatherDetails = mockk()
        val hourlyWeatherDetails9: HourlyWeatherDetails = mockk()
        val hourlyWeatherDetails10: HourlyWeatherDetails = mockk()
        val hourlyWeatherDetails11: HourlyWeatherDetails = mockk()

        val listOfHourlyDetails: List<HourlyWeatherDetails> =
            listOf(
                hourlyWeatherDetails1,
                hourlyWeatherDetails2,
                hourlyWeatherDetails3,
                hourlyWeatherDetails4,
                hourlyWeatherDetails5,
                hourlyWeatherDetails6,
                hourlyWeatherDetails7,
                hourlyWeatherDetails8,
                hourlyWeatherDetails9,
                hourlyWeatherDetails10,
                hourlyWeatherDetails11,
            )

        val result = listOfHourlyDetails.trimList()

        assertEquals(10, result.size)

        assertEquals(hourlyWeatherDetails1, result[0])
        assertEquals(hourlyWeatherDetails2, result[1])
        assertEquals(hourlyWeatherDetails3, result[2])
        assertEquals(hourlyWeatherDetails4, result[3])
        assertEquals(hourlyWeatherDetails5, result[4])
        assertEquals(hourlyWeatherDetails6, result[5])
        assertEquals(hourlyWeatherDetails7, result[6])
        assertEquals(hourlyWeatherDetails8, result[7])
        assertEquals(hourlyWeatherDetails9, result[8])
        assertEquals(hourlyWeatherDetails10, result[9])
    }

    @Test
    fun trimList_givenListOfHourlyWeatherDetailsLessThan10_assertsTrimmedListOfSize10() {
        val hourlyWeatherDetails1: HourlyWeatherDetails = mockk()
        val hourlyWeatherDetails2: HourlyWeatherDetails = mockk()
        val hourlyWeatherDetails3: HourlyWeatherDetails = mockk()

        val listOfHourlyDetails: List<HourlyWeatherDetails> =
            listOf(
                hourlyWeatherDetails1,
                hourlyWeatherDetails2,
                hourlyWeatherDetails3
            )

        val result = listOfHourlyDetails.trimList()

        assertEquals(3, result.size)

        assertEquals(hourlyWeatherDetails1, result[0])
        assertEquals(hourlyWeatherDetails2, result[1])
        assertEquals(hourlyWeatherDetails3, result[2])
    }

    @Test
    fun convertToLocalTime_givenTimeInUtc_assertsConvertedLocalTimeForDetroit() {
        val time = "1689148800"

        assertEquals("4:00 AM", time.convertToLocalTime())
    }

    @Test
    fun toWeatherUi_givenListOfWeatherInfoFromResponse_assertsListOfWeatherInfoWithDescriptionAndIcon() {
        val weatherInfo: WeatherInfo = mockk()
        val listOfWeatherInfo: List<WeatherInfo> = listOf(weatherInfo)

        every { weatherInfo.weatherCondition } returns "weather condition"
        every { weatherInfo.description } returns "cloudy with a chance of meatballs"
        every { weatherInfo.icon } returns "10d"

        val resultList = listOfWeatherInfo.toWeatherUi()

        assertEquals("weather condition", resultList[0].weatherCondition)
        assertEquals("Cloudy With A Chance Of Meatballs", resultList[0].description)
        assertEquals("https://openweathermap.org/img/wn/10d@2x.png", resultList[0].icon)
    }

    @Test
    fun toPercentage_givenPrecipitationProbabilityAsDouble_assertsRoundedStringValueWithPercentage() {
        val probabilityOfPrecipitation = 0.5

        assertEquals("50%", probabilityOfPrecipitation.toPercentage())
    }
}