package com.example.in2000_prosjekt.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.in2000_prosjekt.ui.data.DataSource
import com.example.in2000_prosjekt.ui.data.LocationForecast
import com.example.in2000_prosjekt.ui.data.Nowcast
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ViewModel : ViewModel() {

    val latitude : String = "61.6370"
    val longtitude: String = "8.3092"
    val altitude: String = "2469"

    val dataSource = DataSource(latitude, longtitude, altitude)

    private val appUIstate = MutableStateFlow(AppUiState())
    val appUiState: StateFlow<AppUiState> = appUIstate.asStateFlow()

    init {
        getLocation()
    }

    //exception handler for logging
    val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
        throwable.printStackTrace()
    }
    private fun getLocation(){
        viewModelScope.launch (Dispatchers.IO+ coroutineExceptionHandler) {
            appUIstate.update {
                it.copy(locationForecast = dataSource.fetchLocationForecast()
                )
            }
            //val nowCast = dataSource.fetchNowCast()
        }
    }
}