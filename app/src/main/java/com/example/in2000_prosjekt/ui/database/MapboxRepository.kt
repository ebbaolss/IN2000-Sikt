package com.example.in2000_prosjekt.ui.database

import com.mapbox.geojson.Point
import com.mapbox.geojson.Polygon
import com.mapbox.maps.MapView
import com.mapbox.maps.MapboxMap
import kotlin.math.cos
import kotlin.math.sin

class MapboxRepository {
    /**
     * Creates a circular Polygon
     * @param centerPoint is the center coordinates of the circular polygon
     * @param radius length of the circle radius in decimal degrees 0.1 ≈ 11 kilometers
     * @param numberOfVertices defines the resolution of the polygon. Six vertices creates a hexagon.
     * @return Polygon is a Mapbox geometry type that includes a list OF a list of Point objects.
     */
    fun createRoundPolygon(centerPoint: Point, radius: Double, numberOfVertices: Int): Polygon {
        val polygonPoints = mutableListOf<Point>()
        val angleBetweenVertices = 360.0 / numberOfVertices

        for (i in 0 until numberOfVertices) {
            val angle = i * angleBetweenVertices
            val x = centerPoint.longitude() + (radius * cos(Math.toRadians(angle)))
            val y = centerPoint.latitude() + (radius * sin(Math.toRadians(angle)))
            polygonPoints.add(Point.fromLngLat(x, y))
        }

        return Polygon.fromLngLats(listOf(polygonPoints))
    }


}