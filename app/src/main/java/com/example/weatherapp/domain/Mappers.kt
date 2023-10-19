package com.example.weatherapp.domain

import android.annotation.SuppressLint
import android.util.Log
import com.example.weatherapp.R
import com.example.weatherapp.domain.model.CityData
import com.example.weatherapp.domain.model.ConditionData
import com.example.weatherapp.domain.model.CurrentData
import com.example.weatherapp.domain.model.DailyData
import com.example.weatherapp.domain.model.HourlyData
import com.example.weatherapp.domain.model.InfoData
import com.example.weatherapp.domain.model.LocationData
import com.example.weatherapp.domain.model.WeatherData
import com.example.weatherapp.entity.local.CityEntity
import com.example.weatherapp.entity.remote.weather.CityDto
import com.example.weatherapp.entity.remote.weather.ConditionDto
import com.example.weatherapp.entity.remote.weather.CurrentDto
import com.example.weatherapp.entity.remote.weather.HourDto
import com.example.weatherapp.entity.remote.weather.LocationDto
import com.example.weatherapp.entity.remote.weather.WeatherDto
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun CityEntity.toCityData(): CityData {
    return CityData(
        key = key,
        cityName = cityName,
        country = country,
        region = region,
        latitude = latitude,
        longitude = longitude,
        isCurrent = isCurrent
    )
}

fun CityDto.cityDtoToCityData(): CityData {
    return CityData(
        key = name + region + country,
        cityName = name,
        country = country,
        region = region,
        latitude = lat,
        longitude = lon,
    )
}

fun CityData.toCityEntity(): CityEntity {
    return CityEntity(
        key = key,
        cityName = cityName,
        country = country,
        region = region,
        latitude = latitude,
        longitude = longitude,
        isCurrent = isCurrent
    )
}

fun ConditionDto.toConditionData(isDay: Boolean): ConditionData {
    return ConditionData(
        icon = weatherCodeToIcon(code, isDay),
        desc = text,
        lottie = weatherCodeToLottie(code, isDay)
    )
}

fun LocationDto.toLocationData(): LocationData {
    return LocationData(
        country = country,
        lat = lat,
        lon = lon,
        name = this.name,
        region = region,
        timezoneId = tzId
    )
}

fun CurrentDto.toCurrentData(): CurrentData {
    return CurrentData(
        condition = conditionDto.toConditionData(isDay == 1),
        feelsLikeC = feelsLikeC,
        humidity = humidity,
        isDay = isDay == 1,
        precipitation = precipitation,
        pressure = pressure.toInt(),
        temperature = temperature.toInt(),
        uv = uv.toInt(),
        visibility = visibility.toInt(),
        windSpeed = windSpeed.toInt()
    )
}

fun HourlyData.toInfoDataForMain(): InfoData {
    return InfoData(
        condition = conditionData,
        humidity = humidity,
        precipitation = precipitation,
        pressure = pressure,
        temperature = temperature,
        uv = uvIndex,
        visibility = visibility,
        windSpeed = windSpeed,
        isDay = isDay
    )
}

fun WeatherData.toInfoDataForMain(): InfoData {
    return InfoData(
        condition = currentData.condition,
        humidity = currentData.humidity,
        precipitation = currentData.precipitation,
        pressure = currentData.pressure,
        temperature = currentData.temperature,
        uv = currentData.uv,
        visibility = currentData.visibility,
        windSpeed = currentData.windSpeed,
        sunRise = dailyData[0].sunRise,
        sunSet = dailyData[0].sunSet,
        isDay = currentData.isDay,
    )
}

fun DailyData.toInfoData(): InfoData {
    return InfoData(
        condition = conditionData,
        humidity = humidity.toInt(),
        precipitation = totalPrecipitation,
        temperature = avgTemperature,
        uv = uv.toInt(),
        visibility = visibility.toInt(),
        windSpeed = maxWindSpeed.toInt(),
        sunRise = sunRise,
        sunSet = sunSet,
        snow = snow,
    )
}


