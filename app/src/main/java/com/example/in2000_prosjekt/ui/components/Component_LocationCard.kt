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
import java.text.SimpleDateFormat
import java.util.Date;


@OptIn(ExperimentalMaterial3Api::class)
fun LazyListScope.Sikt_LocationCard(mountain: MapUiState.Mountain, locationInfo: LocationInfo, nowCastInfo: NowCastInfo, alertInfoList: MutableList<AlertInfo>){

    items(1) {

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

        Card(
            colors = CardDefaults.cardColors(Sikt_lyseblå),
            modifier = Modifier.padding(20.dp),
        ){
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
                Sikt_LocationCard_Hour(locationInfo) }
                Spacer(modifier = Modifier.size(20.dp))
                Text(text = "Langtidsvarsel: ", fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 20.dp))
                Spacer(modifier = Modifier.size(10.dp))
                Sikt_LocationCard_NextDays()
                Spacer(modifier = Modifier.size(20.dp))
                Text(text = "Topper i nærheten: ", fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 20.dp))
                Spacer(modifier = Modifier.size(10.dp))
                LazyRow(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) { Sikt_Turer_I_Naerheten(testliste, nowCastInfo) }
                Spacer(modifier = Modifier.size(100.dp))
            }
        }
    }

@Composable
fun Sikt_LocationCard_Hour(locationInfo: LocationInfo) {

    val tempNext1h = locationInfo.tempNext1L
    val tempNext6h = locationInfo.tempNext6L
    val cloudsNext1h = locationInfo.cloudinessNext1L
    val cloudsNext6h = locationInfo.cloudinessNext6L

    val currentTimeMillis = System.currentTimeMillis()
    val date = Date(currentTimeMillis)
    val dateFormat = SimpleDateFormat("HH")
    val tidspunkt = dateFormat.format(date)
    //val tid = "$tidspunkt:00"

    fun add1hour(tidspunkt: String): String {
        println("Tidspunkt: $tidspunkt")
        val nestetime = tidspunkt.toInt()
        val nesteH = nestetime + 1
        return nesteH.toString()
    }

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item { Sikt_LocationCard_Hour_Card("Sikt nå:", tempNext1h, cloudsNext1h) }
        val pluss1 = add1hour(tidspunkt)
        item { Sikt_LocationCard_Hour_Card("$pluss1:00", tempNext1h, cloudsNext1h) }
        val pluss2 = add1hour(pluss1)
        item { Sikt_LocationCard_Hour_Card("$pluss2:00", tempNext6h, cloudsNext6h) }
        val pluss3 = add1hour(pluss2)
        item { Sikt_LocationCard_Hour_Card("$pluss3:00", tempNext6h, cloudsNext6h) }
        val pluss4 = add1hour(pluss3)
        item { Sikt_LocationCard_Hour_Card("$pluss4:00", tempNext6h, cloudsNext6h) }
        val pluss5 = add1hour(pluss4)
        item { Sikt_LocationCard_Hour_Card("$pluss5:00", tempNext6h, cloudsNext6h) }
    }
}

@Composable
fun Sikt_LocationCard_Hour_Card(tid : String, temp : Float, cloudiness : String) {

    fun getCloudVisuals(clouds: String): Int {
        val type = clouds.split("_")
        val typeClouds = type[0]
        return if (typeClouds == "cloudy") {
            R.drawable.small_clouds_both
        } else if (typeClouds == "partlycloudy") {
            R.drawable.small_clouds_big
        } else if (typeClouds == "fair") {
            R.drawable.small_clouds_small
        } else {
            R.drawable.small_clouds_clear
        }
    }

    fun getRightWeather(weather: String): String {
        val type = weather.split("_")
        val typeWeather = type[0]
        return if (typeWeather == "cloudy") {
            "Meget dårlig sikt"
        } else if (typeWeather == "partlycloudy") {
            "Dårlig sikt"
        } else if (typeWeather == "fair") {
            "Lettskyet"
        } else {
            "Klart vær"
        }
    }

    fun getRightKm(km: String): String {
        val type = km.split("_")
        val typeKm = type[0]
        return if (typeKm == "cloudy") {
            "> 1 km sikt"
        } else if (typeKm == "partlycloudy") {
            "1-4 km sikt"
        } else if (typeKm == "fair") {
            "4-10 km sikt"
        } else {
            "< 10 km sikt"
        }
    }

    Card(
        colors = CardDefaults.cardColors(Sikt_bakgrunnblå),
        modifier = Modifier.padding(end = 10.dp),
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = tid,
                color = Sikt_sort,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            Box(
                modifier = Modifier.size(80.dp)
            ) {
                Image(
                    painter = painterResource(id = getCloudVisuals(cloudiness)),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize()
                )
            }
            Text(
                text = getRightKm(cloudiness),
                fontSize = 12.sp,
                color = Sikt_sort,
                fontWeight = FontWeight.Bold
            )
            Text(text = getRightWeather(cloudiness), fontSize = 12.sp, color = Sikt_sort)
            Text(
                text = "$temp°",
                color = Sikt_sort,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
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



