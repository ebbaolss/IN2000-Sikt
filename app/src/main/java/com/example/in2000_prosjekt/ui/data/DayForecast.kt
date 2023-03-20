package com.example.in2000_prosjekt.ui.data

import com.google.gson.annotations.SerializedName

data class DayForecast (

        //Valgt ut de viktigste parameterne til å begynne med
        var time : String,
        var symbol_code : String,
        var air_temperature : Float?,
        var air_temperature_max: Float,
        var air_temperature_min: Float,
        var cloud_area_fraction: Float,
        var cloud_area_fraction_high: Float,
        var cloud_area_fraction_low: Float,
        var cloud_area_fraction_medium: Float,
        var dew_point_temperature: Float,
        var fog_area_fraction: Float,
        var precipitation_amount: Float,
        var probability_of_precipitation: Float,
        var probability_of_thunder: Float,
        var relative_humidity: Float,
        var wind_from_direction: Float

)
