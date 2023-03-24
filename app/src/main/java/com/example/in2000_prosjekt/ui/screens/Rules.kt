package com.example.in2000_prosjekt.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.in2000_prosjekt.R
import com.example.in2000_prosjekt.ui.theme.Sikt_lyseblå

@Composable
fun RulesScreen(onNavigateToNext: () -> Unit){

    val rules: Array<String> = stringArrayResource(id = R.array.rules)

    Column()  {

        Text(
            modifier = Modifier,
            text = "Fjellvettreglene",
            textAlign = TextAlign.Center, //den er fortsatt ikke i midten
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(25.dp))

        rules.forEach {
            Text(text = it,
                fontSize = 18.sp
            )
        }
    }
}

@Preview(showSystemUi = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RulesScreenPreview(){

    val rules: Array<String> = stringArrayResource(id = R.array.rules)
    Card(
        modifier = Modifier
            .padding(20.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(Color(0xFFCDDCEB))
    )   {

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

            Spacer(Modifier.height(30.dp))

            var counter = 1
            rules.forEach {
                Row(modifier = Modifier
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
                    Spacer(Modifier.height(50.dp))
                    counter++
                }
            }
        }

//        rules.forEach {
//            Text(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                text = it,
//                fontSize = 24.sp,
//                fontFamily = FontFamily.SansSerif
//            )
//        }
    }
}

