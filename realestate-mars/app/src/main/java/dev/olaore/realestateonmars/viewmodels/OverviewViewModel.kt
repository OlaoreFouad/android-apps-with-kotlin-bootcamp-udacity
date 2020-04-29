package dev.olaore.realestateonmars.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.olaore.realestateonmars.models.MarsProperty
import dev.olaore.realestateonmars.network.MarsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class OverviewViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()
    private val _property = MutableLiveData<MarsProperty>()

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    val response: LiveData<String>
        get() = _response
    val property: LiveData<MarsProperty>
        get() = _property

    init {
        getProperties()
    }

    private fun getProperties() {
        println("debug: about to call api")
        coroutineScope.launch {

//            launch call to api here
            var defferedList = MarsApi.retrofitService.getProperties()
            try {
                var actualList = defferedList.await()
                setResponse("Success: ${ actualList.size } mars objects were retrieved!")
                _property.value = actualList.first()
            } catch (e: Exception) {
                setResponse(e.message)
            }


        }
    }

    fun setResponse(message: String?) {
        println("debug: from@setResponse $message")
        _response.value = message
    }

}