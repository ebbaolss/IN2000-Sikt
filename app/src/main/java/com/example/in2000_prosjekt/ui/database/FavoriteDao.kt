package com.example.in2000_prosjekt.ui.database

import androidx.room.*

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavorite(favorite: Favorite)

    @Query("SELECT * FROM Favorites WHERE coordinates = :coordinate")
    fun findFavoriteByCoordinate(coordinate: String): Favorite

    @Query("SELECT * FROM favorites")
    fun getAllFavorites(): List<Favorite>

    @Delete
    suspend fun deleteFav(favorite: Favorite)
}