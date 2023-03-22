package com.example.in2000_prosjekt.ui.data

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.gson.*

class DataSourceSunrise(private val latitude: String,
                        private val longtitude: String) {

    private val client = HttpClient() {
        install(ContentNegotiation) {
            gson()
        }
    }

    suspend fun fetchSunrise(): SunriseBuild{

        //PLACEHOLDER NOT CORRECT URL
        val coordinates: String = "lat=$latitude&lon=$longtitude"

        return client.get("https://api.met.no/weatherapi/sunrise/3.0/sun?$coordinates&offset=+01:00").body()
    }


}