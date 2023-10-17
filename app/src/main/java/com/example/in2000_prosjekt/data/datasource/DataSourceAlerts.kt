package com.example.in2000_prosjekt.data.datasource

import com.example.in2000_prosjekt.data.Build
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.gson.*

class DataSourceAlerts (val basePath:String){

    private val client = HttpClient {
        install(ContentNegotiation) {
            gson()
        }
    }

    private suspend fun authURL(URL: String) : HttpResponse {
        return client.get(URL) {
            headers {append("X-gravitee-api-key", "b0012e14-178e-4658-babe-6c9f61954952")}}
    }

    suspend fun fetchMetAlert(latitude: String, longitude: String) : Build {
        //val strCounty: String = "county=$county"
        //byttet til koordinater fordi county kan være tom.

        val coordinates = "lat=$latitude&lon=$longitude"

        return authURL("$basePath/metalerts/1.1/.json?$coordinates").body()
    }
}