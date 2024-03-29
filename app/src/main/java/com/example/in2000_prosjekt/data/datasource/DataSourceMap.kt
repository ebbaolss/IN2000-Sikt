package com.example.in2000_prosjekt.data.datasource

import com.example.in2000_prosjekt.data.map.MapBuild
import com.example.in2000_prosjekt.data.map.MapCoordinatesBuild
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.gson.*

class DataSourceMap {

    private val client = HttpClient {
        install(ContentNegotiation) {
            gson()
        }
    }
    suspend fun fetchMapSearch(path: String): MapBuild {
        return client.get(path).body()
    }
    suspend fun fetchMapCoordinates(path2: String): MapCoordinatesBuild {
        return client.get(path2).body()
    }
}