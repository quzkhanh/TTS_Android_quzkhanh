package com.example.task_4_weatherapp.ui.viewmodel

import com.example.task_4_weatherapp.domain.model.CityWeather

sealed interface CardUiState {
    data object Loading : CardUiState
    data class Success(val data: CityWeather) : CardUiState
    data class Error(val message: String, val cityName: String) : CardUiState
}
