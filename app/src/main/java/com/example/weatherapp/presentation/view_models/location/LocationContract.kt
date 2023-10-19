package com.example.weatherapp.presentation.view_models.location

import com.example.weatherapp.domain.model.CityData

interface LocationContract {

    sealed interface Intent {
        class AddCityWithName(val cityName:String) : Intent
        class ListenCityName(val cityName:String) : Intent
        object AddCityWithLocation:Intent
        object ClearCityList:Intent
        class ChangeCurrentCity(val cityKey:String):Intent
        object NavigateToHome:Intent
        class DeleteCity(val cityData: CityData,val index: Int): Intent
    }

    data class UiState(
        var cityData:List<CityData>?=null,
        var isLoading: Boolean = false,
        var errorMessage: String? = null,
    )

}