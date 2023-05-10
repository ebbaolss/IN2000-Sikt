package com.example.in2000_prosjekt.ui.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import com.example.in2000_prosjekt.ui.*
import io.github.boguszpawlowski.composecalendar.header.MonthState

class ImplementedWeatherRepository : WeatherRepository {

    val dataSource = DataSource(basePath = "https://gw-uio.intark.uh-it.no/in2000/weatherapi") //dette er både forecast og nowcast
    val dataMet = DataSourceAlerts(basePath = "https://gw-uio.intark.uh-it.no/in2000/weatherapi")
    val dataSunrise = DataSourceSunrise(basePath = "https://gw-uio.intark.uh-it.no/in2000/weatherapi")
    val dataFrost = DataSourceFrost(basePath = "https://frost.met.no/observations/v0.jsonld?")

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







    override suspend fun getFrost(latitude: String, longitude: String, referencetime: MonthState): FrostInfo { //åssen får jeg referense time inni her:
        val frostPolygon = dataFrost.fetchFrostWeatherStation(   latitude.toDouble(), longitude.toDouble() )
        val weatherstationid  = frostPolygon.data!!.get(0).id // en værstasjon sin ID: Blindern = SN18700

        Log.d("SN på første responsobjekt: skal være SN63595",  weatherstationid!!)

        val frost = dataFrost.fetchFrost( weatherstationid!!, referencetime = referencetime )  //---------------Dette er rikitg dato

        Log.d("Dette er referencetime som endes gjennom",  referencetime.currentMonth.toString()!!)

        val sightconditionsListofDataforMonth = frost.data

        return FrostInfo(
            sightconditionListofDataforMonth = sightconditionsListofDataforMonth!!
        )
    }
}