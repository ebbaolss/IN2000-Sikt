package com.example.in2000_prosjekt.ui.data

import com.google.gson.annotations.SerializedName

data class LocationForecast(
    //Valgt ut de viktigste parameterne til å begynne med
    @SerializedName("time") var time : List<DayForecast> = listOf()
)
