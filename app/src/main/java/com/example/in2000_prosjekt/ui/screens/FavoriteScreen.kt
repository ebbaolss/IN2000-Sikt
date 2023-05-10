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
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
import com.example.in2000_prosjekt.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
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
            Scaffold(bottomBar = {
                Sikt_BottomBar(
                    onNavigateToMap,
                    onNavigateToFav,
                    onNavigateToInfo,
                    onNavigateToSettings,
                    favorite = true,
                    info = false,
                    map = false,
                    settings = false
                )
            }) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .paint(
                        painterResource(id = R.drawable.map_backround),
                        contentScale = ContentScale.FillBounds
                    ), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Laster inn..", fontWeight = FontWeight.Bold, fontSize = 24.sp, color = Sikt_hvit, modifier = Modifier
                        .background(
                            Color.Black.copy(alpha = 0.6f)
                        ))
                }
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

        Box(modifier = Modifier
            .paint(
                painterResource(id = R.drawable.map_backround),
                contentScale = ContentScale.FillBounds
            )
            .fillMaxSize()) {
            LazyColumn(
                contentPadding = PaddingValues(20.dp)
            ) {
                if (allFavorites.size != 0) {
                    Sikt_Favorite_card(
                        (favoriteUiState as FavoriteUiState.Success).locationF,
                        (favoriteUiState as FavoriteUiState.Success).nowCastF,
                        (favoriteUiState as FavoriteUiState.Success).alertListF,
                        allFavorites,
                        viewModel,
                        onNavigateToMap,
                        onNavigateToFav,
                        onNavigateToInfo,
                        onNavigateToSettings

                    )
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteEmpty(
    onNavigateToMap: () -> Unit, onNavigateToFav: () -> Unit, onNavigateToInfo: () -> Unit, onNavigateToSettings: () -> Unit
){
    Scaffold(bottomBar = { Sikt_BottomBar(onNavigateToMap, onNavigateToFav, onNavigateToSettings, onNavigateToSettings, favorite = true, map = false, info = false, settings = false)}
    ) {
        Box(
            modifier = Modifier
                .paint(
                    painterResource(id = R.drawable.map_backround),
                    contentScale = ContentScale.FillBounds
                )
                .fillMaxSize())
        {
            Card(
                colors = CardDefaults.cardColors(Sikt_lyseblå),
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 40.dp),
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    //verticalArrangement = Arrangement.Center,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "Ingen favoritter enda...",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Hjertemarker en fjelltopp for å legge den til i favorittene dine",
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}





