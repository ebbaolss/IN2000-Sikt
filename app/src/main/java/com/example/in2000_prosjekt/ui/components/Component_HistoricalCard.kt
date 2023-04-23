package com.example.in2000_prosjekt.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.in2000_prosjekt.ui.theme.Sikt_hvit
import com.example.in2000_prosjekt.ui.theme.Sikt_lyseblå
import com.example.in2000_prosjekt.ui.theme.Sikt_mørkeblå

@Composable
fun Sikt_Historisk_Kalender() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp)
            .height(100.dp)
            .background(Sikt_hvit),
    ) {
        Text(text = "Kalender")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Sikt_HistoriskCard(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Sikt_lyseblå),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box() {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Outlined.Refresh, "", tint = Sikt_mørkeblå) //fyll symbol, bytter senere
                    Text(text = "Galdhøpiggen", fontWeight = FontWeight.Bold, fontSize = 30.sp)
                    Icon(Icons.Filled.Favorite, "", tint = Sikt_mørkeblå)
                }
                Text(text = "2469 m.o.h", fontWeight = FontWeight.Bold)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Box() {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Sikt_Datavisualisering_Card(2469, -11f, 4f, true, false, true)
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

        Sikt_Historisk_Kalender()

        Sikt_LoctationCard_Topper_i_naerheten()

    }
}

