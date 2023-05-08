package com.example.in2000_prosjekt.ui.database


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.in2000_prosjekt.ui.AlertInfo
import com.example.in2000_prosjekt.ui.LocationInfo
import com.example.in2000_prosjekt.ui.NowCastInfo
import com.example.in2000_prosjekt.ui.data.ImplementedWeatherRepository
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
            Log.d("SF.VAL", "${searchFavorites.value?.size}")
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

    suspend fun getLocationList() : MutableList<LocationInfo> {
        val favorites = allFavorites.value!!
        val forecastList : MutableList<LocationInfo> = mutableListOf()
        favorites.forEach{ favorite ->
            val forecast = weatherRepository.getLocation(favorite.latitude.toString(), favorite.longtitude.toString(), altitude = "600")
            forecastList.add(forecast)
            //Log.d("FORECASTList", "${forecastList.size}")
        }

        return forecastList
    }


    suspend fun getNowList() : MutableList<NowCastInfo> {
        val favorites = allFavorites.value!!

        val forecastList : MutableList<NowCastInfo> = mutableListOf()

        //Hvis vi får fikse suspend:
        favorites.forEach{ favorite ->
            val forecast = weatherRepository.getNowCast(favorite.latitude.toString(), favorite.longtitude.toString(), favorite.mountainHeight.toString())
            forecastList.add(forecast)
            //Log.d("NOWCASTlist", "${forecastList.size}")
        }

        return forecastList
    }


    suspend fun getAlertInfo() : MutableList<MutableList<AlertInfo>> {

        val favorites = allFavorites.value!!

        val alertList : MutableList<MutableList<AlertInfo>> = mutableListOf()

        favorites.forEach{ favorite ->
            val alert = weatherRepository.getAlert(favorite.latitude.toString(), favorite.longtitude.toString())
            alertList.add(alert)
            //Log.d("ALERTLIST", "${alertList.size}")
        }
        return alertList
    }


}