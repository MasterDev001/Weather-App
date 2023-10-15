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
    suspend fun addCity(cityData: CityEntity): Long

    @Query("DELETE FROM cityTable WHERE `key`=:key")
     fun deleteCity(key: String): Int

    @Query("SELECT * FROM cityTable WHERE `key`=:key")
    suspend fun getCity(key: String): CityEntity

    @Query("SELECT * FROM cityTable")
    suspend fun getAllCites(): List<CityEntity>

    @Query("SELECT * FROM cityTable WHERE isCurrent = 1 LIMIT 1")
    suspend fun getCurrentCity(): CityEntity?

    @Query("UPDATE cityTable SET isCurrent = 1 WHERE `key` = :key")
    suspend fun selectCurrentCity(key: String): Int

    @Query("UPDATE cityTable SET isCurrent = 0")
    suspend fun prepareToSelectCurrent(): Int
}