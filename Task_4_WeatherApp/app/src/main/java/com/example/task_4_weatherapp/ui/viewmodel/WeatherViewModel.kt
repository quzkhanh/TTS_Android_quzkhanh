package com.example.task_4_weatherapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task_4_weatherapp.BuildConfig
import com.example.task_4_weatherapp.data.remote.RetrofitClient
import com.example.task_4_weatherapp.data.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val repository = WeatherRepository(
        api = RetrofitClient.weatherApiService,
        apiKey = BuildConfig.WEATHER_API_KEY
    )

    private val cities = listOf("Hanoi", "Ho Chi Minh", "Da Nang")

    private val _cardStates = MutableStateFlow<List<CardUiState>>(emptyList())
    val cardStates: StateFlow<List<CardUiState>> = _cardStates.asStateFlow()

    init {
        loadAllCities()
    }

    private fun loadAllCities() {
        _cardStates.value = cities.map { CardUiState.Loading }
        cities.forEachIndexed { index, cityName ->
            loadCity(index, cityName)
        }
    }

    private fun loadCity(index: Int, cityName: String) {
        viewModelScope.launch {
            updateCardState(index, CardUiState.Loading)
            try {
                val weather = repository.getCityWeather(cityName)
                updateCardState(index, CardUiState.Success(weather))
            } catch (e: Exception) {
                val message = when {
                    e.message?.contains("404") == true -> "Không tìm thấy thành phố"
                    e.message?.contains("401") == true -> "Lỗi API key"
                    e.message?.contains("Unable to resolve host") == true -> "Không có kết nối mạng"
                    else -> "Lỗi tải dữ liệu"
                }
                updateCardState(index, CardUiState.Error(message, cityName))
            }
        }
    }

    private fun updateCardState(index: Int, state: CardUiState) {
        val current = _cardStates.value.toMutableList()
        if (index < current.size) {
            current[index] = state
        }
        _cardStates.value = current
    }

    fun retryCity(index: Int) {
        val state = _cardStates.value.getOrNull(index)
        if (state is CardUiState.Error) {
            loadCity(index, state.cityName)
        }
    }

    fun refreshAll() {
        loadAllCities()
    }
}
