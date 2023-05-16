package com.example.in2000_prosjekt.data

import com.google.gson.annotations.SerializedName

data class Frost_API_Respons(

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
data class Frost_API_Respons_for_koordinater (

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
data class DataFrost(

    var sourceId : String?,
    var referenceTime : String?,
    var observations : List<Observations?>? //spørsmålstegn også inni <> ?

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
    var value : Int?, //Er en verdi mellom 0-8:(0 = no clouds, 8 = completely overcast).Ref.https://frost.met.no/elementtable
    var unit : String?,
  //  var level : Level?, Denne gjelder ikke nå som vi bruker : mean(cloud_area_fraction P1D)
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


