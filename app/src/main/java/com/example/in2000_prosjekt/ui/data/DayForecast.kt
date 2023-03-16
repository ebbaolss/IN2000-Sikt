package com.example.in2000_prosjekt.ui.data

import com.google.gson.annotations.SerializedName

data class DayForecast (

        //Valgt ut de viktigste parameterne til å begynne med
        @SerializedName("air_temperature") var air_temp : String,
        @SerializedName("air_temperature_max") var air_temp_max: String,
        @SerializedName("air_temperature_min") var air_temp_min: String,
        @SerializedName("cloud_area_fraction") var cloud_area_fraction: String,
        @SerializedName("cloud_area_fraction_high") var cloud_area_high: String,
        @SerializedName("cloud_area_fraction_low") var cloud_area_low: String,
        @SerializedName("cloud_area_fraction_medium") var cloud_area_med: String,
        @SerializedName("dew_point_temperature") var dew_point_temp: String,
        @SerializedName("fog_area_fraction") var fog_area_fraction: String,
        @SerializedName("precipitation_amount") var nedbor_amount: String,
        @SerializedName("probability_of_precipitation") var prob_nedbor: String,
        @SerializedName("probability_of_thunder") var prob_thunder: String,
        @SerializedName("relative_humidity") var relative_humidity: String,
        @SerializedName("wind_from_direction") var wind_direction: String

)