package com.example.in2000_prosjekt.ui.data

data class MapCoordinatesBuild(
    val type: String?,
    val features : List<Content>?,
    val attribution : String?
)

data class Content(
    val type : String?,
    val geometry : Geo?,
    val properties : Prop?
)

data class Geo(
    val coordinates : List<Double>?,
    val type : String?
)

data class Prop(
    val name : String?,
    val mapbox_id : String?,
    val feature_type : String?,
    val address : String?,
    val full_address : String?,
    val place_formatted : String?,
    val context : Contex?,
    val coordinates : Coordinates?,
    val maki : String?,
    val poi_category : List<String>,
    val poi_category_ids : List<String>?,
    val external_ids : ExternalIdsMap?,
    val metadata : MutableMap<Any, Any>?
)

data class Contex(
    val country : CountryMap?,
    val postcode : PostcodeMap?,
    val place : PlaceMap?
)
data class CountryMap(
    val name : String?,
    val country_code : String?,
    val country_code_alpha_3 : String?
)
data class PostcodeMap(
    val name : String?
)
data class PlaceMap(
    val name : String?
)
data class Coordinates(
    val latitude : Float?,
    val longitude : Float?,
    val routable_points : List<RoutablePoints>?
)
data class RoutablePoints(
    val name : String?,
    val latitude : Float?,
    val longitude : Float?
)
data class ExternalIdsMap(
    val foursquare : String?
)