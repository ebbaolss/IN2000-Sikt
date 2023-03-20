package com.example.in2000_prosjekt.ui.data

import android.icu.util.DateInterval
import android.net.wifi.aware.AwareResources
import com.google.gson.annotations.SerializedName

data class Build (
    val features: List<Features>?,
    val lang : String?,
    val lastChange: String?,
    val type: String?
    )

data class Features(
    @SerializedName("") val feature: AlertData?
)
data class AlertData (
    val geometry: GeoData?,
    val properties: PropData?,
    val type: String?,
    //val interval burde være val when, men when er en operasjon..
    @SerializedName("when") val tid : TimeInterval?
    )

data class GeoData (
    val coordinates : List<Coordinates>?,
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
    val county: List<Int?>?,
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
    val description: String?,
    val mimeType : String?,
    val uri : String?
    )

data class Coordinates(
    val coordinates: List<Float>?
)
data class TimeInterval(
    val interval : List<String>?
)
