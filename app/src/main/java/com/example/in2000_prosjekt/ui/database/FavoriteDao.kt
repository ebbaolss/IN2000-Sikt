package com.example.in2000_prosjekt.ui.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoriteDao {

    @Insert()
    suspend fun addFavorite(favorite: Favorite)

    @Query("SELECT * FROM Favorites WHERE longtitude = :longtitude AND latitude = :latitude")
    fun findFavorite(longtitude: Double, latitude: Double): List<Favorite>

    @Query("SELECT * FROM favorites")
    fun getAllFavorites(): LiveData<List<Favorite>>

    @Query("DELETE FROM favorites WHERE longtitude = :longtitude AND latitude = :latitude")
    fun deleteFav(longtitude: Double, latitude: Double)

    @Query("DELETE FROM favorites")
    fun deleteAll()

}