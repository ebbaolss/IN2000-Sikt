package com.example.in2000_prosjekt.ui.uistate

import com.mapbox.geojson.Point

sealed interface MapUiState {
    data class MapboxCameraOptions(
        val currentScreenZoom: Double = 3.258872624950135,

        // Glittertind
        val currentScreenLatitude: Double = 65.15643802604247,
        val currentScreenLongitude: Double = 16.99935690928433,

        //val currentScreenPitch: Double, // Relevant when going into 3D view
        //val currentScreenBearing: Double // Compass direction
    )

    data class Mountain(
        val name: String? = null,
        val point: Point? = null,
        val elevation: Int? = null
    )

    /* Example of a Feature
      Feature {
        type     =  Feature,
        bbox     =  null,
        id       =  268629500,
        geometry =  Point {
            type        =  Point,
            bbox        =  null,
            coordinates =  [8.557780981063843, 61.65138739947395]
        },
        properties = {
            "iso_3166_1" : "NO",
            "elevation_ft" : 8084,
            "name_fr" : "Glittertind",
            "name_zh-Hant" : "格利特峰",
            "iso_3166_2" : "NO-05",
            "worldview" : "all",
            "name" : "Glittertinden",
            "disputed" : "false",
            "name_es" : "Glittertind",
            "name_zh-Hans" : "格利特峰",
            "name_ru" : "Глиттертинд",
            "elevation_m" : 2464,
            "name_ja" : "グリッテルティンデン",
            "maki" : "mountain",
            "name_en" : "Glittertind",
            "name_de" : "Glittertind",
            "filterrank" : 1,
            "class" : "landform",
            "name_script" : "Latin",
            "name_it" : "Glittertind",
            "sizerank" : 16
          }
      } */

}