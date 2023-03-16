package com.example.in2000_prosjekt.ui

import com.example.in2000_prosjekt.ui.data.DayForecast
import com.example.in2000_prosjekt.ui.data.LocationForecast
import com.example.in2000_prosjekt.ui.data.Nowcast

data class AppUiState(
    //test med time:
    //val locationForecast: String
    val locationForecast: List<DayForecast>,
    //val nowcast: Nowcast
)