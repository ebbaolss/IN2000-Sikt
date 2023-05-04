package com.example.in2000_prosjekt.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.in2000_prosjekt.ui.theme.Sikt_hvit
import com.example.in2000_prosjekt.ui.theme.Sikt_lyseblå
import com.example.in2000_prosjekt.ui.theme.Sikt_sort
import com.example.in2000_prosjekt.ui.uistate.MapUiState
import com.mapbox.geojson.Point
import com.example.in2000_prosjekt.R
import com.example.in2000_prosjekt.ui.*
import com.example.in2000_prosjekt.ui.theme.*
import kotlinx.coroutines.runBlocking


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Sikt_LocationCard(mountain: MapUiState.Mountain, locationInfo: LocationInfo, nowCastInfo: NowCastInfo, alertInfoList: MutableList<AlertInfo>, frostinfo: FrostInfo, apiViewModel: APIViewModel){

    val name = mountain.name
    val elevation = mountain.elevation


    //apiViewModel.getAll("$latitude", "$longitude", "$elevation") // dette var gjort for å vise jamila om det går å sende info fra en

    val latitude = mountain.point?.latitude()
    val longitude = mountain.point?.longitude()

    // Gir point koordinatene i lat, så long?
    println("latitude: " + latitude.toString()) // latitude: 8.557780981063843
    println("longitude: " + longitude.toString()) // longitude: 61.65138739947395

    //dataN = apiViewModel.repository.getNowCast(latitude.toString(), longitude.toString(), elevation.toString())

    val weather_high = locationInfo.cloud_area_fraction_high
    val weather_mid = locationInfo.cloud_area_fraction_medium
    val weather_low = locationInfo.cloud_area_fraction_low

   // val temp = dataN.temperatureNow
   // val wind = dataN.windN

    println("weather_high = $weather_high")
    println("weather_mid = $weather_mid")
    println("weather_low = $weather_low")

    Card (
        colors = CardDefaults.cardColors(Sikt_lyseblå),
        modifier = Modifier.padding(20.dp),
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
        ) {
            Sikt_Header(location = "$name", alertinfo = mutableListOf()) // Husk å endre alertinfo
            Sikt_MountainHight(mountainheight = "$elevation")
            Spacer(modifier = Modifier.size(20.dp))


            // her skal kalendern brukes:
            Sikt_Historisk_Kalender(   apiViewModel , frostinfo )


        /*
            //illustrasjon(elevation, 10f,10f,"skyet", "delvisskyet", "klart")
            Spacer(modifier = Modifier.size(20.dp))
            Text(text = "Dagens siktvarsel: ", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.size(10.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item { Sikt_LocationCard_Hour(12,"skyet", 7) }
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
                item { Sikt_Turer_I_Naerheten("fjelltopp", 1899, 8) }
                item { Sikt_Turer_I_Naerheten("fjelltopp", 1899, 8) }
                item { Sikt_Turer_I_Naerheten("fjelltopp", 1899, 8) }
                item { Sikt_Turer_I_Naerheten("fjelltopp", 1899, 8) }
                item { Sikt_Turer_I_Naerheten("fjelltopp", 1899, 8) }
            }

 */

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

@Composable
fun Sikt_LoctationCard_Topper_i_naerheten() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(start = 20.dp, end = 20.dp, top = 5.dp),
    ) {
        Text(text = "Topper i nærheten: ", modifier = Modifier.align(Alignment.TopStart), fontWeight = FontWeight.Bold)
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun ComponentTest() {

    //Sikt_LocationCard()

}