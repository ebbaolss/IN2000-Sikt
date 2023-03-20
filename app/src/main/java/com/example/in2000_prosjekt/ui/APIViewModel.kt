package com.example.in2000_prosjekt.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.in2000_prosjekt.ui.data.DataSource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class APIViewModel : ViewModel() {

    val latitude : String = "61.6370"
    val longtitude: String = "8.3092"
    val altitude: String = "2469"

    val dataSource = DataSource(latitude, longtitude, altitude)

    private val _appUistate = MutableStateFlow(AppUiState())
    val appUiState: StateFlow<AppUiState> = _appUistate.asStateFlow()

    init {
        getLocation()
        getAlert()
    }

    private fun getLocation() {
        viewModelScope.launch(Dispatchers.IO) {

            _appUistate.update {
                it.copy(
                    locationForecast = dataSource.fetchLocationForecast()
                )
            }
            val model = dataSource.fetchLocationForecast()
            println("LOCATION : " + model.properties?.timeseries?.toString())
            //val nowCast = dataSource.fetchNowCast()
        }
    }

    private fun getAlert(){
        viewModelScope.launch(Dispatchers.IO) {
            _appUistate.update {
                it.copy(
                    metAlerts = dataSource.fetchMetAlert()
                )
            }
            val build = dataSource.fetchMetAlert()
            println(" ALERT : " + build.lang)
        }
    }
}