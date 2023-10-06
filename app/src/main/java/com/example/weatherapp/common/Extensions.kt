package com.example.weatherapp.common

import android.annotation.SuppressLint
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@SuppressLint("NewApi")
fun String.parseToLocalDateTime(): LocalDateTime {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val localDate = LocalDate.parse(this, formatter)
    return LocalDateTime.of(localDate, LocalTime.MIDNIGHT)
}