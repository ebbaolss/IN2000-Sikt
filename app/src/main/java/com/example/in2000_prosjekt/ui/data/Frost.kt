package com.example.in2000_prosjekt.ui.data

import com.google.gson.annotations.SerializedName



data class Frost_API_Respons(

    @SerializedName("@context"         ) var context         : String?         = null,
    @SerializedName("@type"            ) var type            : String?         = null,
    @SerializedName("apiVersion"       ) var apiVersion       : String?         = null,
    @SerializedName("license"          ) var license          : String?         = null,
    @SerializedName("createdAt"        ) var createdAt        : String?         = null,
    @SerializedName("queryTime"        ) var queryTime        : Double?         = null,
    @SerializedName("currentItemCount" ) var currentItemCount : Int?            = null,
    @SerializedName("itemsPerPage"     ) var itemsPerPage     : Int?            = null,
    @SerializedName("offset"           ) var offset           : Int?            = null,
    @SerializedName("totalItemCount"   ) var totalItemCount   : Int?            = null,
    @SerializedName("currentLink"      ) var currentLink      : String?         = null,
    @SerializedName("data"             ) var Frost_data             : ArrayList<DataFrost> = arrayListOf()

)


data class DataFrost(

    @SerializedName("sourceId"      ) var sourceId      : String?                 = null,
    @SerializedName("referenceTime" ) var referenceTime : String?                 = null,
    @SerializedName("observations"  ) var observations  : ArrayList<Observations> = arrayListOf()

)



data class Observations (

    @SerializedName("elementId"           ) var elementId           : String? = null,
    @SerializedName("value"               ) var value               : Double? = null,
    @SerializedName("unit"                ) var unit                : String? = null,
    @SerializedName("level"               ) var level               : Level?  = Level(),
    @SerializedName("timeOffset"          ) var timeOffset          : String? = null,
    @SerializedName("timeResolution"      ) var timeResolution      : String? = null,
    @SerializedName("timeSeriesId"        ) var timeSeriesId        : Int?    = null,
    @SerializedName("performanceCategory" ) var performanceCategory : String? = null,
    @SerializedName("exposureCategory"    ) var exposureCategory    : String? = null,
    @SerializedName("qualityCode"         ) var qualityCode         : Int?    = null

)

data class Level (

    @SerializedName("levelType" ) var levelType : String? = null,
    @SerializedName("unit"      ) var unit      : String? = null,
    @SerializedName("value"     ) var value     : Int?    = null

)

data class ResponsUiState (
    val Svar : Frost_API_Respons?

)

data class SamletRespons(

    val samletRespons: Frost_API_Respons
)
