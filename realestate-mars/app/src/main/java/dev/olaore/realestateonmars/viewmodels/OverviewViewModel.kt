package dev.olaore.realestateonmars.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.olaore.realestateonmars.models.MarsProperty
import dev.olaore.realestateonmars.network.MarsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OverviewViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    init {
        getProperties()
    }

    private fun getProperties() {
        println("debug: about to call api")
        MarsApi.retrofitService.getProperties().enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                println("debug: baba!, the thing been fail o!")
                setResponse("Failure: ${ t.message }")
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                println("debug: na the data be this: ${ response.body() }")
                setResponse(response.body())
            }
        })
    }

    fun setResponse(message: String?) {
        println("debug: from@setResponse $message")
        _response.value = message
    }

}