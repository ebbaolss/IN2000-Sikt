package com.example.in2000_prosjekt.ui.data

import android.util.Log
import androidx.compose.runtime.remember
import androidx.lifecycle.MutableLiveData
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
    //var referencetimetest_ = MutableLiveData<String>()

    //Log.d("Inni datasourcefrost", referencetimetest_ )

    /* FEIL 1: Kanke bruke getters og setteers: refencetime blir aldri oppdatert, forblir default value, og gunker ike uten
   / vi prøver å lage getters and setters, kl. 2140 06.05
    get() = field

    set (value) {
        field=value
    }
    */


    //FEIL 2: init blokka kanke brukes på suspend funksjonen fetchFrost

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    init { // brukes ikke til noe enda, per 07.05. kl 1437, bruk denne etter: ALLe mutableStateFlow, mutableLiveData etc. ,
        coroutineScope.launch(Dispatchers.IO) {
            var referencetime: String = "2021-05-12"
           // fetchFrost("SN18700", referencetime)
        }
    }



//var referencetimetest_ by remember {mutableStateOf("") } FEIL 3:  mutableStateof MÅ brukes i Compsable funksjoner
// val referencetimetest_.value =
    var referencetimeMutableLiveData =  MutableLiveData<String>("2021-05-12") // FEIL 4: MutableLiveData Funka ikke: Defaultveriden endrer seg aldri ga: NO transformation error

    //var  referencetime  : String = "2021-05-12"










    var referenceDatoer= MutableStateFlow<String>("")


    //Log.d("referencetime", referencetime.toString() )

    var _referencetimeMutableStateFlow  = MutableStateFlow<String>("") // Error 5: MutableStateFlow Funka heeller ikke: Defaultveriden endrer seg aldri

    suspend fun fetchFrost(source: String, referencetime : String  ): FrostResponsBuild {

        Log.d("logg refrencetime inni fetchfrost",referencetime ) //  PostValue: Funka ikke

        //var url : String = "${basePath}sources=$source&referencetime=$referencetime&elements=mean(cloud_area_fraction%20P1D)"
        var url : String = "${basePath}sources=SN18700&referencetime=${referencetime}&elements=mean(cloud_area_fraction%20P1D)"
        var frostsightconditons : FrostResponsBuild =  client.get(url).body()

        Log.d("ReferencetimeMutableLivePostValue()",referencetimeMutableLiveData.value!! ) //  PostValue: Funka ikke

        Log.d("ReferencetimeUrl",url )
        Log.d("ReferencetimeObject",frostsightconditons.toString() )

        return frostsightconditons

        // Dette gjøres på tordsag kl.14.25 for å se om jeg faktisk sender variablen referencetime gjennom View-ViewModel-Model riktig:
        //Vi hardkoder source til SN18700-Blindern: som alltid har resutlter for mean(cloud_area_fraction%20P1D)"

        // kommenteres ut for å logge urlen og reference time som genereres fra kalenderstate
        //return authURL("${basePath}sources=$source&referencetime=$referencetime&elements=mean(cloud_area_fraction%20P1D)").body()
    }
}
