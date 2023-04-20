package com.example.in2000_prosjekt.ui.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Favorites")
class Favorite {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "FavoriteId")
    var coordinates: Int = 0

    @ColumnInfo(name = "longtitude")
    var longtitude: Double = 0.0

    @ColumnInfo(name = "latitude")
    var latitude: Double = 0.0

    constructor() {}

    constructor(longtitude: Double, latitude: Double){
        this.longtitude = longtitude
        this.latitude = latitude
    }
}
