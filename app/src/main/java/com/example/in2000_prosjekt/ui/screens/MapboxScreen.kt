package com.example.in2000_prosjekt.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.example.in2000_prosjekt.ui.components.Sikt_BottomBar
import com.example.in2000_prosjekt.ui.components.Sikt_BottomSheet
import com.mapbox.geojson.Point
import com.mapbox.maps.dsl.cameraOptions
import com.mapbox.maps.plugin.gestures.OnMapClickListener
import com.mapbox.maps.plugin.gestures.addOnMapClickListener
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.material3.Text

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mapbox.maps.MapView
import androidx.compose.ui.text.input.ImeAction
import com.example.in2000_prosjekt.ui.AppUiState
import com.example.in2000_prosjekt.ui.MapInfo
import com.example.in2000_prosjekt.ui.MapViewModel
import com.example.in2000_prosjekt.ui.SearchUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowMap(onNavigateToMap: () -> Unit, onNavigateToFav: () -> Unit, onNavigateToSettings: () -> Unit, onNavigateToRules: () -> Unit) {
    Scaffold(
        topBar = {SearchBar(MapViewModel())},
        bottomBar = { Sikt_BottomBar(onNavigateToMap, onNavigateToFav, onNavigateToRules, onNavigateToSettings,
            favoritt = false, settings = false, rules = false, map = true) })
    {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                AndroidView(
                    modifier = Modifier,
                    factory = { createFactoryMap(it) }
                )
            }
            Sikt_BottomSheet()

        }
}
fun createFactoryMap(xt: Context) : MapView {
    return MapView(xt).apply {
        val mapboxMap = getMapboxMap()
        mapboxMap.loadStyleUri("mapbox://styles/elisabethb/clf6t1z9z00b101pen0rvc1fu/draft") {
            cameraOptions{
                zoom(19.9)
            }
            //Sikt_BottomSheet()
        }
        mapboxMap.addOnMapClickListener(onMapClickListener = OnMapClickListener {point ->
            onMapClick(point)
        })
    }
}
fun onMapClick(point: Point): Boolean {
    Log.d("Coordinate", point.toString())
    return@onMapClick true
}

//var optionMountains = mutableListOf<String>() //maks 3 elementer, de siste 3 søkt på
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(viewModel: MapViewModel){ //mangler at tastaturet forsvinner når man trykker på kart

    val mapUiState = viewModel.appUiState.collectAsState()

//    _appUistate.update {
//        //body
//    }

    var input by remember { mutableStateOf("") }
    var isTextFieldFocused by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    var textFieldState: Boolean

    //optionMountains.add("Galdhøpiggen")
//    optionMountains.add("Snøhetta")
//    optionMountains.add("Glittertind")

    LazyColumn(
        modifier = Modifier
            .padding(
                start = 20.dp,
                end = 20.dp,
                top = 20.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        item {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    //.align(Alignment.TopCenter)
                    .onFocusChanged {
                        isTextFieldFocused = it.isFocused
                        Log.d("isTextFieldFocused", "$isTextFieldFocused")
                    },

                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                        isTextFieldFocused = false
                        Log.d("Closed Keyboard", "")
                    }),
                //KeyboardOptions = KeyboardOptions.Default,
                //KeyboardActions = KeyboardActions(),
                //KeyboardCapitalization.Words – Capitalize the first character of every word,
                //shape = RoundedCornerShape(8.dp),
                value = input,
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
                onValueChange = { input = it },
                placeholder = { Text(text = "Søk her") },
                label = {
                    Text(
                        text = "Søk her",
                        textAlign = TextAlign.Center
                    )
                },
                trailingIcon = { //søkeknappen
                    Button(
                        onClick = {
                            //bruker har skrevet inn noe og trykket søk,
                            // nå skal api-et kalles på:
                            //suggestionSearch(teksten skrevet inn)

                            //etter dette skal options dukke opp
                            //når det er success så skal det dukke opp på skjermen
                            //nå kan bruker velge alternativ
                            suggestionSearch(input)

                            optionList(input) //sende med lista også? funker denne?
                            input = ""
                            focusManager.clearFocus() //riktig? hva gjør denne

                        }, colors = ButtonDefaults.buttonColors(Color.White),
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            tint = Color.Black,
                            contentDescription = "Search icon",
                            modifier = Modifier.size(24.dp) //trengs denne?
                        )
                    }
                }
            )
        }
        if (isTextFieldFocused) {
            //items(liste med forslagene vi har fått fra api) ? så før det er søkt noe skal siste søk komme opp, ellers byttes
            //lista til forslag.
            items(mapUiState) { mountain -> //disse må gjøres til buttons/clickable

                Row(
                    //.clickable
                    //hvis den er trykket:
                    //1. retrivesearch() med mapid

                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .height(40.dp)
                        .padding(start = 20.dp, top = 9.dp, bottom = 7.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = mountain,
                        fontSize = 15.sp,
                        modifier = Modifier
                            .weight(1f),
                        textAlign = TextAlign.Start,
                    )
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 15.dp)
                    )
                }
            }
        }
    }
}

//@Composable
//fun SearchBarCheckSuccess(apiViewModel: APIViewModel = viewModel()) {
//    val appUiState by apiViewModel.appUiState.collectAsState()
//
//    when(appUiState){
//        is AppUiState.LoadingMapSearch -> Text (text = "loading...", fontSize = 30.sp)
//        is AppUiState.ErrorMapSearch -> {
//            Text (text = "loading...", fontSize = 30.sp)
//        }
//        is AppUiState.SuccessMapSearch -> {
//            retrieveSearch(
//                mapSearchInfo = (appUiState as AppUiState.SuccessMapSearch).mapSearchF,
//            )
//        }
//        else -> {
//            AppUiState.ErrorMapSearch
//        }
//    }
//}
fun suggestionSearch(searchString : String) {
    //da kaller vi på apiet med den stringen getMap(stringen)
    //da får vi

}
fun retrieveSearch(mapSearchInfo: MapInfo,) {

    //her skal man kalle på første api og få hente ut mapbox_id, dette kan vi bruke til å kalle på nytt api med id-en

}
fun optionList(result : String) {


    //lat som jeg har en liste med alle fjelltoppene:
//
//    if (!optionMountains.contains(result)) { //får man  ikke nullpointerexception?
//        optionMountains[2] = optionMountains[1] //?: ""
//        optionMountains[1] = optionMountains[0]
//        optionMountains[0] = result
//    }

    var counter = 1
    optionMountains.forEach{
        optionMountains[counter] = it
        counter++
    }
    optionMountains[0] = result
    optionMountains = optionMountains.subList(0, 2)

}


