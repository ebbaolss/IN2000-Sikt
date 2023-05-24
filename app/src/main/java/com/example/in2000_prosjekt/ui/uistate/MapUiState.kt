package com.example.in2000_prosjekt.ui.uistate


sealed interface MapUiState {
    data class MapboxCameraOptions(
        val currentScreenZoom: Double = 3.258872624950135,

        // Glittertind
        val currentScreenLatitude: Double = 65.15643802604247,
        val currentScreenLongitude: Double = 16.99935690928433,
    )
    data class Mountain(
        val name: String? = null,
        val latitude : String? = null,
        val longitude: String? = null,
        val elevation: Int? = null
    )
    data class MapInfo(
        //hashmap med forslag
        val optionMountains : HashMap<String, String> = hashMapOf(),
        val recentSearch : MutableList<String> = mutableListOf()
    )
    data class MapCoordinatesInfo(
        val latitude : Double = 0.0, //double??
        val longitude : Double = 0.0
    )
}