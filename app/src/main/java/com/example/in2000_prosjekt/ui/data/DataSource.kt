package com.example.in2000_prosjekt.ui.data

import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.statement.*
import io.ktor.client.utils.EmptyContent.headers
import io.ktor.serialization.gson.*

class DataSource (private val latitude: String,
                  private val longtitude: String,
                  private val altitude: String?) {

    private val client = HttpClient() {
        install(ContentNegotiation) {
            gson()
        }
    }

    suspend fun fetchLocationForecast(): Model {

        var coordinates: String = "lat=$latitude&lon=$longtitude"
        if (altitude != null) {
            coordinates += "&altitude=$altitude"
        }
        return authURL("https://gw-uio.intark.uh-it.no/in2000/weatherapi/locationforecast/2.0/complete?$coordinates").body()
    }

    suspend fun authURL(URL: String) : HttpResponse {
        return client.get(URL) {
            headers {append("X-gravitee-api-key", "e4990066-1695-43a6-9ea4-85551da13834")}}
    }

    suspend fun fetchNowCast(): Model{

        //PLACEHOLDER NOT CORRECT URL
        var coordinates: String = "lat=$latitude&lon=$longtitude"
        if (altitude != null) {
            coordinates += "&altitude=$altitude"
        }
        return authURL("https://gw-uio.intark.uh-it.no/in2000/weatherapi/nowcast/2.0/complete?$coordinates").body()
    }


}