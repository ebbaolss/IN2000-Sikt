package com.example.in2000_prosjekt.ui.database


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class FavoriteRepository(private val favoriteDao: FavoriteDao) {

    val allFavorites: LiveData<List<Favorite>> = favoriteDao.getAllFavorites()
    val searchFavorites = MutableLiveData<List<Favorite>>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun addFavorite(newFavorite: Favorite) {
        coroutineScope.launch(Dispatchers.IO) {
            favoriteDao.addFavorite(newFavorite)
        }
    }

    fun deleteFavorite(longtitude: Double, latitude: Double) {
        coroutineScope.launch(Dispatchers.IO) {
            favoriteDao.deleteFav(longtitude, latitude)
        }
    }

    fun findFavorite(longtitude: Double, latitude: Double) {
        coroutineScope.launch(Dispatchers.Main) {
            searchFavorites.value = asyncFind(longtitude, latitude).await()
        }
    }

    private fun asyncFind(longtitude: Double, latitude: Double): Deferred<List<Favorite>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async favoriteDao.findFavorite(longtitude, latitude)
        }

}