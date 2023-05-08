package com.example.in2000_prosjekt.ui.data

import android.util.Log
import com.example.in2000_prosjekt.ui.*
import java.text.SimpleDateFormat
import java.util.Date

class ImplementedWeatherRepository : WeatherRepository {

    val dataSource = DataSource(basePath = "https://gw-uio.intark.uh-it.no/in2000/weatherapi") //dette er både forecast og nowcast
    val dataMet = DataSourceAlerts(basePath = "https://gw-uio.intark.uh-it.no/in2000/weatherapi")
    val dataSunrise = DataSourceSunrise(basePath = "https://gw-uio.intark.uh-it.no/in2000/weatherapi")
    val dataFrost = DataSourceFrost(basePath = "https://frost.met.no/observations/v0.jsonld?")
    val dataMap = DataSourceMap()

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
        Log.d("getLocationEntry", "Entried")
        val forecast = dataSource.fetchLocationForecast(latitude, longitude, altitude)
        Log.d("getLocation", "Data retrieved")

        val currentTimeMillis = System.currentTimeMillis()
        val date = Date(currentTimeMillis)
        val dateFormat = SimpleDateFormat("HH")
        val tidspunkt = dateFormat.format(date)

        val timeDay1 = 24-tidspunkt.toInt()+12
        val timeDay2 = 24-tidspunkt.toInt()+12+24
        val timeDay3 = 24-tidspunkt.toInt()+12+48
        val timeDay4 = 24-tidspunkt.toInt()+12+72

        val temp = forecast.properties?.timeseries?.get(0)?.data?.instant?.details?.air_temperature
        val airfog = forecast.properties?.timeseries?.get(0)?.data?.instant?.details?.fog_area_fraction
        val rain = forecast.properties?.timeseries?.get(0)?.data?.next_1_hours?.details?.precipitation_amount
        val cloud_high = forecast.properties?.timeseries?.get(0)?.data?.instant?.details?.cloud_area_fraction_high
        val cloud_mid = forecast.properties?.timeseries?.get(0)?.data?.instant?.details?.cloud_area_fraction_medium
        val cloud_low = forecast.properties?.timeseries?.get(0)?.data?.instant?.details?.cloud_area_fraction_low
        val cloudiness = forecast.properties?.timeseries?.get(0)?.data?.instant?.details?.cloud_area_fraction

        val tempNext1 = forecast.properties?.timeseries?.get(1)?.data?.instant?.details?.air_temperature
        val tempNext2 = forecast.properties?.timeseries?.get(2)?.data?.instant?.details?.air_temperature
        val tempNext3 = forecast.properties?.timeseries?.get(3)?.data?.instant?.details?.air_temperature
        val tempNext4 = forecast.properties?.timeseries?.get(4)?.data?.instant?.details?.air_temperature
        val tempNext5 = forecast.properties?.timeseries?.get(5)?.data?.instant?.details?.air_temperature
        val tempNext6 = forecast.properties?.timeseries?.get(6)?.data?.instant?.details?.air_temperature
        val tempNext7 = forecast.properties?.timeseries?.get(7)?.data?.instant?.details?.air_temperature
        val tempNext8 = forecast.properties?.timeseries?.get(8)?.data?.instant?.details?.air_temperature
        val tempNext9 = forecast.properties?.timeseries?.get(9)?.data?.instant?.details?.air_temperature
        val tempNext10 = forecast.properties?.timeseries?.get(10)?.data?.instant?.details?.air_temperature
        val tempNext11 = forecast.properties?.timeseries?.get(11)?.data?.instant?.details?.air_temperature
        val tempNext12 = forecast.properties?.timeseries?.get(12)?.data?.instant?.details?.air_temperature

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

        val temp_day1 = forecast.properties?.timeseries?.get(timeDay1)?.data?.instant?.details?.air_temperature
        val temp_day2 = forecast.properties?.timeseries?.get(timeDay2)?.data?.instant?.details?.air_temperature
        val temp_day3 = forecast.properties?.timeseries?.get(timeDay3)?.data?.instant?.details?.air_temperature
        val temp_day4 = forecast.properties?.timeseries?.get(timeDay3)?.data?.instant?.details?.air_temperature

        val cloud_day1 = forecast.properties?.timeseries?.get(timeDay1)?.data?.instant?.details?.cloud_area_fraction
        val cloud_day2 = forecast.properties?.timeseries?.get(timeDay2)?.data?.instant?.details?.cloud_area_fraction
        val cloud_day3 = forecast.properties?.timeseries?.get(timeDay3)?.data?.instant?.details?.cloud_area_fraction
        val cloud_day4 = forecast.properties?.timeseries?.get(timeDay3)?.data?.instant?.details?.cloud_area_fraction


        return LocationInfo(
            temperatureL = (temp ?: -273.5) as Float,
            fog_area_fractionL = airfog!!,
            rainL = rain!!,
            cloud_area_fraction_high = cloud_high!!,
            cloud_area_fraction_medium = cloud_mid!!,
            cloud_area_fraction_low = cloud_low!!,
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
            temp_day1 = temp_day1!!,
            temp_day2 = temp_day2!!,
            temp_day3 = temp_day3!!,
            temp_day4 = temp_day4!!,
            cloud_day1 = cloud_day1!!,
            cloud_day2 = cloud_day2!!,
            cloud_day3 = cloud_day3!!,
            cloud_day4 = cloud_day4!!,
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
            sightcondition = typeFrost!!.toInt()
        )
    }

    override suspend fun getMap(path: String) : MapInfo {
        val mapJson = dataMap.fetchMapSearch(path)

        val mountains = HashMap<String, String>()

        //lage en liste som bare inneholder mountains poi
        for (item in mapJson.suggestions) {
            if (item.feature_type == "poi") {
                mountains[item.name!!] = item.mapbox_id!!
            }
        }

        return MapInfo(
            optionMountains = mountains
        )
    }
    override suspend fun getMapCoordinates(path: String) : MapCoordinatesInfo {
        val mapCoordinatesJson = dataMap.fetchMapCoordinates(path)

        val longitudeMap = mapCoordinatesJson.features?.get(0)?.geometry?.coordinates?.get(0)
        val latitudeMap = mapCoordinatesJson.features?.get(0)?.geometry?.coordinates?.get(1)

        return MapCoordinatesInfo(
            latitude = latitudeMap!!,
            longitude = longitudeMap!!
        )
    }
}