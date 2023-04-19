package com.example.in2000_prosjekt.ui.database


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


class FavoriteViewModel @Inject constructor(private val favoriteRepository: FavoriteRepository) :
    ViewModel() {

    init {
        getAllFavorites()
    }

    val favoriteList: LiveData<List<Favorite>> = favoriteRepository.allFavorites

    val foundFavorite: LiveData<Favorite> = favoriteRepository.foundFavorite

    fun getAllFavorites() {
        favoriteRepository.getAllFavorites()
    }

    fun addFavorite(favorite: Favorite) {
        favoriteRepository.addFavorite(favorite)
        getAllFavorites()
    }

    fun findByCoordinate(coordinate: String) {
        favoriteRepository.findByCoordinate(coordinate)
    }

    fun deleteEmployee(favorite: Favorite) {
        favoriteRepository.deleteFavorite(favorite)
        getAllFavorites()
    }
}

