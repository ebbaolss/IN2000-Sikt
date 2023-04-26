package com.example.in2000_prosjekt.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.in2000_prosjekt.ui.data.ImplementedWeatherRepository
import com.example.in2000_prosjekt.ui.data.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MapViewModel : ViewModel() {

    //manual dependency injection, se codelab
    val repository: WeatherRepository = ImplementedWeatherRepository() //lettvinte måten

    val path = "https://api.mapbox.com/search/searchbox/v1/suggest?q=Gald&limit=9&" +
            "session_token=[GENERATED-UUID]&country=NO&poi_category=mountain&poi_category_exclusions=street&" +
            "access_token=pk.eyJ1IjoiZWxpc2FiZXRoYiIsImEiOiJjbGY2c3N3dDAxYWxsM3ludHY5em5wMnJxIn0.YVrKFoHYA1sCJhgBCbhudw"


    private val _appUistate: MutableStateFlow<AppUiState> = MutableStateFlow(AppUiState.LoadingMapSearch)
    val appUiState: StateFlow<AppUiState> = _appUistate.asStateFlow()

    fun getDataSearch(path: String) {
        viewModelScope.launch() {
            val mapSearchDeferred = viewModelScope.async (Dispatchers.IO){
                repository.getMap(path)
            }

            val mapSearchP = mapSearchDeferred.await()

            _appUistate.update {
                AppUiState.SuccessMapSearch(
                    mapSearchF = mapSearchP
                )
            }
        }
    }
}