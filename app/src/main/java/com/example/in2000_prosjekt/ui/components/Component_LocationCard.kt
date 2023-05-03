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
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    item { Sikt_LocationCard_Hour(12, "skyet", 7) }
                    item { Sikt_LocationCard_Hour(13, "delvisskyet", 8) }
                    item { Sikt_LocationCard_Hour(14, "lettskyet", 8) }
                    item { Sikt_LocationCard_Hour(15, "klart", 7) }
                    item { Sikt_LocationCard_Hour(16, "skyet", 6) }
                    item { Sikt_LocationCard_Hour(17, "skyet", 4) }
                }
                Spacer(modifier = Modifier.size(20.dp))
                Text(text = "Langtidsvarsel: ", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.size(10.dp))
                Sikt_LocationCard_NextDays()
                Spacer(modifier = Modifier.size(20.dp))
                Text(text = "Topper i nærheten: ", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.size(10.dp))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    val testliste = mutableListOf<MapUiState.Mountain>()
                    testliste.add(mountain)
                    testliste.add(mountain)
                    Sikt_Turer_I_Naerheten(testliste, locationInfo, nowCastInfo)
                }
            }
        }
    }
}

@Composable
fun Sikt_LocationCard_Hour(time : Int, clouds : String, temp: Int) {

    fun getCloudVisuals(clouds: String): Int {
        return if (clouds == "skyet") {
            R.drawable.small_clouds_both
        } else if (clouds == "delvisskyet") {
            R.drawable.small_clouds_big
        } else if (clouds == "lettskyet") {
            R.drawable.small_clouds_small
        } else {
            R.drawable.small_clouds_clear
        }
    }

    fun getRightWeather(weather: String): String {
        return if (weather == "skyet") {
            "Meget dårlig sikt"
        } else if (weather == "delvisskyet") {
            "Dårlig sikt"
        } else if (weather == "lettskyet") {
            "Lettskyet"
        } else {
            "Klart vær"
        }
    }

    fun getRightKm(km: String): String {
        return if (km == "skyet") {
            "> 1 km sikt"
        } else if (km == "delvisskyet") {
            "1-4 km sikt"
        } else if (km == "lettskyet") {
            "4-10 km sikt"
        } else {
            "< 10 km sikt"
        }
    }

    Card(
        colors = CardDefaults.cardColors(Sikt_bakgrunnblå),
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(text = "$time:00", color = Sikt_sort, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            Box(
                modifier = Modifier.size(80.dp)
            ) {
                Image(
                    painter = painterResource(id = getCloudVisuals(clouds)),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize()
                )
            }
            Text(text = getRightKm(clouds), fontSize = 12.sp, color = Sikt_sort, fontWeight = FontWeight.Bold)
            Text(text = getRightWeather(clouds), fontSize = 12.sp, color = Sikt_sort)
            Text(text = "$temp°", color = Sikt_sort, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun Ebba_Hour(time : Int, clouds : String, temp: Int) {

    fun getCloudVisuals(clouds: String): Int {
        return if (clouds == "skyet") {
            R.drawable.small_clouds_both
        } else if (clouds == "delvisskyet") {
            R.drawable.small_clouds_big
        } else if (clouds == "lettskyet") {
            R.drawable.small_clouds_small
        } else {
            R.drawable.small_clouds_clear
        }
    }

    fun getRightWeather(weather: String): String {
        return if (weather == "skyet") {
            "Meget dårlig sikt"
        } else if (weather == "delvisskyet") {
            "Dårlig sikt"
        } else if (weather == "lettskyet") {
            "Lettskyet"
        } else {
            "Klart vær"
        }
    }

    fun getRightKm(km: String): String {
        return if (km == "skyet") {
            "> 1 km sikt"
        } else if (km == "delvisskyet") {
            "1-4 km sikt"
        } else if (km == "lettskyet") {
            "4-10 km sikt"
        } else {
            "< 10 km sikt"
        }
    }

    Card(
        colors = CardDefaults.cardColors(Sikt_bakgrunnblå),
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(text = "$time:00", color = Sikt_sort, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            Box(
                modifier = Modifier.size(80.dp)
            ) {
                Image(
                    painter = painterResource(id = getCloudVisuals(clouds)),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize()
                )
            }
            Text(text = getRightKm(clouds), fontSize = 12.sp, color = Sikt_sort, fontWeight = FontWeight.Bold)
            Text(text = getRightWeather(clouds), fontSize = 12.sp, color = Sikt_sort)
            Text(text = "$temp°", color = Sikt_sort, fontSize = 20.sp, fontWeight = FontWeight.Bold)
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
