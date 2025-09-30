package com.shahidcricbuzz.weatherforcastapp.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    @Query("SELECT * FROM weather_forecast ORDER BY date ASC")
    fun getAllWeather(): Flow<List<WeatherEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(weatherList: List<WeatherEntity>)

    @Query("DELETE FROM weather_forecast")
    suspend fun clearAll()

    @Query("SELECT COUNT(*) FROM weather_forecast")
    suspend fun getCount(): Int

    @Transaction
    suspend fun updateWeather(weatherList: List<WeatherEntity>) {
        clearAll()
        insertAll(weatherList)
    }
}