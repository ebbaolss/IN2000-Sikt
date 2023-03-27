package com.example.in2000_prosjekt.ui.data

import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.gson.*

class DataSourceAlerts (private val county: String){

    private val client = HttpClient() {
        install(ContentNegotiation) {
            gson()
        }
    }

    suspend fun authURL(URL: String) : HttpResponse {
        return client.get(URL) {
            headers {append("X-gravitee-api-key", "e4990066-1695-43a6-9ea4-85551da13834")}}
    }

    suspend fun fetchMetAlert() : Build {
        val strCounty: String = "county=$county"

        //Log.d("METALERT","METALERT URL: https://api.met.no/weatherapi/metalerts/1.1/.json?$strCounty")
        return authURL("https://gw-uio.intark.uh-it.no/in2000/weatherapi/metalerts/1.1/.json?$strCounty").body()
    }
}