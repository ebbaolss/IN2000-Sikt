package com.example.in2000_prosjekt.ui.uistate
import com.example.in2000_prosjekt.ui.FrostInfo
import com.example.in2000_prosjekt.ui.data.DataFrost

sealed interface FrostUiState {
    data class Success(
        val frostF: FrostInfo,
       // val referencetime: String
    ) : FrostUiState
    object Error : FrostUiState
    object Loading : FrostUiState


}

data class FrostReferenceTime( //sett Frost på slutten, F brukes allerede
    val frostreferencetime: List<DataFrost>?
)