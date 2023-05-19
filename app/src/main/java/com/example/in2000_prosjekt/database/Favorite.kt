package com.example.in2000_prosjekt.database

import androidx.room.ColumnInfo
import androidx.room.Entity


@Entity(tableName = "Favorites", primaryKeys = ["longtitude", "latitude"])
class Favorite(//@NonNull
    @ColumnInfo(name = "longtitude") var longtitude: Double,//@NonNull
    @ColumnInfo(name = "latitude") var latitude: Double,
    @ColumnInfo(name = "mountainName") var mountainName: String,
    @ColumnInfo(name = "mountainHeight") var mountainHeight: Int
)