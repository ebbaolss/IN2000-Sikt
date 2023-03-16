package com.example.in2000_prosjekt.ui.data

//Satte ? bak alle typene for øyeblikket i tilfelle noe er null, men får se nærmere på dette senere.
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
    val next_12_hours: Next12?
)
data class Instant(
    val details: Map<String, Float>?
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
