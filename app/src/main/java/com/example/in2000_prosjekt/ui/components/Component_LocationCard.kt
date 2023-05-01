package com.example.in2000_prosjekt.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.in2000_prosjekt.ui.theme.Sikt_hvit
import com.example.in2000_prosjekt.ui.theme.Sikt_lyseblå
import com.example.in2000_prosjekt.ui.theme.Sikt_mørkeblå
import com.example.in2000_prosjekt.ui.theme.Sikt_sort
import com.example.in2000_prosjekt.ui.uistate.MapUiState
import com.mapbox.geojson.Point


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Sikt_LocationCard(mountain: MapUiState.Mountain){
    Card (
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(Sikt_lyseblå)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            // horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box() {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Outlined.Refresh,
                            "",
                            tint = Sikt_mørkeblå
                        ) //fyll symbol, bytter senere
                        Text(text = "${mountain.name}", fontWeight = FontWeight.Bold, fontSize = 30.sp)
                        Icon(Icons.Filled.Favorite, "", tint = Sikt_mørkeblå)
                    }
                    Text(text = "${mountain.elevation} m.o.h.", fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Box() {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Sikt_Datavisualisering_Card(2469, 3f, 7f, true, false, true)
                        Column(
                            modifier = Modifier.size(height = 220.dp, width = 150.dp),
                            verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "2000-5000 m.o.h")
                            Text(text = "Sikt: ")
                            Text(text = "1000-2000 m.o.h")
                            Text(text = "Sikt: ")
                            Text(text = "0-1000 m.o.h")
                            Text(text = "Sikt: ")
                        }
                    }
                }
            }
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                item { Sikt_LocationCard_Hour() }
                item { Sikt_LocationCard_Hour() }
                item { Sikt_LocationCard_Hour() }
                item { Sikt_LocationCard_Hour() }
                item { Sikt_LocationCard_Hour() }
                item { Sikt_LocationCard_Hour() }
            }
            Sikt_LocationCard_NextDays()
            Sikt_LoctationCard_Topper_i_naerheten()
        }
    }
}

@Composable
fun Sikt_LocationCard_NextDays() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(start = 30.dp, end = 30.dp, bottom = 20.dp),
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
            item {
                Sikt_Datavisualisering_Card(860 ,-10f, 7f, false, true, true)
                //Sikt_FinnTurer_card(450, -10, 0,true, false, true)
            }
            item {
                Sikt_FinnTurer_card("test",550, 3,false, true, false)
            }
            item {
                Sikt_FinnTurer_card("test",1100, 23,false, false, true)
            }
            item {
                Sikt_FinnTurer_card("test",1670, -1,false, false, false)
            }
            item {
                Sikt_FinnTurer_card("test",2469, 6,true, true, true)
            }
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
            Sikt_sol()
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