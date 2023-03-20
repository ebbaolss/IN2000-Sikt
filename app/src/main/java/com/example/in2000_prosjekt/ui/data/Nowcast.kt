package com.example.in2000_prosjekt.ui.data

import com.google.gson.annotations.SerializedName

data class Nowcast(
    
    @SerializedName("air_temperature") var air_temp : String,
    @SerializedName("precipitation_amount") var nedbor_amount : String,
    @SerializedName("precipitation_rate") var nedbor_rate : String,
    @SerializedName("relative_humidity") var humidity : String,
    @SerializedName("wind_from_direction") var wind_direction : String,
    @SerializedName("wind_speed") var wind_speed : String

)