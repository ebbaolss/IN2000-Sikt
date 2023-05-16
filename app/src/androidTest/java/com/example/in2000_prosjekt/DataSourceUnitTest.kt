package com.example.in2000_prosjekt

import kotlinx.coroutines.*
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test
import com.example.in2000_prosjekt.data.*
import junit.framework.TestCase.*


/*
 Dette er tester lagd med JUnit5

*/
class DataSourceUnitTest {


// Unit test Nr 10: Test av frost apoet
    //Arrange
    private val frostcoordinatesgenerator :  DataSourceFrost = DataSourceFrost(basePath = "https://frost.met.no/observations/v0.jsonld?")

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun coordinatesToPolygonConverterTest() = runTest(UnconfinedTestDispatcher()) {
        //Act
        launch {
            val frostapirespons = frostcoordinatesgenerator.coordinatesToPolygonConverter(60.0,10.0)
            //Assert
            assertEquals("POLYGON((10.0 60.0 , 10.0 60.1 , 10.1 60.0 , 10.1 60.1 ))", frostapirespons )
        }
    }

    // Unit test Nr 11: Test av LocationNow apiet
    //Arrange
    private val locationapirespons = DataSource(basePath = "https://gw-uio.intark.uh-it.no/in2000/weatherapi") // Forecast og nowcast bruker samme api
    @Test
    fun testfetchLocationForecast() = runBlocking {

        //Act
        val locationapirespons = locationapirespons.fetchLocationForecast("70", "12")

        //Assert
        assertEquals("Point", locationapirespons.geometry?.type)
        assertEquals("[12.0, 70.0, 0.0]", locationapirespons.geometry?.coordinates.toString())
        assertNotNull(locationapirespons.geometry)
        assertNotNull(locationapirespons.properties)
    }


    // Unit test Nr 12: Test av NowCast apiet
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

    // Unit test Nr 13: Test av Met alert apoet
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


    // Unit test Nr 14: Test av sunrise apiet
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


    // Unit test Nr 14: Test av frost apoet
    //Arrange
    private val frostrespons :   DataSourceFrost = DataSourceFrost(basePath = "https://frost.met.no/observations/v0.jsonld?")
    @Test
    fun testfetchfrost() = runBlocking {

        //Act
        val frosteapirespons = frostrespons.fetchFrostWeatherStation("58.1447".toDouble(), "7.9982".toDouble(), )

        //Assert
        assertEquals("SourceResponse", frosteapirespons.type)
        assertEquals("https://frost.met.no/schema", frosteapirespons.context)


        assertNotNull(frosteapirespons.data)
        assertNotNull(frosteapirespons.createdAt)

    }






}