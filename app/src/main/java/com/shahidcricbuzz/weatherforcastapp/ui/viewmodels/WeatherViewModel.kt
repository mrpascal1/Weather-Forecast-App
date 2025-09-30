package com.shahidcricbuzz.weatherforcastapp.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahidcricbuzz.weatherforcastapp.data.local.WeatherEntity
import com.shahidcricbuzz.weatherforcastapp.data.repository.WeatherRepository
import com.shahidcricbuzz.weatherforcastapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    private val _weatherState = MutableStateFlow<Resource<List<WeatherEntity>>>(Resource.Loading)
    val weatherState: StateFlow<Resource<List<WeatherEntity>>> = _weatherState.asStateFlow()

    var isLoading by mutableStateOf(false)
        private set

    init {
        loadWeatherFromDb()
    }

    private fun loadWeatherFromDb() {
        viewModelScope.launch {
            repository.getWeatherFromDb()
                .collect { weatherList ->
                    _weatherState.value = if (weatherList.isNotEmpty()) {
                        Resource.Success(weatherList)
                    } else {
                        Resource.Error("No cached weather data available")
                    }
                }
        }
    }

    fun fetchWeather(city: String) {
        isLoading = true
        _weatherState.value = Resource.Loading

        viewModelScope.launch {
            val result = repository.fetchWeatherFromApi(city)
            _weatherState.value = result
            isLoading = false
        }
    }
}