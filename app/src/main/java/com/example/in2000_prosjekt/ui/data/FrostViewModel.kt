package com.example.in2000_prosjekt.ui.data
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.in2000_prosjekt.ui.AppUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

import com.example.in2000_prosjekt.ui.data.*
import com.example.in2000_prosjekt.ui.uistate.FrostReferenceTime
import com.example.in2000_prosjekt.ui.uistate.FrostReferencetimeUiState
import io.ktor.utils.io.errors.*

import kotlinx.coroutines.async


public class FrostViewModel () : ViewModel() {

    private val repository: WeatherRepository = ImplementedWeatherRepository()



    private val _frostUistate: MutableStateFlow<FrostReferencetimeUiState> = MutableStateFlow(FrostReferencetimeUiState.Loading)
    val frostUiState: StateFlow<FrostReferencetimeUiState> = _frostUistate.asStateFlow()




    private val _frostUistate_attempt1_alpaca: MutableStateFlow<FrostReferenceTime> = MutableStateFlow(FrostReferenceTime(""))
    val frostUiState_attempt1_alpaca: StateFlow<FrostReferenceTime> = _frostUistate_attempt1_alpaca.asStateFlow()


    fun getReferencetimeFrost(referencetime: String) {
        viewModelScope.launch() {
            val referencetimedeffered = viewModelScope.async(Dispatchers.IO) {
                repository.getReferencetimeFrost(referencetime)
            }
            val referencetime = referencetimedeffered.await()

            // kl.21.06, 06.05
            Log.d(" Referencetime sendes igjennom oppdatert! " , referencetime.toString() )

            _frostUistate.update {
                //FrostReferenceTime(referencetime2.frostreferencetime) // attempt uten: Success, Loading Error I tilfelle feilen er der, 06.05.kl1512

                FrostReferencetimeUiState.Success(
                    referencedate = referencetime.toString()
                )
            }
        }
    }



}

