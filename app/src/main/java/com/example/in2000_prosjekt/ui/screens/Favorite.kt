package com.example.in2000_prosjekt.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.in2000_prosjekt.R
import com.example.in2000_prosjekt.ui.*
import com.example.in2000_prosjekt.ui.components.FavoriteScreenError
import com.example.in2000_prosjekt.ui.components.Sikt_BottomBar
import com.example.in2000_prosjekt.ui.components.Sikt_favoritt_tekst
//import com.example.in2000_prosjekt.ui.components.Sikt_favoritt_tekst
import com.example.in2000_prosjekt.ui.components.ToppCard
import com.example.in2000_prosjekt.ui.theme.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavoriteScreen(apiViewModel: APIViewModel = viewModel(), onNavigateToMap: () -> Unit, onNavigateToFav: () -> Unit, onNavigateToSettings: () -> Unit, onNavigateToRules: () -> Unit){
    val appUiState by apiViewModel.appUiState.collectAsState()

    when(appUiState){
        is AppUiState.Loading -> Text (text = "loading...", fontSize = 30.sp)
        is AppUiState.Error -> {

           FavoriteScreenError( onNavigateToMap,
               onNavigateToFav,
               onNavigateToRules)
        }
        is AppUiState.Success -> {
            FavoriteScreenSuccess(
                weatherinfo = (appUiState as AppUiState.Success).locationF,
                nowcastinfo = (appUiState as AppUiState.Success).nowCastF,
                sunriseinfo = (appUiState as AppUiState.Success).sunriseF,
                alertinfo = (appUiState as AppUiState.Success).alertListF,
                frostinfo = (appUiState as AppUiState.Success).frostF,
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
fun FavoriteScreenSuccess(weatherinfo: LocationInfo, nowcastinfo: NowCastInfo, sunriseinfo: SunriseInfo, alertinfo: MutableList<AlertInfo>, frostinfo: FrostInfo,
    onNavigateToMap: () -> Unit, onNavigateToFav: () -> Unit, onNavigateToRules: () -> Unit
) {
    Scaffold(topBar = { Sikt_favoritt_tekst()}, bottomBar = { Sikt_BottomBar(onNavigateToMap, onNavigateToFav, onNavigateToRules, favoritt = Sikt_mellomblå, rules = Sikt_lyseblå, map = Sikt_lyseblå)}) {
        LazyColumn(
        ){
            item {
                Spacer(modifier = Modifier.height(100.dp))
                ToppCard(weatherinfo, nowcastinfo, sunriseinfo, alertinfo, frostinfo)
                Spacer(modifier = Modifier.height(15.dp))
            }
            item {
                ToppCard(weatherinfo, nowcastinfo, sunriseinfo, alertinfo, frostinfo)
                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }

}



