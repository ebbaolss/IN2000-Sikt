package com.example.in2000_prosjekt.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.in2000_prosjekt.ui.APIViewModel
import com.example.in2000_prosjekt.ui.AlertInfo
import com.example.in2000_prosjekt.ui.AppUiState
import com.example.in2000_prosjekt.ui.components.Alert_Card
import com.example.in2000_prosjekt.ui.components.Sikt_BottomBar
import com.example.in2000_prosjekt.ui.theme.Sikt_lyseblå
import com.example.in2000_prosjekt.ui.theme.Sikt_mellomblå

@Composable
fun AlertScreen(apiViewModel: APIViewModel = viewModel(),
                onNavigateToMap: () -> Unit,
                onNavigateToFav: () -> Unit,
                onNavigateToSettings: () -> Unit,
                onNavigateToRules: () -> Unit){

    val appUiState by apiViewModel.appUiState.collectAsState()

    when(appUiState){
        is AppUiState.Loading -> Text (text = "loading...", fontSize = 30.sp)
        is AppUiState.Error -> Text (text = "error")
        is AppUiState.Success -> {
            AlertScreenSuccess(
                alertinfo = (appUiState as AppUiState.Success).alertListF,
                onNavigateToMap,
                onNavigateToFav,
                onNavigateToSettings,
                onNavigateToRules
            ) //endre dette til en bedre måte etterhvert?
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertScreenSuccess(alertinfo: MutableList<AlertInfo>,
                       onNavigateToMap: () -> Unit,
                       onNavigateToFav: () -> Unit,
                       onNavigateToSettings: () -> Unit,
                       onNavigateToRules: () -> Unit){

    Scaffold( bottomBar = { Sikt_BottomBar(onNavigateToMap, onNavigateToFav, onNavigateToSettings, onNavigateToRules, favoritt = false, rules = false, settings = false, map = false) })
    {
        Column{
            alertinfo.forEach {
                Alert_Card(alert = it)
            }
        }
    }

}