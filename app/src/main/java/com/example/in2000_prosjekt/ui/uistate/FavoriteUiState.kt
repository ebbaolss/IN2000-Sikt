package com.example.in2000_prosjekt.ui.uistate

sealed interface FavoriteUiState {
    data class Success(
        val locationF: MutableList<LocationInfo>,
        val nowCastF: MutableList<NowCastInfo>,
        val alertListF: MutableList<MutableList<AlertInfo>>,
    ) : FavoriteUiState
    object Error : FavoriteUiState
    object Loading : FavoriteUiState
}