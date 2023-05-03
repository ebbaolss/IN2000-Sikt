package com.example.in2000_prosjekt.ui.database


import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.in2000_prosjekt.ui.AlertInfo
import com.example.in2000_prosjekt.ui.AppUiState
import com.example.in2000_prosjekt.ui.LocationInfo
import com.example.in2000_prosjekt.ui.NowCastInfo
import com.example.in2000_prosjekt.ui.data.DataSource
import com.example.in2000_prosjekt.ui.data.ImplementedWeatherRepository
import com.example.in2000_prosjekt.ui.data.WeatherRepository
import kotlinx.coroutines.*

class FavoriteRepository(private val favoriteDao: FavoriteDao) {

    val weatherRepository = ImplementedWeatherRepository()
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

    fun deleteAll(){
        coroutineScope.launch(Dispatchers.IO) {
            favoriteDao.deleteAll()
        }
    }

    private fun asyncFind(longtitude: Double, latitude: Double): Deferred<List<Favorite>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async favoriteDao.findFavorite(longtitude, latitude)
        }


    fun getLocationList() : MutableList<LocationInfo> {
        val favorites = allFavorites.value!!

        val forecastList : MutableList<LocationInfo> = mutableListOf()

        //Alternativt:

        favorites.forEach{ favorite ->
            coroutineScope.launch {
                val forecast = weatherRepository.getLocation(favorite.latitude.toString(), favorite.longtitude.toString(), altitude = "600")
                forecastList.add(forecast)
            }
        }

        return forecastList
    }


     fun getNowList() : MutableList<NowCastInfo> {
         val favorites = allFavorites.value!!

        val forecastList : MutableList<NowCastInfo> = mutableListOf()

        //Hvis vi får fikse suspend:
        favorites.forEach{ favorite ->
            coroutineScope.launch {
                val forecast = weatherRepository.getNowCast(favorite.latitude.toString(), favorite.longtitude.toString(), altitude = "600")
                forecastList.add(forecast)
            }
        }

        return forecastList
    }


    fun getAlertInfo() : MutableList<MutableList<AlertInfo>> {

        val favorites = allFavorites.value!!

        val alertList : MutableList<MutableList<AlertInfo>> = mutableListOf()

        favorites.forEach{ favorite ->
            coroutineScope.launch {
                val alert = weatherRepository.getAlert(favorite.latitude.toString(), favorite.longtitude.toString())
                alertList.add(alert)
            }
        }

        return alertList
    }

}