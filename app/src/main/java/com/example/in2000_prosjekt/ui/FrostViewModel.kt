package com.example.in2000_prosjekt.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.in2000_prosjekt.ui.data.DataSourceFrost
import com.example.in2000_prosjekt.ui.data.ResponsUiState
import com.example.in2000_prosjekt.ui.data.ResponsUiState_For_SN_Id
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class FrostViewModel: ViewModel() {


    // Vi separer query parameters med egne variabler(source, elements, baseurl, referencetime):
    // Videre så følger vi standarden om at tegnet: "?" skiller path og query: Så er et søk lesbart



    var elements = "air_temperature"// Dette er værmålingen vi ønsker: For enkelthetsskyld så velges bare: air temperature
    var referencetime ="2021-05-17%2F2021-05-17" // Frost API, bruker UTC-tidsformat, denne ønskes senere å kunne bestemmes av en bruker ved hjelp av en Date picker (en bibloteksfunskjon i jetpack compose)

    // Sensor stedskode: Denne stringen beskriver stedet til der du gjør kallet: SN= angir typen sensor (SN står for "sensory system"). Tallet 18700 er koden for en konkrekt lokalisasjon.%3A0: Angir hvilket kvalitet







    var url_med_Polygon ="https://frost.met.no/sources/v0.jsonld?types=SensorSystem&elements=air_temperature&geometry=POLYGON((7.9982%2058.1447%20%2C%208.0982%2058.1447%20%2C7.9982%2058.2447%20%2C%208.0982%2058.2447%20))"


//----------------------------------------------------------------------------------------






    private var _responsUIState : MutableStateFlow <ResponsUiState> = MutableStateFlow(ResponsUiState(Svar = null))
    var responsUiState: StateFlow<ResponsUiState> = _responsUIState.asStateFlow()




    private val _responsUIState1 = MutableStateFlow (ResponsUiState_For_SN_Id(Svar = null))
    val responsUiState1: StateFlow<ResponsUiState_For_SN_Id> = _responsUIState1.asStateFlow()



    var dataSource= DataSourceFrost()




    fun loadTemp(elements:String= "air_temperature", referencetime :String ="2021-05-17%2F2021-05-17", source:String) : Deferred<ResponsUiState> {

        return viewModelScope.async (Dispatchers.IO) {


            var innhold = dataSource.fetchApiSvar(elements, referencetime, source)
            var resp= ResponsUiState( Svar= innhold)


            _responsUIState.value =resp

            Log.d("test", resp.toString())
            return@async resp

        }
    }


    private var dataSource2= DataSourceFrost()


    fun loadSN_Id( koordinat:String) {
        viewModelScope.launch (Dispatchers.IO) {
            val innhold1 = dataSource2.fetchApiSvarkoordinater(koordinat)
            _responsUIState1.value = ResponsUiState_For_SN_Id(innhold1)

        }
    }



}