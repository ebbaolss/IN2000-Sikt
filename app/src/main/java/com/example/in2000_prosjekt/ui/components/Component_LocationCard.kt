package com.example.in2000_prosjekt.ui.components

import android.util.Log
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.in2000_prosjekt.R
import com.example.in2000_prosjekt.ui.AlertInfo
import com.example.in2000_prosjekt.ui.LocationInfo
import com.example.in2000_prosjekt.ui.NowCastInfo
import com.example.in2000_prosjekt.ui.database.FavoriteViewModel
import com.example.in2000_prosjekt.ui.data.ImplementedWeatherRepository
import com.example.in2000_prosjekt.ui.theme.*
import com.example.in2000_prosjekt.ui.uistate.MapUiState
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
fun LazyListScope.Sikt_LocationCard(mountain: MapUiState.Mountain, locationInfo: LocationInfo, nowCastInfo: NowCastInfo, alertInfoList: MutableList<AlertInfo>,favoriteViewModel: FavoriteViewModel){

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

        // SKAL SLETTES NÅR VI FÅR LISTE OVER FJELL I NÆRHETEN:
            val testliste = mutableListOf<MapUiState.Mountain>()
        /*
            testliste.add(mountain)
            testliste.add(mountain)
            testliste.add(mountain)
            testliste.add(mountain)*/

        Card(
            colors = CardDefaults.cardColors(Sikt_lyseblå),
            modifier = Modifier.padding(top = 20.dp, bottom = 20.dp),
        ){
            Column(
                modifier = Modifier.padding(20.dp),
            ) {
                Sikt_Header(location = "$name", elevation!!, latitude!!.toDouble(), longitude!!.toDouble(),alertInfoList, favoriteViewModel) // Husk å endre alertinfo
                Sikt_MountainHight(mountainheight = "$elevation")
                Spacer(modifier = Modifier.size(20.dp))
                illustrasjon(elevation, temp, wind, weatherHigh, weatherMid, weatherLow)
                Spacer(modifier = Modifier.size(20.dp))
                Text(text = "Dagens siktvarsel: ", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.size(10.dp))
                Sikt_LocationCard_Hour(locationInfo) }
                Spacer(modifier = Modifier.size(10.dp))
                Text(text = "Langtidsvarsel: ", fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 20.dp))
                Spacer(modifier = Modifier.size(10.dp))
                Sikt_LocationCard_NextDays(locationInfo, nowCastInfo)
                Spacer(modifier = Modifier.size(20.dp))
                Text(text = "Topper i nærheten: ", fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 20.dp))
                Spacer(modifier = Modifier.size(10.dp))
                // Bytt ut testliste med fjelltopper i nærheten:
                if (testliste.size != 0) {
                    LazyRow(
                        modifier = Modifier.padding(
                            start = 20.dp,
                            end = 20.dp
                        )
                    ) { Sikt_Turer_I_Naerheten(testliste, nowCastInfo) }
                } else {
                    Text(text = "Ingen topper i nærheten...", modifier = Modifier.padding(start = 20.dp))
                }
                Spacer(modifier = Modifier.size(100.dp))
            }
        }
    }


@Composable
fun Sikt_LocationCard_Hour(locationInfo: LocationInfo) {

    val currentTimeMillis = System.currentTimeMillis()
    val date = Date(currentTimeMillis)
    val dateFormat = SimpleDateFormat("HH")
    val tidspunkt = dateFormat.format(date)

    /*
    LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        for (i in 0..11) {
            val hour = (tidspunkt.toInt() + i) % 24
            val melding = when {
                i == 0 -> "Sikt nå:"
                hour in 0..9 -> "0$hour:00"
                else -> "$hour:00"
            }
            item { Sikt_LocationCard_Hour_Card(melding, locationInfo.tempNext1, locationInfo.cloudinessNext1) }
        }
    }*/

    fun getMelding(index : Int) : String {
        val hour = (tidspunkt.toInt() + index) % 24
        val melding = when {
            index == 0 -> return "Sikt nå:"
            hour in 0..9 -> return "0$hour:00"
            else -> return "$hour:00"
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
fun Sikt_LocationCard_Hour_Card(tid : String, temp : Float, cloudiness : Float) {

    Card(
        colors = CardDefaults.cardColors(Sikt_bakgrunnblå),
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
    val temp_day1 = locationInfo.temp_day1
    val temp_day2 = locationInfo.temp_day2
    val temp_day3 = locationInfo.temp_day3
    val temp_day4 = locationInfo.temp_day4

    val cloudsToday = locationInfo.cloud_area_fraction
    val cloud_day1 = locationInfo.cloud_day1
    val cloud_day2 = locationInfo.cloud_day2
    val cloud_day3 = locationInfo.cloud_day3
    val cloud_day4 = locationInfo.cloud_day4

    Card(
        modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(Sikt_bakgrunnblå)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            Sikt_LocationCard_NextDaysContent("I dag", tempToday, cloudsToday)
            Divider(thickness = 1.dp, color = Sikt_sort, modifier = Modifier.fillMaxWidth().padding(10.dp))
            Sikt_LocationCard_NextDaysContent("I morgen", temp_day1, cloud_day1)
            Divider(thickness = 1.dp, color = Sikt_sort, modifier = Modifier.fillMaxWidth().padding(10.dp))
            Sikt_LocationCard_NextDaysContent("Om 2 dager", temp_day2, cloud_day2)

            Divider(thickness = 1.dp, color = Sikt_sort, modifier = Modifier.fillMaxWidth().padding(10.dp))
            Sikt_LocationCard_NextDaysContent("Om 3 dager", temp_day3, cloud_day3)
            Divider(thickness = 1.dp, color = Sikt_sort, modifier = Modifier.fillMaxWidth().padding(10.dp))
            Sikt_LocationCard_NextDaysContent("Om 4 dager", temp_day4, cloud_day4)
        }
    }
}

@Composable
fun Sikt_LocationCard_NextDaysContent(tekst : String, temp : Float, cloudiness: Float) {
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
