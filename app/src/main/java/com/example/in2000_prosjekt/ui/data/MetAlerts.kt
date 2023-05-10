package com.example.in2000_prosjekt.ui.data

import com.google.gson.annotations.SerializedName

data class Build (
    val features: List<Features>?,
    val lang : String?,
    val lastChange: String?,
    val type: String?
    )

data class Features(
    val geometry : GeometryAlert?,
    val properties: PropertiesAlert?,
    val type : String?,
    @SerializedName("when") val tid : WhenMet?,
)
data class WhenMet(
    val interval : List<String?>
)

data class PropertiesAlert(
    val area : String?,
    val awarenessResponse : String?,
    val awarenessSeriousness : String?,
    val awareness_level : String?,
    val awareness_type : String?,
    val certainty : String?,
    val consequences : String?,
    val county : List<Int>?,
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

data class Resources(
    val description: String?,
    val mimeType : String?,
    val uri : String?
)

data class GeometryAlert(
    val coordinates : List<List<List<Float>>?>?, //fikse dette senere til polygon
    val type : String?
)
