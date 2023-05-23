package com.example.in2000_prosjekt.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
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
import com.example.in2000_prosjekt.ui.AppUiState
import com.example.in2000_prosjekt.ui.components.Sikt_BottomBar
import com.example.in2000_prosjekt.ui.components.*
import com.example.in2000_prosjekt.ui.components.siktLocationcard
import com.example.in2000_prosjekt.database.MapViewModel
import com.example.in2000_prosjekt.ui.uistate.MapUiState
import com.mapbox.bindgen.Expected
import com.mapbox.geojson.Feature
import com.mapbox.geojson.Point
import com.mapbox.maps.*
import com.mapbox.maps.dsl.cameraOptions
import com.mapbox.maps.extension.style.expressions.dsl.generated.eq
import com.mapbox.maps.extension.style.layers.generated.circleLayer
import com.mapbox.maps.extension.style.sources.generated.vectorSource
import com.mapbox.maps.extension.style.style
import com.mapbox.maps.plugin.compass.compass
import com.mapbox.maps.plugin.gestures.addOnMapClickListener
import com.mapbox.maps.plugin.scalebar.scalebar
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.draw.paint
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.mapbox.maps.MapView
import androidx.compose.ui.text.input.ImeAction
import com.example.in2000_prosjekt.R
import com.example.in2000_prosjekt.database.FavoriteViewModel
import com.example.in2000_prosjekt.ui.*
import com.example.in2000_prosjekt.ui.theme.Sikt_lightblue


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

    var locationCardState by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            SearchBar(mapViewModel, apiViewModel, {focusManager.clearFocus()}, {locationCardState = true}) { /* locationCardState = true */ }
        },
        bottomBar = {
            Sikt_BottomBar(
                onNavigateToMap,
                onNavigateToFav,
                onNavigateToInfo,
                onNavigateToSettings,
                favorite = false,
                info = false,
                map = true,
                settings = false
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
                    val map = createFactoryMap(it, cameraOptionsUiState)
                    val mapboxMap = map.getMapboxMap()

                    mapboxMap.addOnCameraChangeListener(onCameraChangeListener = {
                        focusManager.clearFocus()
                        locationCardState = false
                    })

                    mapboxMap.addOnMapClickListener(onMapClickListener = { point ->
                        Log.d("LocationCardState", "$locationCardState")
                        focusManager.clearFocus()
                        locationCardState = false

                        onMapClick(point, mapboxMap, mapViewModel, apiViewModel) {
                            locationCardState = true
                            Log.d("Location Card State", "$locationCardState")
                        }

                    })
                    mapboxMap.addOnCameraChangeListener(onCameraChangeListener = {
                        Log.d("CameraChangeListener", "invoked")
                        onCameraChange(mapboxMap, mapViewModel)
                    })

                    map
                },
                update = {
                    // pull cameraSettings from the UiState
                    // Camera settings
                    it.getMapboxMap().setCamera(
                        cameraOptions {
                            // Henter kamerakoordinater fra UiState
                            val lng = cameraOptionsUiState.currentScreenLongitude
                            val lat = cameraOptionsUiState.currentScreenLatitude
                            //val zoom = cameraOptionsUiState.currentScreenZoom

                            Log.d("Update Camera Coordinates", "Lng: $lng, Lat: $lat")

                            center(Point.fromLngLat(lng, lat))
                        }
                    )
                }
            )

            if (locationCardState){
                when (appUiState) {
                    is AppUiState.Loading -> {
                        Scaffold(bottomBar = {
                            Sikt_BottomBar(
                                onNavigateToMap,
                                onNavigateToFav,
                                onNavigateToInfo,
                                onNavigateToSettings,
                                favorite = false,
                                info = false,
                                map = true,
                                settings = false
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
                                        //verticalArrangement = Arrangement.Center,
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
                        Log.d("Location Card", "Initialising")

                        LazyColumn(
                            modifier = Modifier
                                //.fillMaxSize()
                                .padding(top = 70.dp, bottom = 70.dp, start = 20.dp, end = 20.dp)
                        ) {
                            // Må legge inn listen over fjelltopper i nærheten:
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

fun onCameraChange(mapboxMap: MapboxMap, viewModel: MapViewModel) {
    val screenCenter = mapboxMap.cameraState.center
    val zoom = mapboxMap.cameraState.zoom
    viewModel.updateCameraPosition(screenCenter)
    viewModel.updateCameraZoomState(zoom)
    Log.d("onCameraChange", "Coordinates $screenCenter, Zoom level: $zoom")
}

fun createFactoryMap(xt: Context, CameraOptionsUiState: MapUiState.MapboxCameraOptions) : MapView {

    val mapView = MapView(xt).apply {
        val mapboxMap = getMapboxMap()

        mapboxMap.loadStyle(
            // Declares map style
            style(styleUri = Style.OUTDOORS) {

                // Adding data layer source to rendered map
                +vectorSource(id = "STREETS_V8") {
                    url("mapbox://mapbox.mapbox-streets-v8")
                }

                // Creates an interactable point layer on top of style layer
                +circleLayer(layerId = "MOUNTAINS_DATALAYER", sourceId = "STREETS_V8") {
                    // natural label er et lag i STREETS_V8 datasettet til Mapbox og inneholder naturobjekter som fjell, innsjøer etc.
                    sourceLayer("natural_label")

                    // Filtering out all natural labels points that are not marked with the mountains icon
                    filter(
                        eq {
                            get { literal("maki") }
                            literal("mountain")
                        }
                    )

                    circleOpacity(0.0)
                }
            }
        )

        // Camera settings
        mapboxMap.setCamera(
            cameraOptions {
                zoom(CameraOptionsUiState.currentScreenZoom)
                // Koordinatene til Glittertind
                center(Point.fromLngLat(
                    CameraOptionsUiState.currentScreenLongitude,
                    CameraOptionsUiState.currentScreenLatitude
                ))
            }
        )
    }


    // Editing compass settings, so that searchbar does not block compass
    mapView.compass.updateSettings {
        marginTop = 250F
    }

    // Editing scalebar, so that searchbar does not block scalebar
    mapView.scalebar.updateSettings {
        marginTop = 250F
    }

    return mapView
}

// Definerer hva som skal skje når brukeren trykker på kartet
fun onMapClick(point: Point, mapboxMap: MapboxMap, mapViewModel: MapViewModel, apiViewModel: APIViewModel, onClick : () -> Unit) : Boolean {
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
        RenderedQueryOptions(listOf("MOUNTAINS_DATALAYER"), null)
    ) {
        onFeatureClicked(it) { feature ->
            if (feature.id() != null) {
                val name = feature.getStringProperty("name")
                val elevation = feature.getStringProperty("elevation_m").toInt()
                val point = feature.geometry() as Point
                val latitude =  point.latitude().toString()
                val longitude = point.longitude().toString()

                // Saving a clicked mountain to the UiState through the view model
                mapViewModel.updateMountain(MapUiState.Mountain(name, latitude, longitude, elevation))

                apiViewModel.getAll(latitude, longitude, "$elevation")

                onClick()

                // DEBUGGING
                Log.d("Map Feature Clicked", feature.toString())
                Log.d("Feature Contents:", " \nMountain Name:" + feature.getStringProperty("name").toString() +
                        "\nElevation: " + feature.getStringProperty("elevation_m").toString() + " m.o.h.\n" +
                        "Point: " + feature.geometry().toString()
                )
            }
        }
    }
    return true
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

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(viewModel: MapViewModel, apiViewModel: APIViewModel, onSearch : () -> Unit, clearFocus : () -> Unit, onClick : () -> Unit){

    val mapUiState = viewModel.mapInfoUiState.collectAsState()
    val mapCooUiState = viewModel.coordinatesUiState.collectAsState()

    var input by remember { mutableStateOf("") }
    var isTextFieldFocused by remember { mutableStateOf(false) }
    val recentSearchHashmap : HashMap<String, String> = hashMapOf()
    val keyboardController = LocalSoftwareKeyboardController.current
    var showRecent = true

    //bare midlertidlig
    val elevetion = 2469

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
                        isTextFieldFocused = it.isFocused
                    }
                    .onKeyEvent {
                        if (it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_ENTER) {
                            input = input.replace("ø", "oe")
                            input = input.replace("æ", "ae")
                            input = input.replace("å", "aa")
                            if (input.length > 1) {
                                viewModel.getDataSearch(input)
                                showRecent = false
                            }
                            input = ""
                        }
                        false
                    },

                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        input = input.replace("ø", "oe")
                        input = input.replace("æ", "ae")
                        input = input.replace("å", "aa")
                        if (input.length > 1) {
                            viewModel.getDataSearch(input)
                            showRecent = false
                        }
                        input = ""
                        //keyboardController?.hide()
                        //isTextFieldFocused = false
                        //focusManager.clearFocus()
                    }),
                value = input,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                ),
                onValueChange = {
                    //if (it == "ø")
                    println(it)
                    input = it
                },
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

                            if (input.length > 1) {
                                showRecent = false
                                input = input.replace("ø", "oe")
                                input = input.replace("æ", "ae")
                                input = input.replace("å", "aa")
                                viewModel.getDataSearch(input)

                            }

                            input = ""

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

        if (isTextFieldFocused && showRecent) {

            items(mapUiState.value.recentSearch) { mountain ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .height(40.dp)
                        .padding(start = 20.dp, top = 9.dp, bottom = 7.dp)
                        .clickable(enabled = true, onClick = {

                            viewModel.updateRecentSearch(mountain, false)

                            viewModel.showSelectedMountain(
                                recentSearchHashmap[mountain]!!,
                                mountain,
                                elevetion
                            )

                            clearFocus()
                            showRecent = true

                            onSearch()

                            //------------------For å få opp card---------------

                            println("se")
                            println(mapCooUiState.value.latitude.toString())
                            println(mapCooUiState.value.longitude.toString())

                            //endre altitude på linja nedenfor!!
                            ///viewModel.getAllSearch(mapCooUiState.value.latitude.toString(), mapCooUiState.value.longitude.toString(), elevetion.toString())
                            //bruke koordinatene over til å få opp card
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
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 15.dp)
                            .clickable(
                                onClick = {
                                    viewModel.updateRecentSearch(mountain, true)
                                }
                            )
                    )
                }
            }
        }
        if (isTextFieldFocused && !showRecent) {
            if (mapUiState.value.optionMountains.size == 0){
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
            } else {

                items(mapUiState.value.optionMountains.keys.toList()) { mountain ->

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .height(40.dp)
                            .padding(start = 20.dp, top = 9.dp, bottom = 7.dp)
                            .clickable(enabled = true, onClick = {

                                viewModel.updateRecentSearch(mountain, false)

                                recentSearchHashmap[mountain] =
                                    mapUiState.value.optionMountains[mountain]!!

                                viewModel.showSelectedMountain(
                                    recentSearchHashmap[mountain]!!,
                                    mountain,
                                    elevetion
                                )

                                //focusManager.clearFocus()
                                clearFocus()
                                showRecent = true

                                //skal gjøre den true så carded vises i ShowMap():
                                onSearch()

                                //------------------For å få opp card---------------

                                println("se2")
                                println(mapCooUiState.value.latitude.toString())
                                println(mapCooUiState.value.longitude.toString())

                                //endre altitude på linja nedenfor!!
                                //denne funker ikke fordi variablene rekker ikke å bli oppdatert før getAll kjøres.
                                //så heller kalle på den i mapViewModel? men teit å kalle på et annet viewmodel fra et viewmodel
                                //viewModel.getAllSearch(mapCooUiState.value.latitude.toString(), mapCooUiState.value.longitude.toString(), elevetion.toString())
                                //bruke koordinatene over til å få opp card
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