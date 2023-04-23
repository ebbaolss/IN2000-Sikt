package com.example.in2000_prosjekt.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.in2000_prosjekt.ui.*
import com.example.in2000_prosjekt.ui.components.Sikt_BottomBar
import com.example.in2000_prosjekt.ui.components.Sikt_Favorite_card
import com.example.in2000_prosjekt.ui.components.Sikt_favoritt_tekst
import com.example.in2000_prosjekt.ui.theme.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavoriteScreen(apiViewModel: APIViewModel = viewModel(), onNavigateToMap: () -> Unit, onNavigateToFav: () -> Unit, onNavigateToSettings: () -> Unit, onNavigateToRules: () -> Unit){
    val appUiState by apiViewModel.appUiState.collectAsState()

    when(appUiState){
        is AppUiState.Loading -> Text (text = "loading...", fontSize = 30.sp)
        is AppUiState.Error -> Text (text = "error")
        is AppUiState.Success -> {
            FavoriteScreenSuccess(
                weatherinfo = (appUiState as AppUiState.Success).locationF,
                nowcastinfo = (appUiState as AppUiState.Success).nowCastF,
                sunriseinfo = (appUiState as AppUiState.Success).sunriseF,
                alertinfo = (appUiState as AppUiState.Success).alertListF,
                frostinfo = (appUiState as AppUiState.Success).frostF,
                onNavigateToMap,
                onNavigateToFav,
                onNavigateToSettings,
                onNavigateToRules
            ) //endre dette til en bedre måte etterhvert?
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavoriteScreenSuccess(weatherinfo: LocationInfo, nowcastinfo: NowCastInfo, sunriseinfo: SunriseInfo, alertinfo: MutableList<AlertInfo>, frostinfo: FrostInfo,
    onNavigateToMap: () -> Unit, onNavigateToFav: () -> Unit, onNavigateToSettings: () -> Unit, onNavigateToRules: () -> Unit
) {
    Scaffold(topBar = { Sikt_favoritt_tekst()}, bottomBar = { Sikt_BottomBar(onNavigateToMap, onNavigateToFav, onNavigateToSettings, onNavigateToRules, favoritt = Sikt_mellomblå, settings = Sikt_lyseblå, rules = Sikt_lyseblå, map = Sikt_lyseblå)},
        containerColor = Sikt_mellomblå
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ){
            item {
                Spacer(modifier = Modifier.height(100.dp))
                Sikt_Favorite_card()
                //Sikt_Favorite_card(weatherinfo, nowcastinfo, sunriseinfo, alertinfo)
                Spacer(modifier = Modifier.height(15.dp))
            }
            item {
                Sikt_Favorite_card()
                //Sikt_Favorite_card(weatherinfo, nowcastinfo, sunriseinfo, alertinfo)
                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }   
}


