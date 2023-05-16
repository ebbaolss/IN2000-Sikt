package com.example.in2000_prosjekt.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favorite: Favorite)

    @Query("SELECT * FROM favorites WHERE longtitude LIKE :longtitude AND latitude LIKE :latitude")
    fun findFavorite(longtitude: Double, latitude: Double): List<Favorite>

    @Query("SELECT * FROM favorites")
    fun getAllFavorites(): LiveData<List<Favorite>>

    @Query("DELETE FROM favorites WHERE longtitude LIKE :longtitude AND latitude LIKE :latitude")
    fun deleteFav(longtitude: Double, latitude: Double)

    @Query("DELETE FROM favorites")
    fun deleteAll()

}