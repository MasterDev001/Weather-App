package com.example.weatherapp.app.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weatherapp.entity.local.CityDao
import com.example.weatherapp.entity.local.MyDatabase
import com.example.weatherapp.entity.location.LocationTracker
import com.example.weatherapp.entity.location.LocationTrackerImpl
import com.example.weatherapp.entity.remote.WeatherApi
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi {
        return Retrofit.Builder().baseUrl("https://api.weatherapi.com/")
            .addConverterFactory(MoshiConverterFactory.create()).build().create()
    }


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MyDatabase {
        return Room.databaseBuilder(context, MyDatabase::class.java, MyDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries().build()
    }

    @[Provides Singleton]
    fun providesCityDao(db: MyDatabase): CityDao = db.cityDao

    @[Provides Singleton]
    fun providerFusedLocationClient(app: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(app)
    }

    @[Provides Singleton]
    fun provideLocationTracker(@ApplicationContext context: Context,locationProviderClient: FusedLocationProviderClient): LocationTracker =
        LocationTrackerImpl(context,locationProviderClient)


}