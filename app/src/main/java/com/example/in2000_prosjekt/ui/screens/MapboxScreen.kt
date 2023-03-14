package com.example.in2000_prosjekt.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.example.in2000_prosjekt.ui.components.Sikt_BottomBar
import com.mapbox.maps.MapView
import com.mapbox.maps.dsl.cameraOptions

@Composable
    fun ShowMap(onNavigateToNext: () -> Unit){

        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Sikt_BottomBar()
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
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
}
