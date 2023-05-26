package com.example.in2000_prosjekt.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.in2000_prosjekt.data.repository.ImplementedWeatherRepository
import com.example.in2000_prosjekt.data.repository.WeatherRepository
import com.example.in2000_prosjekt.ui.uistate.AppUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import io.ktor.utils.io.errors.*
import kotlinx.coroutines.async

class APIViewModel : ViewModel()
    {

    //manual dependency injection, se codelab
    private val repository: WeatherRepository = ImplementedWeatherRepository()

    private val _appUistate: MutableStateFlow<AppUiState> = MutableStateFlow(AppUiState.Loading)
    val appUiState: StateFlow<AppUiState> = _appUistate.asStateFlow()

    fun getAll(latitude: String, longitude: String, altitude: String) {
        viewModelScope.launch {
            try {
                val locationDeferred = viewModelScope.async (Dispatchers.IO) {
                    repository.getLocation(latitude, longitude, altitude)
                }
                val locationP = locationDeferred.await()

                val nowCastDeferred = viewModelScope.async (Dispatchers.IO){
                    repository.getNowCast(latitude, longitude, altitude)
                }
                val nowCastP = nowCastDeferred.await()

                val alertDeferred = viewModelScope.async (Dispatchers.IO){
                    repository.getAlert(latitude, longitude)
                }
                val alertP = alertDeferred.await()

                _appUistate.update {
                    AppUiState.Success(
                        locationF = locationP,
                        nowCastF = nowCastP,
                        alertListF = alertP,
                    )
                }
            } catch (e: IOException) {// Inntreffer ved nettverksavbrudd
                _appUistate.update {
                    AppUiState.Error
                }
            }
        }
    }
}

