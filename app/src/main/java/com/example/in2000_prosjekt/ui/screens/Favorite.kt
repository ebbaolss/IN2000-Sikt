package com.example.in2000_prosjekt.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.in2000_prosjekt.ui.*
import com.example.in2000_prosjekt.ui.components.Sikt_BottomBar
import com.example.in2000_prosjekt.ui.components.Sikt_favoritt_tekst
import com.example.in2000_prosjekt.ui.components.ToppCard
import com.example.in2000_prosjekt.ui.theme.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavoriteScreen(apiViewModel: APIViewModel = viewModel(), onNavigateToMap: () -> Unit, onNavigateToFav: () -> Unit, onNavigateToRules: () -> Unit){
    val appUiState by apiViewModel.appUiState.collectAsState()

    when(appUiState){
        is AppUiState2.Loading -> Text (text = "loading...", fontSize = 30.sp)
        is AppUiState2.Error -> Text (text = "error")
        is AppUiState2.Success -> {
            FavoriteScreenSuccess(
                weatherinfo = (appUiState as AppUiState2.Success).locationInfo,
                nowcastinfo = (appUiState as AppUiState2.Success).nowCastDef,
                sunriseinfo = (appUiState as AppUiState2.Success).sunrise,
                //alertinfo = (appUiState as AppUiState2.Success).alert
                onNavigateToMap,
                onNavigateToFav,
                onNavigateToRules
            ) //endre dette til en bedre måte etterhvert?
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavoriteScreenSuccess(weatherinfo: LocationInfo, nowcastinfo: NowCastInfo, sunriseinfo: SunriseInfo,
    //alertinfo: AlertInfo
    onNavigateToMap: () -> Unit, onNavigateToFav: () -> Unit, onNavigateToRules: () -> Unit
) {
    /*Scaffold(topBar = { Sikt_favoritt_tekst()}, bottomBar = { Sikt_BottomBar(onNavigateToMap, onNavigateToFav, onNavigateToRules)}) {

    val temperatur = appUiState.nowcast?.properties?.timeseries?.get(0)?.data?.instant?.details?.air_temperature.toString()
    val sikt = appUiState.locationForecast?.properties?.timeseries?.get(0)?.data?.instant?.details?.fog_area_fraction.toString()
    val nedbor = appUiState.locationForecast?.properties?.timeseries?.get(0)?.data?.instant?.details?.precipitation_amount.toString()
    val vind = appUiState.nowcast?.properties?.timeseries?.get(0)?.data?.instant?.details?.wind_speed.toString()
    val varsel = "0"
    val soloppgang = appUiState.sunrise?.properties?.sunrise?.time.toString()
    val solnedgang = appUiState.sunrise?.properties?.sunset?.time.toString()
    }*/

    Scaffold(topBar = { Sikt_favoritt_tekst()}, bottomBar = { Sikt_BottomBar(onNavigateToMap, onNavigateToFav, onNavigateToRules, favoritt = Sikt_mellomblå, rules = Sikt_lyseblå, map = Sikt_lyseblå)}) {
        LazyColumn(
        ){
            item {
                Spacer(modifier = Modifier.height(100.dp))
                ToppCard(weatherinfo, nowcastinfo, sunriseinfo)
                Spacer(modifier = Modifier.height(15.dp))
            }
            item {
                ToppCard(weatherinfo, nowcastinfo, sunriseinfo)
                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }
}
