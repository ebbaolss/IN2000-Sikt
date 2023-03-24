package com.example.in2000_prosjekt.ui.components

import android.annotation.SuppressLint
import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.in2000_prosjekt.R
import com.example.in2000_prosjekt.ui.theme.*

@Composable
fun Sikt_BottomBar() {

    BottomAppBar(
        modifier = Modifier.clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)),
        containerColor = Sikt_lyseblå,

        ) {
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            Column (horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(120.dp)
            ){
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        Icons.Outlined.LocationOn,
                        contentDescription = "Localized description",
                        tint = Sikt_mellomblå,
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Sikt_lyseblå)
                            .padding(5.dp))
                }
                Text(text = "Utforsk")
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(120.dp)
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        Icons.Outlined.Favorite,
                        contentDescription = "Localized description",
                        tint = Sikt_mellomblå,
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Sikt_lyseblå)
                            .padding(5.dp)
                    )
                }
                Text(text = "Favoritter")
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(120.dp)
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        Icons.Outlined.Menu,
                        "",
                        tint = Sikt_mellomblå,
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Sikt_lyseblå)
                            .padding(5.dp)
                    )
                }
                Text(text = "Fjellvettreglene")
            }
        }
    }
}


@Composable
fun Sikt_topBar() {

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Sikt_favoritter_card(){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(Sikt_lyseblå),
        shape = RoundedCornerShape(50.dp),
        colors = CardDefaults.cardColors(Color(0xFFCDDCEB)) // vil sette bagrunnsfargen til sikt_lyseblå men ????
    ) {
        //todoooo
    }
}


@Composable
fun Sikt_BlueButton(title : String) {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(Sikt_lyseblå),
        modifier = Modifier.width(172.dp)
    ) {
        Text(text = title, color = Sikt_mellomblå, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun Sikt_GreyButton(title : String) {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(Sikt_grå),
        modifier = Modifier.width(172.dp)
    ) {
        Text(text = title, color = Sikt_sort, fontWeight = FontWeight.Bold)
    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun testComponent() {

    Scaffold(topBar = { Sikt_topBar() }, bottomBar = { Sikt_BottomBar() }) {

    }
}