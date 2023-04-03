package com.example.in2000_prosjekt.ui.data

import android.net.http.HttpResponseCache
import android.util.Log

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.gson.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import okhttp3.Credentials.basic

import android.net.http.HttpResponseCache.install
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.gson.*
import io.ktor.client.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.statement.*
import io.ktor.http.*


class DataSourceFrost ( ) {


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


    suspend fun fetchApiSvar(
        elements: String,
        referencetime: String,
        source: String
    ): Frost_API_Respons {

        var baseurl = "https://frost.met.no/observations/v0.jsonld?"

        val respons: Frost_API_Respons =
            client.get("${baseurl}sources=${source}&referencetime=${referencetime}&elements=${elements}")
                .body()

        Log.d("API call", respons.toString())
        return respons


    }


    suspend fun fetchApiSvar_Ref_dokumentasjon_Path_Segments(
        elements: String,
        referencetime: String,
        source: String
    ): Frost_API_Respons {


        var baseurl = "https://frost.met.no/observations/v0.jsonld?"
        val respons: HttpResponse =
            client.get("${baseurl}sources=${source}&referencetime=${referencetime}&elements=${elements}") {
                url {
                    appendPathSegments("")
                }
            }


        var responded: Frost_API_Respons = respons.body()


        Log.d("API call1", respons.toString())
        return responded


    }


    suspend fun fetchApiSvarkoordinater(
        koord: String = "60,10",
        Foresporsel_om_Oke_str_polygon: Boolean = true
    ): Frost_API_Respons_for_koordinater {


        var okning: Double
        // Foresporsel_om_Oke_str_polygon er True eller False nå, men kan endres til en String (som angir en gradvis økning, eller ja eller nei.
        // Det er When-setningen eller if-setningen nå som bestemmer hvor stor økningen skal være)

        if (Foresporsel_om_Oke_str_polygon) {

            okning = 0.1 // når true Så er polygonet  11.1km * 11.1km

        } else okning = 0.01// når false Så er polygonet  1.11km * 1.11km


        var baseurl = "https://frost.met.no/observations/v0.jsonld?"

        var kordinater = koord.split(",")

        fun koordinat_Til_Polygon_konverter(longitutde: Double, latitude: Double): String {


            // Eksemepl noen dypper inn galdehøpiggen= 61.3811 og 8.1845

            /*
            POLYGON((10 60,10 65, 11 65, 10 60)) // merk at det alltid er longitude som kommer først.

             */

            var long_Point1 = longitutde
            var lat_Point1 = latitude


            var long_Point2 = longitutde + okning
            var lat_Point2 = latitude

            var long_Point3 = longitutde
            var lat_Point3 = latitude + okning

            var long_Point4 = longitutde + okning
            var lat_Point4 = latitude + okning


            // Dette er en firkant: kan endres til en seks- eller åttekant
            var polygon =
                "POLYGON((${long_Point1} ${lat_Point1} , ${long_Point2} ${lat_Point2} ,${long_Point3} ${lat_Point3} , ${long_Point4} ${lat_Point4} ))"



            return polygon

        }


        var polygon_generert_Fra_koordinater =
            koordinat_Til_Polygon_konverter(kordinater[1].toDouble(), kordinater[0].toDouble())


        var url_med_Polygon =
            "https://frost.met.no/sources/v0.jsonld?types=SensorSystem&elements=air_temperature&geometry=${polygon_generert_Fra_koordinater}"

        val respons2: Frost_API_Respons_for_koordinater = client.get(url_med_Polygon).body()
        Log.d("API call2", respons2.toString())
        return respons2

    }
}