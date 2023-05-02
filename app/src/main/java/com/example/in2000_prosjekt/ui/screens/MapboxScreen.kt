package com.example.in2000_prosjekt.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.in2000_prosjekt.ui.components.Sikt_BottomBar
import com.example.in2000_prosjekt.ui.components.Sikt_BottomSheet
import com.example.in2000_prosjekt.ui.components.Sikt_LocationCard
import com.example.in2000_prosjekt.ui.database.MapViewModel
import com.example.in2000_prosjekt.ui.uistate.MapUiState
import com.mapbox.bindgen.Expected
import com.mapbox.geojson.Feature
import com.mapbox.geojson.Point
import com.mapbox.maps.*
import com.mapbox.maps.dsl.cameraOptions
import com.mapbox.maps.extension.style.expressions.dsl.generated.eq
import com.mapbox.maps.extension.style.layers.generated.circleLayer
import com.mapbox.maps.extension.style.layers.generated.CircleLayer
import com.mapbox.maps.extension.style.layers.generated.circleLayer
import com.mapbox.maps.extension.style.layers.getLayerAs
import com.mapbox.maps.extension.style.sources.generated.vectorSource
import com.mapbox.maps.extension.style.style
import com.mapbox.maps.plugin.compass.compass
import com.mapbox.maps.plugin.delegates.listeners.OnCameraChangeListener
import com.mapbox.maps.plugin.gestures.OnMapClickListener
import com.mapbox.maps.plugin.gestures.addOnMapClickListener
import com.mapbox.maps.plugin.scalebar.scalebar
import kotlin.math.sqrt

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowMap(
    onNavigateToMap: () -> Unit,
    onNavigateToFav: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateToRules: () -> Unit,
    mapViewModel: MapViewModel
) {
    val cameraOptionsUiState by mapViewModel.cameraOptionsUiState.collectAsState()
    val mountainUiState by mapViewModel.mountainUiState.collectAsState()
    var locationCardState by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            Sikt_BottomBar(
                onNavigateToMap,
                onNavigateToFav,
                onNavigateToRules,
                onNavigateToSettings,
                favoritt = false,
                rules = false,
                map = true,
                settings = false
            )
        }) {
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

                    mapboxMap.addOnMapClickListener(onMapClickListener = OnMapClickListener { point ->
                        locationCardState = onMapClick(point, mapboxMap, mapViewModel)
                        Log.d("LocationCardState", "$locationCardState")
                        locationCardState
                    })
                    mapboxMap.addOnCameraChangeListener(onCameraChangeListener = OnCameraChangeListener {
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
                            val zoom = cameraOptionsUiState.currentScreenZoom

                            Log.d("Update Camera Coordinates", "Lng: $lng, Lat: $lat")

                            center(Point.fromLngLat(lng, lat))
                        }
                    )
                }
            )

            if (locationCardState){
                Sikt_LocationCard(mountainUiState)
            }
        }

        // Menu
        Sikt_BottomSheet()
    }
}

fun onCameraChange(mapboxMap: MapboxMap, viewModel: MapViewModel) {
    val screenCenter = mapboxMap.cameraState.center
    val zoom = mapboxMap.cameraState.zoom
    viewModel.updateCameraPosition(screenCenter)
    viewModel.updateCameraZoomState(zoom)
    Log.d("onCameraChange", "Coordinates $screenCenter, Zoom level: $zoom")
}


fun createFactoryMap(xt: Context, cameraOptionsUiState: MapUiState.MapboxCameraOptions) : MapView {

    val mapView = MapView(xt).apply {
        val mapboxMap = getMapboxMap()
        val cameraOptionsUiState = cameraOptionsUiState

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
                zoom(cameraOptionsUiState.currentScreenZoom)
                // Koordinatene til Glittertind
                center(Point.fromLngLat(cameraOptionsUiState.currentScreenLongitude,cameraOptionsUiState.currentScreenLatitude))
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
fun onMapClick(point: Point, mapboxMap: MapboxMap, viewModel: MapViewModel): Boolean {
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
    ) { it ->
        onFeatureClicked(it) { feature ->
            if (feature.id() != null) {
                val name = feature.getStringProperty("name")
                val elevation = feature.getStringProperty("elevation_m").toInt()
                val point = feature.geometry() as Point

                // Saving a clicked mountain to the UiState through the view model
                viewModel.updateMountain(MapUiState.Mountain(name, point, elevation))

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
