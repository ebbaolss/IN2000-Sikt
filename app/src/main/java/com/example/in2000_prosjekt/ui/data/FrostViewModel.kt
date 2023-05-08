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

import com.example.in2000_prosjekt.ui.uistate.FrostReferenceTime
import com.example.in2000_prosjekt.ui.uistate.FrostUiState
import io.github.boguszpawlowski.composecalendar.header.MonthState

import kotlinx.coroutines.async


public class FrostViewModel () : ViewModel() {

    private val repository: WeatherRepository = ImplementedWeatherRepository()



    private val _frostUistate: MutableStateFlow<FrostUiState> = MutableStateFlow(FrostUiState.Loading)
    val frostUiState: StateFlow<FrostUiState> = _frostUistate.asStateFlow()




    private val _frostUistate_attempt1_alpaca: MutableStateFlow<FrostReferenceTime> = MutableStateFlow(FrostReferenceTime(""))
    val frostUiState_attempt1_alpaca: StateFlow<FrostReferenceTime> = _frostUistate_attempt1_alpaca.asStateFlow()


    fun getFrost(latitude: String, longitude: String, referencetime: MonthState) {
        viewModelScope.launch() {


            val frostDeferred = viewModelScope.async(Dispatchers.IO) {
                repository.getFrost(latitude, longitude,referencetime) // går ikke å sette funksjoenn getFrsot inni funksjonen getReferenceTime
            }
            val frostP = frostDeferred.await()

            Log.d("FrostDeffered", "Success")


            // kl.21.06, 06.05
            Log.d(" Referencetime sendes igjennom oppdatert! " , referencetime.currentMonth.toString() )

            _frostUistate.update {
                //FrostReferenceTime(referencetime2.frostreferencetime) // attempt uten: Success, Loading Error I tilfelle feilen er der, 06.05.kl1512

                FrostUiState.Success(
                    frostF =frostP

                )
            }
        }
    }



}

