package com.example.in2000_prosjekt.ui.data

import android.util.Log
import com.example.in2000_prosjekt.ui.*

class ImplementedWeatherRepository : WeatherRepository {

    val dataSource = DataSource(basePath = "https://gw-uio.intark.uh-it.no/in2000/weatherapi") //dette er både forecast og nowcast
    val dataMet = DataSourceAlerts(basePath = "https://gw-uio.intark.uh-it.no/in2000/weatherapi")
    val dataSunrise = DataSourceSunrise(basePath = "https://gw-uio.intark.uh-it.no/in2000/weatherapi")
    val dataFrost = DataSourceFrost(basePath = "https://frost.met.no/observations/v0.jsonld?")
    val dataMapSearch = DataSourceMap()

    //----------------------
    //Frost: // frost test variabler: Disse trengs ikke
    val source = "SN18700" //Dette er navnet på en værstasjon: Frost API'et krever at man bruker en værstasjon sin id, når man gjøre forespøsler etter historisk værdata
    //----------------------

    override suspend fun getFrost(
        latitude: String,
        longitude: String,
        referencetime: String
    ): MutableList<FrostInfo> {

        val nearweatherstationpolygon = dataFrost.fetchApiSvarkoordinater(latitude, longitude)
        val nearestweatherstationid =
            nearweatherstationpolygon.data?.get(0)?.id // denne henter nærmeste værstasjons id, basert på et polygon
        val historicdata = dataFrost.fetchFrostTemp(
            "mean(cloud_area_fraction%20P1D)",
            referencetime,
            nearestweatherstationid!!
        )

        val mutableFrostInfoList: MutableList<FrostInfo> = mutableListOf()

        historicdata.data?.forEach { dataFrost ->
            val referencetime = dataFrost.referenceTime
            val sightCondition = dataFrost.observations?.first()?.value

            if (referencetime != null && sightCondition != null) {
                mutableFrostInfoList.add(
                    FrostInfo(
                        referencetime,             // dato målingen ble gjort
                        sightCondition.toString()  // En double verdi fra 0 - 8 lagret som en string
                    )
                )
            }
        }

        Log.d("Sightconditions", mutableFrostInfoList.toString())

        return mutableFrostInfoList
    }

    override suspend fun getLocation(
        latitude: String,
        longitude: String,
        altitude: String
    ): LocationInfo {
        val forecast = dataSource.fetchLocationForecast(latitude, longitude, altitude)

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
    override suspend fun getNowCast(latitude: String, longitude: String, altitude: String): NowCastInfo {
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

    override suspend fun getMap(path: String) : MapInfo {
        val mapJson = dataMapSearch.fetchMapSearch(path)
        //listeHer med fjell som forslag
        var mountains = HashMap<String, String>() //maks 3 elementer, de siste 3 søkt på

        //while og sortere ting, lages en liste
        for (item in mapJson.suggestions) {
            if (item.feature_type == "poi") {
                mountains[item.name!!] = item.mapbox_id!!
            }
        }

        return MapInfo(
            optionMountains = mountains
        )
    }

    //ny fun for å hentew indeks mapsearch
}

