package com.example.in2000_prosjekt.database
import com.example.in2000_prosjekt.ui.AlertInfo
import com.example.in2000_prosjekt.ui.LocationInfo
import com.example.in2000_prosjekt.ui.NowCastInfo

sealed interface FavoriteUiState {
    data class Success(
        val locationF: MutableList<LocationInfo>,
        val nowCastF: MutableList<NowCastInfo>,
        val alertListF: MutableList<MutableList<AlertInfo>>,
    ) : FavoriteUiState
    object Error : FavoriteUiState
    object Loading : FavoriteUiState
}