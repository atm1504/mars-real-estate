package com.atm1504.marsrealestate.overview

import android.annotation.SuppressLint
import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.atm1504.marsrealestate.network.MarsApi
import com.atm1504.marsrealestate.network.MarsProperty
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        MarsApi.retrofitService.getProperties().enqueue(object : Callback<List<MarsProperty>> {
            override fun onFailure(call: Call<List<MarsProperty>>, t: Throwable) {
                // _response.value = "Failure: " + t.message
                Toast.makeText(context, "Error occurred: " + t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<List<MarsProperty>>,
                response: Response<List<MarsProperty>>
            ) {
                _response.value = response.body()
            }
        })
    }
}