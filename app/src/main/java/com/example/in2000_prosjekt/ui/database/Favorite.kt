package com.example.in2000_prosjekt.ui.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Favorites")
data class Favorite(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "Coordinates")
    var coordinates: String,

    @ColumnInfo(name = "Long")
    var longitude: Double,

    @ColumnInfo(name = "Lat")
    var latitude: Double,
)
