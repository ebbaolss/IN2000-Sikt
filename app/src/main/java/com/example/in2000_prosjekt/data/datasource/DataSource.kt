package com.example.in2000_prosjekt.data.datasource

import com.example.in2000_prosjekt.data.Model
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.statement.*
import io.ktor.serialization.gson.*

class DataSource (val basePath:String) {

    suspend fun fetchLocationForecast(latitude: String, longitude: String, altitude: String? = null): Model {
        //datasource for nowcast og locationForecast
        val client = HttpClient {
            install(ContentNegotiation) {
                gson()
            }
        }

        var coordinates = "lat=$latitude&lon=$longitude"
        if (altitude != null) {
            coordinates += "&altitude=$altitude"
        }
        val  url = "$basePath/locationforecast/2.0/complete?$coordinates"
        val body  = clientProxyServerCall(client, url)
        return body.body()
    }

    private suspend fun clientProxyServerCall(client: HttpClient, URL: String) : HttpResponse {
        return client.get(URL) {
            headers {
                append("X-gravitee-api-key", "e4990066-1695-43a6-9ea4-85551da13834")
            }
        }
    }

    suspend fun fetchNowCast(latitude: String, longitude: String, altitude: String? = null): Model {
        //datasource for nowcast og locationForecast
        val client = HttpClient {
            install(ContentNegotiation) {
                gson()
            }
        }
        var coordinates = ""
        if (altitude != null) {
            coordinates += "&altitude=$altitude"
        }
        coordinates = "lat=$latitude&lon=$longitude"

        val url = "$basePath/nowcast/2.0/complete?$coordinates"
        return clientProxyServerCall(client, url).body()
    }
}