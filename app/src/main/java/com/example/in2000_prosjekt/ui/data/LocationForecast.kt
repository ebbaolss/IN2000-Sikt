package com.example.in2000_prosjekt.ui.data

//Satte ? bak alle typene for øyeblikket i tilfelle noe er null, men får se nærmere på dette senere.
//Denne kan brukes for nowcast også
data class Model(
    val type : String?,
    val geometry: Geometry?,
    val properties: Properties?
)
data class Geometry(
    val type: String?,
    val coordinates: List<Float>?
)
data class Properties(
    val meta: Meta?,
    val timeseries: List<Timeserie>?
)
data class Meta(
    val updated_at: String?,
    val units: Map<String, String>?
)
data class Timeserie(
    val time: String?,
    val data: Data?
)
data class Data(
    val instant : Instant?,
    val next_12_hours: Next12?,
    val next_1_hours : Next1?,
    val next_6_hours: Next6?
)
data class Instant(
    val details: Details
)
data class Next12(
    val summary: Map<String, String>?
)
data class Next1(
    val summary: Map<String, String>?,
    val details: Map<String, Float>?
)
data class Next6(
    val summary: Map<String, String>?,
    val details: Map<String, Float>?
)

data class Details(

    var time : String?,
    var symbol_code : String?,
    var air_temperature : Float?,
    var air_temperature_max: Float?,
    var air_temperature_min: Float?,
    var cloud_area_fraction: Float?,
    var cloud_area_fraction_high: Float?,
    var cloud_area_fraction_low: Float?,
    var cloud_area_fraction_medium: Float?,
    var dew_point_temperature: Float?,
    var fog_area_fraction: Float?,
    var precipitation_amount: Float?,
    var precipitation_amount_max: Float?,
    var precipitation_amount_min: Float?,
    var probability_of_precipitation: Float?,
    var probability_of_thunder: Float?,
    var relative_humidity: Float?,
    var ultraviolet_index_clear_sky_max : Float?,
    var wind_from_direction: Float?,
    var wind_speed: Float?,
    var wind_speed_of_gust: Float?
)
