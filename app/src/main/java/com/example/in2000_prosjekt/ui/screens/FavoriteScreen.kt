package com.example.in2000_prosjekt.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
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
import com.example.in2000_prosjekt.ui.components.siktFavoriteCard
import com.example.in2000_prosjekt.database.Favorite
import com.example.in2000_prosjekt.ui.uistate.FavoriteUiState
import com.example.in2000_prosjekt.ui.FavoriteViewModel
import com.example.in2000_prosjekt.ui.theme.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavoriteScreen(
    onNavigateToMap: () -> Unit,
    onNavigateToFav: () -> Unit,
    onNavigateToInfo: () -> Unit,
    onNavigateToSettings: () -> Unit,
    viewModel: FavoriteViewModel
){

    val favoriteUiState by viewModel.favUiState.collectAsState()
    val allFavorites by viewModel.allFavorites.observeAsState(listOf())

    if(allFavorites.isNotEmpty()){
        viewModel.update()
    } else {
        viewModel.updateEmpty()
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
                Box(
                    modifier = Modifier
                        .paint(
                            painterResource(id = R.drawable.map_backround),
                            contentScale = ContentScale.FillBounds
                        )
                        .fillMaxSize(), contentAlignment = Alignment.Center)
                {
                    Card(
                        colors = CardDefaults.cardColors(Sikt_lightblue),
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 40.dp),
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(20.dp)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text(
                                text = "Laster inn...",
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        is FavoriteUiState.Error -> {
            FavoriteScreenError(
                onNavigateToMap,
                onNavigateToFav, onNavigateToInfo,
                onNavigateToSettings
            )
        }
        is FavoriteUiState.Success -> {
            if(allFavorites.isNotEmpty()){
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


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavoriteScreenSuccess(
    onNavigateToMap: () -> Unit, onNavigateToFav: () -> Unit, onNavigateToInfo: () -> Unit, onNavigateToSettings: () -> Unit, viewModel: FavoriteViewModel, allFavorites : List<Favorite>
) {
    val favoriteUiState by viewModel.favUiState.collectAsState()

    val loc = (favoriteUiState as FavoriteUiState.Success).locationF
    val now = (favoriteUiState as FavoriteUiState.Success).nowCastF
    val al = (favoriteUiState as FavoriteUiState.Success).alertListF

    Scaffold(bottomBar = { Sikt_BottomBar(onNavigateToMap, onNavigateToFav, onNavigateToInfo, onNavigateToSettings, favorite = true, info = false, map = false, settings = false)}
    ) {

        Box(modifier = Modifier
            .paint(
                painterResource(id = R.drawable.map_backround),
                contentScale = ContentScale.FillBounds
            )
            .fillMaxSize()) {
            LazyColumn(
                contentPadding = PaddingValues(20.dp), modifier = Modifier.padding(top = 0.dp, bottom = 70.dp)
            ) {
                if (allFavorites.isNotEmpty()) {
                    if ( loc.size == allFavorites.size && now.size == allFavorites.size && al.size == allFavorites.size){
                        siktFavoriteCard(
                            loc,
                            now,
                            al,
                            allFavorites,
                            viewModel,
                            onNavigateToMap,
                            onNavigateToFav,
                            onNavigateToInfo,
                            onNavigateToSettings
                        )
                    } else {
                        viewModel.update()
                    }
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavoriteEmpty(
    onNavigateToMap: () -> Unit, onNavigateToFav: () -> Unit, onNavigateToInfo: () -> Unit, onNavigateToSettings: () -> Unit
){
    Scaffold(bottomBar = { Sikt_BottomBar(onNavigateToMap, onNavigateToFav, onNavigateToInfo, onNavigateToSettings, favorite = true, map = false, info = false, settings = false)}
    ) {
        Box(
            modifier = Modifier
                .paint(
                    painterResource(id = R.drawable.map_backround),
                    contentScale = ContentScale.FillBounds
                )
                .fillMaxSize(), contentAlignment = Alignment.Center)
        {
            Card(
                colors = CardDefaults.cardColors(Sikt_lightblue),
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 40.dp),
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
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




