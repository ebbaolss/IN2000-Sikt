package com.example.in2000_prosjekt.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.in2000_prosjekt.ui.data.DataSourceFrost
import com.example.in2000_prosjekt.ui.data.ResponsUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class FrostViewModel: ViewModel() {

    // Vi separer query parameters med egne variabler(source, elements, baseurl, referencetime):
    // Videre så følger vi standarden om at tegnet: "?" skiller path og query: Så er et søk lesbart


    var source =
        "SN55770"// Sensor stedskode: Denne stringen beskriver stedet til der du gjør kallet: SN= angir typen sensor (SN står for "sensory system"). Tallet 18700 er koden for en konkrekt lokalisasjon.%3A0: Angir hvilket kvalitet

    private val elements =
        "air_temperature"// Dette er værmålingen vi ønsker: For enkelthetsskyld så velges bare: air temperature

    private val baseurl = "https://frost.met.no/observations/v0.jsonld?"

    private val referencetime =
        "2021-05-17%2F2021-05-17" // Frost API, bruker UTC-tidsformat, denne ønskes senere å kunne bestemmes av en bruker ved hjelp av en Date picker (en bibloteksfunskjon i jetpack compose)


    fun settSource(text: String): Unit {
        source = text
    }


// funker ikke å ha en egen url-variable: Og heller ikke å lage en streng som

    val url =
        "${baseurl}sources=${source}&referencetime=${referencetime}&elements=${elements}" // den kontruerste urlen


    private val dataSource = DataSourceFrost(url)

    private val url_uten_variable_erstatninger =
        "https://frost.met.no/observations/v0.jsonld?sources=SN18700%3A0&referencetime=2021-05-17%2F2021-05-17&elements=air_temperature"


    init {

        Log.d("endra", source)


    }


    private val _responsUIState = MutableStateFlow(ResponsUiState(Svar = null))


    //Endrer på denne: Nå i dag 18.44
    val responsUiState: StateFlow<ResponsUiState> = _responsUIState.asStateFlow()


    init {

        loadUsers()

    }


    private fun loadUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            val innhold = dataSource.fetchApiSvar()
            _responsUIState.value = ResponsUiState(Svar = innhold)
        }
    }

}