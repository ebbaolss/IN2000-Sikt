package com.example.in2000_prosjekt.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.PointF
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.in2000_prosjekt.ui.components.Sikt_BottomBar
import com.example.in2000_prosjekt.ui.components.Sikt_BottomSheet

import com.mapbox.geojson.Point
import com.example.in2000_prosjekt.ui.theme.Sikt_lyseblå
import com.example.in2000_prosjekt.ui.theme.Sikt_mellomblå

import com.mapbox.bindgen.Expected
import com.mapbox.geojson.Feature
import com.mapbox.geojson.FeatureCollection
import com.mapbox.geojson.Point
import com.mapbox.maps.*
import com.mapbox.maps.dsl.cameraOptions
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
import com.mapbox.maps.plugin.gestures.OnMapClickListener
import com.mapbox.maps.plugin.gestures.addOnMapClickListener
import org.jetbrains.annotations.Nullable
import java.util.concurrent.CountDownLatch

import com.mapbox.maps.MapView
import com.mapbox.maps.dsl.cameraOptions
import com.mapbox.maps.plugin.gestures.OnMapClickListener
import com.mapbox.maps.plugin.gestures.addOnMapClickListener


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowMap(onNavigateToMap: () -> Unit, onNavigateToFav: () -> Unit, onNavigateToSettings: () -> Unit, onNavigateToRules: () -> Unit) {
    Scaffold(bottomBar = { Sikt_BottomBar(onNavigateToMap, onNavigateToFav, onNavigateToRules, onNavigateToSettings, favoritt = false, settings = false, rules = false, map = true) }) {
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

        mapboxMap.loadStyle(
            style(styleUri = Style.OUTDOORS) {
                +vectorSource(id = "SOURCE_ID") {
                    url("mapbox://mapbox.mapbox-streets-v8")
                }
                +circleLayer(layerId = "LAYER_ID", sourceId = "SOURCE_ID") {
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

                Log.d("Feature", feature.getStringProperty("name").toString() + ". Elevation: " + feature.getStringProperty("elevation_m").toString() + "m.o.h.")

                mapboxMap.getStyle() { style ->
                    val layer = style.getLayerAs<CircleLayer>("LAYER_ID")!!
                    layer.circleColor(Color.BLUE)
                }
            }
        }
    }
    return true
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