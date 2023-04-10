package com.example.in2000_prosjekt.ui.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class FavoriteRepository(private val favoriteDao: FavoriteDao) {

    val allFavorites: Flow<List<Favorite>> = favoriteDao.getAlphabizedFavorites()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(favorite: Favorite){
        favoriteDao.insert(favorite)
    }

}