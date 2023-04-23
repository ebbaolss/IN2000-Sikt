package com.example.in2000_prosjekt.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

import com.example.in2000_prosjekt.ui.data.*
import io.ktor.client.call.*
import io.ktor.utils.io.errors.*
import kotlinx.coroutines.Deferred

import kotlinx.coroutines.async

class APIViewModel : ViewModel() {

    //manual dependency injection, se codelab
    val repository: WeatherRepository = ImplementedWeatherRepository() //lettvinte måten

    private val _appUistate: MutableStateFlow< AppUiState > = MutableStateFlow(AppUiState.Loading)
    val appUiState: StateFlow<AppUiState> = _appUistate.asStateFlow()
    val latitude = " 63.073"
    val longtitude = "8.98"
    val altitude: String = "600"

    init { //etterhvert så endrer man  fra å ha init til å kalle på getAll fra en annen fil
        //favoritter skal loades med en gang appen åpner, database se codelab
        getAll("63.073","8.98","600","3434" )
    }

    fun getAll(latitude: String, longitude: String, altitude: String, county: String) {
        viewModelScope.launch() {
            val locationDeferred = viewModelScope.async (Dispatchers.IO){
                repository.getLocation(latitude, longitude, altitude)
            }
            val nowCastDeferred = viewModelScope.async (Dispatchers.IO){
                repository.getNowCast(latitude, longitude, altitude)
            }
            val sunsetDeferred = viewModelScope.async (Dispatchers.IO){
                repository.getSunrise(latitude, longitude)
            }
            val alertDeferred = viewModelScope.async (Dispatchers.IO){
                repository.getAlert(latitude, longitude)
            }
            val frostDeferred = viewModelScope.async (Dispatchers.IO){
                repository.getFrost(latitude, longitude)
            }

            val nowCastP = nowCastDeferred.await()
            val locationP = locationDeferred.await()
            val sunsetP = sunsetDeferred.await()
            val alertP = alertDeferred.await()
            val frostP = frostDeferred.await()

            _appUistate.update {
                AppUiState.Success(
                    locationF = locationP,
                    nowCastF = nowCastP,
                    sunriseF = sunsetP,
                    alertListF = alertP,
                    //frostF = frostP
                )
            }
        }
    }
}

