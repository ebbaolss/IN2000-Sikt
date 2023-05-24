package com.example.in2000_prosjekt.ui

data class LocationInfo( //gi ting navn med også L på slutten
    val temperatureL: Int,
    val fog_area_fractionL: Float,
    val cloud_area_fraction: Float,
    var cloud_area_fraction_high: Float,
    var cloud_area_fraction_low: Float,
    var cloud_area_fraction_medium: Float,
    val rainL: Float,
    val tempNext1: Int,
    val tempNext2: Int,
    val tempNext3: Int,
    val tempNext4: Int,
    val tempNext5: Int,
    val tempNext6: Int,
    val tempNext7: Int,
    val tempNext8: Int,
    val tempNext9: Int,
    val tempNext10: Int,
    val tempNext11: Int,
    val tempNext12: Int,
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
    val temp_day1: Int,
    val temp_day2: Int,
    val temp_day3: Int,
    val temp_day4: Int,
    val cloud_day1: Float,
    val cloud_day2: Float,
    val cloud_day3: Float,
    val cloud_day4: Float,
)
data class NowCastInfo( //sette N eller Now på slutten
    val temperatureNow: Int,
    val windN : Float
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

sealed interface AppUiState {
    data class Success(
        val locationF: LocationInfo,
        val nowCastF: NowCastInfo,
        val alertListF: MutableList<AlertInfo>,
    ) : AppUiState
    object Error : AppUiState
    object Loading : AppUiState
}