package com.example.weatherapp.common

sealed class ResultData<out T>{
    class Success<T>(val data:T):ResultData<T>()
    class Error<T>(val message:String):ResultData<T>()
    class Loading<T>(val isLoading:Boolean):ResultData<T>()
}
