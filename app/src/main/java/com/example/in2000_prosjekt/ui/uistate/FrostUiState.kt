package com.example.in2000_prosjekt.ui.uistate
import com.example.in2000_prosjekt.ui.FrostInfo



sealed interface FrostUiState {

    data class Success(
        val frostF: FrostInfo    ) : FrostUiState

    object Error : FrostUiState
    object Loading : FrostUiState






}

 /*

data class FrostUiState( //sett Frost på slutten, F brukes allerede
    val frostF: FrostInfo    )



  */
