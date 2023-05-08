package com.example.in2000_prosjekt.ui.data

import android.util.Log
import androidx.compose.runtime.remember
import androidx.lifecycle.MutableLiveData
import io.github.boguszpawlowski.composecalendar.header.MonthState
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DataSourceFrost (val basePath: String, /* var referencetime: String = "2021-05-6" */) {
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

    suspend fun fetchFrost(source: String, referencetime : MonthState): FrostResponsBuild {

        val year =  referencetime.currentMonth.year.toString() // 2023
        val year21 =  referencetime.currentMonth.year.minus(2).toString() // 2021
        val year2021 =  referencetime.currentMonth.minusYears(2).toString() //2021-05
        val monthweneedresultsfrom =  referencetime.currentMonth.monthValue.toString() // 05 BEginning period we want api call resutlts from, value dynamically ajusts to current month being shown on the calender
        val nextmonthweneedresultsfrom =  referencetime.currentMonth.monthValue.plus(1).toString()// 06 Ending period we want api call resutlts from, the value dynamically ajusts to next month being shown on the calender

        //2021-05%2F2021-06
        val datesforfrostsightconditions = year21+"-"+monthweneedresultsfrom+"%2F"+year21+"-"+nextmonthweneedresultsfrom


        //var url : String = "${basePath}sources=$source&referencetime=$referencetime&elements=mean(cloud_area_fraction%20P1D)"
        var url : String = "${basePath}sources=SN18700&referencetime=${datesforfrostsightconditions}&elements=mean(cloud_area_fraction%20P1D)"
        var frostsightconditons : FrostResponsBuild =  client.get(url).body()

        Log.d("datesforfrostsightconditions()",datesforfrostsightconditions!! ) //  PostValue: Funka ikke

        Log.d("ReferencetimeUrl",url )
        Log.d("ReferencetimeObject",frostsightconditons.toString() )

        return frostsightconditons
    }
}
