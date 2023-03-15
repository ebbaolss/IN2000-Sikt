package com.example.in2000_prosjekt.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.in2000_prosjekt.ui.ViewModel
import com.example.in2000_prosjekt.ui.data.DataSource
import com.example.in2000_prosjekt.ui.data.LocationForecast

@Composable
fun API_test(apiViewModel: ViewModel = viewModel(),
             onNavigateToNext: () -> Unit
){

    val appUiState by apiViewModel.appUiState.collectAsState()

    Column {

        Text(
            modifier = Modifier,
            text = "API-TEST",
            textAlign = TextAlign.Center,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(25.dp))

        Text(text = "Temperature = ${appUiState.locationForecast.air_temp}\n" +
                "Cloud Area = ${appUiState.locationForecast.cloud_area_fraction}")
    }

}


