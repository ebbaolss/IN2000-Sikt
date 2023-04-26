package com.example.in2000_prosjekt.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

import android.util.Log
import com.example.in2000_prosjekt.ui.data.*

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import java.time.LocalDate

class APIViewModel () : ViewModel()
    {

    //for å teste
    var latitude : String = "61.6370"
    var longitude: String = "8.3092"
    var altitude: String = "2469"


    //----------------------
    //Frost:
    var elements = "air_temperature"// Dette er værmålingen vi ønsker: For enkelthetsskyld så velges bare: air temperature
    var referencetime ="2021-05-17%2F2021-05-17" // Frost API, bruker UTC-tidsformat, denne ønskes senere å kunne bestemmes av en bruker ved hjelp av en Date picker (en bibloteksfunskjon i jetpack compose)
    //var url_med_Polygon ="https://frost.met.no/sources/v0.jsonld?types=SensorSystem&elements=air_temperature&geometry=POLYGON((7.9982%2058.1447%20%2C%208.0982%2058.1447%20%2C7.9982%2058.2447%20%2C%208.0982%2058.2447%20))"
    val source = "SN18700" //skjønner ikke denne, hvor får vi dette fra? Hva er det? Spørr Nebil
    //----------------------

    /*
    kommunenr for galhøpiggen
    val county : String = "3434"
    */
    //kommunenr med farevarsler nå
    val county : String = "54"

    val dataSource = DataSource(basePath = "https://gw-uio.intark.uh-it.no/in2000/weatherapi") //dette er både forecast og nowcast
    val dataMet = DataSourceAlerts(basePath = "https://gw-uio.intark.uh-it.no/in2000/weatherapi")
    val dataSunrise = DataSourceSunrise(basePath = "https://gw-uio.intark.uh-it.no/in2000/weatherapi")
    val dataFrost = DataSourceFrost(basePath = "https://frost.met.no/observations/v0.jsonld?")

    private val _appUistate: MutableStateFlow< AppUiState > = MutableStateFlow(AppUiState.Loading)
    val appUiState: StateFlow<AppUiState> = _appUistate.asStateFlow()

    init {
        getAll()
    }

    fun getAll(referencetimetest: String = "") {
        viewModelScope.launch() {
            val nowCastDeferred = getNowCast()
            val locationDeferred = getLocation()
            val sunsetDeferred = getSunrise()
            val alertDeferred = getAlert()
            val frostDeferred = getFrost(referencetimetest)

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
                    frostF = frostP
                )
            }
        }
    }

    private fun getLocation() : Deferred<LocationInfo>{
        return viewModelScope.async(Dispatchers.IO) {

            val forecast = dataSource.fetchLocationForecast(latitude, longitude, altitude)

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

            val forecastNow = dataSource.fetchNowCast(latitude, longitude, altitude)

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

            val sunrise = dataSunrise.fetchSunrise(latitude, longitude)

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
            val alert = dataMet.fetchMetAlert(county)

            var alertList : MutableList<AlertInfo> = mutableListOf()
            //Dette er klønete, men appen kræsjer ikke hvis det ikke er fare
            var area : String?
            var type : String?
            var cons : String?
            var rec : String?
            var desc: String?
            var alertType: String?
            var alertLevel: String?

            alert.features?.forEach{
                val prop = it.properties

                area = prop?.area
                type = prop?.eventAwarenessName
                cons = prop?.consequences
                rec = prop?.instruction
                desc = prop?.description
                alertType = prop?.awareness_type
                alertLevel = prop?.awareness_level

                val alertF = AlertInfo(
                    areaA = area!!,
                    typeA = type!!,
                    consequenseA = cons!!,
                    recomendationA = rec!!,
                    descriptionA = desc!!,
                    alertTypeA = alertType!!,
                    alertLevelA = alertLevel!!
                )

                alertList.add(alertF)
            }
            //Log.d("area", area.toString())
            return@async alertList
        }
}

    fun getFrost( referencetimetest: MutableList<LocalDate> ) : Deferred<FrostInfo> {
        return viewModelScope.async(Dispatchers.IO) {

            var datefrom = referencetimetest.get(0) // dag 01, på kalendern
            var dateto =referencetimetest.get(31) // dag 30, på kalendern

            val frost = dataFrost.fetchFrostTemp(elements, referencetimetest, source)
            val frostPolygon = dataFrost.fetchApiSvarkoordinater(longitude,latitude)

            val meancloudareafraction = frost.data?.get(0)?.observations?.get(0)?.value
            val long = frostPolygon.data?.get(0)?.geometry?.coordinates?.get(0)
            val lat = frostPolygon.data?.get(0)?.geometry?.coordinates?.get(1)

            Log.d("typefrost", meancloudareafraction.toString())
            Log.d("lat", lat.toString())
            Log.d("long", long.toString())

            val frostF = FrostInfo(
                sightcondition = meancloudareafraction!!, //ikke egt ha toString her

            )
            return@async frostF
        }
    }
}