package com.example.in2000_prosjekt.database


import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.utils.io.errors.*
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application) : ViewModel() {

    private val uiState: MutableStateFlow<FavoriteUiState> = MutableStateFlow(FavoriteUiState.Loading)
    val favUiState: StateFlow<FavoriteUiState> = uiState.asStateFlow()

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
        Log.d("ADDED", "with long: ${favorite.longtitude}, lat: ${favorite.latitude}, name: ${favorite.mountainName}, height: ${favorite.mountainHeight}")
        repository.addFavorite(favorite)
    }

    fun findFavorite(longtitude: Double, latitude: Double,  location : String , height : Int){
        Log.d("FINDING", "with long: $longtitude, lat : $latitude, name: $location, height: $height")
        repository.findFavorite(longtitude, latitude)
    }

    fun deleteUpdate(longtitude: Double, latitude: Double) {
        viewModelScope.launch {
            try {
                viewModelScope.async {
                    repository.deleteFavorite(longtitude, latitude)
                }
                val locationInfo = viewModelScope.async {
                    repository.getLocationList()
                }
                val locationP = locationInfo.await()
                val nowCastInfo = viewModelScope.async {
                    repository.getNowList()
                }
                val nowCastP = nowCastInfo.await()
                val alertInfo = viewModelScope.async {
                    repository.getAlertInfo()
                }
                val alertP = alertInfo.await()

                uiState.update {
                    FavoriteUiState.Success(
                        locationP,
                        nowCastP,
                        alertP
                    )
                }
            } catch (e: IOException) {// Inntreffer ved nettverksavbrudd
                uiState.update {
                    FavoriteUiState.Error
                }
            }
        }
    }

    fun update() {
        viewModelScope.launch {
            try {
                val locationInfo = viewModelScope.async {
                    repository.getLocationList()
                }
                val locationP = locationInfo.await()
                Log.d("UPDATE", "locationinfo updated size: ${locationP.size}")
                val nowCastInfo = viewModelScope.async {
                    repository.getNowList()
                }
                val nowCastP = nowCastInfo.await()
                Log.d("UPDATE", "nowcast updated size: ${nowCastP.size}")
                val alertInfo = viewModelScope.async {
                    repository.getAlertInfo()
                }
                val alertP = alertInfo.await()
                Log.d("UPDATE", "alert updated size: ${alertP.size}")

                uiState.update {
                    FavoriteUiState.Success(
                        locationP,
                        nowCastP,
                        alertP
                    )
                }
            } catch (e: IOException) {
                Log.d("ERROR", "error i update")
                uiState.update {
                    FavoriteUiState.Error
                }
            }
        }
    }
    fun updateEmpty(){
        viewModelScope.launch {
            try {
                uiState.update {
                    FavoriteUiState.Success(
                        mutableListOf(),
                        mutableListOf(),
                        mutableListOf()
                    )
                }
            } catch (e: IOException) {// Inntreffer ved nettverksavbrudd
                uiState.update {
                    FavoriteUiState.Error
                }
            }
        }
    }

    fun deleteAll(){
        repository.deleteAll()
    }
}
