package com.example.in2000_prosjekt.ui.data

import com.example.in2000_prosjekt.ui.*

interface WeatherRepository {
    suspend fun getLocation(latitude: String, longitude: String, altitude: String): LocationInfo
    suspend fun getNowCast(latitude: String, longtitude: String, altitude: String) : NowCastInfo
    suspend fun getSunrise(latitude: String, longtitude: String) : SunriseInfo
    suspend fun getAlert(county: String) : MutableList<AlertInfo>
    suspend fun getFrost(latitude: String, longtitude: String) : FrostInfo
    //val frost = dataFrost.fetchFrostTemp(elements, referencetime, source) hva med denne fra view
}

