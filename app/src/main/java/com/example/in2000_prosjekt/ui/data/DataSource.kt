package com.example.in2000_prosjekt.ui.data

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.gson.*

class DataSource (private val latitude: String,
                  private val longtitude: String,
                  private val altitude: String?) {

    private val client = HttpClient () {
        install(ContentNegotiation) {
            gson()
        }
    }
    suspend fun fetchLocationForecast(): List<DayForecast> {
        var coordinates: String = "lat=$latitude&lon=$longtitude"
        if (altitude != null) {
            coordinates += "&altitude=$altitude"
        }
        return client.get("https://api.met.no/weatherapi/locationforecast/2.0/complete?$coordinates").body()
    }

    suspend fun fetchNowCast(): Nowcast{

        //PLACEHOLDER NOT CORRECT URL
        var coordinates: String = "lat=$latitude&lon=$longtitude"
        if (altitude != null) {
            coordinates += "&altitude=$altitude"
        }

        return client.get("https://api.met.no/weatherapi/nowcast/2.0/complete?$coordinates").body()
    }
}