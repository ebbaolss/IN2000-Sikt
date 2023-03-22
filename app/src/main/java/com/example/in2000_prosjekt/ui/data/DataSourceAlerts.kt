package com.example.in2000_prosjekt.ui.data

import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.gson.*

class DataSourceAlerts (private val county: String){

    private val client = HttpClient() {
        install(ContentNegotiation) {
            gson()
        }
    }

    suspend fun fetchMetAlert() : Build {
        val strCounty: String = "county=$county"

        Log.d("METALERT","METALERT URL: https://api.met.no/weatherapi/metalerts/1.1/.json?$strCounty")
        return client.get("https://api.met.no/weatherapi/metalerts/1.1/.json?$strCounty").body()
    }
}