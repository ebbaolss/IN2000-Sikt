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

    fun getDataSearch(query: String) {

        val path = "https://api.mapbox.com/search/searchbox/v1/suggest?q=$query&limit=10&" +
                "session_token=[GENERATED-UUID]&country=NO&poi_category=mountain&poi_category_exclusions=street&" +
                "access_token=pk.eyJ1IjoiZWxpc2FiZXRoYiIsImEiOiJjbGY2c3N3dDAxYWxsM3ludHY5em5wMnJxIn0.YVrKFoHYA1sCJhgBCbhudw"

        viewModelScope.launch() {
            val mapSearchDeferred = viewModelScope.async (Dispatchers.IO){
                repository.getMap(path)
            }
            val mapSearchP = mapSearchDeferred.await()

            _appUistate.update { //kan man bare oppdatere denne ene og ikke også recent?
                MapInfo(
                    optionMountains = mapSearchP.optionMountains //hvor ligger name og mapbox_id? legges det til egt?
                )
            }
        }
    }
    fun getDataSearchCoordinates(mapboxId: String) {

        //ha denne her? må nesten det eller?
        val path2 = "https://api.mapbox.com/search/searchbox/v1/retrieve/$mapboxId==?" +
                "session_token=[GENERATED-UUID]&" +
                "access_token=pk.eyJ1IjoiZWxpc2FiZXRoYiIsImEiOiJjbGY2c3N3dDAxYWxsM3ludHY5em5wMnJxIn0.YVrKFoHYA1sCJhgBCbhudw"

        viewModelScope.launch() {
            val mapSearchCoordinatesDeferred = viewModelScope.async (Dispatchers.IO){
                repository.getMapCoordinates(path2) //lage egen ny
            }

            val mapSearchCoordinatesP = mapSearchCoordinatesDeferred.await()

            _appUistate2.update {
                MapCoordinatesInfo(
                    latitude = mapSearchCoordinatesP.latitude,
                    longitude = mapSearchCoordinatesP.longitude
                )
            }
        }
    }

    fun updateRecentSearch(input : String) {
        //MANGLER:
        //å fikse sånn at det bare er 3 elementer i lista max, og at sist søkt ligger øverst

        val updatetList : MutableList<String> = mutableListOf()

        updatetList.add(input)

        _appUistate.update {
            MapInfo(
                recentSearch = updatetList
            )
        }
        println("Recent search: ${appUiState.value.recentSearch}")
    }
}

//fun optionList(result : String, listRecent : List<String>) : List<String> {
//
//    //lat som jeg har en liste med alle fjelltoppene:
////
////    if (!optionMountains.contains(result)) { //får man  ikke nullpointerexception?
////        optionMountains[2] = optionMountains[1] //?: ""
////        optionMountains[1] = optionMountains[0]
////        optionMountains[0] = result
////    }
//
//    var counter = 1
//    if (listRecent.isEmpty()) {
//        listRecent.add(result)
//    }
//
//    listRecent.forEach{
//        listRecent[counter] = it
//        counter++
//    }
//    listRecent[0] = result
//    listRecent = listRecent.subList(0, 2)
//
//    return listRecent
//
//}