package com.example.in2000_prosjekt.ui.database


import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class FavoriteViewModel(application: Application) : ViewModel() {

    val allFavorites: LiveData<List<Favorite>>
    private val repository: FavoriteRepository
    val searchFavorites: MutableLiveData<List<Favorite>>

    init {
        val favoriteDb = FavoriteDatabase.getInstance(application)
        val favoriteDao = favoriteDb.favoriteDao()
        repository = FavoriteRepository(favoriteDao)

        allFavorites = repository.allFavorites
        searchFavorites = repository.searchFavorites
    }

    fun addFavorite(favorite: Favorite) {
        repository.addFavorite(favorite)
    }

    fun findFavorite(longtitude: Double, latitude: Double) {
        repository.findFavorite(longtitude, latitude)
    }

    fun deleteFavorite(longtitude: Double, latitude: Double) {
        repository.deleteFavorite(longtitude, latitude)
    }

    fun deleteAll(){
        repository.deleteAll()
    }
}

