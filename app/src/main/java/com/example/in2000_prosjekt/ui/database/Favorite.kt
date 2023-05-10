package com.example.in2000_prosjekt.ui.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity


@Entity(tableName = "Favorites", primaryKeys = ["longtitude", "latitude"])
class Favorite {

    /*
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "FavoriteId")
    var coordinates: Int = 0
    */

    @NonNull
    @ColumnInfo(name = "longtitude")
    var longtitude: Double = 0.0

    @NonNull
    @ColumnInfo(name = "latitude")
    var latitude: Double = 0.0

    @ColumnInfo(name = "mountainName")
    var mountainName: String = ""

    @ColumnInfo(name = "mountainHeight")
    var mountainHeight: Int = 0

    constructor() {}

    constructor(longtitude: Double, latitude: Double, mountainName: String, mountainHeight: Int) {
        this.longtitude = longtitude
        this.latitude = latitude
        this.mountainName = mountainName
        this.mountainHeight = mountainHeight
    }
}