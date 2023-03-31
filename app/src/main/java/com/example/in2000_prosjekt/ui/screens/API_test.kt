package com.example.in2000_prosjekt.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.in2000_prosjekt.ui.APIViewModel
import com.example.in2000_prosjekt.ui.components.ToppCard
import com.example.in2000_prosjekt.ui.*

@Composable
fun API_test(
    apiViewModel: APIViewModel = viewModel(),
    onNavigateToNext: () -> Unit
){

    val appUiState by apiViewModel.appUiState.collectAsState()

    when(appUiState){
        is AppUiState2.Loading -> Text (text = "loading...", fontSize = 30.sp)
        is AppUiState2.Error -> Text (text = "error")
        is AppUiState2.Success -> {
            ToppCard(
                weatherinfo = (appUiState as AppUiState2.Success).locationInfo,
                nowcastinfo = (appUiState as AppUiState2.Success).nowCastDef,
                sunriseinfo = (appUiState as AppUiState2.Success).sunrise,
                //alertinfo = (appUiState as AppUiState2.Success).alert
            ) //endre dette til en bedre måte etterhvert?
        }
    }
}


