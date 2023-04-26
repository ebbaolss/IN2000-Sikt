package com.example.in2000_prosjekt.ui

data class SearchUiState(
    val optionMountains : List<String> = emptyList()
)

data class LocationInfo( //gi ting navn med også L på slutten
    val temperatureL: Float,
    val fog_area_fractionL : Float,
    var cloud_area_fraction_high: Float,
    var cloud_area_fraction_low: Float,
    var cloud_area_fraction_medium: Float,
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

data class FrostInfo(
    val typeFrost : String,
    val longFrost : Double,
    val latFrost : Double,
)
data class MapInfo(
    //liste med forslag
    val mapboxId : String,
    val feature_type : String
)

sealed interface AppUiState {

    //En data class per screen
    data class SuccessFavorite(
        val locationF: LocationInfo,
        val nowCastF: NowCastInfo,
        val sunriseF: SunriseInfo,
        val alertListF: MutableList<AlertInfo>,
        val frostF: FrostInfo
    ) : AppUiState
    object ErrorFavorite : AppUiState
    object LoadingFavorite : AppUiState

    data class SuccessMapSearch(
        val mapSearchF : MapInfo
    ) : AppUiState
    object ErrorMapSearch : AppUiState
    object LoadingMapSearch : AppUiState
}