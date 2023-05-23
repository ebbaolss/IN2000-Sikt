package com.example.in2000_prosjekt.database

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.in2000_prosjekt.data.ImplementedWeatherRepository
import com.example.in2000_prosjekt.data.WeatherRepository
import com.example.in2000_prosjekt.ui.uistate.MapUiState
import com.mapbox.geojson.Point
import com.mapbox.maps.MapboxMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MapViewModel(application: Application) : ViewModel() {

    private val _cameraOptionsUiState: MutableStateFlow<MapUiState.MapboxCameraOptions> = MutableStateFlow(MapUiState.MapboxCameraOptions())
    val cameraOptionsUiState: StateFlow<MapUiState.MapboxCameraOptions> = _cameraOptionsUiState.asStateFlow()

    private val _mountainUiState = MutableStateFlow(MapUiState.Mountain())
    val mountainUiState: StateFlow<MapUiState.Mountain> = _mountainUiState.asStateFlow()

    private val _mapInfoUistate = MutableStateFlow(MapUiState.MapInfo())
    val mapInfoUiState = _mapInfoUistate.asStateFlow()

    private val mapCooUistate = MutableStateFlow(MapUiState.MapCoordinatesInfo())
    val coordinatesUiState = mapCooUistate.asStateFlow()

    private val repository: WeatherRepository = ImplementedWeatherRepository() //lettvinte måten

    fun getDataSearch(query: String) : Boolean {

        val path = "https://api.mapbox.com/search/searchbox/v1/suggest?q=$query&limit=10&" +
                "session_token=[GENERATED-UUID]&country=NO&poi_category=mountain&poi_category_exclusions=street&" +
                "access_token=pk.eyJ1IjoiZWxpc2FiZXRoYiIsImEiOiJjbGY2c3N3dDAxYWxsM3ludHY5em5wMnJxIn0.YVrKFoHYA1sCJhgBCbhudw"

        viewModelScope.launch {
            val mapSearchDeferred = viewModelScope.async(Dispatchers.IO){
                repository.getMap(path)
            }
            val mapSearchP = mapSearchDeferred.await()

            _mapInfoUistate.value = MapUiState.MapInfo(mapSearchP.optionMountains, _mapInfoUistate.value.recentSearch)
        }
        return true
    }
    fun showSelectedMountain( mapboxId : String, name: String) {
        viewModelScope.launch {

            val path2 = "https://api.mapbox.com/search/searchbox/v1/retrieve/$mapboxId?" +
                    "session_token=[GENERATED-UUID]&" +
                    "access_token=pk.eyJ1IjoiZWxpc2FiZXRoYiIsImEiOiJjbGY2c3N3dDAxYWxsM3ludHY5em5wMnJxIn0.YVrKFoHYA1sCJhgBCbhudw"


            val mapSearchCoordinates = repository.getMapCoordinates(path2)

            //updateMountain(MapUiState.Mountain(name, mapSearchCoordinates.latitude.toString(), mapSearchCoordinates.longitude.toString(), altitude))

            mapCooUistate.update {
                MapUiState.MapCoordinatesInfo(
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
            it.copy(
                name = mountain.name,
                latitude = mountain.latitude,
                longitude = mountain.longitude,
                elevation = mountain.elevation
            )
        }
    }

    fun onCameraChange(mapboxMap: MapboxMap) {
        val screenCenter = mapboxMap.cameraState.center
        val zoom = mapboxMap.cameraState.zoom
        updateCameraPosition(screenCenter)
        updateCameraZoomState(zoom)
    }

    private fun updateCameraPosition(point: Point) {
        _cameraOptionsUiState.update {
            it.copy(currentScreenLatitude = point.latitude(), currentScreenLongitude = point.longitude())
        }
    }

    private fun updateCameraZoomState(zoom: Double) {
        _cameraOptionsUiState.update {
            it.copy(currentScreenZoom = zoom)
        }
    }

}