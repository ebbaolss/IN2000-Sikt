package com.example.in2000_prosjekt.data

import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.gson.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.statement.*

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

    private suspend fun authURL(URL: String) : HttpResponse {

        return client.get(URL) {
            headers {append("X-gravitee-api-key", "e4990066-1695-43a6-9ea4-85551da13834")}}
    }

    suspend fun fetchFrostTemp(
        elements: String,
        referencetime: String,
        source: String
    ): Frost_API_Respons {

        return authURL("${basePath}sources=$source&referencetime=$referencetime&elements=$elements").body() // hvorfor har vi client i hver datasource når vi bruker .body påauth

        /* fra tirs 18.04
        try{

            return authURL("${basePath}sources=$source&referencetime=$referencetime&elements=$elements").body() // hvorfor har vi client i hver datasource når vi bruker .body påauth
        } catch (cause: ResponseException) {
            println(cause)
            cause.response


        }
        return Frost_API_Respons( "Error har skjedd","","","","",3?,0.5,3.4)

         */


    }

    //DENNE MÅ SEES PÅ SAMMEN PÅ ONSDAG 12.04
    suspend fun fetchApiSvarkoordinater(lat: String, long: String): Frost_API_Respons_for_koordinater {

        val increasePolygon = true //?? denne blir alltid true...
        // Foresporsel_om_Oke_str_polygon er True eller False nå, men kan endres til en String (som angir en gradvis økning, eller ja eller nei.
        // Det er When-setningen eller if-setningen nå som bestemmer hvor stor økningen skal være)

        val increase: Double = if (increasePolygon) {

            0.1 // når true Så er polygonet  11.1km * 11.1km

        } else 0.01// når false Så er polygonet  1.11km * 1.11km


        fun coordinatesToPolygonConverter(longitutde: Double, latitude: Double): String {

            // Eksemepl noen dypper inn galdehøpiggen= 61.3811 og 8.1845

            /*
            POLYGON((10 60,10 65, 11 65, 10 60)) // merk at det alltid er longitude som kommer først.

             */

            val longPoint1 = longitutde
            val latPoint1 = latitude

            val longPoint2 = longitutde + increase
            val latPoint2 = latitude

            val longPoint3 = longitutde
            val latPoint3 = latitude + increase

            val longPoint4 = longitutde + increase
            val latPoint4 = latitude + increase


            // Dette er en firkant: kan endres til en seks- eller åttekant
            val polygon =
                "POLYGON((${longPoint1} $latPoint1 , $longPoint2 $latPoint2 ,${longPoint3} $latPoint3 , $longPoint4 $latPoint4 ))"

            return polygon
        }

        val polygonMadeFromCoordinates =
            coordinatesToPolygonConverter(long.toDouble(), lat.toDouble())

        val urlPolygon =
            "https://frost.met.no/sources/v0.jsonld?types=SensorSystem&elements=air_temperature&geometry=${polygonMadeFromCoordinates}"

        //get call her?? (authURL.....)
        val respons2: Frost_API_Respons_for_koordinater = authURL(urlPolygon).body()
        Log.d("API call2", respons2.toString())
        return respons2

    }
}