package com.example.in2000_prosjekt.data.map

data class MapBuild(
    val suggestions: List<Contents>,
    val attribution : String?
)

data class Contents(
    val name : String?,
    val mapbox_id : String?,
    val feature_type : String?

)

data class Info(
    val country : Country?,
    val region : Region?,
    val postcode : Postcode?,
    val place : Place?,
    val street : Street?
)

data class Country(
    val id : String?,
    val name :  String?,
    val country_code : String?,
    val country_code_alpha_3 : String?,
)
data class Region(
    val id : String?,
    val name : String?,
    val region_code : String?,
    val region_code_full : String?

)

data class Postcode(
    val id : String?,
    val name : String?
)

data class Place(
    val id : String?,
    val name : String?
)
data class Street(
    val name : String?
)
data class ExternalIds(
    val foursquare : String?
)