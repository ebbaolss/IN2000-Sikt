package com.example.in2000_prosjekt.ui.database


import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteRepository(private val favoriteDao: FavoriteDao) {

    val allFavorites = MutableLiveData<List<Favorite>>()
    val foundFavorite = MutableLiveData<Favorite>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun addFavorite(newFavorite: Favorite) {
        coroutineScope.launch(Dispatchers.IO) {
            favoriteDao.addFavorite(newFavorite)
        }
    }

    fun getAllFavorites() {
        coroutineScope.launch(Dispatchers.IO) {
            allFavorites.postValue(favoriteDao.getAllFavorites())
        }
    }

    fun deleteFavorite(favorite: Favorite) {
        coroutineScope.launch(Dispatchers.IO) {
            favoriteDao.deleteFav(favorite)
        }
    }

    fun findByCoordinate(coordinate: String) {
        coroutineScope.launch(Dispatchers.IO) {
            foundFavorite.postValue(favoriteDao.findFavoriteByCoordinate(coordinate))
        }
    }

}