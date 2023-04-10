package com.example.in2000_prosjekt.ui.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_table")
data class Favorite(
    @PrimaryKey val coordinate: String,
    @ColumnInfo(name = "favorite") val favorite: String
    )


