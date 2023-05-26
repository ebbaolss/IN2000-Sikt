package com.example.in2000_prosjekt.data.map

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.in2000_prosjekt.ui.MapViewModel

class MapViewModelFactory() : ViewModelProvider.Factory {

    override fun <T: ViewModel> create(modelClass: Class<T>) : T{
        return MapViewModel() as T
    }

}