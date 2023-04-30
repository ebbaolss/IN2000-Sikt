package com.example.in2000_prosjekt.ui.database

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.in2000_prosjekt.ui.uistate.MapUiState
import com.mapbox.geojson.Point
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MapViewModel(application: Application) : ViewModel() {
    private val _cameraOptionsUiState: MutableStateFlow<MapUiState.MapboxCameraOptions> = MutableStateFlow(MapUiState.MapboxCameraOptions())
    val cameraOptionsUiState: StateFlow<MapUiState.MapboxCameraOptions> = _cameraOptionsUiState.asStateFlow()

    private val _mountainUiState = MutableStateFlow(MapUiState.Mountain())
    val mountainUiState: StateFlow<MapUiState.Mountain> = _mountainUiState.asStateFlow()

    fun updateMountain(mountain: MapUiState.Mountain) {
        _mountainUiState.update {
            it.copy(mountain.name, mountain.point, mountain.elevation)
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
}