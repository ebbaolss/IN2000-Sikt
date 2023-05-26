package com.example.in2000_prosjekt.ui.screens

import androidx.compose.ui.res.painterResource
import com.example.in2000_prosjekt.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun StartPage(onNavigateToNext: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.landingpage),
            contentDescription = "",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxSize(),

        )
    }
    LaunchedEffect(Unit) {
        delay(2.seconds)
        onNavigateToNext()
    }
}
