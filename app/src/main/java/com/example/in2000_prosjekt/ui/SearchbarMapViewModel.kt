package com.example.in2000_prosjekt.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.in2000_prosjekt.ui.data.ImplementedWeatherRepository
import com.example.in2000_prosjekt.ui.data.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchbarMapViewModel : ViewModel() {

    //manual dependency injection, se codelab
    val repository: WeatherRepository = ImplementedWeatherRepository() //lettvinte måten

    private val _appUistate = MutableStateFlow(MapInfo())
    val appUiState = _appUistate.asStateFlow()

    private val _appUistate2 = MutableStateFlow(MapCoordinatesInfo())

    fun getDataSearch(query: String) : Boolean {

        val path = "https://api.mapbox.com/search/searchbox/v1/suggest?q=$query&limit=10&" +
                "session_token=[GENERATED-UUID]&country=NO&poi_category=mountain&poi_category_exclusions=street&" +
                "access_token=pk.eyJ1IjoiZWxpc2FiZXRoYiIsImEiOiJjbGY2c3N3dDAxYWxsM3ludHY5em5wMnJxIn0.YVrKFoHYA1sCJhgBCbhudw"

        viewModelScope.launch() {
            val mapSearchDeferred = viewModelScope.async(Dispatchers.IO){
                repository.getMap(path)
            }
            val mapSearchP = mapSearchDeferred.await()

            _appUistate.value = MapInfo(mapSearchP.optionMountains, _appUistate.value.recentSearch)
        }
        return true
    }
    fun showSelectedMountain( mapboxId : String) {
        viewModelScope.launch() {

            val path2 = "https://api.mapbox.com/search/searchbox/v1/retrieve/$mapboxId?" +
                    "session_token=[GENERATED-UUID]&" +
                    "access_token=pk.eyJ1IjoiZWxpc2FiZXRoYiIsImEiOiJjbGY2c3N3dDAxYWxsM3ludHY5em5wMnJxIn0.YVrKFoHYA1sCJhgBCbhudw"


            val mapSearchCoordinates = repository.getMapCoordinates(path2)

            //åpne card eller oppdatere det som får cardet til å vises
            println(mapSearchCoordinates.latitude)
            println(mapSearchCoordinates.longitude)

            _appUistate2.update {
                MapCoordinatesInfo(
                    latitude = mapSearchCoordinates.latitude,
                    longitude = mapSearchCoordinates.longitude
                )
            }
        }
    }

    fun updateRecentSearch(input : String, delete : Boolean) : Boolean {

        val updatetList: MutableList<String> = appUiState.value.recentSearch.toMutableList()

        if (delete) {
            updatetList.remove(input)

        } else {

            if (input in updatetList) return true
            if (updatetList.isEmpty()) {
                updatetList.add(input)
            } else {
                updatetList.add(input)
                for (i in updatetList.size - 1 downTo 1 ) {
                    updatetList[i] = updatetList[i - 1]
                }
                updatetList[0] = input
                if (updatetList.size > 3) {
                    updatetList.removeAt(updatetList.lastIndex)
                }
            }
        }
        _appUistate.update {
            it.copy(
                recentSearch = updatetList
            )
        }
        return false
    }
}