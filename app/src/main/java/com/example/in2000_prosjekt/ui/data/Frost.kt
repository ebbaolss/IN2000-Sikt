package com.example.in2000_prosjekt.ui.data

import com.google.gson.annotations.SerializedName

data class FrostResponsBuild(


    @SerializedName("@context") var context : String?,
    @SerializedName("@type") var type : String?,
    var apiVersion : String?,
    var license : String?,
    var createdAt : String?,
    var queryTime : Double?,
    var currentItemCount : Int?,
    var itemsPerPage : Int?,
    var offset : Int?,
    var totalItemCount : Int?,
    var currentLink : String?,
    var data : List<DataFrost>?


)
data class DataFrost(

    var sourceId : String?,
    var referenceTime : String?,
    var observations : List<Observations?>? //spørsmålstegn også inni <> ?

)
data class FrostCoordinatesBuild (

    @SerializedName("@context") var context : String?,
    @SerializedName("@type") var type : String?,
    var apiVersion : String?,
    var license : String?,
    var createdAt : String?,
    var queryTime : Double?,
    var currentItemCount : Int?,
    var itemsPerPage : Int?,
    var offset : Int?,
    var totalItemCount : Int?,
    var currentLink : String?,
    var data: List<Data_For_Koordinater>?


)
data class Data_For_Koordinater (

    var type : String?,
    var id : String?,
    var name : String?,
    var shortName : String?,
    var country : String?,
    var countryCode : String?,
    var geometry : Geometri?,
    var masl : Int?,
    var validFrom : String?,
    var county : String?,
    var countyId : Int?,
    var municipality : String?,
    var municipalityId : Int?,
    var stationHolders : List<String>?,
    var externalIds : List<String>?,
    var wigosId : String?

)
data class Geometri (

    var type : String?,
    var coordinates : List<Double>?,
    var nearest : Boolean?
)
data class Observations (

    var elementId : String?,
    var value : Double?,
    var unit : String?,
    var timeOffset : String?,
    var timeResolution : String?,
    var timeSeriesId : Int?,
    var performanceCategory : String?,
    var exposureCategory : String?,
    var qualityCode : Int?
)
data class Level (

    var levelType : String?,
    var unit : String?,
    var value : Int?
)


