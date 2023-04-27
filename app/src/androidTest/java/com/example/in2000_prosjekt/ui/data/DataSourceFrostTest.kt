package com.example.in2000_prosjekt.ui.data

import android.util.Log

import org.junit.Test



 class DataSourceFrostTest {

   // var DataSourceFrost(basepath=).

    //@Mock
    //lateinit DataSourceFrost()

     var a :  DataSourceFrost = DataSourceFrost(basePath = "https://frost.met.no/observations/v0.jsonld?")


    @Test
    suspend fun coordinatesToPolygonConvertertest() {

        var b = a.fetchApiSvarkoordinater(60.0,10.0)

        //Log.d("Test av testen", b.toString() )

       // assertEquals("",a.coordinatesToPolygonConverter(60.0 ,10.0) )

    }
}