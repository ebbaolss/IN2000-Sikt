package com.example.in2000_prosjekt.ui.uistate
import com.example.in2000_prosjekt.ui.FrostInfo
import com.example.in2000_prosjekt.ui.data.DataFrost

sealed interface FrostReferencetimeUiState {
    data class Success(
        val referencedate: String, // pleide å være :FrostReferenceTime
       // val referencetime: String
    ) : FrostReferencetimeUiState


    object Error : FrostReferencetimeUiState
    object Loading : FrostReferencetimeUiState




}




data class FrostReferenceTime( //sett Frost på slutten, F brukes allerede
    val frostreferencetime: String
)