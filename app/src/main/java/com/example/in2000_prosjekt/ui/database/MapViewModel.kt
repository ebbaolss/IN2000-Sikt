package com.example.in2000_prosjekt.ui.database

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.in2000_prosjekt.ui.*
import com.example.in2000_prosjekt.ui.data.ImplementedWeatherRepository
import com.example.in2000_prosjekt.ui.data.WeatherRepository
import com.example.in2000_prosjekt.ui.screens.onFeatureClicked
import com.example.in2000_prosjekt.ui.uistate.MapUiState
import com.mapbox.geojson.Point
import com.mapbox.maps.MapView
import com.mapbox.maps.MapboxMap
import com.mapbox.maps.RenderedQueryGeometry
import com.mapbox.maps.RenderedQueryOptions
import com.mapbox.maps.ScreenBox
import com.mapbox.maps.ScreenCoordinate
import com.mapbox.maps.Style
import com.mapbox.maps.dsl.cameraOptions
import com.mapbox.maps.extension.style.expressions.dsl.generated.eq
import com.mapbox.maps.extension.style.layers.generated.circleLayer
import com.mapbox.maps.extension.style.sources.generated.vectorSource
import com.mapbox.maps.extension.style.style
import com.mapbox.maps.plugin.compass.compass
import com.mapbox.maps.plugin.scalebar.scalebar
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

    lateinit var mapView: MapView

    val locationCardState  = mutableStateOf(false)

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

            // updateMountain(MapUiState.Mountain(name, mapSearchCoordinates.latitude.toString(), mapSearchCoordinates.longitude.toString(), altitude))
            // getAllSearch(mapSearchCoordinates.latitude.toString(),mapSearchCoordinates.longitude.toString(), altitude.toString())

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

    fun createFactoryMap(xt: Context, CameraOptionsUiState: MapUiState.MapboxCameraOptions) {
        mapView = MapView(xt).apply {
            val mapboxMap = getMapboxMap()
            val cameraOptionsUiState = CameraOptionsUiState

            mapboxMap.loadStyle(
                // Declares map style
                style(styleUri = Style.OUTDOORS) {

                    // Adding data layer source to rendered map
                    +vectorSource(id = "STREETS_V8") {
                        url("mapbox://mapbox.mapbox-streets-v8")
                    }

                    // Creates an interactable point layer on top of style layer
                    +circleLayer(layerId = "MOUNTAINS_DATALAYER", sourceId = "STREETS_V8") {
                        // natural label er et lag i STREETS_V8 datasettet til Mapbox og inneholder naturobjekter som fjell, innsjøer etc.
                        sourceLayer("natural_label")

                        // Filtering out all natural labels points that are not marked with the mountains icon
                        filter(
                            eq {
                                get { literal("maki") }
                                literal("mountain")
                            }
                        )

                        circleOpacity(0.0)
                    }
                }
            )

            // Camera settings
            mapboxMap.setCamera(
                cameraOptions {
                    zoom(cameraOptionsUiState.currentScreenZoom)
                    // Koordinatene til Glittertind
                    center(
                        Point.fromLngLat(
                            cameraOptionsUiState.currentScreenLongitude,
                            cameraOptionsUiState.currentScreenLatitude
                        )
                    )
                }
            )
        }


        // Editing compass settings, so that searchbar does not block compass
        mapView.compass.updateSettings {
            marginTop = 250F
        }

        // Editing scalebar, so that searchbar does not block scalebar
        mapView.scalebar.updateSettings {
            marginTop = 250F
        }
    }

    // Definerer hva som skal skje når brukeren trykker på kartet
    fun onMapClick(point: Point, mapboxMap: MapboxMap, mapViewModel: MapViewModel, apiViewModel: APIViewModel, onClick : () -> Unit) : Boolean {
        Log.d("Coordinate", point.toString())

        mapboxMap.queryRenderedFeatures(
            RenderedQueryGeometry(
                ScreenBox(
                ScreenCoordinate(
                    mapboxMap.pixelForCoordinate(point).x - 10.0,
                    mapboxMap.pixelForCoordinate(point).y - 10.0
                ),
                ScreenCoordinate(
                    mapboxMap.pixelForCoordinate(point).x + 10.0,
                    mapboxMap.pixelForCoordinate(point).y + 10.0
                )
            )
            ),
            RenderedQueryOptions(listOf("MOUNTAINS_DATALAYER"), null)
        ) { it ->
            onFeatureClicked(it) { feature ->
                if (feature.id() != null) {
                    val name = feature.getStringProperty("name")
                    val elevation = feature.getStringProperty("elevation_m").toInt()
                    val point = feature.geometry() as Point
                    val latitude =  point.latitude().toString()
                    val longitude = point.longitude().toString()

                    // Saving a clicked mountain to the UiState through the view model
                    mapViewModel.updateMountain(MapUiState.Mountain(name, latitude, longitude, elevation))

                    apiViewModel.getAll(latitude, longitude, "$elevation")

                    onClick()

                    // DEBUGGING
                    Log.d("Map Feature Clicked", feature.toString())
                    Log.d("Feature Contents:", " \nMountain Name:" + feature.getStringProperty("name").toString() +
                            "\nElevation: " + feature.getStringProperty("elevation_m").toString() + " m.o.h.\n" +
                            "Point: " + feature.geometry().toString()
                    )
                }
            }
        }
        return true
    }
}