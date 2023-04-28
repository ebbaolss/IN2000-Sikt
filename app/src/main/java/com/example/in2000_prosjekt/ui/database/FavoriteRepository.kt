package com.example.in2000_prosjekt.ui.database


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.in2000_prosjekt.ui.LocationInfo
import com.example.in2000_prosjekt.ui.data.DataSource
import kotlinx.coroutines.*

class FavoriteRepository(private val favoriteDao: FavoriteDao) {

    val dataSource = DataSource(basePath = "https://gw-uio.intark.uh-it.no/in2000/weatherapi") //dette er både forecast og nowcast
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

    suspend fun getAllInfo() : MutableList<LocationInfo> {
        val favorites = allFavorites.value!!

        val forecastList : MutableList<LocationInfo> = mutableListOf()

        favorites.forEach{ favorite ->
            val forecast = dataSource.fetchLocationForecast(favorite.latitude.toString(), favorite.longtitude.toString())

            val temp = forecast.properties?.timeseries?.get(0)?.data?.instant?.details?.air_temperature
            val airfog = forecast.properties?.timeseries?.get(0)?.data?.instant?.details?.fog_area_fraction
            val rain = forecast.properties?.timeseries?.get(0)?.data?.next_1_hours?.details?.get("precipitation_amount")
            val cloud_high = forecast.properties?.timeseries?.get(0)?.data?.instant?.details?.cloud_area_fraction_high
            val cloud_mid = forecast.properties?.timeseries?.get(0)?.data?.instant?.details?.cloud_area_fraction_medium
            val cloud_low = forecast.properties?.timeseries?.get(0)?.data?.instant?.details?.cloud_area_fraction_low

            val locationF = LocationInfo(
                temperatureL = (temp ?: -273.5) as Float,
                fog_area_fractionL = airfog!!,
                rainL = rain!!,
                cloud_area_fraction_high = cloud_high!!,
                cloud_area_fraction_medium = cloud_mid!!,
                cloud_area_fraction_low = cloud_low!!,
            )

            forecastList.add(locationF)
        }

        return forecastList
    }

}