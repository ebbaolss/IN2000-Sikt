package com.example.in2000_prosjekt.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.PointF
import android.util.Log
import android.view.View
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.in2000_prosjekt.ui.APIViewModel
import com.example.in2000_prosjekt.ui.components.Sikt_BottomBar
import com.example.in2000_prosjekt.ui.components.Sikt_BottomSheet

import com.example.in2000_prosjekt.ui.theme.Sikt_lyseblå
import com.example.in2000_prosjekt.ui.theme.Sikt_mellomblå
import com.example.in2000_prosjekt.ui.uistate.MapUiState

import com.mapbox.bindgen.Expected
import com.mapbox.geojson.Feature
import com.mapbox.geojson.FeatureCollection
import com.mapbox.geojson.Point
import com.mapbox.maps.*
import com.mapbox.maps.dsl.cameraOptions
import com.mapbox.maps.extension.observable.eventdata.CameraChangedEventData
import com.mapbox.maps.extension.style.expressions.dsl.generated.eq
import com.mapbox.maps.extension.style.expressions.dsl.generated.get
import com.mapbox.maps.extension.style.expressions.dsl.generated.literal
import com.mapbox.maps.extension.style.expressions.dsl.generated.switchCase
import com.mapbox.maps.extension.style.layers.Layer
import com.mapbox.maps.extension.style.layers.generated.CircleLayer
import com.mapbox.maps.extension.style.layers.generated.circleLayer
import com.mapbox.maps.extension.style.layers.getLayer
import com.mapbox.maps.extension.style.layers.getLayerAs
import com.mapbox.maps.extension.style.sources.generated.geoJsonSource
import com.mapbox.maps.extension.style.sources.generated.vectorSource
import com.mapbox.maps.extension.style.style
import com.mapbox.maps.plugin.attribution.attribution
import com.mapbox.maps.plugin.compass.CompassPlugin
import com.mapbox.maps.plugin.compass.CompassViewPlugin
import com.mapbox.maps.plugin.compass.compass
import com.mapbox.maps.plugin.delegates.listeners.OnCameraChangeListener
import com.mapbox.maps.plugin.gestures.OnMapClickListener
import com.mapbox.maps.plugin.gestures.addOnMapClickListener
import com.mapbox.maps.plugin.scalebar.scalebar
import org.jetbrains.annotations.Nullable
import java.util.concurrent.CountDownLatch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowMap(
    onNavigateToMap: () -> Unit,
    onNavigateToFav: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateToRules: () -> Unit,
    viewModel: APIViewModel
) {
    val cameraOptionsUiState by viewModel.cameraOptionsUiState.collectAsState()
    val mountainUiState by viewModel.mountainUiState.collectAsState()

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
                        onMapClick(point, mapboxMap, viewModel)
                    })
                    mapboxMap.addOnCameraChangeListener(onCameraChangeListener = OnCameraChangeListener { cameraData ->
                        onCameraChange(mapboxMap, viewModel)
                    })

                    map
                },
                update = {
                    // pull cameraSettings from the UiState
                }
            )
        }

        // Menu
        Sikt_BottomSheet()
    }
}

fun onCameraChange(mapboxMap: MapboxMap, viewModel: APIViewModel) {

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
                }
            }
        )

        // Camera settings
        mapboxMap.setCamera(
            cameraOptions {
                zoom(13.0)
                // Koordinatene til Glittertind
                center(Point.fromLngLat(8.557801680731075,61.651356077904666))
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
fun onMapClick(point: Point, mapboxMap: MapboxMap, viewModel: APIViewModel): Boolean {
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
                val point = feature.geometry() as Point
                val elevation = feature.getNumberProperty("elevation_m") as Int

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
