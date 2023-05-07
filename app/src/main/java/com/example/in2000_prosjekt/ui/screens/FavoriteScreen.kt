package com.example.in2000_prosjekt.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.in2000_prosjekt.R
import com.example.in2000_prosjekt.ui.*
import com.example.in2000_prosjekt.ui.components.FavoriteScreenError
import com.example.in2000_prosjekt.ui.components.Sikt_BottomBar
import com.example.in2000_prosjekt.ui.components.Sikt_Favorite_card
import com.example.in2000_prosjekt.ui.database.Favorite
import com.example.in2000_prosjekt.ui.database.FavoriteViewModel
//import com.example.in2000_prosjekt.ui.components.Sikt_favoritt_tekst
import com.example.in2000_prosjekt.ui.theme.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavoriteScreen(onNavigateToMap: () -> Unit, onNavigateToFav: () -> Unit, onNavigateToInfo: () -> Unit, onNavigateToSettings: () -> Unit, viewModel: FavoriteViewModel, apiViewModel: APIViewModel){

    //Used for testing:
    //viewModel.deleteAll()
    //addFavTest(viewModel)

    val appUiState by apiViewModel.appUiState.collectAsState()
    val allFavorites by viewModel.allFavorites.observeAsState(listOf())

    val listUi = checkFavs( apiViewModel = apiViewModel , allFavorites = allFavorites)

    if(allFavorites.size != 0){
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
                FavoriteScreenError( onNavigateToMap, onNavigateToFav, onNavigateToInfo, onNavigateToSettings)
            }
            is AppUiState.Success -> {
                FavoriteScreenSuccess(
                    onNavigateToMap,
                    onNavigateToFav,
                    onNavigateToSettings,
                    onNavigateToInfo,
                    viewModel,
                    apiViewModel,
                    allFavorites,
                    listUi
                )
            }
        }
    }
}

//FOR TESTING ONLY, DELETE WHEN DONE
fun addFavTest(viewModel: FavoriteViewModel){
    Log.d("ADDFAV", "addet favorite to viewmodel")
    val fav = Favorite(8.0,61.0)
    viewModel.addFavorite(fav)
}
@Composable
fun checkFavs(apiViewModel: APIViewModel, allFavorites: List<Favorite>) : MutableList<AppUiState>{
    val appUiState by apiViewModel.appUiState.collectAsState()
    val listUi : MutableList<AppUiState> = mutableListOf()

    allFavorites.forEach {
        apiViewModel.getAll(it.latitude.toString(), it.longtitude.toString(), "50")
        listUi.add(appUiState)
    }
    return listUi
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavoriteScreenSuccess(
    //weatherinfo: LocationInfo, nowcastinfo: NowCastInfo, sunriseinfo: SunriseInfo, alertinfo: MutableList<AlertInfo>, //frostinfo: FrostInfo,
    onNavigateToMap: () -> Unit, onNavigateToFav: () -> Unit, onNavigateToInfo: () -> Unit, onNavigateToSettings: () -> Unit, viewModel: FavoriteViewModel, apiViewModel: APIViewModel, allFavorites : List<Favorite>,listUi : MutableList<AppUiState>

) {

    var weatherinfo : MutableList<LocationInfo> = mutableListOf()
    var nowcastinfo : MutableList<NowCastInfo> = mutableListOf()
    var alertinfo : MutableList<MutableList<AlertInfo>> = mutableListOf()

    listUi.forEach{
        weatherinfo.add((it as AppUiState.Success).locationF)
        nowcastinfo.add(it.nowCastF)
        alertinfo.add(it.alertListF)
    }

    Scaffold(bottomBar = { Sikt_BottomBar(onNavigateToMap, onNavigateToFav, onNavigateToInfo, onNavigateToSettings, favorite = true, info = false, map = false, settings = false)}
    ) {
        LazyColumn(
            contentPadding = PaddingValues(20.dp)
        ) {
            Sikt_Favorite_card(weatherinfo, nowcastinfo, alertinfo)
        }
    }
}




