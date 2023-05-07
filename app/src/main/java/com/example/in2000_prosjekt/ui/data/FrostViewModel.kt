package com.example.in2000_prosjekt.ui.data
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

import com.example.in2000_prosjekt.ui.data.*
import com.example.in2000_prosjekt.ui.uistate.FrostReferencetimeUiState
import io.ktor.utils.io.errors.*

import kotlinx.coroutines.async


public class FrostViewModel () : ViewModel() {

    private val repository: WeatherRepository = ImplementedWeatherRepository()

    private val _frostUistate: MutableStateFlow<FrostReferencetimeUiState> =
        MutableStateFlow(FrostReferencetimeUiState.Loading)
    val frostUiState: StateFlow<FrostReferencetimeUiState> = _frostUistate.asStateFlow()


    fun getReferencetimeFrost(referencetime: String) {

        viewModelScope.launch() {
            val referencetime1 = viewModelScope.async(Dispatchers.IO) {
                repository.getReferencetimeFrost(referencetime)
            }
            val referencetime2 = referencetime1.await()


            // kl.21.06, 06.05
            Log.d(" Referencetime sendes igjennom oppdatert! " , referencetime2.toString() )

            _frostUistate.update {
                FrostReferencetimeUiState.Success(
                    referencedate = referencetime2
                )
            }
        }
    }



}

