package com.example.in2000_prosjekt.ui.data

import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.gson.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.statement.*

class DataSourceFrost (val basePath: String) {

    private val client = HttpClient() {
        install(Auth) {
            basic {
                credentials {
                    BasicAuthCredentials(
                        username = "1cf3b8eb-0fbd-46c9-803d-32206f191ccf",
                        password = ""
                    )
                }
            }
        }
        install(ContentNegotiation) {
            gson()
        }
    }

    suspend fun authURL(URL: String) : HttpResponse {
        return client.get(URL) {
            headers {append("X-gravitee-api-key", "e4990066-1695-43a6-9ea4-85551da13834")}}
    }

    //Dette er en funksjon som genererer et polygon rundt koordinatet gitt fra kartet
    fun coordinatesToPolygonConverter(latitude: Double, longitude: Double): String { // 60,10
        val increase = 0.1 // tilsvarer en radius på rundt 11km, hvilket forholdsmessig gir en turgåer en anvendelig/formålstjenelig ide/info om siktforhold i nærheten av seg
        var longpointincreased = longitude + increase
        var latpointincreased = latitude + increase


        var polygon ="POLYGON((${longitude} $latitude , $longitude $latpointincreased , ${longpointincreased} $latitude , $longpointincreased $latpointincreased ))"

        return polygon
    }

    suspend fun fetchFrostWeatherStation(longitude: Double, latitude: Double): FrostCoordinatesBuild {
        var polygon= coordinatesToPolygonConverter (longitude,latitude) // generer deg et polygon fra et koordinat

        return  authURL("https://frost.met.no/sources/v0.jsonld?types=SensorSystem&geometry=${polygon}").body()
    }

    suspend fun fetchFrost(  source: String, referencetime: String,): FrostResponsBuild {
        return authURL("${basePath}sources=$source&referencetime=$referencetime&elements=mean(cloud_area_fraction%20P1D)").body()
    }



}