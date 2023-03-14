package com.example.in2000_prosjekt.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.in2000_prosjekt.R
import com.example.in2000_prosjekt.ui.theme.Sikt_hvit
import com.example.in2000_prosjekt.ui.theme.Sikt_lyseblå
import com.example.in2000_prosjekt.ui.theme.Sikt_mellomblå

@Composable
fun Sikt_BottomBar() {

    BottomAppBar(
        modifier = Modifier,
        containerColor = Sikt_hvit,

        ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Outlined.LocationOn, contentDescription = "Localized description", tint = Sikt_mellomblå)
            //Text(text = "Utforsk")
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Outlined.Favorite, contentDescription = "Localized description", tint = Sikt_mellomblå)
            //Text(text = "Favoritter")
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Outlined.Menu, "", tint = Sikt_mellomblå)
            //Text(text = "Fjellvettreglene")
        }
    }
}

@Composable
fun Sikt_topBar() {

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun testComponent() {

    Scaffold(topBar = { Sikt_topBar() }, bottomBar = { Sikt_BottomBar() }) {

    }
}