@SuppressLint("NewApi")
fun HourDto.toHourlyData(): HourlyData {
    val hourTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    return HourlyData(
        hour = LocalDateTime.parse(time, hourTimeFormatter)
            .format(DateTimeFormatter.ofPattern("HH:mm")),
        precipitation = precipitation,
        pressure = pressure.toInt(),
        humidity = humidity,
        temperature = temperature.toInt(),
        visibility = visibility.toInt(),
        windSpeed = windSpeed.toInt(),
        uvIndex = uv.toInt(),
        feelsLike = feelsLike.toInt(),
        conditionData = conditionDto.toConditionData(isDay == 1),
        isDay = isDay == 1,
    )
}

@SuppressLint("NewApi")
fun WeatherDto.toWeatherData(): WeatherData {
    val currentData = currentDto.toCurrentData()
    val locationData = locationDto.toLocationData()
    val dailyDataList = ArrayList<DailyData>()
    val hourlyDataList = ArrayList<HourlyData>()
    forecastDto.forecastDay.map {
        dailyDataList.add(
            DailyData(
                date = LocalDateTime.parse(it.date + "T00:00", DateTimeFormatter.ISO_DATE_TIME),
                sunRise = it.astroDto.sunrise,
                sunSet = it.astroDto.sunset,
                avgTemperature = it.dayDto.avgTemp.toInt(),
                temperatureMax = it.dayDto.maxTemp.toInt(),
                temperatureMin = it.dayDto.minTemp.toInt(),
                totalPrecipitation = it.dayDto.totalPrecip,
                visibility = it.dayDto.avgVisibility,
                maxWindSpeed = it.dayDto.maxWind,
                humidity = it.dayDto.avgHumidity,
                uv = it.dayDto.uv,
                conditionData = it.dayDto.conditionDto.toConditionData(true),
                snow = it.dayDto.totalSnowCm
            )
        )
    }
    val firstDayHours = forecastDto.forecastDay[0].hourDto
    val secondDayHours = forecastDto.forecastDay[1].hourDto
    hourlyDataList.addAll(firstDayHours.map { it.toHourlyData() })
    hourlyDataList.addAll(secondDayHours.map { it.toHourlyData() })

    return WeatherData(
        locationData = locationData,
        currentData = currentData,
        dailyData = dailyDataList,
        hourlyData = hourlyDataList
    )
}

