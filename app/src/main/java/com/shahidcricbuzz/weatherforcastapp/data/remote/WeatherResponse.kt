package com.shahidcricbuzz.weatherforcastapp.data.remote

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class WeatherResponse(
    @SerializedName("list") val forecastList: List<ForecastItem>
)

@Keep
data class ForecastItem(
    @SerializedName("dt_txt") val dateTime: String,
    @SerializedName("main") val main: MainWeather,
    @SerializedName("weather") val weather: List<WeatherCondition>
)

@Keep
data class MainWeather(
    @SerializedName("temp") val temperature: Double
)

@Keep
data class WeatherCondition(
    @SerializedName("main") val main: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
)