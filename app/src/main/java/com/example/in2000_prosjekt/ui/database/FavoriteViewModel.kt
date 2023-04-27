package com.example.in2000_prosjekt.ui.database


import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.in2000_prosjekt.ui.AppUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class FavoriteViewModel(application: Application) : ViewModel() {

    private val _appUistate: MutableStateFlow<AppUiState> = MutableStateFlow(AppUiState.Loading)
    val appUiState: StateFlow<AppUiState> = _appUistate.asStateFlow()

    val allFavorites: LiveData<List<Favorite>>
    private val repository: FavoriteRepository
    val searchFavorites: MutableLiveData<List<Favorite>>

    init {
        val favoriteDb = FavoriteDatabase.getInstance(application)
        val favoriteDao = favoriteDb.favoriteDao()
        repository = FavoriteRepository(favoriteDao)

        allFavorites = repository.allFavorites
        searchFavorites = repository.searchFavorites
        _appUistate.update {
            AppUiState.Success()
        }
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

