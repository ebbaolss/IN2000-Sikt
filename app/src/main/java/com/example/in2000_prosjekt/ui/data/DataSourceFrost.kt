package com.example.in2000_prosjekt.ui.data

import android.net.http.HttpResponseCache
import android.util.Log

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.gson.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import okhttp3.Credentials.basic


class DataSourceFrost (val path: String ) {


    private val client = HttpClient () {

        HttpResponseCache.install(Auth) {
            basic {
                credentials {
                    BasicAuthCredentials(
                        username = "1cf3b8eb-0fbd-46c9-803d-32206f191ccf",
                        password = ""
                    )
                }
            }
        }


        HttpResponseCache.install(ContentNegotiation) {
            gson()

        }
    }


    suspend fun fetchApiSvar() : Frost_API_Respons {

        val respons : Frost_API_Respons = client.get(path).body()
        Log.d("API call", respons.toString())
        return respons
    }


}