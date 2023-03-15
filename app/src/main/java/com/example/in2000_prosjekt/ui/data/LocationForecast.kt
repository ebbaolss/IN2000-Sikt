package com.example.in2000_prosjekt.ui.data

import com.google.gson.annotations.SerializedName

data class LocationForecast(
    //Valgt ut de viktigste parameterne til å begynne med
    @SerializedName("air_temperature") var air_temp : List<String> = listOf(),
    @SerializedName("air_temperature_max") var air_temp_max: List<String> = listOf(),
    @SerializedName("air_temperature_min") var air_temp_min: List<String> = listOf(),
    @SerializedName("cloud_area_fraction") var cloud_area_fraction: List<String> = listOf(),
    @SerializedName("cloud_area_fraction_high") var cloud_area_high: List<String> = listOf(),
    @SerializedName("cloud_area_fraction_low") var cloud_area_low: List<String> = listOf(),
    @SerializedName("cloud_area_fraction_medium") var cloud_area_med: List<String> = listOf(),
    @SerializedName("dew_point_temperature") var dew_point_temp: List<String> = listOf(),
    @SerializedName("fog_area_fraction") var fog_area_fraction: List<String> = listOf(),
    @SerializedName("precipitation_amount") var nedbor_amount: List<String> = listOf(),
    @SerializedName("probability_of_precipitation") var prob_nedbor: List<String> = listOf(),
    @SerializedName("probability_of_thunder") var prob_thunder: List<String> = listOf(),
    @SerializedName("relative_humidity") var relative_humidity: List<String> = listOf(),
    @SerializedName("wind_from_direction") var wind_direction: List<String> = listOf()
    )
