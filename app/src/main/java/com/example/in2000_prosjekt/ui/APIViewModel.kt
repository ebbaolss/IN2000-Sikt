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
    val repository: WeatherRepository = ImplementedWeatherRepository() //lettvinte måten

    private val _appUistate: MutableStateFlow< AppUiState > = MutableStateFlow(AppUiState.Loading)
    val appUiState: StateFlow<AppUiState> = _appUistate.asStateFlow()

    lateinit var locationInfoState: LocationInfo
    lateinit var nowCastInfoState: NowCastInfo
    lateinit var alertInfoState: MutableList<AlertInfo>

    fun getAll(latitude: String, longitude: String, altitude: String) {
        viewModelScope.launch() {
            try {
                val locationDeferred = viewModelScope.async (Dispatchers.IO) {
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
                /*val frostDeferred = viewModelScope.async (Dispatchers.IO){
                    repository.getFrost(latitude, longitude)
                }*/

                nowCastInfoState = nowCastDeferred.await()
                Log.d("nowCastDeferred", "Success")

                locationInfoState = locationDeferred.await()
                Log.d("locationDeffered", "Success")

                val sunsetP = sunsetDeferred.await()
                Log.d("sunriseDeferred", "Success")

                alertInfoState = alertDeferred.await()
                Log.d("alertDeferred", "Success")

                //val frostP = frostDeferred.await()


                _appUistate.update {
                    AppUiState.Success(
                        locationF = locationInfoState,
                        nowCastF = nowCastInfoState,
                        sunriseF = sunsetP,
                        alertListF = alertInfoState,
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

