package com.example.weatherapp.presentation.view_models.info

interface InfoContract {

    sealed interface Intent {
        object NavigateToHomeScreen : Intent
    }

}