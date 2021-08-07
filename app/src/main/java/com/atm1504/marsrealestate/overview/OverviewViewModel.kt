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

    enum class MarsApiStatus { LOADING, ERROR, DONE }

    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext

    private val _status = MutableLiveData<MarsApiStatus>()
    val status: LiveData<MarsApiStatus>
        get() = _status

    private val _properties = MutableLiveData<List<MarsProperty>>()

    val properties: LiveData<List<MarsProperty>>
        get() = _properties

    private val _navigateToSelectedProperty = MutableLiveData<MarsProperty?>()

    val navigateToSelectedProperty: LiveData<MarsProperty?>
        get() = _navigateToSelectedProperty

    init {
        _navigateToSelectedProperty.value = null
        getMarsRealEstateProperties()
    }

    private fun getMarsRealEstateProperties() {

        viewModelScope.launch {
            try {
                _status.value = MarsApiStatus.LOADING
                val listResult = MarsApi.retrofitService.getProperties()
                _properties.value = listResult
                _status.value = MarsApiStatus.DONE

            } catch (e: Exception) {
                Toast.makeText(context, "Error occurred while fetching data", Toast.LENGTH_LONG)
                    .show()
                _status.value = MarsApiStatus.ERROR
                _properties.value = ArrayList()
            }
        }
    }

    fun displayPropertyDetails(marsProperty: MarsProperty) {
        _navigateToSelectedProperty.value = marsProperty
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }
}