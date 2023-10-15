package com.example.weatherapp.presentation.view_models.info

import com.example.weatherapp.app.navigation.AppNavigator
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoViewModelImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : ViewModel(), InfoViewModel {

    override fun onEventDispatcher(intent: InfoContract.Intent) {
        when (intent) {
            is InfoContract.Intent.NavigateToHomeScreen -> {
                viewModelScope.launch { appNavigator.back() }
            }
        }
    }
}