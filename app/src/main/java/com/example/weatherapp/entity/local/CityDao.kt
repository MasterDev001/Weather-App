package com.example.weatherapp.entity.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCity(cityData: CityEntity):Long

    @Delete
    suspend fun deleteCity(cityData: CityEntity):Int

    @Delete
    suspend fun deleteCityList(cityList:List<CityEntity>):Int

    @Query("SELECT * FROM cityTable WHERE id=:id")
    suspend fun getCity(id: Int): CityEntity

    @Query("SELECT * FROM cityTable")
    suspend fun getAllCites(): List<CityEntity>

    @Query("SELECT * FROM cityTable WHERE isCurrent = 1 LIMIT 1")
    suspend fun getCurrentCity():CityEntity

    @Query("UPDATE cityTable SET isCurrent = 0")
    suspend fun prepareToSelectCurrent():Int
}