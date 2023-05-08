package com.example.in2000_prosjekt.ui.data

import com.example.in2000_prosjekt.ui.*
import io.github.boguszpawlowski.composecalendar.header.MonthState

interface WeatherRepository {
   // abstract val testdatesMutableLiveData: MutableLiveData<String> funka ikke

    suspend fun getLocation(latitude: String, longitude: String, altitude: String): LocationInfo
    suspend fun getNowCast(latitude: String, longitude: String, altitude: String) : NowCastInfo
    suspend fun getSunrise(latitude: String, longitude: String) : SunriseInfo
    suspend fun getAlert(latitude: String, longitude: String) : MutableList<AlertInfo>
    suspend fun getFrost(latitude: String, longitude: String, referencetime: MonthState) : FrostInfo

}

