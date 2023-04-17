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

    suspend fun fetchFrostTemp(
        elements: String,
        referencetime: String,
        source: String
    ): Frost_API_Respons {

        return authURL("${basePath}sources=$source&referencetime=$referencetime&elements=$elements").body()
    }

    //DENNE MÅ SEES PÅ SAMMEN PÅ ONSDAG 12.04
    suspend fun fetchApiSvarkoordinater(lat: String, long: String): Frost_API_Respons_for_koordinater {

        val increasePolygon: Boolean = true //?? denne blir alltid true...
        val increase: Double
        // Foresporsel_om_Oke_str_polygon er True eller False nå, men kan endres til en String (som angir en gradvis økning, eller ja eller nei.
        // Det er When-setningen eller if-setningen nå som bestemmer hvor stor økningen skal være)

        if (increasePolygon) {

            increase = 0.1 // når true Så er polygonet  11.1km * 11.1km

        } else increase = 0.01// når false Så er polygonet  1.11km * 1.11km


        fun coordinatesToPolygonConverter(longitutde: Double, latitude: Double): String {

            // Eksemepl noen dypper inn galdehøpiggen= 61.3811 og 8.1845

            /*
            POLYGON((10 60,10 65, 11 65, 10 60)) // merk at det alltid er longitude som kommer først.

             */

            var long_Point1 = longitutde
            var lat_Point1 = latitude

            var long_Point2 = longitutde + increase
            var lat_Point2 = latitude

            var long_Point3 = longitutde
            var lat_Point3 = latitude + increase

            var long_Point4 = longitutde + increase
            var lat_Point4 = latitude + increase


            // Dette er en firkant: kan endres til en seks- eller åttekant
            var polygon =
                "POLYGON((${long_Point1} $lat_Point1 , $long_Point2 $lat_Point2 ,${long_Point3} $lat_Point3 , $long_Point4 $lat_Point4 ))"

            return polygon
        }

        var polygonMadeFromCoordinates =
            coordinatesToPolygonConverter(long.toDouble(), lat.toDouble())

        var urlPolygon =
            "https://frost.met.no/sources/v0.jsonld?types=SensorSystem&elements=air_temperature&geometry=${polygonMadeFromCoordinates}"

        //get call her?? (authURL.....)
        val respons2: Frost_API_Respons_for_koordinater = authURL(urlPolygon).body()
        Log.d("API call2", respons2.toString())
        return respons2

    }
}