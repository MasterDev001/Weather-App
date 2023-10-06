package com.example.weatherapp.presentation.view_models.location

import com.example.weatherapp.domain.model.CityData

interface LocationContract {

    sealed interface Intent {
        class AddCityWithName(val cityName:String) : Intent
        object AddCityWithLocation:Intent
    }

    data class UiState(
        var cityData:List<CityData>?=null,
        var isLoading: Boolean? = false,
        var message: String? = null,
    )

}