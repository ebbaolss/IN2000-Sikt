package com.example.in2000_prosjekt.ui.data

import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.gson.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.statement.*
import io.ktor.http.*

class DataSourceFrost (val basePath: String) {

    private val client = HttpClient(CIO) {

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
        val increase = 1 //Den 02.05, kl.19.55 Settesdenne variablen var 0.1, som tilsvarer et polygon med 11,1 km radiuus// tilsvarer en radius på rundt 11km, hvilket forholdsmessig gir en turgåer en anvendelig/formålstjenelig ide/info om siktforhold i nærheten av seg
        var longpointincreased = longitude + increase
        var latpointincreased = latitude + increase


        var polygon ="POLYGON(($longitude%20$latitude%2C$longitude%20$latpointincreased%2C$longpointincreased%20$latitude%2C$longpointincreased%20$latpointincreased))"// // nettet sier at spaces kan foråsake 505 "HTTP version not supported" errors som jeg får stadig, selv om det har funka før, og lenken er gyldig

        return polygon
    }

    suspend fun fetchFrostWeatherStation( latitude: Double, longitude: Double): FrostCoordinatesBuild {


        var polygon= coordinatesToPolygonConverter (latitude,  longitude) // generer deg et polygon fra et koordinat


        return  authURL("https://frost.met.no/sources/v0.jsonld?types=SensorSystem&geometry=${polygon}").body() //trenger ikke authURL(URL: String) i Frost sitt api, proxy servern brukes bare på Met vær apier (NowCast, LocationForecast, MetAlert)
    }

    suspend fun fetchFrost(  source: String, referencetime: String,): FrostResponsBuild {
        return authURL("${basePath}sources=$source&referencetime=$referencetime&elements=mean(cloud_area_fraction%20P1D)").body()
    }




}