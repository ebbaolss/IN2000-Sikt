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
import com.example.in2000_prosjekt.ui.uistate.FrostUiState
import io.ktor.utils.io.errors.*

import kotlinx.coroutines.async


public class FrostViewModel () : ViewModel() {

    private val repository: WeatherRepository = ImplementedWeatherRepository()

    private val _frostUistate: MutableStateFlow<FrostUiState> = MutableStateFlow(FrostUiState.Loading)
    val frostUiState: StateFlow<FrostUiState> = _frostUistate.asStateFlow()


    /* Trokke det er slik: Bare bruk repository.getRerencetime på ritkig sted. kl.18.00 06.05

      private val _frostreferencetime: MutableStateFlow<FrostUiState> = MutableStateFlow(FrostUiState.Loading)
    val frostreferencetime: StateFlow<FrostUiState> = _frostUistate.asStateFlow()

        val yourVariable : MutableLiveData<String> = MutableLiveData() // https://stackoverflow.com/questions/58124749/how-to-pass-parameters-values-to-the-viewmodel-in-mvvm-pattern FUNKA IKKE
     */



    fun getReferencetimeFrost( referencetime: String )  {

        viewModelScope.launch() {
            val referencetime1 = viewModelScope.async(Dispatchers.IO) {
                repository.getReferencetimeFrost(referencetime)

            }
            /*
            val referencetime2 = referencetime1.await()


            _frostUistate.update {
                FrostUiState.Success(
                    referencetime = referencetime

                )
            }

             */
        }

    }

// Forsøk 1 Denne tilnærmingen innebærer å kommentere ut frost fra ApiViewModel
    fun getFrost(latitude: String, longitude: String) {
        viewModelScope.launch() {
            try {

                Log.d("frost", "Pre-deferred")

                val frostDeferred = viewModelScope.async(Dispatchers.IO) {
                    repository.getFrost(latitude, longitude,)
                }
                val frostP = frostDeferred.await()

                Log.d("FrostDeffered", "Success")


                _frostUistate.update {
                    FrostUiState.Success(
                        frostF = frostP


                    )
                }





            } catch (e: IOException) {// Inntreffer ved nettverksavbrudd
                _frostUistate.update {
                    FrostUiState.Error
                }
            }
        }
    }













   /* // Forsøk 1 Denne tilnærmingen innebærer å kommentere ut frost fra ApiViewModel
    fun getReferansedatefromCalendar(referencedate: String ) {
        viewModelScope.launch() {
            try {
             repository.getReferencetimeFrost(referencedate)


            } catch (e: IOException) {// Inntreffer ved nettverksavbrudd
                _frostUistate.update {
                    FrostUiState.Error
                }
            }
        }
    }
*/


// forsøk 5 Begge: Utkommentert
    /*
    fun getFrostWithReferenceTime(latitude: String, longitude: String,referencedate: String ) {
        viewModelScope.launch() {
            try {


                val frostDeferred = viewModelScope.async(Dispatchers.IO) {
                    repository.getFrost(latitude, longitude,)
                }
                val frostP = frostDeferred.await()



                _frostUistate.update {
                    FrostUiState.Success(
                        frostF = frostP
                    )
                }
            } catch (e: IOException) {// Inntreffer ved nettverksavbrudd
                _frostUistate.update {
                    FrostUiState.Error
                }
            }
        }
    }

     */


}

