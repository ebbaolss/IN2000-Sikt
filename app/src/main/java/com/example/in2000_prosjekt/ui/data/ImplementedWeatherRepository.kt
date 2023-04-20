package com.example.in2000_prosjekt.ui.data

import android.util.Log
import com.example.in2000_prosjekt.ui.*

class ImplementedWeatherRepository : WeatherRepository {

    val dataSource = DataSource(basePath = "https://gw-uio.intark.uh-it.no/in2000/weatherapi") //dette er både forecast og nowcast
    val dataMet = DataSourceAlerts(basePath = "https://gw-uio.intark.uh-it.no/in2000/weatherapi")
    val dataSunrise = DataSourceSunrise(basePath = "https://gw-uio.intark.uh-it.no/in2000/weatherapi")
    val dataFrost = DataSourceFrost(basePath = "https://frost.met.no/observations/v0.jsonld?")

    //----------------------
    //Frost:
    var elements = "air_temperature"// Dette er værmålingen vi ønsker: For enkelthetsskyld så velges bare: air temperature
    var referencetime ="2021-05-17%2F2021-05-17" // Frost API, bruker UTC-tidsformat, denne ønskes senere å kunne bestemmes av en bruker ved hjelp av en Date picker (en bibloteksfunskjon i jetpack compose)
    //var url_med_Polygon ="https://frost.met.no/sources/v0.jsonld?types=SensorSystem&elements=air_temperature&geometry=POLYGON((7.9982%2058.1447%20%2C%208.0982%2058.1447%20%2C7.9982%2058.2447%20%2C%208.0982%2058.2447%20))"
    val source = "SN18700" //skjønner ikke denne, hvor får vi dette fra? Hva er det? Spørr Nebil
    //----------------------

    override suspend fun getLocation(
        latitude: String,
        longitude: String,
        altitude: String
    ): LocationInfo {
        val forecast = dataSource.fetchLocationForecast(latitude, longitude, altitude)

        val temp = forecast.properties?.timeseries?.get(0)?.data?.instant?.details?.air_temperature
        val airfog =
            forecast.properties?.timeseries?.get(0)?.data?.instant?.details?.fog_area_fraction
        val rain =
            forecast.properties?.timeseries?.get(0)?.data?.next_1_hours?.details?.get("precipitation_amount")

        return LocationInfo(
            temperatureL = (temp ?: -273.5) as Float,
            fog_area_fractionL = airfog!!,
            rainL = rain!!
        )
    }

    override suspend fun getNowCast(
        latitude: String,
        longitude: String,
        altitude: String
    ): NowCastInfo {
        val forecastNow = dataSource.fetchNowCast(latitude, longitude, altitude)

        val tempNow =
            forecastNow.properties?.timeseries?.get(0)?.data?.instant?.details?.air_temperature
        val windN = forecastNow.properties?.timeseries?.get(0)?.data?.instant?.details?.wind_speed

        return NowCastInfo(
            temperatureNow = (tempNow ?: -273.5) as Float, //dette må fikses bedre
            windN = windN!! //funker dette eller må jeg gjøre som over?
        )
    }

    override suspend fun getSunrise(latitude: String, longitude: String): SunriseInfo {
        val sunrise = dataSunrise.fetchSunrise(latitude, longitude)

        val sunriseToday = sunrise.properties?.sunrise?.time
        val sunsetToday = sunrise.properties?.sunset?.time

        return SunriseInfo(
            sunriseS = sunriseToday!!,
            sunsetS = sunsetToday!!
        )
    }

    override suspend fun getAlert(latitude: String, longitude: String): MutableList<AlertInfo> {
        val alert = dataMet.fetchMetAlert(latitude, longitude)

        val alertList : MutableList<AlertInfo> = mutableListOf()
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
        return alertList
    }

    override suspend fun getFrost(latitude: String, longitude: String): FrostInfo {

        val frost = dataFrost.fetchFrostTemp(elements, referencetime, source)  //hardkoded parameterne, fiks dette
        val frostPolygon = dataFrost.fetchApiSvarkoordinater(latitude, longitude)

        val typeFrost = frost.type
        val long = frostPolygon.data?.get(0)?.geometry?.coordinates?.get(0)
        val lat = frostPolygon.data?.get(0)?.geometry?.coordinates?.get(1)

        Log.d("typefrost", typeFrost.toString())
        Log.d("lat", lat.toString())
        Log.d("long", long.toString())

        return FrostInfo(
            typeFrost = typeFrost.toString(), //ikke egt ha toString her
            longFrost = long!!,
            latFrost = lat!!,
        )
    }
}