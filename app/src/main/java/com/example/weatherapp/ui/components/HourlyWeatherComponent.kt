package com.example.weatherapp.ui.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.weatherapp.datalibrary.mapper.toPercentage
import com.example.weatherapp.ui.utils.WeatherState

@Composable
fun HourlyWeatherComponent(
    state: WeatherState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .fillMaxHeight()
    ) {

        val list = state.currentWeatherDataResponse?.listOfHourlyWeather ?: listOf()
        for (data in list) {
            Card(
                backgroundColor = Color.Black.copy(alpha = 0.6f),
                shape = RoundedCornerShape(10.dp),
                modifier = modifier.padding(8.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    //Time
                    Text(
                        text = data.time,
                        color = Color.White,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    //Temperature
                    Text(
                        text = "${data.currentWeather.temperature.toInt()}°F",
                        fontSize = 40.sp,
                        color = Color.White, modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    //Weather Icon
                    Image(
                        painter = rememberAsyncImagePainter(data.hourlyWeather[0].icon),
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp)
                            .align(Alignment.CenterHorizontally)
                    )

                    //Weather Condition
                    Text(
                        text = data.hourlyWeather[0].weatherCondition,
                        fontSize = 24.sp,
                        color = Color.White, modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    //Precipitation
                    Text(
                        text = data.probabilityOfPrecipitation.toPercentage(),
                        color = Color.White,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }

            }
        }

    }

}