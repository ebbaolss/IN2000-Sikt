package com.example.in2000_prosjekt.data.repository

import com.example.in2000_prosjekt.ui.uistate.AlertInfo
import com.example.in2000_prosjekt.ui.uistate.LocationInfo
import com.example.in2000_prosjekt.ui.uistate.MapUiState
import com.example.in2000_prosjekt.ui.uistate.NowCastInfo

interface WeatherRepository {
    suspend fun getLocation(latitude: String, longitude: String, altitude: String): LocationInfo
    suspend fun getNowCast(latitude: String, longitude: String, altitude: String) : NowCastInfo
    suspend fun getAlert(latitude: String, longitude: String) : MutableList<AlertInfo>
    suspend fun getMap(path: String) : MapUiState.MapInfo
    suspend fun getMapCoordinates(path: String) : MapUiState.MapCoordinatesInfo
}

