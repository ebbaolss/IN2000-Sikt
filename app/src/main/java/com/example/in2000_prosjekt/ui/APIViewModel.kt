package com.example.in2000_prosjekt.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.in2000_prosjekt.ui.data.DataSource
import com.example.in2000_prosjekt.ui.data.DataSourceAlerts
import com.example.in2000_prosjekt.ui.data.DataSourceSunrise
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

import android.util.Log
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

class APIViewModel : ViewModel() {

    //for å teste
    val latitude : String = "61.6370"
    val longtitude: String = "8.3092"
    val altitude: String = "2469"

    /*
    kommunenr for galhøpiggen
    val county : String = "3434"
    */
    //kommunenr med farevarsler nå
    val county : String = "42"

    val dataSource = DataSource(basePath = "https://gw-uio.intark.uh-it.no/in2000/weatherapi")
    val dataMet = DataSourceAlerts(county)
    val dataSunrise = DataSourceSunrise(basePath = "https://gw-uio.intark.uh-it.no/in2000/weatherapi")

    private val _appUistate: MutableStateFlow< AppUiState2 > = MutableStateFlow(AppUiState2.Loading)
    val appUiState: StateFlow<AppUiState2> = _appUistate.asStateFlow()

    init {
        getAll()
    }
    fun getAll() {
        viewModelScope.launch() {
            val nowCastDeferred = getNowCast()
            val locationDeferred = getLocation()
            val sunsetDeferred = getSunrise()
            //val alertDeferred = getAlert()

            val nowCastP = nowCastDeferred.await()
            val locationP = locationDeferred.await()
            val sunsetP = sunsetDeferred.await()
            //val alertP = alertDeferred.await()

            _appUistate.update {
                AppUiState2.Success(
                    locationInfo = locationP,
                    nowCastDef = nowCastP,
                    sunrise = sunsetP,
                    //alert = alertP
                )
            }
        }
    }
    private fun getLocation() : Deferred<LocationInfo>{
        return viewModelScope.async(Dispatchers.IO) {

            val forecast = dataSource.fetchLocationForecast(latitude, longtitude, altitude)

            val temp = forecast.properties?.timeseries?.get(0)?.data?.instant?.details?.air_temperature
            val airfog = forecast.properties?.timeseries?.get(0)?.data?.instant?.details?.fog_area_fraction
            //val rain = forecast.properties?.timeseries?.get(0)?.data?.instant?.details?.precipitation_amount
            val rain = forecast.properties?.timeseries?.get(0)?.data?.next_1_hours?.details?.get("precipitation_amount")
            Log.d("location", temp.toString())
            Log.d("rain next 1 hour:", rain.toString())

            val locationF = LocationInfo(
                temperatureL = (temp ?: -273.5) as Float,
                fog_area_fractionL = airfog!!,
                rainL = rain!!
            )
            return@async locationF
        }
    }

    private fun getNowCast() : Deferred<NowCastInfo> {
        return viewModelScope.async(Dispatchers.IO) {

            val forecastNow = dataSource.fetchNowCast(latitude, longtitude, altitude)

            val tempNow = forecastNow.properties?.timeseries?.get(0)?.data?.instant?.details?.air_temperature
            val windN = forecastNow.properties?.timeseries?.get(0)?.data?.instant?.details?.wind_speed
            Log.d("Tempnow", tempNow.toString()) //denne gir 19.9

            val nowCastF = NowCastInfo(
                temperatureNow = (tempNow ?: -273.5) as Float, //dette må fikses bedre
                windN = windN!! //funker dette eller må jeg gjøre som over?
            )
            return@async nowCastF
        }
    }

    private fun getSunrise() : Deferred<SunriseInfo> {
        return viewModelScope.async(Dispatchers.IO) {

            val sunrise = dataSunrise.fetchSunrise(latitude, longtitude)

            val sunriseToday = sunrise.properties?.sunrise?.time
            val sunsetToday = sunrise.properties?.sunset?.time
            Log.d("sunrise", sunriseToday.toString())

            val sunriseF = SunriseInfo(
                sunriseS = sunriseToday!!,
                sunsetS = sunsetToday!!
            )
            return@async sunriseF
        }
    }

//      IKKE FÅTT METALERT TIL Å FUNKE, DET KRASJER HELE APPEN NÅ
//      SÅ HAR KOMMENTERT UT METALERT HER I VIEWMODEL, APPUISTATE OG API-TEST SCREEN

//    private fun getAlert() : Deferred<AlertInfo>{
//        return viewModelScope.async(Dispatchers.IO) {
//
//              val alert = dataMet.fetchMetAlert(county)
//
//              val language = alert.type
//              Log.d("alert", language.toString())
//
//              val alertF = AlertInfo(
//                  language = language!!
//              )
//              return@async alertF
//        }
//    }

// DET GAMLE METALERT
//    private fun getAlert(){
//        viewModelScope.launch(Dispatchers.IO) {
//            _appUistate.update {
//                it.copy(
//                    metAlerts = dataMet.fetchMetAlert()
//                )
//            }
//            val build = dataMet.fetchMetAlert()
//
//        }
//    }
}