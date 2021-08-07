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

    private val _status = MutableLiveData<String>()

    val status: LiveData<String>
        get() = _status

    private val _properties = MutableLiveData<List<MarsProperty>>()

    val properties: LiveData<List<MarsProperty>>
        get() = _properties

    init {
        getMarsRealEstateProperties()
    }


    private fun getMarsRealEstateProperties() {

        viewModelScope.launch {
            try {
                val listResult = MarsApi.retrofitService.getProperties()
                if (listResult.isNotEmpty()) {
                    _properties.value = listResult
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Error occurred while fetching data", Toast.LENGTH_LONG)
                    .show()
                _status.value = "Failure: ${e.message}"
            }
        }
    }
}