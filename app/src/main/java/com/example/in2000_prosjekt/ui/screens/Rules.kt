package com.example.in2000_prosjekt.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.in2000_prosjekt.R
import com.example.in2000_prosjekt.ui.components.Sikt_BottomBar
import com.example.in2000_prosjekt.ui.components.Sikt_sol
import com.example.in2000_prosjekt.ui.theme.Sikt_lyseblå
import com.example.in2000_prosjekt.ui.theme.Sikt_mellomblå

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
            Sikt_sol()
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                text = "Fjellvettreglene",
                textAlign = TextAlign.Center,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                )

                Spacer(Modifier.height(10.dp))

                var counter = 1
                rules.forEach {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 20.dp, vertical = 5.dp),
                    ) {

                        Text(
                            text = "$counter. ",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold

                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = it,
                            fontSize = 18.sp,
                            fontFamily = FontFamily.SansSerif
                        )
                        Spacer(Modifier.height(30.dp))
                        counter++
                    }
                }
            }
        }
    }
}



