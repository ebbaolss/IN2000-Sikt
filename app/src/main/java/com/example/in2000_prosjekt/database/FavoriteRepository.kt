package com.example.in2000_prosjekt.database


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.in2000_prosjekt.ui.AlertInfo
import com.example.in2000_prosjekt.ui.LocationInfo
import com.example.in2000_prosjekt.ui.NowCastInfo
import com.example.in2000_prosjekt.data.ImplementedWeatherRepository
import kotlinx.coroutines.*

class FavoriteRepository(private val favoriteDao: FavoriteDao) {

    private val weatherRepository = ImplementedWeatherRepository()
    val allFavorites: LiveData<List<Favorite>> = favoriteDao.getAllFavorites()
    val searchFavorites = MutableLiveData<List<Favorite>>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun addFavorite(newFavorite: Favorite) {
        coroutineScope.launch(Dispatchers.IO) {
            favoriteDao.addFavorite(newFavorite)
        }
    }

    fun deleteFavorite(longitude: Double, latitude: Double) : Boolean{
        var deleted = false
        coroutineScope.launch(Dispatchers.IO) {
            favoriteDao.deleteFav(longitude, latitude)
            deleted = true
        }
        return deleted
    }

    fun findFavorite(longitude: Double, latitude: Double) {
        coroutineScope.launch(Dispatchers.Main) {
            searchFavorites.value = asyncFindAsync(longitude, latitude).await()
            Log.d("SF.VAL", "${searchFavorites.value?.size}")
        }
    }

    fun deleteAll(){
        coroutineScope.launch(Dispatchers.IO) {
            favoriteDao.deleteAll()
        }
    }

    private fun asyncFindAsync(longitude: Double, latitude: Double): Deferred<List<Favorite>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async favoriteDao.findFavorite(longitude, latitude)
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