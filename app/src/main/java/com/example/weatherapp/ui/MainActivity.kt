package com.example.weatherapp.ui

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.ui.components.CurrentWeatherComponent
import com.example.weatherapp.ui.components.HourlyWeatherComponent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            viewModel.loadWeatherInfo()
        }
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            )
        )
        setContent {
            MaterialTheme(
                typography = Typography(
                    body1 = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        color = Color.Blue
                    )
                )
            ) {
                val searchText by viewModel.searchText.collectAsState()
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.weather_background),
                        contentDescription = null,
                        contentScale = ContentScale.FillHeight,
                        modifier = Modifier.fillMaxSize(),
                        alpha = if (viewModel.state.isLoading) 0.6f else DefaultAlpha
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        //Todo: Given more time, I can improve the search functionality to include zipcode search
                        TextField(value = searchText, onValueChange = viewModel::onSearchTextChange,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp), textStyle = TextStyle(
                                color = Color.White, fontSize = 16.sp
                            ),
                            placeholder = { Text(text = "Search", color = Color.White) })
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            onClick = viewModel::searchWeather,
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
                        ) {
                            Text(text = "Search", color = Color.White)
                        }
                        CurrentWeatherComponent(viewModel.state)
                        Spacer(modifier = Modifier.height(16.dp))
                        HourlyWeatherComponent(viewModel.state)
                    }

                    if (viewModel.state.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center),
                            strokeWidth = 6.dp,
                            color = Color.Black
                        )
                    }
                    viewModel.state.error?.let { error ->
                        Text(
                            text = error,
                            color = Color.Red,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.align(Alignment.Center),
                            fontSize = 25.sp
                        )
                    }
                }
            }
        }
    }
}