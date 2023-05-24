package com.example.in2000_prosjekt.data

import com.example.in2000_prosjekt.ui.*
import com.example.in2000_prosjekt.ui.uistate.MapUiState

interface WeatherRepository {
    suspend fun getLocation(latitude: String, longitude: String, altitude: String): LocationInfo
    suspend fun getNowCast(latitude: String, longitude: String, altitude: String) : NowCastInfo
    suspend fun getAlert(latitude: String, longitude: String) : MutableList<AlertInfo>
    suspend fun getMap(path: String) : MapUiState.MapInfo
    suspend fun getMapCoordinates(path: String) : MapUiState.MapCoordinatesInfo
}

