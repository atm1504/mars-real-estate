package com.atm1504.marsrealestate.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.atm1504.marsrealestate.network.MarsProperty

class DetailViewModel(marsProperty: MarsProperty, app: Application) :
    AndroidViewModel(app) {

    private val _selectedProperty = MutableLiveData<MarsProperty>()

    val selectedProperty: LiveData<MarsProperty>
        get() = _selectedProperty

    init {
        _selectedProperty.value = marsProperty
    }
}