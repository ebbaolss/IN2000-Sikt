package com.example.in2000_prosjekt.ui.database

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.in2000_prosjekt.ui.theme.Sikt_hvit
import com.example.in2000_prosjekt.ui.theme.Sikt_orange
import androidx.lifecycle.viewmodel.compose.viewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatabaseScreenTest(viewModel: FavoriteViewModel = viewModel()) {

    val listState = rememberLazyListState()
    val fav1 = Favorite("coordinate", 1.1, 2.2)
    var valgt : Favorite

    Scaffold() {
        Column(
            Modifier
                .fillMaxSize()
                .background(Sikt_orange),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .background(Sikt_hvit),
            ) {
                Text(text = "Database", fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.TopCenter))
                val favList: List<Favorite> by viewModel.favoriteList.observeAsState(initial = listOf())

                if (favList.isNotEmpty()) {
                    Text(text = "Favorites found")
                    LazyColumn(state = listState) {
                        items(favList.size) { fav ->
                            Text(text = "Favoritt")
                            favList.toString()
                        }
                    }
                }
                else {
                    Spacer(modifier = Modifier.height(50.dp))
                    Text(text = "No favorites")
                }
            }
            Button(onClick = {
                val nyFav = Favorite("12345", 3.3, 4.4)
                viewModel.addFavorite(nyFav)
            }) {
                Text(text = "Legg til favoritt")
            }

            Button(onClick = { viewModel.findByCoordinate("1234") }) {
                Text(text = "Find by coordinate")
                valgt = viewModel.foundFavorite.observeAsState().value!!
                var coordinate = valgt.coordinates.toString()
                var lon = valgt.longitude.toString()
                var lat = valgt.latitude.toString()

            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showSystemUi = true)
@Composable
fun PreviewDatabasePage() {

}