fun weatherCodeToIcon(code: Int, isDay: Boolean): Int {
    if (isDay) {
        return when (code) {
            1000 -> R.drawable.day_113
            1003 -> R.drawable.day_116
            1006 -> R.drawable.day_119_122
            1009 -> R.drawable.day_119_122
            1030 -> R.drawable.day_143
            1063 -> R.drawable.day_176
            1066 -> R.drawable.day_179_323_329
            1069 -> R.drawable.day_182
            1072 -> R.drawable.both_185_281_302_311
            1087 -> R.drawable.day_200
            1114 -> R.drawable.both_227
            1117 -> R.drawable.both_230
            1135 -> R.drawable.both_248
            1147 -> R.drawable.both_260
            1150 -> R.drawable.day_263
            1153 -> R.drawable.both_266
            1168 -> R.drawable.both_185_281_302_311
            1171 -> R.drawable.both_284_308_314
            1180 -> R.drawable.day_293_299_353
            1183 -> R.drawable.both_296
            1186 -> R.drawable.day_293_299_353
            1189 -> R.drawable.both_185_281_302_311
            1192 -> R.drawable.day_305_356
            1195 -> R.drawable.both_284_308_314
            1198 -> R.drawable.both_185_281_302_311
            1201 -> R.drawable.both_284_308_314
            1204 -> R.drawable.both_317_368
            1207 -> R.drawable.both_320
            1210 -> R.drawable.day_179_323_329
            1213 -> R.drawable.day_293_299_353
            1216 -> R.drawable.day_179_323_329
            1219 -> R.drawable.both_332
            1222 -> R.drawable.day_335
            1225 -> R.drawable.both_371_338
            1237 -> R.drawable.both_350_374
            1240 -> R.drawable.day_293_299_353
            1243 -> R.drawable.day_305_356
            1246 -> R.drawable.both_359
            1249 -> R.drawable.both_362_365
            1252 -> R.drawable.both_362_365
            1255 -> R.drawable.both_317_368
            1258 -> R.drawable.both_371_338
            1261 -> R.drawable.both_350_374
            1264 -> R.drawable.day_377
            1273 -> R.drawable.day_386
            1276 -> R.drawable.both_389
            1279 -> R.drawable.day_392
            1282 -> R.drawable.both_395
            else -> R.drawable.day_113
        }
    } else {
        return when (code) {
            1000 -> R.drawable.night_113
            1003 -> R.drawable.night_116_119_122
            1006 -> R.drawable.night_116_119_122
            1009 -> R.drawable.night_116_119_122
            1030 -> R.drawable.night_143
            1063 -> R.drawable.night_176
            1066 -> R.drawable.night_179_323_329
            1069 -> R.drawable.night_182
            1072 -> R.drawable.both_185_281_302_311
            1087 -> R.drawable.night_200
            1114 -> R.drawable.both_227
            1117 -> R.drawable.both_230
            1135 -> R.drawable.both_248
            1147 -> R.drawable.both_260
            1150 -> R.drawable.night_263
            1153 -> R.drawable.both_266
            1168 -> R.drawable.both_185_281_302_311
            1171 -> R.drawable.both_284_308_314
            1180 -> R.drawable.night_293_299_353
            1183 -> R.drawable.both_296
            1186 -> R.drawable.night_293_299_353
            1189 -> R.drawable.both_185_281_302_311
            1192 -> R.drawable.night_305_356
            1195 -> R.drawable.both_284_308_314
            1198 -> R.drawable.both_185_281_302_311
            1201 -> R.drawable.both_284_308_314
            1204 -> R.drawable.both_317_368
            1207 -> R.drawable.both_320
            1210 -> R.drawable.night_179_323_329
            1213 -> R.drawable.night_293_299_353
            1216 -> R.drawable.night_179_323_329
            1219 -> R.drawable.both_332
            1222 -> R.drawable.night_335
            1225 -> R.drawable.both_371_338
            1237 -> R.drawable.both_350_374
            1240 -> R.drawable.night_293_299_353
            1243 -> R.drawable.night_305_356
            1246 -> R.drawable.both_359
            1249 -> R.drawable.both_362_365
            1252 -> R.drawable.both_362_365
            1255 -> R.drawable.both_317_368
            1258 -> R.drawable.both_371_338
            1261 -> R.drawable.both_350_374
            1264 -> R.drawable.night_377
            1273 -> R.drawable.night_386
            1276 -> R.drawable.both_389
            1279 -> R.drawable.night_392
            1282 -> R.drawable.both_395
            else -> R.drawable.night_113
        }
    }
}

