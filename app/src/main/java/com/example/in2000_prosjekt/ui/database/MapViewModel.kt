package com.example.in2000_prosjekt.ui.database

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.in2000_prosjekt.ui.*
import com.example.in2000_prosjekt.ui.data.ImplementedWeatherRepository
import com.example.in2000_prosjekt.ui.data.WeatherRepository
import com.example.in2000_prosjekt.ui.uistate.MapUiState
import com.mapbox.geojson.Point
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class MapViewModel(application: Application) : ViewModel() {

    private val _cameraOptionsUiState: MutableStateFlow<MapUiState.MapboxCameraOptions> = MutableStateFlow(MapUiState.MapboxCameraOptions())
    val cameraOptionsUiState: StateFlow<MapUiState.MapboxCameraOptions> = _cameraOptionsUiState.asStateFlow()

    private val _mountainUiState = MutableStateFlow(MapUiState.Mountain())
    val mountainUiState: StateFlow<MapUiState.Mountain> = _mountainUiState.asStateFlow()

    private val repository: WeatherRepository = ImplementedWeatherRepository() //lettvinte måten

    private val _appUistate: MutableStateFlow< AppUiState > = MutableStateFlow(AppUiState.Loading)
    val appUiState: StateFlow<AppUiState> = _appUistate.asStateFlow()

    private val _mapInfoUistate = MutableStateFlow(MapInfo())
    val mapInfoUiState = _mapInfoUistate.asStateFlow()

    private val _MapCooUistate = MutableStateFlow(MapCoordinatesInfo())
    val CoordinatesUiState = _MapCooUistate.asStateFlow()

    fun getDataSearch(query: String) : Boolean {

        val path = "https://api.mapbox.com/search/searchbox/v1/suggest?q=$query&limit=10&" +
                "session_token=[GENERATED-UUID]&country=NO&poi_category=mountain&poi_category_exclusions=street&" +
                "access_token=pk.eyJ1IjoiZWxpc2FiZXRoYiIsImEiOiJjbGY2c3N3dDAxYWxsM3ludHY5em5wMnJxIn0.YVrKFoHYA1sCJhgBCbhudw"

        viewModelScope.launch() {
            val mapSearchDeferred = viewModelScope.async(Dispatchers.IO){
                repository.getMap(path)
            }
            val mapSearchP = mapSearchDeferred.await()

            _mapInfoUistate.value = MapInfo(mapSearchP.optionMountains, _mapInfoUistate.value.recentSearch)
        }
        return true
    }
    fun showSelectedMountain( mapboxId : String, name: String, altitude : Int) {
        viewModelScope.launch() {

            val path2 = "https://api.mapbox.com/search/searchbox/v1/retrieve/$mapboxId?" +
                    "session_token=[GENERATED-UUID]&" +
                    "access_token=pk.eyJ1IjoiZWxpc2FiZXRoYiIsImEiOiJjbGY2c3N3dDAxYWxsM3ludHY5em5wMnJxIn0.YVrKFoHYA1sCJhgBCbhudw"


            val mapSearchCoordinates = repository.getMapCoordinates(path2)

            //åpne card eller oppdatere det som får cardet til å vises
            println(mapSearchCoordinates.latitude)
            println(mapSearchCoordinates.longitude)

            updateMountain(MapUiState.Mountain(name, mapSearchCoordinates.latitude.toString(), mapSearchCoordinates.longitude.toString(), altitude))
            getAllSearch(mapSearchCoordinates.latitude.toString(),mapSearchCoordinates.longitude.toString(), altitude.toString())

            _MapCooUistate.update {
                MapCoordinatesInfo(
                    latitude = mapSearchCoordinates.latitude,
                    longitude = mapSearchCoordinates.longitude
                )
            }
        }
    }
    fun updateRecentSearch(input : String, delete : Boolean) : Boolean {

        val updatetList: MutableList<String> = mapInfoUiState.value.recentSearch.toMutableList()

        if (delete) {
            updatetList.remove(input)

        } else {

            if (input in updatetList) return true
            if (updatetList.isEmpty()) {
                updatetList.add(input)
            } else {
                updatetList.add(input)
                for (i in updatetList.size - 1 downTo 1 ) {
                    updatetList[i] = updatetList[i - 1]
                }
                updatetList[0] = input
                if (updatetList.size > 3) {
                    updatetList.removeAt(updatetList.lastIndex)
                }
            }
        }
        _mapInfoUistate.update {
            it.copy(
                recentSearch = updatetList
            )
        }
        return false
    }

    fun updateMountain(mountain: MapUiState.Mountain) {
        _mountainUiState.update {
            it.copy(mountain.name, mountain.latitude, mountain.longitude, mountain.elevation)
        }
    }

    fun updateCameraPosition(point: Point) {
        _cameraOptionsUiState.update {
            it.copy(currentScreenLatitude = point.latitude(), currentScreenLongitude = point.longitude())
        }
    }

    fun updateCameraZoomState(zoom: Double) {
        _cameraOptionsUiState.update {
            it.copy(currentScreenZoom = zoom)
        }
    }

    fun getAllSearch(latitude: String, longitude: String, altitude: String) {
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

                /*val frostDeferred = viewModelScope.async (Dispatchers.IO){
                    repository.getFrost(latitude, longitude)
                }*/

                //val frostP = frostDeferred.await()


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