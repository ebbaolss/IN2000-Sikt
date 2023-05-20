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
import com.example.in2000_prosjekt.ui.AlertInfo
import com.example.in2000_prosjekt.ui.LocationInfo
import com.example.in2000_prosjekt.ui.NowCastInfo
import com.example.in2000_prosjekt.database.FavoriteViewModel
import com.example.in2000_prosjekt.ui.theme.*
import com.example.in2000_prosjekt.ui.uistate.MapUiState
import java.text.SimpleDateFormat
import java.util.Date


fun LazyListScope.siktLocationcard(mountain: MapUiState.Mountain, locationInfo: LocationInfo, nowCastInfo: NowCastInfo, alertInfoList: MutableList<AlertInfo>, favoriteViewModel: FavoriteViewModel){

    items(1) {

        val name = mountain.name
        val elevation = mountain.elevation

        val latitude = mountain.latitude
        val longitude = mountain.longitude

        val weatherHigh = locationInfo.cloud_area_fraction_high
        val weatherMid = locationInfo.cloud_area_fraction_medium
        val weatherLow = locationInfo.cloud_area_fraction_low

        val temp = nowCastInfo.temperatureNow
        val wind = nowCastInfo.windN

        Card(
            colors = CardDefaults.cardColors(Sikt_lightblue),
            modifier = Modifier.padding(top = 20.dp, bottom = 20.dp),
        ){
            Column(
                modifier = Modifier.padding(20.dp),
            ) {
                Sikt_Header(location = "$name", elevation!!, latitude!!.toDouble(), longitude!!.toDouble(),alertInfoList, favoriteViewModel) // Husk å endre alertinfo
                Sikt_MountainHight(mountainheight = "$elevation")
                Spacer(modifier = Modifier.size(20.dp))
                Illustrasjon(elevation, temp, wind, weatherHigh, weatherMid, weatherLow)
                Spacer(modifier = Modifier.size(20.dp))
                Text(text = "Dagens siktvarsel: ", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.size(10.dp))
                Sikt_LocationCard_Hour(locationInfo) }
            Spacer(modifier = Modifier.size(10.dp))
            Text(text = "Langtidsvarsel: ", fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 20.dp))
            Spacer(modifier = Modifier.size(10.dp))
            Sikt_LocationCard_NextDays(locationInfo, nowCastInfo)
            Spacer(modifier = Modifier.size(20.dp))
        }
    }
}


@Composable
fun Sikt_LocationCard_Hour(locationInfo: LocationInfo) {

    val currentTimeMillis = System.currentTimeMillis()
    val date = Date(currentTimeMillis)
    val dateFormat = SimpleDateFormat("HH")
    val tidspunkt = dateFormat.format(date)

    fun getMelding(index : Int) : String {
        val hour = (tidspunkt.toInt() + index) % 24
        return when {
            index == 0 -> "Sikt nå:"
            hour in 0..9 -> "0$hour:00"
            else -> "$hour:00"
        }
    }

    LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        item { Sikt_LocationCard_Hour_Card(getMelding(0), locationInfo.tempNext1, locationInfo.cloudinessNext1) }
        item { Sikt_LocationCard_Hour_Card(getMelding(1), locationInfo.tempNext2, locationInfo.cloudinessNext2) }
        item { Sikt_LocationCard_Hour_Card(getMelding(2), locationInfo.tempNext3, locationInfo.cloudinessNext3) }
        item { Sikt_LocationCard_Hour_Card(getMelding(3), locationInfo.tempNext4, locationInfo.cloudinessNext4) }
        item { Sikt_LocationCard_Hour_Card(getMelding(4), locationInfo.tempNext5, locationInfo.cloudinessNext5) }
        item { Sikt_LocationCard_Hour_Card(getMelding(5), locationInfo.tempNext6, locationInfo.cloudinessNext6) }
        item { Sikt_LocationCard_Hour_Card(getMelding(6), locationInfo.tempNext7, locationInfo.cloudinessNext7) }
        item { Sikt_LocationCard_Hour_Card(getMelding(7), locationInfo.tempNext8, locationInfo.cloudinessNext8) }
        item { Sikt_LocationCard_Hour_Card(getMelding(8), locationInfo.tempNext9, locationInfo.cloudinessNext9) }
        item { Sikt_LocationCard_Hour_Card(getMelding(9), locationInfo.tempNext10, locationInfo.cloudinessNext10) }
        item { Sikt_LocationCard_Hour_Card(getMelding(10), locationInfo.tempNext11, locationInfo.cloudinessNext11) }
        item { Sikt_LocationCard_Hour_Card(getMelding(11), locationInfo.tempNext12, locationInfo.cloudinessNext12) }
    }
}

@Composable
fun Sikt_LocationCard_Hour_Card(tid: String, temp: Int, cloudiness: Float) {

    Card(
        colors = CardDefaults.cardColors(Sikt_backroundBlue),
        modifier = Modifier.padding(end = 5.dp),
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
                    painter = painterResource(id = getRightCloudiness(cloudiness)),
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
            Text(text = getRightSikt(cloudiness), fontSize = 12.sp, color = Sikt_sort)
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
fun Sikt_LocationCard_NextDays(locationInfo: LocationInfo, nowCastInfo: NowCastInfo) {

    val tempToday = nowCastInfo.temperatureNow
    val tempDay1 = locationInfo.temp_day1
    val tempDay2 = locationInfo.temp_day2
    val tempDay3 = locationInfo.temp_day3
    val tempDay4 = locationInfo.temp_day4

    val cloudsToday = locationInfo.cloud_area_fraction
    val cloudDay1 = locationInfo.cloud_day1
    val cloudDay2 = locationInfo.cloud_day2
    val cloudDay3 = locationInfo.cloud_day3
    val cloudDay4 = locationInfo.cloud_day4

    Card(
        modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(Sikt_backroundBlue)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            Sikt_LocationCard_NextDaysContent("I dag", tempToday, cloudsToday)
            Divider(thickness = 1.dp, color = Sikt_sort, modifier = Modifier.fillMaxWidth().padding(10.dp))
            Sikt_LocationCard_NextDaysContent("I morgen", tempDay1, cloudDay1)
            Divider(thickness = 1.dp, color = Sikt_sort, modifier = Modifier.fillMaxWidth().padding(10.dp))
            Sikt_LocationCard_NextDaysContent("Om 2 dager", tempDay2, cloudDay2)

            Divider(thickness = 1.dp, color = Sikt_sort, modifier = Modifier.fillMaxWidth().padding(10.dp))
            Sikt_LocationCard_NextDaysContent("Om 3 dager", tempDay3, cloudDay3)
            Divider(thickness = 1.dp, color = Sikt_sort, modifier = Modifier.fillMaxWidth().padding(10.dp))
            Sikt_LocationCard_NextDaysContent("Om 4 dager", tempDay4, cloudDay4)
        }
    }
}

@Composable
fun Sikt_LocationCard_NextDaysContent(tekst: String, temp: Int, cloudiness: Float) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(top=5.dp, bottom = 5.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.width(70.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = tekst,
                color = Sikt_sort,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
            )
        }
        Box(
            modifier = Modifier.size(50.dp)
        ) {
            Image(
                painter = painterResource(id = getRightCloudiness(cloudiness)),
                contentDescription = "",
                modifier = Modifier.fillMaxSize()
            )
        }
        Text(
            text = "$temp°",
            color = Sikt_sort,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = getRightKm(cloudiness),
                color = Sikt_sort,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = getRightSikt(cloudiness), color = Sikt_sort, fontSize = 12.sp)
        }
    }
}