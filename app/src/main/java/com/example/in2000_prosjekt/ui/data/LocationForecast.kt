package com.example.in2000_prosjekt.ui.data

import com.google.gson.annotations.SerializedName

data class LocationForecast(
    //Valgt ut de viktigste parameterne til å begynne med
    @SerializedName("details") var data : Map<String,Any> = mapOf(),
    //@SerializedName("time") var time : String
)
