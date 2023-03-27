package com.example.in2000_prosjekt.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.in2000_prosjekt.ui.APIViewModel
import com.example.in2000_prosjekt.ui.components.ToppCard


@Composable
fun API_test(
    apiViewModel: APIViewModel = viewModel(),
    onNavigateToNext: () -> Unit
){

    val appUiState by apiViewModel.appUiState.collectAsState()

    val temperatur = appUiState.nowcast?.properties?.timeseries?.get(0)?.data?.instant?.details?.air_temperature.toString()
    val sikt = appUiState.locationForecast?.properties?.timeseries?.get(0)?.data?.instant?.details?.fog_area_fraction.toString()
    val nedbor = appUiState.locationForecast?.properties?.timeseries?.get(0)?.data?.instant?.details?.precipitation_amount.toString()
    val vind = appUiState.nowcast?.properties?.timeseries?.get(0)?.data?.instant?.details?.wind_speed.toString()
    val varsel = "0"
    val soloppgang = appUiState.sunrise?.properties?.sunrise?.time.toString()
    val solnedgang = appUiState.sunrise?.properties?.sunset?.time.toString()


    ToppCard(temperatur, sikt, nedbor, vind, varsel, soloppgang, solnedgang)

    /*Column {

        Text(
            modifier = Modifier,
            text = "API-TEST",
            textAlign = TextAlign.Center,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(25.dp))

        /*Text(
            //text = "metAlerts = ${appUiState.metAlerts?.features?.get(0).toString()}"
            //"locationforecast = ${appUiState.locationForecast?.properties?.timeseries?.get(1)?.data?.next_12_hours?.summary?.values}\n"
            // + "Cloud Area = ${appUiState.locationForecast[0].cloud_area_fraction}"
        )*/
        Text(text = "sunrise = ${appUiState.sunrise?.properties?.sunrise.toString()}")
        Text(text = "locationforecast = ${appUiState.locationForecast?.properties?.timeseries?.get(1)?.data?.next_12_hours?.summary?.values}\n")

        Text(text = "nowcast = ${appUiState.nowcast?.properties?.timeseries?.get(0)?.data?.next_1_hours?.details?.values?.toString()}")

     */

}


