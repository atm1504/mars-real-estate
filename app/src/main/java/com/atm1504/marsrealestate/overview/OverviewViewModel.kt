package com.atm1504.marsrealestate.overview

import android.annotation.SuppressLint
import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.atm1504.marsrealestate.network.MarsApi
import com.atm1504.marsrealestate.network.MarsProperty
import kotlinx.coroutines.launch

class OverviewViewModel(app: Application) : AndroidViewModel(app) {

    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext

    private val _response = MutableLiveData<List<MarsProperty>>()

    val response: LiveData<List<MarsProperty>>
        get() = _response

    init {
        getMarsRealEstateProperties()
    }


    private fun getMarsRealEstateProperties() {

        viewModelScope.launch {
            try {
                val listResult = MarsApi.retrofitService.getProperties()
                _response.value = listResult
            } catch (e: Exception) {
                Toast.makeText(context, "Error occurred while fetching data", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}