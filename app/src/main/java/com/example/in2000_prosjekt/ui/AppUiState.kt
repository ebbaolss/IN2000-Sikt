package com.example.in2000_prosjekt.ui

import com.example.in2000_prosjekt.ui.data.Next6

data class LocationInfo( //gi ting navn med også L på slutten
    val temperatureL: Float,
    val fog_area_fractionL : Float,
    val cloud_area_fraction : Float,
    var cloud_area_fraction_high: Float,
    var cloud_area_fraction_low: Float,
    var cloud_area_fraction_medium: Float,
    val rainL : Float,
    val tempNext1L : Float,
    val tempNext6L: Float,
    val cloudinessNext1L : String,
    val cloudinessNext6L : String,
)
data class NowCastInfo( //sette N eller Now på slutten
    val temperatureNow: Float,
    val windN : Float
)
data class SunriseInfo( //sett S på slutten
    val sunriseS: String,
    val sunsetS: String
)
data class AlertInfo( //sett A på slutten
    val areaA: String,
    val typeA: String,
    val consequenseA: String,
    val recomendationA : String,
    val descriptionA : String,
    val alertTypeA: String,
    val alertLevelA: String,
    val timeIntervalA: List<String?>?
)

data class FrostInfo( //sett Frost på slutten, F brukes allerede
    val sightcondition: Int,
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

sealed interface AppUiState {
    data class Success(
        val locationF: LocationInfo,
        val nowCastF: NowCastInfo,
        val sunriseF: SunriseInfo,
        val alertListF: MutableList<AlertInfo>,
        // val frostF: FrostInfo
    ) : AppUiState
    object Error : AppUiState
    object Loading : AppUiState
}