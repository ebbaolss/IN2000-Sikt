package com.example.in2000_prosjekt.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.in2000_prosjekt.ui.data.DataSource
import com.example.in2000_prosjekt.ui.data.DataSourceAlerts
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
    /*
    kommunenr for galhøpiggen
    val county : String = "3434"
    */
    //kommunenr med farevarsler nå
    val county : String = "54"

    val dataSource = DataSource(latitude, longtitude, altitude)
    val dataMet = DataSourceAlerts(county)

    private val _appUistate = MutableStateFlow(AppUiState())
    val appUiState: StateFlow<AppUiState> = _appUistate.asStateFlow()

    init {
        getLocation()
        getAlert()
        getNowCast()
    }

    private fun getLocation() {
        viewModelScope.launch(Dispatchers.IO) {

            _appUistate.update {
                it.copy(
                    locationForecast = dataSource.fetchLocationForecast()
                )
            }
            val model = dataSource.fetchLocationForecast()
            /* println("LOCATION TEMP : " + model.properties?.timeseries?.get(0)?.data?.instant?.details?.air_temperature.toString())
            println("WINDSPEED: " + model.properties?.timeseries?.get(0)?.data?.instant?.details?.wind_speed.toString())
            */
            //val nowCast = dataSource.fetchNowCast()
        }
    }

    private fun getNowCast() {
        viewModelScope.launch(Dispatchers.IO) {

            _appUistate.update {
                it.copy(
                    nowcast = dataSource.fetchNowCast()
                )
            }
            val mod = dataSource.fetchNowCast()
            /* println("NOWCAST TEMP : " + mod.properties?.timeseries?.get(0)?.data?.instant?.details?.air_temperature.toString())
            println("NOWCAST WIND : " + mod.properties?.timeseries?.get(0)?.data?.instant?.details?.wind_speed.toString())
             */
        }
    }

    private fun getAlert(){
        viewModelScope.launch(Dispatchers.IO) {
            _appUistate.update {
                it.copy(
                    metAlerts = dataMet.fetchMetAlert()
                )
            }
            val build = dataMet.fetchMetAlert()
            println(" ALERT lang (Should say \"no\" ) : " + build.lang )
            println(" ALERT TESTMET : " + build.features?.toString())
            println(" ALERT type : " + build.type )
            println(" ALERT lastChange : " + build.lastChange )
        }
    }
}