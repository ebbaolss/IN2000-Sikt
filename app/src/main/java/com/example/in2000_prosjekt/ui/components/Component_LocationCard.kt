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
import com.example.in2000_prosjekt.ui.theme.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Sikt_LocationCard(mountain: MapUiState.Mountain){
    Card (
        colors = CardDefaults.cardColors(Sikt_lyseblå),
        modifier = Modifier.padding(20.dp),
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
        ) {
            Sikt_Header("Fjelltopp")
            // Text(text = "${mountain.name}", fontWeight = FontWeight.Bold, fontSize = 30.sp)
            Sikt_MountainHight("1800")
            //Text(text = "${mountain.elevation} m.o.h.", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.size(20.dp))
            illustrasjon(1469, -11f,5f,"skyet", "delvisskyet", "klart")
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
        modifier = Modifier.width(110.dp)
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
        modifier = Modifier.fillMaxWidth().height(100.dp),
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

@Composable
fun Sikt_LocationCard_Hour() {
    
    Card(
        modifier = Modifier
            .width(70.dp)
            .height(80.dp)
            .padding(10.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(Sikt_hvit)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "12:00", color = Sikt_sort, fontSize = 12.sp)
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun ComponentTest() {

    Card(
        modifier = Modifier
            .width(70.dp)
            .height(80.dp)
            .padding(10.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(Sikt_hvit)
    ) {
        Sikt_LocationCard(MapUiState.Mountain("Galdhøpiggen", Point.fromLngLat(50.0,10.0), 2469))
        //Sikt_LocationCard_NextDays()
        //Sikt_LoctationCard_Topper_i_naerheten()
        //Sikt_LocationCard_Hour()
    }
}