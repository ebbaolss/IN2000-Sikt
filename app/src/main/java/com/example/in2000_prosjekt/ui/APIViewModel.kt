package com.example.in2000_prosjekt.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

import com.example.in2000_prosjekt.ui.data.*
import com.example.in2000_prosjekt.ui.uistate.MapUiState
import com.mapbox.geojson.Point
import io.ktor.utils.io.errors.*

import kotlinx.coroutines.async
import java.time.LocalDate

class APIViewModel () : ViewModel()
    {

    //manual dependency injection, se codelab
    private val repository: WeatherRepository = ImplementedWeatherRepository() //lettvinte måten

    private val _appUistate: MutableStateFlow< AppUiState > = MutableStateFlow(AppUiState.Loading)
    val appUiState: StateFlow<AppUiState> = _appUistate.asStateFlow()




    private val currentLatitude = 61.651356077904666
    private val currentLongitude = 8.557801680731075
    private val altitude: String = "600"





    fun getAll(latitude: String, longitude: String, altitude: String) {
        viewModelScope.launch() {
            try {
                val locationDeferred = viewModelScope.async (Dispatchers.IO) {
                    repository.getLocation(latitude, longitude, altitude)
                }
                val locationP = locationDeferred.await()

                val nowCastDeferred = viewModelScope.async (Dispatchers.IO){
                    repository.getNowCast(latitude, longitude, altitude)
                }

                val nowCastP = nowCastDeferred.await()

                val sunsetDeferred = viewModelScope.async (Dispatchers.IO){
                    repository.getSunrise(latitude, longitude)
                }
                val sunsetP = sunsetDeferred.await()

                val alertDeferred = viewModelScope.async (Dispatchers.IO){
                    repository.getAlert(latitude, longitude)
                }
                val alertP = alertDeferred.await()









                _appUistate.update {
                    AppUiState.Success(
                        locationF = locationP,
                        nowCastF = nowCastP,
                        sunriseF = sunsetP,
                        alertListF = alertP,
                        //frostF = frostP
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

