package com.example.in2000_prosjekt.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import com.example.in2000_prosjekt.ui.components.Sikt_Favorite_card
import com.example.in2000_prosjekt.ui.database.FavoriteViewModel
//import com.example.in2000_prosjekt.ui.components.Sikt_favoritt_tekst
import com.example.in2000_prosjekt.ui.theme.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavoriteScreen(viewModel: FavoriteViewModel, onNavigateToMap: () -> Unit, onNavigateToFav: () -> Unit, onNavigateToSettings: () -> Unit, onNavigateToRules: () -> Unit){

    val appUiState by viewModel.appUiState.collectAsState()

    when(appUiState){
        is AppUiState.Loading -> 
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Sikt_mellomblå), 
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(painter = painterResource(id = R.drawable.outline_pending), contentDescription = "", tint = Sikt_hvit, modifier = Modifier.size(50.dp))
                Text(text = "Loading", color = Sikt_hvit, fontSize = 30.sp, fontWeight = FontWeight.Bold)
            }
        is AppUiState.Error -> {

           FavoriteScreenError( onNavigateToMap,
               onNavigateToFav,onNavigateToSettings,
               onNavigateToRules)
        }
        is AppUiState.Success -> {
            FavoriteScreenSuccess(
                /*
                weatherinfo = (appUiState as AppUiState.Success).locationF,
                nowcastinfo = (appUiState as AppUiState.Success).nowCastF,
                sunriseinfo = (appUiState as AppUiState.Success).sunriseF,
                alertinfo = (appUiState as AppUiState.Success).alertListF,

                 */
                //frostinfo = (appUiState as AppUiState.Success).frostF,
                onNavigateToMap,
                onNavigateToFav,
                onNavigateToSettings,
                onNavigateToRules,
                viewModel
            ) //endre dette til en bedre måte etterhvert?
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavoriteScreenSuccess(
    //weatherinfo: LocationInfo, nowcastinfo: NowCastInfo, sunriseinfo: SunriseInfo, alertinfo: MutableList<AlertInfo>, //frostinfo: FrostInfo,
    onNavigateToMap: () -> Unit, onNavigateToFav: () -> Unit, onNavigateToSettings: () -> Unit, onNavigateToRules: () -> Unit, viewModel: FavoriteViewModel
) {
    //var weatherinfo = viewModel.getLocationList()
    //var nowcastinfo = viewModel.getNowInfo()

    Scaffold(bottomBar = { Sikt_BottomBar(onNavigateToMap, onNavigateToFav, onNavigateToRules, onNavigateToSettings, favoritt = true, rules = false, map = false, settings = false)}) {
        LazyColumn(
            contentPadding = PaddingValues(20.dp)
        ) {
            //Sikt_Favorite_card(weatherinfo = weatherinfo, nowcastinfo = nowcastinfo )
        }
    }
}



