package com.example.in2000_prosjekt.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
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
                alertinfo = (appUiState as AppUiState2.Success).alertList,
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
fun FavoriteScreenSuccess(weatherinfo: LocationInfo, nowcastinfo: NowCastInfo, sunriseinfo: SunriseInfo, alertinfo: MutableList<AlertInfo>,
    onNavigateToMap: () -> Unit, onNavigateToFav: () -> Unit, onNavigateToRules: () -> Unit
) {
    Scaffold(topBar = { CenterAlignedTopAppBar(colors = TopAppBarDefaults.centerAlignedTopAppBarColors(Sikt_lyseblå), title = {
        Text(text = "Favoritter", fontSize = 40.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(10.dp))
    })
    }, bottomBar = {Sikt_BottomBar(onNavigateToMap, onNavigateToFav, onNavigateToRules, favoritt = Sikt_mellomblå, rules = Sikt_lyseblå, map = Sikt_lyseblå)}) {
        LazyColumn{
            item {
                Spacer(modifier = Modifier.height(100.dp))
                ToppCard(weatherinfo, nowcastinfo, sunriseinfo, alertinfo)
                Spacer(modifier = Modifier.height(15.dp))
            }
            item {
                ToppCard(weatherinfo, nowcastinfo, sunriseinfo, alertinfo)
                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }
}


