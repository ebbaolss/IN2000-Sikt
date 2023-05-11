package com.example.in2000_prosjekt.ui.data

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.gson.*

class DataSourceAlerts (val basePath:String){

    private val client = HttpClient() {
        install(ContentNegotiation) {
            gson()
        }
    }

    suspend fun authURL(URL: String) : HttpResponse {
        return client.get(URL) {
            headers {append("X-gravitee-api-key", "e4990066-1695-43a6-9ea4-85551da13834")}}
    }

    suspend fun fetchMetAlert(latitude: String, longtitude: String) : Build {
        //val strCounty: String = "county=$county"
        //byttet til koordinater fordi county kan være tom.

        val coordinates = "lat=$latitude&lon=$longtitude"

        return authURL("$basePath/metalerts/1.1/.json?$coordinates").body()
    }
}