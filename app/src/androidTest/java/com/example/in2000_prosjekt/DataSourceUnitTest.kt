package com.example.in2000_prosjekt

import kotlinx.coroutines.*
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test
import com.example.in2000_prosjekt.ui.data.*
import junit.framework.TestCase.*


/*
 Dette er tester lagd med JUnit5

*/
class DataSourceUnitTest {



    val dataMet = DataSourceAlerts(basePath = "https://gw-uio.intark.uh-it.no/in2000/weatherapi")
    val dataSunrise = DataSourceSunrise(basePath = "https://gw-uio.intark.uh-it.no/in2000/weatherapi")
    val dataFrost = DataSourceFrost(basePath = "https://frost.met.no/observations/v0.jsonld?")


    //Arrange
    private val Frostapirespons :  DataSourceFrost = DataSourceFrost(basePath = "https://frost.met.no/observations/v0.jsonld?")

    @Test
    fun coordinatesToPolygonConverterTest() = runTest(UnconfinedTestDispatcher()) {
        //Act
        launch {
            val frostapirespons = Frostapirespons.coordinatesToPolygonConverter(60.0,10.0)
            //Assert
            assertEquals("POLYGON((10.0 60.0 , 10.0 60.1 , 10.1 60.0 , 10.1 60.1 ))", frostapirespons )
        }
    }

    //Arrange
    private val Locationapirespons = DataSource(basePath = "https://gw-uio.intark.uh-it.no/in2000/weatherapi") // Forecast og nowcast bruker samme api
    @Test
    fun testfetchLocationForecast() = runBlocking {

        //Act
        val locationapirespons = Locationapirespons.fetchLocationForecast("70", "12")

        //Assert
        assertEquals("Point", locationapirespons.geometry?.type)
        assertEquals("[12.0, 70.0, 0.0]", locationapirespons.geometry?.coordinates.toString())
        assertNotNull(locationapirespons.geometry)
        assertNotNull(locationapirespons.properties)
    }



    //Arrange
    private val nowcastapirespons :  DataSource = DataSource(basePath = "https://gw-uio.intark.uh-it.no/in2000/weatherapi")
    @Test
    fun testfetchNowCast() = runBlocking {

        //Act
        val nowcastapirespons = nowcastapirespons.fetchNowCast("60.32", "8")

        //Assert
        assertEquals("Feature", nowcastapirespons.type)
        assertEquals("[8.0, 60.32, 1183.0]", nowcastapirespons.geometry?.coordinates.toString())

        assertNotNull(nowcastapirespons.properties?.meta)
        assertNotNull(nowcastapirespons.properties?.timeseries?.size)
    }

    //Arrange
    private val alertapirespons :  DataSourceAlerts = DataSourceAlerts(basePath = "https://gw-uio.intark.uh-it.no/in2000/weatherapi")
    @Test
    fun testfetchMetAlerts() = runBlocking {

        //Act
        val alertapirespons = alertapirespons.fetchMetAlert("65", "9")

        //Assert
        assertEquals("FeatureCollection", alertapirespons.type)
        assertEquals("no", alertapirespons.lang)

        assertNotNull(alertapirespons.lastChange)
        assertNotNull(alertapirespons.features)
    }


    //Arrange
    private val sunriserespons :  DataSourceSunrise = DataSourceSunrise(basePath = "https://gw-uio.intark.uh-it.no/in2000/weatherapi")
    @Test
    fun testfetchSunrise() = runBlocking {

        //Act
        val sunriseapirespons = sunriserespons.fetchSunrise("65", "9")

        //Assert
        assertEquals("https://api.met.no/license_data.html", sunriseapirespons.licenseURL)

        assertEquals("MET Norway", sunriseapirespons.copyright)

        assertNotNull(sunriseapirespons.geometry)
        assertNotNull(sunriseapirespons.properties?.sunrise)
        assertNotNull(sunriseapirespons.properties?.sunset)
    }





}