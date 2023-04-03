package com.example.in2000_prosjekt.ui

data class LocationInfo( //gi ting navn med også L på slutten
    val temperatureL: Float,
    val fog_area_fractionL : Float,
    val rainL : Float
)
data class NowCastInfo( //sette N eller Now på slutten
    val temperatureNow: Float,
    val windN : Float
)
data class SunriseInfo( //sett S på slutten
    val sunriseS: String,
    val sunsetS: String
)
data class AlertInfo(
    val areaA: String,
    val typeA: String,
    val consequenseA: String
)

sealed interface AppUiState2 {
    data class Success(
        val locationInfo: LocationInfo,
        val nowCastDef: NowCastInfo,
        val sunrise: SunriseInfo,
        val alert: AlertInfo
    ) : AppUiState2
    object Error : AppUiState2
    object Loading : AppUiState2
}