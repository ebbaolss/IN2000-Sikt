package com.example.in2000_prosjekt.ui.data

import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.statement.*
import io.ktor.serialization.gson.*

class DataSource (val basePath:String) {

    suspend fun fetchLocationForecast(latitude: String, longtitude: String, altitude: String? = null): Model {
        //datasource for nowcast og locationForecast
        val client = HttpClient() {
            install(ContentNegotiation) {
                gson()
            }
        }

        var coordinates: String = "lat=$latitude&lon=$longtitude"
        if (altitude != null) {
            coordinates += "&altitude=$altitude"
        }
        val  url = "$basePath/locationforecast/2.0/complete?$coordinates"
        Log.d("DATASOURCE: fetchlocation", url)
        val body  = clientProxyServerCall(client, url)
        Log.d("DATASOURCE: returned body", body.toString())
        return body.body()
    }

    suspend fun clientProxyServerCall(client: HttpClient,URL: String) : HttpResponse {
        return client.get(URL) {
            headers {
                append("X-gravitee-api-key", "e4990066-1695-43a6-9ea4-85551da13834")
            }
        }
    }

    suspend fun fetchNowCast(latitude: String, longtitude: String, altitude: String? = null): Model{
        //datasource for nowcast og locationForecast
        val client = HttpClient() {
            install(ContentNegotiation) {
                gson()
            }
        }
        var coordinates: String = ""
        if (altitude != null) {
            coordinates += "&altitude=$altitude"
        }
        coordinates = "lat=$latitude&lon=$longtitude"

        val url = "$basePath/nowcast/2.0/complete?$coordinates"
        Log.d("DATASOURCE: fetchNowcast", url)
        return clientProxyServerCall(client, url).body()
    }
}