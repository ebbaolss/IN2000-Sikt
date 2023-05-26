package com.example.in2000_prosjekt.data.repository

import com.example.in2000_prosjekt.data.datasource.DataSource
import com.example.in2000_prosjekt.data.datasource.DataSourceAlerts
import com.example.in2000_prosjekt.data.datasource.DataSourceMap
import com.example.in2000_prosjekt.ui.uistate.AlertInfo
import com.example.in2000_prosjekt.ui.uistate.LocationInfo
import com.example.in2000_prosjekt.ui.uistate.MapUiState
import com.example.in2000_prosjekt.ui.uistate.NowCastInfo
import java.text.SimpleDateFormat
import java.util.Date

class ImplementedWeatherRepository : WeatherRepository {

    private val dataSource = DataSource(basePath = "https://gw-uio.intark.uh-it.no/in2000/weatherapi") //dette er både forecast og nowcast
    private val dataMet = DataSourceAlerts(basePath = "https://gw-uio.intark.uh-it.no/in2000/weatherapi")
    private val dataMap = DataSourceMap()

    override suspend fun getLocation(
        latitude: String,
        longitude: String,
        altitude: String
    ): LocationInfo {
        val forecast = dataSource.fetchLocationForecast(latitude, longitude, altitude)

        val currentTimeMillis = System.currentTimeMillis()
        val date = Date(currentTimeMillis)
        val dateFormat = SimpleDateFormat("HH")
        val tidspunkt = dateFormat.format(date)

        val timeDay1 = 24-tidspunkt.toInt()+12
        val timeDay2 = 24-tidspunkt.toInt()+12+24
        val timeDay3 = 24-tidspunkt.toInt()+12+48

        val temp = forecast.properties?.timeseries?.get(0)?.data?.instant?.details?.air_temperature?.toInt()
        val airfog = forecast.properties?.timeseries?.get(0)?.data?.instant?.details?.fog_area_fraction
        val rain = forecast.properties?.timeseries?.get(0)?.data?.next_1_hours?.details?.precipitation_amount
        val cloudHigh = forecast.properties?.timeseries?.get(0)?.data?.instant?.details?.cloud_area_fraction_high
        val cloudMid = forecast.properties?.timeseries?.get(0)?.data?.instant?.details?.cloud_area_fraction_medium
        val cloudLow = forecast.properties?.timeseries?.get(0)?.data?.instant?.details?.cloud_area_fraction_low
        val cloudiness = forecast.properties?.timeseries?.get(0)?.data?.instant?.details?.cloud_area_fraction

        val tempNext1 = forecast.properties?.timeseries?.get(1)?.data?.instant?.details?.air_temperature?.toInt()
        val tempNext2 = forecast.properties?.timeseries?.get(2)?.data?.instant?.details?.air_temperature?.toInt()
        val tempNext3 = forecast.properties?.timeseries?.get(3)?.data?.instant?.details?.air_temperature?.toInt()
        val tempNext4 = forecast.properties?.timeseries?.get(4)?.data?.instant?.details?.air_temperature?.toInt()
        val tempNext5 = forecast.properties?.timeseries?.get(5)?.data?.instant?.details?.air_temperature?.toInt()
        val tempNext6 = forecast.properties?.timeseries?.get(6)?.data?.instant?.details?.air_temperature?.toInt()
        val tempNext7 = forecast.properties?.timeseries?.get(7)?.data?.instant?.details?.air_temperature?.toInt()
        val tempNext8 = forecast.properties?.timeseries?.get(8)?.data?.instant?.details?.air_temperature?.toInt()
        val tempNext9 = forecast.properties?.timeseries?.get(9)?.data?.instant?.details?.air_temperature?.toInt()
        val tempNext10 = forecast.properties?.timeseries?.get(10)?.data?.instant?.details?.air_temperature?.toInt()
        val tempNext11 = forecast.properties?.timeseries?.get(11)?.data?.instant?.details?.air_temperature?.toInt()
        val tempNext12 = forecast.properties?.timeseries?.get(12)?.data?.instant?.details?.air_temperature?.toInt()

        val cloudinessNext1 = forecast.properties?.timeseries?.get(1)?.data?.instant?.details?.cloud_area_fraction
        val cloudinessNext2 = forecast.properties?.timeseries?.get(2)?.data?.instant?.details?.cloud_area_fraction
        val cloudinessNext3 = forecast.properties?.timeseries?.get(3)?.data?.instant?.details?.cloud_area_fraction
        val cloudinessNext4 = forecast.properties?.timeseries?.get(4)?.data?.instant?.details?.cloud_area_fraction
        val cloudinessNext5 = forecast.properties?.timeseries?.get(5)?.data?.instant?.details?.cloud_area_fraction
        val cloudinessNext6 = forecast.properties?.timeseries?.get(6)?.data?.instant?.details?.cloud_area_fraction
        val cloudinessNext7 = forecast.properties?.timeseries?.get(7)?.data?.instant?.details?.cloud_area_fraction
        val cloudinessNext8 = forecast.properties?.timeseries?.get(8)?.data?.instant?.details?.cloud_area_fraction
        val cloudinessNext9 = forecast.properties?.timeseries?.get(9)?.data?.instant?.details?.cloud_area_fraction
        val cloudinessNext10 = forecast.properties?.timeseries?.get(10)?.data?.instant?.details?.cloud_area_fraction
        val cloudinessNext11 = forecast.properties?.timeseries?.get(11)?.data?.instant?.details?.cloud_area_fraction
        val cloudinessNext12 = forecast.properties?.timeseries?.get(12)?.data?.instant?.details?.cloud_area_fraction

        val tempDay1 = forecast.properties?.timeseries?.get(timeDay1)?.data?.instant?.details?.air_temperature?.toInt()
        val tempDay2 = forecast.properties?.timeseries?.get(timeDay2)?.data?.instant?.details?.air_temperature?.toInt()
        val tempDay3 = forecast.properties?.timeseries?.get(timeDay3)?.data?.instant?.details?.air_temperature?.toInt()
        val tempDay4 = forecast.properties?.timeseries?.get(timeDay3)?.data?.instant?.details?.air_temperature?.toInt()

        val cloudDay1 = forecast.properties?.timeseries?.get(timeDay1)?.data?.instant?.details?.cloud_area_fraction
        val cloudDay2 = forecast.properties?.timeseries?.get(timeDay2)?.data?.instant?.details?.cloud_area_fraction
        val cloudDay3 = forecast.properties?.timeseries?.get(timeDay3)?.data?.instant?.details?.cloud_area_fraction
        val cloudDay4 = forecast.properties?.timeseries?.get(timeDay3)?.data?.instant?.details?.cloud_area_fraction

        return LocationInfo(
            temperatureL = temp!!,
            fog_area_fractionL = airfog!!,
            rainL = rain!!,
            cloud_area_fraction_high = cloudHigh!!,
            cloud_area_fraction_medium = cloudMid!!,
            cloud_area_fraction_low = cloudLow!!,
            cloud_area_fraction = cloudiness!!,

            tempNext1 = tempNext1!!,
            tempNext2 = tempNext2!!,
            tempNext3 = tempNext3!!,
            tempNext4 = tempNext4!!,
            tempNext5 = tempNext5!!,
            tempNext6 = tempNext6!!,
            tempNext7 = tempNext7!!,
            tempNext8 = tempNext8!!,
            tempNext9 = tempNext9!!,
            tempNext10 = tempNext10!!,
            tempNext11 = tempNext11!!,
            tempNext12 = tempNext12!!,
            
            cloudinessNext1 = cloudinessNext1!!,
            cloudinessNext2 = cloudinessNext2!!,
            cloudinessNext3 = cloudinessNext3!!,
            cloudinessNext4 = cloudinessNext4!!,
            cloudinessNext5 = cloudinessNext5!!,
            cloudinessNext6 = cloudinessNext6!!,
            cloudinessNext7 = cloudinessNext7!!,
            cloudinessNext8 = cloudinessNext8!!,
            cloudinessNext9 = cloudinessNext9!!,
            cloudinessNext10 = cloudinessNext10!!,
            cloudinessNext11 = cloudinessNext11!!,
            cloudinessNext12 = cloudinessNext12!!,
            
            temp_day1 = tempDay1!!,
            temp_day2 = tempDay2!!,
            temp_day3 = tempDay3!!,
            temp_day4 = tempDay4!!,
            cloud_day1 = cloudDay1!!,
            cloud_day2 = cloudDay2!!,
            cloud_day3 = cloudDay3!!,
            cloud_day4 = cloudDay4!!,
        )
    }

