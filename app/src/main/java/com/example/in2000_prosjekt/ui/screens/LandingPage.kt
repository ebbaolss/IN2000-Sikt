package com.example.in2000_prosjekt.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.in2000_prosjekt.ui.components.Sikt_BlueButton
import com.example.in2000_prosjekt.ui.components.Sikt_GreyButton
import com.example.in2000_prosjekt.ui.components.Sikt_BottomBar
import com.example.in2000_prosjekt.ui.theme.Sikt_grå
import com.example.in2000_prosjekt.ui.theme.Sikt_lyseblå
import com.example.in2000_prosjekt.ui.theme.Sikt_sort

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandingPage(onNavigateToNext: () -> Unit){
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(Sikt_lyseblå)) {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {
            Spacer(modifier = Modifier.height(50.dp))
            Text(text = "planlegg tur", fontSize = 32.sp)
            Text(text = "få informasjon", fontSize = 32.sp)
            Text(text = "lagre favoritter", fontSize = 32.sp)
            Spacer(modifier = Modifier.height(100.dp))
            Sikt_BlueButton(title = "Kom igang")
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { onNavigateToNext() },
                colors = ButtonDefaults.buttonColors(Sikt_grå),
                modifier = Modifier.width(172.dp)
            ) {
                Text(text = "Hopp over", color = Sikt_sort, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(50.dp))
        }
    }

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun LandingPagePreview() {
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(Sikt_lyseblå)) {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {
            Spacer(modifier = Modifier.height(50.dp))
            Text(text = "planlegg tur", fontSize = 32.sp)
            Text(text = "få informasjon", fontSize = 32.sp)
            Text(text = "lagre favoritter", fontSize = 32.sp)
            Spacer(modifier = Modifier.height(100.dp))
            Sikt_BlueButton(title = "Kom igang")
            Spacer(modifier = Modifier.height(20.dp))
            Sikt_GreyButton(title = "Hopp over")
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}