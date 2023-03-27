package com.example.in2000_prosjekt.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.example.in2000_prosjekt.ui.components.Sikt_BlueButton
import com.example.in2000_prosjekt.ui.components.Sikt_BottomBar
import com.mapbox.maps.MapView
import com.mapbox.maps.dsl.cameraOptions

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
    fun ShowMap(onNavigateToNext: () -> Unit){

        Scaffold(bottomBar = { Sikt_BottomBar()}) {
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
