package com.example.in2000_prosjekt.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.in2000_prosjekt.R
import com.example.in2000_prosjekt.ui.components.Sikt_BottomBar
import com.example.in2000_prosjekt.ui.components.Sikt_Turer_I_Naerheten
import com.example.in2000_prosjekt.ui.components.Sikt_skyillustasjon
import com.example.in2000_prosjekt.ui.theme.Sikt_lyseblå
import com.example.in2000_prosjekt.ui.theme.Sikt_mellomblå
import com.example.in2000_prosjekt.ui.theme.Sikt_mørkeblå

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RulesScreen(onNavigateToMap: () -> Unit, onNavigateToFav: () -> Unit, onNavigateToSettings: () -> Unit, onNavigateToRules: () -> Unit){

    Scaffold(bottomBar = { Sikt_BottomBar(onNavigateToMap, onNavigateToFav, onNavigateToRules, onNavigateToSettings, favoritt = false, settings = false, rules = true, map = false) }, containerColor = Sikt_mellomblå) {
        val rules: Array<String> = stringArrayResource(id = R.array.rules)
        val scrollState = rememberScrollState()
        Card(
            modifier = Modifier
                .padding(20.dp)
                .verticalScroll(scrollState),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(Sikt_lyseblå)
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_contact_phone),
                    contentDescription = "Phone illustration",
                    tint = Sikt_mørkeblå,
                    modifier = Modifier.align(Alignment.CenterHorizontally).size(100.dp),
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    text = "Informasjon",
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(50.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Din posisjon:",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.fillMaxWidth().padding(start = 20.dp),
                    //Placeholder for ditt koordinat:
                    text = "59.99436° N, 10,71848° Ø",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal
                )
                Spacer(modifier = Modifier.size(20.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Nødnummer:",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Medisinsk Nødtelefon:",
                        textAlign = TextAlign.Start,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = "113",
                        textAlign = TextAlign.End,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Brann:",
                        textAlign = TextAlign.Start,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = "110",
                        textAlign = TextAlign.End,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Politi:",
                        textAlign = TextAlign.Start,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = "112",
                        textAlign = TextAlign.End,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Politiets sentralbord:",
                        textAlign = TextAlign.Start,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = "02800",
                        textAlign = TextAlign.End,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Legevakten:",
                        textAlign = TextAlign.Start,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = "116117",
                        textAlign = TextAlign.End,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
                Spacer(modifier = Modifier.size(20.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Fjellvettreglene:",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    var counter = 1
                    rules.forEach {
                        Row(
                            modifier = Modifier
                                .padding(vertical = 2.dp)
                        ) {
                            Text(
                                text = "$counter. ",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                //modifier = Modifier.padding(start = 20.dp)
                            )
                            Text(
                                modifier = Modifier.fillMaxWidth().padding(end = 20.dp),
                                text = it,
                                fontSize = 18.sp,
                                fontFamily = FontFamily.SansSerif
                            )
                            Spacer(Modifier.height(30.dp))
                            counter++
                        }
                    }
                }
                Spacer(modifier = Modifier.size(100.dp))
            }
        }
    }
}