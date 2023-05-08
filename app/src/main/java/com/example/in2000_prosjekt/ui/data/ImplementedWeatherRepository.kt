package com.example.in2000_prosjekt.ui.data

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import com.example.in2000_prosjekt.ui.*
import com.example.in2000_prosjekt.ui.uistate.FrostReferenceTime
import com.example.in2000_prosjekt.ui.uistate.FrostReferencetimeUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ImplementedWeatherRepository : WeatherRepository {

    val dataSource = DataSource(basePath = "https://gw-uio.intark.uh-it.no/in2000/weatherapi") //dette er både forecast og nowcast
    val dataMet = DataSourceAlerts(basePath = "https://gw-uio.intark.uh-it.no/in2000/weatherapi")
    val dataSunrise = DataSourceSunrise(basePath = "https://gw-uio.intark.uh-it.no/in2000/weatherapi")

    //----------------------
    //Frost: Disse variablene er uviktige per 03.05
    //    var referencetime ="2021-05-17%2F2021-05-17" // Frost API, bruker UTC-tidsformat, denne ønskes senere å kunne bestemmes av en bruker ved hjelp av en Date picker (en bibloteksfunskjon i jetpack compose)
    var elements = "air_temperature"// Dette er værmålingen vi ønsker: For enkelthetsskyld så velges bare: air temperature
    //var url_med_Polygon ="https://frost.met.no/sources/v0.jsonld?types=SensorSystem&elements=air_temperature&geometry=POLYGON((7.9982%2058.1447%20%2C%208.0982%2058.1447%20%2C7.9982%2058.2447%20%2C%208.0982%2058.2447%20))"
    val source = "SN18700" //skjønner ikke denne, hvor får vi dette fra? Hva er det? Spørr Nebil
    //----------------------

    override suspend fun getLocation(
        latitude: String,
        longitude: String,
        altitude: String
    ): LocationInfo {
        Log.d("getLocationEntry", "Entried")
        val forecast = dataSource.fetchLocationForecast(latitude, longitude, altitude)
        Log.d("getLocation", "Data retrieved")

        val temp = forecast.properties?.timeseries?.get(0)?.data?.instant?.details?.air_temperature
        val airfog = forecast.properties?.timeseries?.get(0)?.data?.instant?.details?.fog_area_fraction
        val rain = forecast.properties?.timeseries?.get(0)?.data?.next_1_hours?.details?.get("precipitation_amount")
        val cloud_high = forecast.properties?.timeseries?.get(0)?.data?.instant?.details?.cloud_area_fraction_high
        val cloud_mid = forecast.properties?.timeseries?.get(0)?.data?.instant?.details?.cloud_area_fraction_medium
        val cloud_low = forecast.properties?.timeseries?.get(0)?.data?.instant?.details?.cloud_area_fraction_low

        return LocationInfo(
            temperatureL = (temp ?: -273.5) as Float,
            fog_area_fractionL = airfog!!,
            rainL = rain!!,
            cloud_area_fraction_high = cloud_high!!,
            cloud_area_fraction_medium = cloud_mid!!,
            cloud_area_fraction_low = cloud_low!!,
        )
    }

    override suspend fun getNowCast(
        latitude: String,
        longitude: String,
        altitude: String
    ): NowCastInfo {
        Log.d("getNowCastEntry", "Entried")
        val forecastNow = dataSource.fetchNowCast(latitude, longitude, altitude)
        Log.d("getNowCast", "Data retrieved")

        val tempNow =
            forecastNow.properties?.timeseries?.get(0)?.data?.instant?.details?.air_temperature
        val windN = forecastNow.properties?.timeseries?.get(0)?.data?.instant?.details?.wind_speed

        return NowCastInfo(
            temperatureNow = (tempNow ?: -273.5) as Float, //dette må fikses bedre
            windN = windN!! //funker dette eller må jeg gjøre som over?
        )
    }

    override suspend fun getSunrise(latitude: String, longitude: String): SunriseInfo {
        Log.d("getSunriseEntry", "Entried")
        val sunrise = dataSunrise.fetchSunrise(latitude, longitude)
        Log.d("getSunrise", "Data retrieved")

        val sunriseToday = sunrise.properties?.sunrise?.time
        val sunsetToday = sunrise.properties?.sunset?.time

        return SunriseInfo(
            sunriseS = sunriseToday!!,
            sunsetS = sunsetToday!!
        )
    }

    override suspend fun getAlert(latitude: String, longitude: String): MutableList<AlertInfo> {
        Log.d("getAlertEntry", "Entried")
        val alert = dataMet.fetchMetAlert(latitude, longitude)
        Log.d("getAlert", "Data retrieved")

        val alertList : MutableList<AlertInfo> = mutableListOf()
        //Dette er klønete, men appen kræsjer ikke hvis det ikke er fare
        var area : String?
        var type : String?
        var cons : String?
        var rec : String?
        var desc: String?
        var alertType: String?
        var alertLevel: String?
        var timeIntervalA: List<String?>?

        alert.features?.forEach{
            val prop = it.properties

            area = prop?.area
            type = prop?.eventAwarenessName
            cons = prop?.consequences
            rec = prop?.instruction
            desc = prop?.description
            alertType = prop?.awareness_type
            alertLevel = prop?.awareness_level
            timeIntervalA = it.tid?.interval

            val alertF = AlertInfo(
                areaA = area!!,
                typeA = type!!,
                consequenseA = cons!!,
                recomendationA = rec!!,
                descriptionA = desc!!,
                alertTypeA = alertType!!,
                alertLevelA = alertLevel!!,
                timeIntervalA = timeIntervalA!!
            )

            alertList.add(alertF)
        }
        //Log.d("area", area.toString())
        return alertList
    }



    val testdatesMutableLiveData = MutableLiveData<String>("2021-2%2F2021-3")

    val dataFrost = DataSourceFrost(basePath = "https://frost.met.no/observations/v0.jsonld?")
    var data: String = "2021-10%2F2021-11" //---------------Dette er variablen vi ønsker å endre
    //var datamutable
    var a  = mutableStateOf("")
    var b : String =""
    var c : String =""

    init{
        //data ="2021-1%2F2021-2" // Dette funket ikke, kl 13.00, 08.05

    }
    override suspend fun getReferencetimeFrost( calenderreferencetime: String ): String   { //---------------Dette er rikitg dato
        data= calenderreferencetime
        a.value= calenderreferencetime
        b= calenderreferencetime
        dataFrost.getReferencetimeFrost(calenderreferencetime)

        dataFrost.referenceDatoer.value=calenderreferencetime
        dataFrost.data=calenderreferencetime
        Log.d("repositoryvaibledataz",data) //---------------Dette er rikitg dato
        Log.d("testt",calenderreferencetime) //---------------Dette er rikitg dato
        Log.d("sdsdsdsdsd",a.value) //---------------Dette er rikitg dato
        Log.d("sdsdsdsdseed",b) //---------------Dette er rikitg dato
        return data

    }




    override suspend fun getFrost(latitude: String, longitude: String ): FrostInfo { //åssen får jeg referense time inni her:

        val tessstt = dataFrost.data

        Log.d("Saalangt", data)
        Log.d("Saalangt45454frafrost", tessstt)
        Log.d("Saalangdost", a.value)
        val frostPolygon = dataFrost.fetchFrostWeatherStation(   latitude.toDouble(), longitude.toDouble() )
        val weatherstationid  = frostPolygon.data!!.get(0).id // en værstasjon sin ID: Blindern = SN18700



        val test1datesMutableLiveData =testdatesMutableLiveData
        Log.d("test1datesMutableLiveData",  test1datesMutableLiveData.value!!)
        Log.d("SN på første responsobjekt: skal være SN63595",  weatherstationid!!)

        val frost = dataFrost.fetchFrost( weatherstationid!!, referencetime = data )  //---------------Dette er rikitg dato


        val sightconditionsListofDataforMonth = frost.data

        return FrostInfo(
            sightconditionListofDataforMonth = sightconditionsListofDataforMonth!!
        )
    }
}