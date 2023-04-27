package com.example.in2000_prosjekt.ui.screens

import android.content.Context
import android.graphics.Color
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.example.in2000_prosjekt.ui.components.Sikt_BottomBar
import com.example.in2000_prosjekt.ui.components.Sikt_BottomSheet
import com.mapbox.geojson.Point
import com.mapbox.bindgen.Expected
import com.mapbox.geojson.Feature
import com.mapbox.geojson.Point
import com.mapbox.maps.*
import com.mapbox.maps.dsl.cameraOptions
import com.mapbox.maps.extension.style.expressions.dsl.generated.eq
import com.mapbox.maps.extension.style.layers.generated.CircleLayer
import com.mapbox.maps.extension.style.layers.generated.circleLayer
import com.mapbox.maps.extension.style.layers.getLayerAs
import com.mapbox.maps.extension.style.sources.generated.vectorSource
import com.mapbox.maps.extension.style.style
import com.mapbox.maps.plugin.gestures.OnMapClickListener
import com.mapbox.maps.plugin.gestures.addOnMapClickListener
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.foundation.lazy.items
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.in2000_prosjekt.ui.AppUiState
import com.example.in2000_prosjekt.ui.MapInfo
import com.example.in2000_prosjekt.ui.MapViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter") //burde unngå disse så langt det lar seg gjøre, men her måtte vi for å slippe padding
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
                factory = {
                    createFactoryMap(it)
                }
            )
        }

        Sikt_BottomSheet()
    }
}

fun createFactoryMap(xt: Context) : MapView {
    return MapView(xt).apply {
        val mapboxMap = getMapboxMap()

        mapboxMap.loadStyle(
            style(styleUri = Style.OUTDOORS) {
                +vectorSource(id = "STREETS_V8") {
                    url("mapbox://mapbox.mapbox-streets-v8")
                }
                +circleLayer(layerId = "LAYER_ID", sourceId = "STREETS_V8") {
                    sourceLayer("natural_label")

                    // Removing all natural labels points that are not mountains e.g. lakes
                    filter(
                        eq {
                            get { literal("maki") }
                            literal("mountain")
                        }
                    )

                    // Styling mountain points
                    circleRadius(10.0)
                    circleColor(Color.YELLOW)
                    circleOpacity(1.0)
                    circleStrokeColor(Color.BLACK)
                }
            }
        )

        mapboxMap.setCamera(
            cameraOptions {
                zoom(13.0)
                // Koordinatene til Glittertind
                center(Point.fromLngLat(8.557801680731075,61.651356077904666))
            }
        )

        mapboxMap.addOnMapClickListener(onMapClickListener = OnMapClickListener { point ->
            onMapClick(point, mapboxMap)
        })
    }
}



// Definerer hva som skal skje når brukeren trykker på kartet
fun onMapClick(point: Point, mapboxMap: MapboxMap): Boolean {
    Log.d("Coordinate", point.toString())
    mapboxMap.queryRenderedFeatures(
        RenderedQueryGeometry(ScreenBox(
            ScreenCoordinate(
                mapboxMap.pixelForCoordinate(point).x - 10.0,
                mapboxMap.pixelForCoordinate(point).y - 10.0
            ),
            ScreenCoordinate(
                mapboxMap.pixelForCoordinate(point).x + 10.0,
                mapboxMap.pixelForCoordinate(point).y + 10.0
            )
        )),
        RenderedQueryOptions(listOf("LAYER_ID"), null)
    ) { it ->
        onFeatureClicked(it) { feature ->
            if (feature.id() != null) {

                Log.d("Feature", feature.getStringProperty("name").toString() + ". Elevation: " + feature.getStringProperty("elevation_m").toString() + " m.o.h.")

                mapboxMap.getStyle() { style ->
                    val layer = style.getLayerAs<CircleLayer>("LAYER_ID")!!
                    layer.circleColor(Color.BLUE)
                }
            }
        }
    }
    return true
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(viewModel: MapViewModel){
    //MANGLER :
    // at tastaturet forsvinner når man trykker på kart
    // at det slutter å blinke i searchfeltet når man trykker på kartet
    //at man kan søke med enter, keylistener greie

    val mapUiState = viewModel.appUiState.collectAsState()

    var input by remember { mutableStateOf("") }
    var isTextFieldFocused by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

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
                    .onFocusChanged {
                        println("focus changes\n")
                        isTextFieldFocused = it.isFocused
                    },

                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                        isTextFieldFocused = false
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
                            suggestionSearch(viewModel, input)


                            //etter dette skal options dukke opp
                            //når det er success så skal det dukke opp på skjermen
                            //nå kan bruker velge alternativ

                            input = ""

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

            items(mapUiState.value.optionMountains.keys.toList()) { mountain -> //disse må gjøres til buttons/clickable

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .height(40.dp)
                        .padding(start = 20.dp, top = 9.dp, bottom = 7.dp)
                        .clickable( enabled = true, onClick = {

                            //oppdatere lista
                            viewModel.updateRecentSearch(mountain)

                            //neste er å kalle på nytt api retrieveSearch()
                            //lag en funk i view og repository
                            //lage ny map.kt
                            //lage ny data class i uistate
                            //hente koordinater fra uistate
                            //bruke de til å få opp card
                            //finspekkeri

                            focusManager.clearFocus()

                            println(mapUiState.value.recentSearch)
                        }),

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
fun suggestionSearch(apiViewModel: MapViewModel, searchString : String) {
    //da kaller vi på apiet med den stringen
    //oppdaterer lista i uistate, så nå har vi liste med bare mountain poi
    apiViewModel.getDataSearch(searchString)
}
fun retrieveSearch(mapSearchInfo: MapInfo,) {

    //her skal man kalle på det nadre apiet med mapbox_id
    //dette må lages, men så og si bare å kopiere det man gjorde på getDataSearch
    //da får man oppdatert koordinater, lat og long

}


fun onFeatureClicked(
    expected: Expected<String, List<QueriedFeature>>,
    onFeatureClicked: (Feature) -> Unit
) {
    if (expected.isValue && expected.value?.size!! > 0) {
        expected.value?.get(0)?.feature?.let { feature ->
            onFeatureClicked.invoke(feature)
        }
    }
}