    override suspend fun getNowCast(
        latitude: String,
        longitude: String,
        altitude: String
    ): NowCastInfo {
        val forecastNow = dataSource.fetchNowCast(latitude, longitude, altitude)

        val tempNow =
            forecastNow.properties?.timeseries?.get(0)?.data?.instant?.details?.air_temperature?.toInt()
        val windN = forecastNow.properties?.timeseries?.get(0)?.data?.instant?.details?.wind_speed

        return NowCastInfo(
            temperatureNow = tempNow!!,
            windN = windN!!
        )
    }

    override suspend fun getAlert(latitude: String, longitude: String): MutableList<AlertInfo> {
        val alert = dataMet.fetchMetAlert(latitude, longitude)

        val alertList : MutableList<AlertInfo> = mutableListOf()
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
        return alertList
    }

    override suspend fun getMap(path: String) : MapUiState.MapInfo {
        val mapJson = dataMap.fetchMapSearch(path)

        val mountains = HashMap<String, String>()

        //lage en liste som bare inneholder mountains poi
        for (item in mapJson.suggestions) {
            if (item.feature_type == "poi") {
                mountains[item.name!!] = item.mapbox_id!!
            }
        }

        return MapUiState.MapInfo(
            optionMountains = mountains
        )
    }
    override suspend fun getMapCoordinates(path: String) : MapUiState.MapCoordinatesInfo {
        val mapCoordinatesJson = dataMap.fetchMapCoordinates(path)

        val longitudeMap = mapCoordinatesJson.features?.get(0)?.geometry?.coordinates?.get(0)
        val latitudeMap = mapCoordinatesJson.features?.get(0)?.geometry?.coordinates?.get(1)

        return MapUiState.MapCoordinatesInfo(
            latitude = latitudeMap!!,
            longitude = longitudeMap!!
        )
    }
}