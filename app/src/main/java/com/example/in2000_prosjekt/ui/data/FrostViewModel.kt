package com.example.in2000_prosjekt.ui.data
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.in2000_prosjekt.ui.AppUiState
import com.example.in2000_prosjekt.ui.FrostInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

import com.example.in2000_prosjekt.ui.uistate.FrostUiState
import io.github.boguszpawlowski.composecalendar.header.MonthState

import kotlinx.coroutines.async



public class FrostViewModel () : ViewModel() {

    private val repository: WeatherRepository = ImplementedWeatherRepository()




    private val _frostUistate: MutableStateFlow<FrostUiState> = MutableStateFlow(FrostUiState.Loading)
    val frostUiState: StateFlow<FrostUiState> = _frostUistate.asStateFlow()



/* Attept 1, å ha et frostuiState: som bare sender ut repsonsen til apikallet. Altså den har bare 1 state (ikke success, loading, og error)
    val sightconditionListofDataforMonth = listOf<DataFrost>()
    val frostresponse =  FrostInfo(sightconditionListofDataforMonth=sightconditionListofDataforMonth)

    val frostuistate = FrostUiState(frostresponse)

    private val _frostUistate= MutableStateFlow(frostuistate)
    val frostUiState: StateFlow<FrostUiState> = _frostUistate.asStateFlow()

 */



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
