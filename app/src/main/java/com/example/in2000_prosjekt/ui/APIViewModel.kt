package com.example.in2000_prosjekt.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

import com.example.in2000_prosjekt.ui.data.*
import io.ktor.client.call.*
import io.ktor.utils.io.errors.*

import kotlinx.coroutines.async

class APIViewModel : ViewModel() {

    //manual dependency injection, se codelab
    val repository: WeatherRepository = ImplementedWeatherRepository() //lettvinte måten

    private val _appUistate: MutableStateFlow< AppUiState > = MutableStateFlow(AppUiState.Loading)
    val appUiState: StateFlow<AppUiState> = _appUistate.asStateFlow()
    val latitude = " 63.073"
    val longtitude = "8.98"
    val altitude: String = "600"

    init { //etterhvert så endrer man  fra å ha init til å kalle på getAll fra en annen fil
        //favoritter skal loades med en gang appen åpner, database se codelab
        getAll("63.073","8.98","600","3434" )
    }

    fun getAll(latitude: String, longitude: String, altitude: String, county: String) {
        viewModelScope.launch() {
            val locationDeferred = viewModelScope.async (Dispatchers.IO){
                repository.getLocation(latitude, longitude, altitude)
            }
            val nowCastDeferred = viewModelScope.async (Dispatchers.IO){
                repository.getNowCast(latitude, longitude, altitude)
            }
            val sunsetDeferred = viewModelScope.async (Dispatchers.IO){
                repository.getSunrise(latitude, longitude)
            }
            val alertDeferred = viewModelScope.async (Dispatchers.IO){
                repository.getAlert(latitude, longitude)
            }
            val frostDeferred = viewModelScope.async (Dispatchers.IO){
                repository.getFrost(latitude, longitude)
            }

            val nowCastP = nowCastDeferred.await()
            val locationP = locationDeferred.await()
            val sunsetP = sunsetDeferred.await()
            val alertP = alertDeferred.await()
            val frostP = frostDeferred.await()

            _appUistate.update {
                AppUiState.Success(
                    locationF = locationP,
                    nowCastF = nowCastP,
                    sunriseF = sunsetP,
                    alertListF = alertP,
                    //frostF = frostP
                )
            }
        }
    }
    
    private fun getLocation() : Deferred<LocationInfo>{
        return viewModelScope.async(Dispatchers.IO) {

            val forecast = dataSource.fetchLocationForecast(latitude, longtitude, altitude)

            val temp = forecast.properties?.timeseries?.get(0)?.data?.instant?.details?.air_temperature
            val airfog = forecast.properties?.timeseries?.get(0)?.data?.instant?.details?.fog_area_fraction
            val rain = forecast.properties?.timeseries?.get(0)?.data?.next_1_hours?.details?.get("precipitation_amount")

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

            val sunriseF = SunriseInfo(
                sunriseS = sunriseToday!!,
                sunsetS = sunsetToday!!
            )
            return@async sunriseF
        }
    }

    private fun getAlert() : Deferred<MutableList<AlertInfo>>{
        return viewModelScope.async(Dispatchers.IO) {
            val alert = dataMet.fetchMetAlert(latitude, longtitude)

            var alertList : MutableList<AlertInfo> = mutableListOf()
            //Dette er klønete, men appen kræsjer ikke hvis det ikke er fare
            var area : String?
            var type : String?
            var cons : String?
            var rec : String?
            var desc: String?
            var alertType: String?
            var alertLevel: String?
            var timeInterval: List<String?>?

            alert.features?.forEach{
                val prop = it.properties
                val tid = it.tid

                area = prop?.area
                type = prop?.eventAwarenessName
                cons = prop?.consequences
                rec = prop?.instruction
                desc = prop?.description
                alertType = prop?.awareness_type
                alertLevel = prop?.awareness_level
                timeInterval = tid?.interval

                val alertF = AlertInfo(
                    areaA = area!!,
                    typeA = type!!,
                    consequenseA = cons!!,
                    recomendationA = rec!!,
                    descriptionA = desc!!,
                    alertTypeA = alertType!!,
                    alertLevelA = alertLevel!!,
                    timeIntervalA = timeInterval!!
                )

                alertList.add(alertF)
            }
            //Log.d("area", area.toString())
            return@async alertList
        }
}

