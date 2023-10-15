package com.example.weatherapp.app.di

import com.example.weatherapp.app.navigation.AppNavigator
import com.example.weatherapp.app.navigation.AppNavigatorDispatcher
import com.example.weatherapp.app.navigation.AppNavigatorHandler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Binds
    fun appNavigatorModule(impl: AppNavigatorDispatcher): AppNavigator

    @Binds
    fun appNavigatorHandlerModule(impl: AppNavigatorDispatcher): AppNavigatorHandler
}