fun weatherCodeToLottie(code: Int, isDay: Boolean): Int {
    if (isDay) {
        return when (code) {
            1000 -> R.raw.clear_day
            1003 -> R.raw.partly_cloudy_day
            1006 -> R.raw.cloudy
            1009 -> R.raw.overcast_day
            1030 -> R.raw.mist
            1063 -> R.raw.partly_cloudy_day_rain
            1066 -> R.raw.partly_cloudy_day_snow
            1069 -> R.raw.partly_cloudy_day_sleet
            1072 -> R.raw.partly_cloudy_day_drizzle
            1087 -> R.raw.thunderstorms_day
            1114 -> R.raw.sleet
            1117 -> R.raw.blizzard
            1135 -> R.raw.fog
            1147 -> R.raw.fog
            1150 -> R.raw.drizzle
            1153 -> R.raw.drizzle
            1168 -> R.raw.drizzle
            1171 -> R.raw.drizzle
            1180 -> R.raw.partly_cloudy_day_rain
            1183 -> R.raw.rain
            1186 -> R.raw.partly_cloudy_day_rain
            1189 -> R.raw.rain
            1192 -> R.raw.partly_cloudy_day_rain
            1195 -> R.raw.rain
            1198 -> R.raw.rain
            1201 -> R.raw.rain
            1204 -> R.raw.sleet
            1207 -> R.raw.sleet
            1210 -> R.raw.partly_cloudy_day_snow
            1213 -> R.raw.snow
            1216 -> R.raw.snow
            1219 -> R.raw.snow
            1222 -> R.raw.partly_cloudy_day_snow
            1225 -> R.raw.snow
            1237 -> R.raw.hail
            1240 -> R.raw.partly_cloudy_day_rain
            1243 -> R.raw.partly_cloudy_day_rain
            1246 -> R.raw.partly_cloudy_day_rain
            1249 -> R.raw.sleet
            1252 -> R.raw.sleet
            1255 -> R.raw.partly_cloudy_day_snow
            1258 -> R.raw.snow
            1261 -> R.raw.partly_cloudy_day_hail
            1264 -> R.raw.partly_cloudy_day_hail
            1273 -> R.raw.thunderstorms_day_rain
            1276 -> R.raw.thunderstorms_rain
            1279 -> R.raw.thunderstorms_day_snow
            1282 -> R.raw.thunderstorms_snow
            else -> R.raw.clear_day
        }
    } else {
        return when (code) {
            1000 -> R.raw.clear_night
            1003 -> R.raw.partly_cloudy_night
            1006 -> R.raw.cloudy
            1009 -> R.raw.overcast_night
            1030 -> R.raw.mist
            1063 -> R.raw.partly_cloudy_night_rain
            1066 -> R.raw.partly_cloudy_night_snow
            1069 -> R.raw.partly_cloudy_night_sleet
            1072 -> R.raw.partly_cloudy_night_drizzle
            1087 -> R.raw.thunderstorms_night
            1114 -> R.raw.sleet
            1117 -> R.raw.blizzard
            1135 -> R.raw.fog
            1147 -> R.raw.fog
            1150 -> R.raw.drizzle
            1153 -> R.raw.drizzle
            1168 -> R.raw.drizzle
            1171 -> R.raw.drizzle
            1180 -> R.raw.partly_cloudy_night_rain
            1183 -> R.raw.rain
            1186 -> R.raw.partly_cloudy_night_rain
            1189 -> R.raw.rain
            1192 -> R.raw.partly_cloudy_night_rain
            1195 -> R.raw.rain
            1198 -> R.raw.rain
            1201 -> R.raw.rain
            1204 -> R.raw.sleet
            1207 -> R.raw.sleet
            1210 -> R.raw.partly_cloudy_night_snow
            1213 -> R.raw.snow
            1216 -> R.raw.snow
            1219 -> R.raw.snow
            1222 -> R.raw.partly_cloudy_night_snow
            1225 -> R.raw.snow
            1237 -> R.raw.hail
            1240 -> R.raw.partly_cloudy_night_rain
            1243 -> R.raw.partly_cloudy_night_rain
            1246 -> R.raw.partly_cloudy_night_rain
            1249 -> R.raw.sleet
            1252 -> R.raw.sleet
            1255 -> R.raw.partly_cloudy_night_snow
            1258 -> R.raw.snow
            1261 -> R.raw.partly_cloudy_night_hail
            1264 -> R.raw.partly_cloudy_night_hail
            1273 -> R.raw.thunderstorms_night_rain
            1276 -> R.raw.thunderstorms_rain
            1279 -> R.raw.thunderstorms_night_snow
            1282 -> R.raw.thunderstorms_snow
            else -> R.raw.clear_night
        }
    }
}
