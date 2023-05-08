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
import com.example.in2000_prosjekt.ui.database.FavoriteUiState
import com.example.in2000_prosjekt.ui.database.FavoriteViewModel
//import com.example.in2000_prosjekt.ui.components.Sikt_favoritt_tekst
import com.example.in2000_prosjekt.ui.theme.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavoriteScreen(onNavigateToMap: () -> Unit, onNavigateToFav: () -> Unit, onNavigateToInfo: () -> Unit, onNavigateToSettings: () -> Unit, viewModel: FavoriteViewModel, apiViewModel: APIViewModel){

    //Used for testing:
    //viewModel.deleteAll()
    //addFavTest(viewModel)

    val favoriteUiState by viewModel.favUiState.collectAsState()
    val allFavorites by viewModel.allFavorites.observeAsState(listOf())

    Log.d("FAVS", "${allFavorites.size}")
    //viewModel.deleteAll()
    if(allFavorites.isNotEmpty()){
        viewModel.update()
    } else {
        FavoriteEmpty(
            onNavigateToMap = onNavigateToMap,
            onNavigateToFav = onNavigateToFav,
            onNavigateToInfo = onNavigateToInfo,
            onNavigateToSettings = onNavigateToSettings)
    }

    when(favoriteUiState){
        is FavoriteUiState.Loading ->
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
        is FavoriteUiState.Error -> {
            FavoriteScreenError( onNavigateToMap,
                onNavigateToFav,onNavigateToInfo,
                onNavigateToSettings)
        }
        is FavoriteUiState.Success -> {
            if(allFavorites.size > 0){
                FavoriteScreenSuccess(
                    onNavigateToMap,
                    onNavigateToFav,
                    onNavigateToInfo,
                    onNavigateToSettings,
                    viewModel,
                    allFavorites
                )
            } else {
                FavoriteEmpty(
                    onNavigateToMap = onNavigateToMap,
                    onNavigateToFav = onNavigateToFav,
                    onNavigateToInfo = onNavigateToInfo,
                    onNavigateToSettings = onNavigateToSettings)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavoriteScreenSuccess(
    onNavigateToMap: () -> Unit, onNavigateToFav: () -> Unit, onNavigateToInfo: () -> Unit, onNavigateToSettings: () -> Unit, viewModel: FavoriteViewModel, allFavorites : List<Favorite>


) {
    val favoriteUiState by viewModel.favUiState.collectAsState()



    Scaffold(bottomBar = { Sikt_BottomBar(onNavigateToMap, onNavigateToFav, onNavigateToInfo, onNavigateToSettings, favorite = true, info = false, map = false, settings = false)}
    ) {
        Log.d("SIZELOC", " ${(favoriteUiState as FavoriteUiState.Success).locationF.size}")
        LazyColumn(
            contentPadding = PaddingValues(20.dp)
        ) {
            if (allFavorites.size != 0) {
                Sikt_Favorite_card(
                    (favoriteUiState as FavoriteUiState.Success).locationF,
                    (favoriteUiState as FavoriteUiState.Success).nowCastF,
                    (favoriteUiState as FavoriteUiState.Success).alertListF,
                    allFavorites,
                    viewModel
                )
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteEmpty(
    onNavigateToMap: () -> Unit, onNavigateToFav: () -> Unit,onNavigateToInfo: () -> Unit, onNavigateToSettings: () -> Unit
){
    Scaffold(bottomBar = { Sikt_BottomBar(onNavigateToMap, onNavigateToFav, onNavigateToInfo, onNavigateToSettings, favorite = true, info = false, map = false, settings = false)}
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(text = "NO FAVORITES")
        }
    }
}




