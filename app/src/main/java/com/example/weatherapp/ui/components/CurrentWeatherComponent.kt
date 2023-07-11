package com.example.weatherapp.ui.components

import androidx.compose.foundation.Image
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
import com.example.weatherapp.ui.utils.WeatherState

@Composable
fun CurrentWeatherComponent(
    state: WeatherState,
    modifier: Modifier = Modifier
) {
    state.currentWeatherDataResponse?.let { data ->
        Card(
            backgroundColor = Color.Black.copy(alpha = 0.6f),
            shape = RoundedCornerShape(10.dp),
            modifier = modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(1f),
                    horizontalAlignment = Alignment.Start
                ) {
                    //Temperature
                    Text(
                        text = "${data.listOfHourlyWeather[0].currentWeather.temperature.toInt()}°F",
                        modifier = Modifier.align(
                            Alignment.Start
                        ),
                        fontSize = 50.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    //Description
                    Text(
                        text = data.listOfHourlyWeather[0].hourlyWeather[0].description,
                        modifier = Modifier.align(
                            Alignment.Start
                        ), fontSize = 20.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    //Feels Like
                    Text(
                        text = "Feels like ${data.listOfHourlyWeather[0].currentWeather.feelsLike.toInt()}°F",
                        modifier = Modifier.align(
                            Alignment.Start
                        ), fontSize = 20.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(32.dp))

                    //Minimum and Maximum temperature
                    Text(
                        text = "Min: ${data.listOfHourlyWeather[0].currentWeather.minimumTemperature.toInt()}°F - Max: ${data.listOfHourlyWeather[0].currentWeather.maximumTemperature.toInt()}°F",
                        modifier = Modifier.align(
                            Alignment.Start
                        ),
                        fontSize = 20.sp,
                        color = Color.White
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .weight(1f),
                    horizontalAlignment = Alignment.End
                ) {
                    //Weather Icon
                    Image(
                        painter = rememberAsyncImagePainter(data.listOfHourlyWeather[0].hourlyWeather[0].icon),
                        contentDescription = null,
                        modifier = Modifier.size(250.dp)
                    )
                }
            }
        }
    }

}