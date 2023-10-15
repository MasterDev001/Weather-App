package com.example.weatherapp.entity.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CityEntity::class], version = 4)
abstract class MyDatabase:RoomDatabase() {

    abstract val cityDao: CityDao

    companion object {
        const val DATABASE_NAME= "weather_database"
    }

}