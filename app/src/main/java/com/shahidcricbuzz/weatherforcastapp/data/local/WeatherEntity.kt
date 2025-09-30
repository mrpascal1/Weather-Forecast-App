package com.shahidcricbuzz.weatherforcastapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_forecast")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val cityName: String,
    val date: String,
    val temperature: Double,
    val description: String,
    val icon: String
)