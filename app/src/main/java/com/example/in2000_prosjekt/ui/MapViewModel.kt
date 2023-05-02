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

class MapViewModel : ViewModel() {

    //manual dependency injection, se codelab
    val repository: WeatherRepository = ImplementedWeatherRepository() //lettvinte måten

    private val _appUistate = MutableStateFlow(MapInfo())
    val appUiState = _appUistate.asStateFlow()

    //Må det lages ny uiState eller kan jeg sette de sammen til en data class? og bruke it.copy()
    //Fjerne dette: feil på MapCoordinatesInfo update
    //ha dette, feil på _appUistate2
    private val _appUistate2 = MutableStateFlow(MapCoordinatesInfo())
    val appUiState2 = _appUistate2.asStateFlow()

    fun getDataSearch(query: String) : Boolean {

        val path = "https://api.mapbox.com/search/searchbox/v1/suggest?q=$query&limit=10&" +
                "session_token=[GENERATED-UUID]&country=NO&poi_category=mountain&poi_category_exclusions=street&" +
                "access_token=pk.eyJ1IjoiZWxpc2FiZXRoYiIsImEiOiJjbGY2c3N3dDAxYWxsM3ludHY5em5wMnJxIn0.YVrKFoHYA1sCJhgBCbhudw"

        viewModelScope.launch() {
            val mapSearchDeferred = viewModelScope.async (Dispatchers.IO){
                repository.getMap(path)
            }
            val mapSearchP = mapSearchDeferred.await()

            _appUistate.value = MapInfo(mapSearchP.optionMountains, _appUistate.value.recentSearch)
            println("oppdatert") //kommer aldri hit
        }
        return true
    }
    fun getDataSearchCoordinates(mapboxId: String) {

        //ha denne her? må nesten det eller?
        val path2 = "https://api.mapbox.com/search/searchbox/v1/retrieve/$mapboxId?" +
                "session_token=[GENERATED-UUID]&" +
                "access_token=pk.eyJ1IjoiZWxpc2FiZXRoYiIsImEiOiJjbGY2c3N3dDAxYWxsM3ludHY5em5wMnJxIn0.YVrKFoHYA1sCJhgBCbhudw"

        viewModelScope.launch() {
            val mapSearchCoordinatesDeferred = viewModelScope.async (Dispatchers.IO){
                repository.getMapCoordinates(path2)
            }

            val mapSearchCoordinatesP = mapSearchCoordinatesDeferred.await()

            _appUistate2.update {
                MapCoordinatesInfo(
                    latitude = mapSearchCoordinatesP.latitude,
                    longitude = mapSearchCoordinatesP.longitude
                )
            }
            println("update ferdig")
        }
    }

    fun updateRecentSearch(input : String) : Boolean {

        val updatetList = appUiState.value.recentSearch

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
        _appUistate.update {
            it.copy(
                recentSearch = updatetList
            )
        }
        return false
    }
}
