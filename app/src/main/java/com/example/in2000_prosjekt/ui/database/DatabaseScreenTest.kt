package com.example.in2000_prosjekt.ui.database

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatabaseScreenTest(viewModel: FavoriteViewModel = viewModel()) {

    val allFavorites by viewModel.allFavorites.observeAsState(listOf())
    val searchFavorites by viewModel.searchFavorites.observeAsState(listOf())

    MainFavoriteScreen(
        allFavorites = allFavorites,
        searchFavorites = searchFavorites,
        viewModel = viewModel
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainFavoriteScreen(
    allFavorites: List<Favorite>,
    searchFavorites: List<Favorite>,
    viewModel: FavoriteViewModel
){

    var favLongtitudeString by remember { mutableStateOf("0.0") }
    var favLatitudeString by remember { mutableStateOf("0.0") }
    var searching by remember { mutableStateOf(false) }
    var favLongtitude by remember { mutableStateOf( favLongtitudeString.toDouble())}
    var favLatitude by remember { mutableStateOf(favLatitudeString.toDouble()) }

    Column(
        horizontalAlignment = CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Log.d("DATABASESCREEN", "Før textfield")
        TextField(
            modifier = Modifier
                .padding()
                .wrapContentSize(),
            value = favLongtitudeString,
            onValueChange = { favLongtitudeString = it
                            favLongtitude = it.toDouble()
                            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = {Text("Longtitude")}
        )

        TextField(
            modifier = Modifier
                .padding()
                .wrapContentSize(),
            value = favLatitudeString,
            onValueChange = {favLatitudeString = it
                favLatitude = it.toDouble()
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text("Latitude")}
        )

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Button(onClick = {
                Log.d("ADD", "Kommer inn i Add med long: $favLongtitude, lat_ $favLatitude")
                //if(favLatitude!= 0.0 || favLongtitude!=0.0){
                /*
                viewModel.addFavorite(
                        Favorite(
                            favLongtitude,
                            favLatitude,

                        )
                    )
                    searching = false

                 */
                //}
            }) {
                Text("Add")
            }

            Button(onClick = {
                searching = true
                viewModel.findFavorite(favLongtitude, favLatitude)
            }) {
                Text("Search")
            }

            Button(onClick = {
                searching = false
                viewModel.deleteFavorite(favLongtitude,favLatitude)
            }) {
                Text("Delete")
            }

            Button(onClick = {
                searching = false
                favLatitude = 0.0
                favLongtitude = 0.0
            }) {
                Text("Clear")
            }
        }
        Text(text = "ANTALL FAVORITTER: ${allFavorites.size}")
        allFavorites.forEach {
            Text(text = "FAVORIT = lat : ${it.latitude} , long: ${it.longtitude}")
        }
    }
}