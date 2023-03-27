package com.example.in2000_prosjekt.ui.data

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.statement.*
import io.ktor.serialization.gson.*

class DataSourceSunrise(private val latitude: String,
                        private val longtitude: String) {

    private val client = HttpClient() {
        install(ContentNegotiation) {
            gson()
        }
    }

    suspend fun authURL(URL: String) : HttpResponse {
        return client.get(URL) {
            headers {append("X-gravitee-api-key", "e4990066-1695-43a6-9ea4-85551da13834")}}
    }
    suspend fun fetchSunrise(): SunriseBuild{

        //PLACEHOLDER NOT CORRECT URL
        val coordinates: String = "lat=$latitude&lon=$longtitude"

        return authURL("https://gw-uio.intark.uh-it.no/in2000/weatherapi/sunrise/3.0/sun?$coordinates&offset=+01:00").body()
    }


}