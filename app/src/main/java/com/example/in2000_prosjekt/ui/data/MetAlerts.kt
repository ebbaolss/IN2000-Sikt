package com.example.in2000_prosjekt.ui.data

import android.icu.util.DateInterval
import android.net.wifi.aware.AwareResources

data class Build (
    val features: List<Features>?,
    val lang : String?,
    val lastChange: String?,
    val type: String?
    )

data class Features (
    val data : AlertData?
    )

data class AlertData (
    val geometry: GeoData?,
    val properties: PropData?,
    val type: String?,
    //val interval burde være val when, men when er en operasjon..
    val interval : Map<String, List<String>>?
    )

data class GeoData (
    val coordinates : Coordinates?,
    val type : String?
    )

data class PropData (
    val area : String?,
    val awarenessResponse : String?,
    val awarenessSeriousness : String?,
    val awareness_level : String?,
    val awareness_type : String?,
    val certainty : String?,
    val consequences : String?,
    // usikker på om county vil være Int eller List siden doku sier Int men viser list i json fil
    val county: Integer?,
    val description : String?,
    val event : String?,
    val eventAwarenessName : String?,
    val eventEndingTime: String?,
    val geographicDomain : String?,
    val id : String?,
    val instruction : String?,
    val resources: List<Resources?>?,
    val severity : String?,
    val title : String?,
    val triggerLevel : String?,
    val type: String?
)

data class Resources (
    val res : Map<String,String>?
    )

data class Coordinates(
    val coordinates: List<List<Float>>?
)