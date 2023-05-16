package com.example.in2000_prosjekt.data

import com.google.gson.annotations.SerializedName

data class SunriseBuild (
    val copyright : String?,
    val licenseURL : String?,
    val type : String,
    val geometry : GeometrySun?,
    @SerializedName("when") val tid : When?,
    val properties : PropertiesSun?,
)

data class GeometrySun(
    val type : String,
    val coordinates:  List<Float>?
)

data class When(
    val interval : List<String>?
)

data class PropertiesSun(
    val body : String?,
    val sunrise : Sunrise?,
    val sunset : Sunset?,
    val solarnoon : Solarnoon?,
    val solarmidnight : Solarmidnight?
)

data class Sunrise(
    val time : String?,
    val azimuth : Float?
)

data class Sunset(
    val time : String?,
    val azimuth : Float?
)

data class Solarnoon(
    val time : String?,
    val disc_centre_elevation : Float?,
    val visible : Boolean?
)

data class Solarmidnight(
    val time : String?,
    val disc_centre_elevation : Float?,
    val visible : Boolean?
)
