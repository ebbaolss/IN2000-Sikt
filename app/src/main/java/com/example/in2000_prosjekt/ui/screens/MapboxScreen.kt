package com.example.in2000_prosjekt.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.in2000_prosjekt.ui.APIViewModel
import com.example.in2000_prosjekt.ui.uistate.AppUiState
import com.example.in2000_prosjekt.ui.components.Sikt_BottomBar
import com.example.in2000_prosjekt.ui.components.*
import com.example.in2000_prosjekt.ui.components.siktLocationcard
import com.example.in2000_prosjekt.ui.MapViewModel
import com.mapbox.bindgen.Expected
import com.mapbox.geojson.Feature
import com.mapbox.geojson.Point
import com.mapbox.maps.*
import com.mapbox.maps.dsl.cameraOptions
import com.mapbox.maps.plugin.gestures.addOnMapClickListener
import androidx.compose.runtime.Composable
import android.view.KeyEvent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.draw.paint
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.input.ImeAction
import com.example.in2000_prosjekt.R
import com.example.in2000_prosjekt.ui.FavoriteViewModel
import com.example.in2000_prosjekt.ui.*
import com.example.in2000_prosjekt.ui.theme.Sikt_lightblue
import com.example.in2000_prosjekt.ui.uistate.MapUiState


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ShowMap(
    onNavigateToMap: () -> Unit,
    onNavigateToFav: () -> Unit,
    onNavigateToInfo: () -> Unit,
    onNavigateToSettings: () -> Unit,
    mapViewModel: MapViewModel,
    apiViewModel: APIViewModel,
    favoriteViewModel: FavoriteViewModel
) {

    val cameraOptionsUiState by mapViewModel.cameraOptionsUiState.collectAsState()
    val mountainUiState by mapViewModel.mountainUiState.collectAsState()
    val appUiState by apiViewModel.appUiState.collectAsState()

    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            SearchBar(mapViewModel, {focusManager.clearFocus()}, {mapViewModel.locationCardState.value =false}) {  }
        },
        bottomBar = {
            Sikt_BottomBar(
                onNavigateToMap,
                onNavigateToFav,
                onNavigateToInfo,
                onNavigateToSettings,
                favorite =false,
                info =false,
                map =true,
                settings =false
            )
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AndroidView(
                modifier = Modifier,
                factory = {
                    mapViewModel.createFactoryMap(it, cameraOptionsUiState)
                    val mapboxMap = mapViewModel.mapView.getMapboxMap()

                    mapboxMap.addOnCameraChangeListener(onCameraChangeListener = {
                        focusManager.clearFocus()
                        mapViewModel.locationCardState.value=false
                    })

                    mapboxMap.addOnMapClickListener(onMapClickListener = { point ->
                        focusManager.clearFocus()
                        mapViewModel.locationCardState.value =false

                        onMapClick(point, mapboxMap, mapViewModel, apiViewModel) {
                            mapViewModel.locationCardState.value=true
                        }
                    })

                    mapboxMap.addOnCameraChangeListener(onCameraChangeListener = {
                        mapViewModel.onCameraChange(mapboxMap)
                    })

                    mapViewModel.mapView
                },
                update = {
                    // pull cameraSettings from the UiState
                    // Camera settings
                    it.getMapboxMap().setCamera(
                        cameraOptions {
                            // Henter kamerakoordinater fra UiState
                            val lng = cameraOptionsUiState.currentScreenLongitude
                            val lat = cameraOptionsUiState.currentScreenLatitude
                            val zoom = cameraOptionsUiState.currentScreenZoom

                            center(Point.fromLngLat(lng, lat))
                            zoom(zoom)
                        }
                    )
                }
            )

            if (mapViewModel.locationCardState.value){
                when(appUiState) {
                    is AppUiState.Loading -> {
                        Scaffold(bottomBar = {
                            Sikt_BottomBar(
                                onNavigateToMap,
                                onNavigateToFav,
                                onNavigateToInfo,
                                onNavigateToSettings,
                                favorite =false,
                                info =false,
                                map =true,
                                settings =false
                            )
                        }, modifier = Modifier.fillMaxSize()) {
                            Box(
                                modifier = Modifier
                                    .paint(
                                        painterResource(id = R.drawable.map_backround),
                                        contentScale = ContentScale.FillBounds
                                    )
                                    .fillMaxSize(), contentAlignment = Alignment.Center
                            )
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
                    }
                    is AppUiState.Error -> {
                        AppScreenError( onNavigateToMap,
                            onNavigateToFav,onNavigateToSettings,
                            onNavigateToInfo)
                    }
                    is AppUiState.Success -> {
                        LazyColumn(
                            modifier = Modifier
                                .padding(top = 70.dp, bottom = 70.dp, start = 20.dp, end = 20.dp)
                        ) {
                            siktLocationcard(
                                mountainUiState,
                                (appUiState as AppUiState.Success).locationF,
                                (appUiState as AppUiState.Success).nowCastF,
                                (appUiState as AppUiState.Success).alertListF,
                                favoriteViewModel
                            )
                        }
                    }
                }
            }
        }
    }
}

