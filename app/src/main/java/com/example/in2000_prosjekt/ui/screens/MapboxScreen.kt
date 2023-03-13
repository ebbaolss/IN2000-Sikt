package com.example.in2000_prosjekt.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.mapbox.maps.MapView
import com.mapbox.maps.dsl.cameraOptions

@Composable
    fun ShowMap(onNavigateToNext: () -> Unit){

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        AndroidView(
            modifier = Modifier,
            factory = {context ->
                MapView(context).apply {
                    getMapboxMap().loadStyleUri("mapbox://styles/elisabethb/clf6t1z9z00b101pen0rvc1fu/draft") {
                        cameraOptions{
                            zoom(19.9)
                        }
                    }
                }
            }
        )
    }
}
