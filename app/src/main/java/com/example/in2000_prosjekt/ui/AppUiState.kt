package com.example.in2000_prosjekt.ui

data class LocationInfo( //gi ting navn med også L på slutten
    val temperatureL: Float,
    val fog_area_fractionL: Float,
    val cloud_area_fraction: Float,
    var cloud_area_fraction_high: Float,
    var cloud_area_fraction_low: Float,
    var cloud_area_fraction_medium: Float,
    val rainL: Float,
    val tempNext1: Float,
    val tempNext2: Float,
    val tempNext3: Float,
    val tempNext4: Float,
    val tempNext5: Float,
    val tempNext6: Float,
    val tempNext7: Float,
    val tempNext8: Float,
    val tempNext9: Float,
    val tempNext10: Float,
    val tempNext11: Float,
    val tempNext12: Float,
    val cloudinessNext1: Float,
    val cloudinessNext2: Float,
    val cloudinessNext3: Float,
    val cloudinessNext4: Float,
    val cloudinessNext5: Float,
    val cloudinessNext6: Float,
    val cloudinessNext7: Float,
    val cloudinessNext8: Float,
    val cloudinessNext9: Float,
    val cloudinessNext10: Float,
    val cloudinessNext11: Float,
    val cloudinessNext12: Float,
    val temp_day1: Float,
    val temp_day2: Float,
    val temp_day3: Float,
    val temp_day4: Float,
    val cloud_day1: Float,
    val cloud_day2: Float,
    val cloud_day3: Float,
    val cloud_day4: Float,
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