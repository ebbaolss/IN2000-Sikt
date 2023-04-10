package com.example.in2000_prosjekt.ui.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorite_table ORDER BY favorite ASC")
    fun getAlphabizedFavorites(): Flow<List<Favorite>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favorite: Favorite)

    @Query("DELETE FROM favorite_table")
    suspend fun deleteAll()

    /*
    @Delete
    fun deleteFavorite(favorite: Favorite)

     */
}