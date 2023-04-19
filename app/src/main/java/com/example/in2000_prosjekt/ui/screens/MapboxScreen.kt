package com.example.in2000_prosjekt.ui.screens

import com.example.in2000_prosjekt.ui.components.Sikt_BlueButton
import com.example.in2000_prosjekt.ui.components.Sikt_BottomBar
import com.example.in2000_prosjekt.ui.theme.Sikt_lyseblå
import com.example.in2000_prosjekt.ui.theme.Sikt_mellomblå
import com.example.in2000_prosjekt.ui.theme.Sikt_mørkeblå
import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.mapbox.geojson.Point
import com.mapbox.maps.MapView
import com.mapbox.maps.dsl.cameraOptions
import com.mapbox.maps.plugin.gestures.OnMapClickListener
import com.mapbox.maps.plugin.gestures.addOnMapClickListener

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowMap(onNavigateToMap: () -> Unit, onNavigateToFav: () -> Unit, onNavigateToRules: () -> Unit) {
    Scaffold(bottomBar = { Sikt_BottomBar(onNavigateToMap, onNavigateToFav, onNavigateToRules, favoritt = Sikt_lyseblå, rules = Sikt_lyseblå, map = Sikt_mellomblå)}) {
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
    }
}
fun createFactoryMap(xt: Context) : MapView {
    return MapView(xt).apply {
        val mapboxMap = getMapboxMap()
        mapboxMap.loadStyleUri("mapbox://styles/elisabethb/clf6t1z9z00b101pen0rvc1fu/draft")
        mapboxMap.setCamera(
            cameraOptions {
                zoom(13.0)
                // Koordinatene til Glittertind
                center(Point.fromLngLat(8.557801680731075,61.651356077904666))
            }
        )
        mapboxMap.addOnMapClickListener(onMapClickListener = OnMapClickListener {point ->
            onMapClick(point)
        })
    }
}
fun onMapClick(point: Point): Boolean {
    Log.d("Coordinate", point.toString())
    return@onMapClick true
}
