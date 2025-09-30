package com.shahidcricbuzz.weatherforcastapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.shahidcricbuzz.weatherforcastapp.data.local.WeatherEntity
import com.shahidcricbuzz.weatherforcastapp.ui.theme.WeatherForecastAppTheme
import com.shahidcricbuzz.weatherforcastapp.util.Resource

@Preview(showSystemUi = true, name = "Loading")
@Composable
fun WeatherScreenPreviewLoading() {
    WeatherForecastAppTheme {
        WeatherScreen(
            weatherState = Resource.Loading,
            isLoading = true
        )
    }
}

@Preview(showSystemUi = true, name = "Success")
@Composable
fun WeatherScreenPreviewSuccess() {
    val fakeData = listOf(
        WeatherEntity(
            id = 1,
            cityName = "London",
            date = "2024-06-10",
            temperature = 18.5,
            description = "scattered clouds",
            icon = "03d"
        ),
        WeatherEntity(
            id = 2,
            cityName = "London",
            date = "2024-06-11",
            temperature = 22.0,
            description = "clear sky",
            icon = "01d"
        ),
        WeatherEntity(
            id = 3,
            cityName = "London",
            date = "2024-06-12",
            temperature = 16.2,
            description = "light rain",
            icon = "10d"
        )
    )
    WeatherForecastAppTheme {
        WeatherScreen(
            city = "London",
            weatherState = Resource.Success(fakeData),
            isLoading = false
        )
    }
}

@Preview(showSystemUi = true, name = "Error")
@Composable
fun WeatherScreenPreviewError() {
    WeatherForecastAppTheme {
        WeatherScreen(
            weatherState = Resource.Error("City not found"),
            isLoading = false
        )
    }
}