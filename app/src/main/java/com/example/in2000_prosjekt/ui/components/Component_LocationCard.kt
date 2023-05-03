package com.example.in2000_prosjekt.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.in2000_prosjekt.R
import com.example.in2000_prosjekt.ui.AlertInfo
import com.example.in2000_prosjekt.ui.LocationInfo
import com.example.in2000_prosjekt.ui.NowCastInfo
import com.example.in2000_prosjekt.ui.data.Next12
import com.example.in2000_prosjekt.ui.theme.*
import com.example.in2000_prosjekt.ui.uistate.MapUiState


@OptIn(ExperimentalMaterial3Api::class)
fun LazyListScope.Sikt_LocationCard(mountain: MapUiState.Mountain, locationInfo: LocationInfo, nowCastInfo: NowCastInfo, alertInfoList: MutableList<AlertInfo>){

    items(1) {
        // endre til lazylist:

        val name = mountain.name
        val elevation = mountain.elevation

        val latitude = mountain.point?.latitude()
        val longitude = mountain.point?.longitude()

        // Gir point koordinatene i lat, så long?
        println("latitude: " + latitude.toString()) // glittertind: latitude: 8.557780981063843
        println("longitude: " + longitude.toString()) // glittertind: longitude: 61.65138739947395

        val weather_high = locationInfo.cloud_area_fraction_high
        val weather_mid = locationInfo.cloud_area_fraction_medium
        val weather_low = locationInfo.cloud_area_fraction_low

        val temp = nowCastInfo.temperatureNow
        val wind = nowCastInfo.windN

        println("weather_high = $weather_high")
        println("weather_mid = $weather_mid")
        println("weather_low = $weather_low")

        // SKAL SLETTES NÅR VI FÅR LISTE OVER FJELL I NÆRHETEN:
            val testliste = mutableListOf<MapUiState.Mountain>()
            testliste.add(mountain)
            testliste.add(mountain)
            testliste.add(mountain)
            testliste.add(mountain)

        // TEST FOR NESTE 12 TIMER
            val tidliste = mutableListOf<Int>()
            tidliste.add(12)
            tidliste.add(13)
            tidliste.add(14)
            tidliste.add(15)

        Card(
            colors = CardDefaults.cardColors(Sikt_lyseblå),
            modifier = Modifier.padding(20.dp),
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
            ) {
                Sikt_Header(location = "$name", alertinfo = alertInfoList)
                Sikt_MountainHight(mountainheight = "$elevation")
                Spacer(modifier = Modifier.size(20.dp))
                illustrasjon(elevation, temp, wind, weather_high, weather_mid, weather_low)
                Spacer(modifier = Modifier.size(20.dp))
                Text(text = "Dagens siktvarsel: ", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.size(10.dp))
                LazyRow() {Sikt_LocationCard_Hour(tidliste, locationInfo, nowCastInfo) }
                Spacer(modifier = Modifier.size(20.dp))
                //Text(text = "Langtidsvarsel: ", fontWeight = FontWeight.Bold)
                //Spacer(modifier = Modifier.size(10.dp))
                //Sikt_LocationCard_NextDays()
                Spacer(modifier = Modifier.size(20.dp))
                Text(text = "Topper i nærheten: ", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.size(10.dp))
                LazyRow() { Sikt_Turer_I_Naerheten(testliste, nowCastInfo) }
                Spacer(modifier = Modifier.size(100.dp))
            }
        }
    }
}

fun LazyListScope.Sikt_LocationCard_Hour(next12hours: MutableList<Int>, locationInfo: LocationInfo, nowCastInfo: NowCastInfo) {
    //time : Int, clouds : String, temp: Int) {

    val clouds = locationInfo.cloud_area_fraction
    println("clouds: $clouds")

    fun getCloudVisuals(clouds: Float): Int {
        return if (clouds >= 75) {
            R.drawable.clouds_high_both
        } else if (clouds >= 50) {
            R.drawable.clouds_high_big
        } else if (clouds >= 25 ) {
            R.drawable.clouds_high_small
        } else {
            R.drawable.klart
        }
    }

    fun getRightWeather(weather: Float): String {
        return if (weather >= 75) {
            "Meget dårlig sikt"
        } else if (weather >= 50) {
            "Dårlig sikt"
        } else if (weather >= 25) {
            "Lettskyet"
        } else {
            "Klart vær"
        }
    }

    fun getRightKm(km: Float): String {
        return if (km >= 75) {
            "> 1 km sikt"
        } else if (km >= 50) {
            "1-4 km sikt"
        } else if (km >= 25) {
            "4-10 km sikt"
        } else {
            "< 10 km sikt"
        }
    }

    items(1) {
        next12hours.forEach { tid ->

            val temp = nowCastInfo.temperatureNow

            Card(
                colors = CardDefaults.cardColors(Sikt_bakgrunnblå),
                modifier = Modifier.padding(end = 10.dp)
            ) {
                Column(
                    modifier = Modifier.padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(
                        text = "$tid:00",
                        color = Sikt_sort,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Box(
                        modifier = Modifier.size(80.dp)
                    ) {
                        Image(
                            painter = painterResource(id = getCloudVisuals(clouds)),
                            contentDescription = "",
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    Text(
                        text = getRightKm(clouds),
                        fontSize = 12.sp,
                        color = Sikt_sort,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = getRightWeather(clouds), fontSize = 12.sp, color = Sikt_sort)
                    Text(
                        text = "$temp°",
                        color = Sikt_sort,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun Sikt_LocationCard_NextDays() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(Sikt_hvit)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = "Idag", color = Sikt_sort, fontSize = 12.sp)
                Spacer(modifier = Modifier.height(30.dp))
                Text(text = "Lettskyet", color = Sikt_sort, fontSize = 12.sp)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = "Fredag", color = Sikt_sort, fontSize = 12.sp)
                Spacer(modifier = Modifier.height(30.dp))
                Text(text = "Lettskyet", color = Sikt_sort, fontSize = 12.sp)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = "Lørdag", color = Sikt_sort, fontSize = 12.sp)
                Spacer(modifier = Modifier.height(30.dp))
                Text(text = "Lettskyet", color = Sikt_sort, fontSize = 12.sp)
            }
        }
    }
}