// When panning and zooming the map view the center screen coordinates and camera settings are updated to the cameraUiState.
// funOnScreenGesture(cameraOptions: MapboxCameraOptions)
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

// Definerer hva som skal skje når brukeren trykker på kartet
fun onMapClick(point: Point, mapboxMap: MapboxMap, mapViewModel: MapViewModel, apiViewModel: APIViewModel, onClick : () -> Unit) : Boolean {

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
        RenderedQueryOptions(listOf("MOUNTAINS_DATALAYER"),null)
    ) {
        onFeatureClicked(it) { feature ->
            if (feature.id() !=null) {
                val name = feature.getStringProperty("name")
                val elevation = feature.getStringProperty("elevation_m").toInt()
                val pointMap = feature.geometry()as Point
                        val latitude =  pointMap.latitude().toString()
                val longitude = pointMap.longitude().toString()

                // Saving a clicked mountain to the UiState through the view model
                mapViewModel.updateMountain(MapUiState.Mountain(name, latitude, longitude, elevation))

                apiViewModel.getAll(latitude, longitude, "$elevation")

                onClick()

            }
        }
    }
    return true
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(mapViewModel: MapViewModel, onSearch : () -> Unit, clearFocus : () -> Unit, onClick : () -> Unit){

    val mapUiState = mapViewModel.mapInfoUiState.collectAsState()

    var input by remember { mutableStateOf("") }
    var isTextFieldFocused by remember { mutableStateOf(false) }
    val recentSearchHashmap : HashMap<String, String> = hashMapOf()
    var showRecent=true

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
                        isTextFieldFocused= it.isFocused
                    }
                    .onKeyEvent {
                        if(it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_ENTER) {
                            input = input.replace("ø", "oe")
                            input=input.replace("æ", "ae")
                            input=input.replace("å", "aa")
                            if(input.length > 1) {
                                mapViewModel.getDataSearch(input)
                                showRecent=false
                            }
                            input= ""
                        }
                        false
                    },

                singleLine =true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        input =input.replace("ø", "oe")
                        input=input.replace("æ", "ae")
                        input= input.replace("å", "aa")
                        if(input.length > 1) {
                            mapViewModel.getDataSearch(input)
                            showRecent=false
                        }
                        input= ""
                    }),
                value =input,
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
                onValueChange = {

                    input= it
                },
                placeholder = { Text(text = "Søk her") },
                label = {
                    Text(
                        text = "Søk her",
                        textAlign = TextAlign.Center
                    )
                },
                trailingIcon = {//søkeknappen
                    Button(
                        onClick = {

                            if(input.length > 1) {
                                showRecent=false
                                input=input.replace("ø", "oe")
                                input= input.replace("æ", "ae")
                                input=input.replace("å", "aa")
                                mapViewModel.getDataSearch(input)
                            }

                            input= ""

                        }, colors = ButtonDefaults.buttonColors(Color.White),
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            tint = Color.Black,
                            contentDescription = "Search icon",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            )
        }

        if(isTextFieldFocused &&showRecent) {

            items(mapUiState.value.recentSearch) { mountain ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .height(40.dp)
                        .padding(start = 20.dp, top = 9.dp, bottom = 7.dp)
                        .clickable(enabled =true, onClick = {

                            mapViewModel.updateRecentSearch(mountain,false)

                            mapViewModel.showSelectedMountain(
                                recentSearchHashmap[mountain]!!
                            )

                            clearFocus()
                            showRecent=true
                            onSearch()
                            onClick()
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
                        contentDescription =null,
                        modifier = Modifier
                            .padding(end = 15.dp)
                            .clickable(
                                onClick = {
                                    mapViewModel.updateRecentSearch(mountain,true)
                                }
                            )
                    )
                }
            }
        }
        if(isTextFieldFocused&& !showRecent) {
            if(mapUiState.value.optionMountains.size == 0){
                item {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .height(40.dp)
                            .padding(start = 20.dp, top = 9.dp, bottom = 7.dp),

                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = "Finner ikke topp",
                            fontSize = 15.sp,
                            modifier = Modifier
                                .weight(1f),
                            textAlign = TextAlign.Start,
                        )
                    }
                }
            }else{

                items(mapUiState.value.optionMountains.keys.toList()) { mountain ->

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .height(40.dp)
                            .padding(start = 20.dp, top = 9.dp, bottom = 7.dp)
                            .clickable(enabled =true, onClick = {

                                mapViewModel.updateRecentSearch(mountain,false)

                                recentSearchHashmap[mountain] =
                                    mapUiState.value.optionMountains[mountain]!!

                                mapViewModel.showSelectedMountain(
                                    recentSearchHashmap[mountain]!!
                                )

                                clearFocus()
                                showRecent=true
                                onSearch()
                                onClick()

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
                    }
                }
            }
        }
    }
}