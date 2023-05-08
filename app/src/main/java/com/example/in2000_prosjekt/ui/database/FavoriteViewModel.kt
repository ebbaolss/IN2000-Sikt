package com.example.in2000_prosjekt.ui.database


import android.app.Application
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.in2000_prosjekt.ui.AlertInfo
import com.example.in2000_prosjekt.ui.AppUiState
import com.example.in2000_prosjekt.ui.LocationInfo
import com.example.in2000_prosjekt.ui.NowCastInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.utils.io.errors.*
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteViewModel(application: Application) : ViewModel() {

    private val _Uistate: MutableStateFlow<FavoriteUiState> = MutableStateFlow(FavoriteUiState.Loading)
    val favUiState: StateFlow<FavoriteUiState> = _Uistate.asStateFlow()

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

    fun deleteFavorite(longtitude: Double, latitude: Double) {
        repository.deleteFavorite(longtitude, latitude)
    }

    fun update() {
        viewModelScope.launch() {
            try {
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

                _Uistate.update {
                    FavoriteUiState.Success(
                        locationP,
                        nowCastP,
                        alertP
                    )
                }
            } catch (e: IOException) {// Inntreffer ved nettverksavbrudd
                _Uistate.update {
                    FavoriteUiState.Error
                }
            }
        }
    }

    fun deleteAll(){
        repository.deleteAll()
    }
}

