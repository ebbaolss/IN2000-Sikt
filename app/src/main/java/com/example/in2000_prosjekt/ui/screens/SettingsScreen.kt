package com.example.in2000_prosjekt.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.in2000_prosjekt.ui.components.DeleteAllButton
import com.example.in2000_prosjekt.ui.components.Sikt_BottomBar
import com.example.in2000_prosjekt.ui.components.Sikt_InformationCard
import com.example.in2000_prosjekt.ui.components.Sikt_Turer_I_Naerheten
import com.example.in2000_prosjekt.ui.components.Sikt_skyillustasjon
import com.example.in2000_prosjekt.ui.database.FavoriteViewModel
import com.example.in2000_prosjekt.ui.theme.Sikt_lyseblå
import com.example.in2000_prosjekt.ui.theme.Sikt_mellomblå

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(onNavigateToMap: () -> Unit, onNavigateToFav: () -> Unit, onNavigateToSettings: () -> Unit, onNavigateToRules: () -> Unit, viewModel: FavoriteViewModel){

    Scaffold(bottomBar = { Sikt_BottomBar(onNavigateToMap, onNavigateToFav, onNavigateToRules, onNavigateToSettings, favoritt = false, settings = false, rules = true, map = false) }, containerColor = Sikt_mellomblå) {
        val rules: Array<String> = stringArrayResource(id = R.array.rules)

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 70.dp)
        ){
            Sikt_InformationCard(rules)
        }
    }
}