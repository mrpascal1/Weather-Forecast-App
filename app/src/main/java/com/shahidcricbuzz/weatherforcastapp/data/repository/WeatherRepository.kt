package com.shahidcricbuzz.weatherforcastapp.data.repository

import com.shahidcricbuzz.weatherforcastapp.data.local.WeatherDao
import com.shahidcricbuzz.weatherforcastapp.data.local.WeatherEntity
import com.shahidcricbuzz.weatherforcastapp.data.remote.WeatherApi
import com.shahidcricbuzz.weatherforcastapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val api: WeatherApi,
    private val dao: WeatherDao
) {
    private val apiKey = "930564dda71dcf1e15df4ed4777f7822"

    fun getWeatherFromDb(): Flow<List<WeatherEntity>> {
        return dao.getAllWeather()
    }

    suspend fun fetchWeatherFromApi(city: String): Resource<List<WeatherEntity>> {
        return try {
            val response = api.getWeatherForecast(city, apiKey)
            val weatherList = response.forecastList
                .filter { it.dateTime.endsWith("12:00:00") }
                .take(3)
                .map { forecast ->
                    WeatherEntity(
                        cityName = city,
                        date = forecast.dateTime.substring(0, 10),
                        temperature = forecast.main.temperature,
                        description = forecast.weather.first().description,
                        icon = forecast.weather.first().icon
                    )
                }

            dao.updateWeather(weatherList)

            Resource.Success(weatherList)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An unknown error occurred")
        }
    }
}