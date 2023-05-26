package com.example.in2000_prosjekt.database

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.in2000_prosjekt.ui.FavoriteViewModel

class FavoriteViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

    override fun <T: ViewModel> create(modelClass: Class<T>) : T{
        return FavoriteViewModel(application) as T
    }

}