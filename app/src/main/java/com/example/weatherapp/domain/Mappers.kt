package com.example.weatherapp.domain

import android.annotation.SuppressLint
import android.util.Log
import com.example.weatherapp.common.parseToLocalDateTime
import com.example.weatherapp.domain.model.CityData
import com.example.weatherapp.domain.model.DailyData
import com.example.weatherapp.domain.model.HourlyData
import com.example.weatherapp.domain.model.WeatherType
import com.example.weatherapp.entity.local.CityEntity
import com.example.weatherapp.entity.remote.daily.DailyDataDto
import com.example.weatherapp.entity.remote.daily.DailyDto
import com.example.weatherapp.entity.remote.geocoding.CityDataDto
import com.example.weatherapp.entity.remote.geocoding.CityDto
import com.example.weatherapp.entity.remote.hourly.HourlyDataDto
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import kotlin.math.log

@SuppressLint("NewApi")
fun HourlyDataDto.toListHourlyData(): List<HourlyData> {
    return this.hourList.mapIndexed { index, hour ->
        val precipitation: Int = precipitationList[index]
        val pressure: Double = pressureList[index]
        val humidity: Int = humidityList[index]
        val temperature: Int = temperatureList[index].toInt()
        val visibility: Int = visibilityList[index]
        val windSpeed: Double = windSpeedList[index]
        val uvIndex: Double = uvIndexList[index]
        val windDirection: Int = windDirectionList[index]
        val weatherType: WeatherType = WeatherType.fromWMO(weatherCodeList[index])
        val isDay: Boolean = isDayList[index] == 1
        HourlyData(
            hour = LocalDateTime.parse(hour, DateTimeFormatter.ISO_DATE_TIME)
                .format(DateTimeFormatter.ofPattern("HH:mm")).toString(),
            precipitation = precipitation,
            pressure,
            humidity,
            temperature,
            visibility,
            windSpeed,
            uvIndex,
            windDirection,
            weatherType,
            isDay
        )
    }
}

@SuppressLint("NewApi")
fun DailyDto.toListDailyData(): List<DailyData> {
    val currentTemperature = currentWeather.currentTemperature.toInt()
    val currentWeatherType = WeatherType.fromWMO(currentWeather.weatherCode)
    return this.daily.dateList.mapIndexed { index, date ->
        val sunRise = daily.sunriseList[index].split("T")[1]
        val sunSet = daily.sunsetList[index].split("T")[1]
        val temperatureMax = daily.temperatureMaxList[index].toInt()
        val temperatureMin = daily.temperatureMinList[index].toInt()
        val weatherType = WeatherType.fromWMO(daily.weatherCodeList[index])
        DailyData(
            date = LocalDateTime.now(),
            sunRise,
            sunSet,
            temperatureMax,
            temperatureMin,
            weatherType,
            currentTemperature,
            currentWeatherType
        )
    }
}

fun CityDto.toCityDataList(): List<CityData> {
    return this.cityList.map { cityDataDto ->
        CityData(
            id = cityDataDto.id,
            cityName = cityDataDto.name,
            country = cityDataDto.country,
            admin1 = cityDataDto.admin1,
            countryCode = cityDataDto.countryCode,
            latitude = cityDataDto.latitude,
            longitude = cityDataDto.longitude
        )
    }
}

fun CityEntity.toCityData(): CityData {
    return CityData(
        id = id,
        cityName = cityName,
        country = country,
        admin1 = admin1,
        countryCode = countryCode,
        latitude = latitude,
        longitude = longitude,
        isCurrent = isCurrent
    )
}

fun CityData.toCityEntity(): CityEntity {
    return CityEntity(
        id = id,
        cityName = cityName,
        country = country,
        admin1 = admin1,
        countryCode = countryCode,
        latitude = latitude,
        longitude = longitude,
        isCurrent = isCurrent
    )
}

