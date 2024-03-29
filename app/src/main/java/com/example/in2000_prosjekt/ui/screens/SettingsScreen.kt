package com.example.in2000_prosjekt.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.in2000_prosjekt.R
import com.example.in2000_prosjekt.ui.components.Sikt_BottomBar
import com.example.in2000_prosjekt.ui.components.siktSettingsCard
import com.example.in2000_prosjekt.ui.FavoriteViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingsScreen(onNavigateToMap: () -> Unit, onNavigateToFav: () -> Unit, onNavigateToInfo:() -> Unit, onNavigateToSettings: () -> Unit, viewModel: FavoriteViewModel){

    Scaffold(bottomBar = { Sikt_BottomBar(onNavigateToMap, onNavigateToFav, onNavigateToInfo, onNavigateToSettings, map = false, favorite = false, info = false, settings = true) }) {

        Box(modifier = Modifier.paint(painterResource(id = R.drawable.map_backround), contentScale = ContentScale.FillBounds)) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 70.dp)
            ){
                siktSettingsCard(viewModel)
            }
        }
    }
}