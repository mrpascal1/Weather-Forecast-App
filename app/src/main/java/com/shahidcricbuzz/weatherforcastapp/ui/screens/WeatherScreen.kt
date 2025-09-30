package com.shahidcricbuzz.weatherforcastapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.shahidcricbuzz.weatherforcastapp.data.local.WeatherEntity
import com.shahidcricbuzz.weatherforcastapp.ui.viewmodels.WeatherViewModel
import com.shahidcricbuzz.weatherforcastapp.util.Resource


@Composable
fun WeatherScreenWithViewModel(
    viewModel: WeatherViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    var city by remember { mutableStateOf("") }
    val weatherState by viewModel.weatherState.collectAsStateWithLifecycle()
    val isLoading = viewModel.isLoading

    WeatherScreen(
        city = city,
        onCityChange = { city = it },
        weatherState = weatherState,
        isLoading = isLoading,
        onSearch = { viewModel.fetchWeather(it) },
        modifier = modifier
    )
}

@Composable
fun WeatherScreen(
    city: String = "",
    onCityChange: (String) -> Unit = {},
    weatherState: Resource<List<WeatherEntity>> = Resource.Loading,
    isLoading: Boolean = false,
    onSearch: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // City Search
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = city,
                onValueChange = onCityChange,
                label = { Text("Enter city name") },
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = { onSearch(city) },
                enabled = city.isNotBlank() && !isLoading
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(16.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                } else {
                    Text("Search")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Weather Forecast
        WeatherForecastDisplay(weatherState = weatherState)
    }
}
