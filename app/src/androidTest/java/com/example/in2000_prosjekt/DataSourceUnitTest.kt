package com.example.in2000_prosjekt

import kotlinx.coroutines.*
import org.junit.Test
import com.example.in2000_prosjekt.data.*
import junit.framework.TestCase.*


/*
 Dette er tester lagd med JUnit5

*/
class DataSourceUnitTest {


    // Unit test Nr 8: Test av LocationNow apiet
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


    // Unit test Nr 9: Test av NowCast apiet
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

    // Unit test Nr 10: Test av Met alert apoet
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

}