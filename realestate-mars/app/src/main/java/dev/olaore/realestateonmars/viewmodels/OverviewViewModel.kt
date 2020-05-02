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
import java.lang.Exception

// class to denote status of the mars api request
enum class MarsApiStatus { DONE, ERROR }

class OverviewViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()
    private val _properties = MutableLiveData<List<MarsProperty>>()
    private val _status = MutableLiveData<MarsApiStatus>()
    private val _navigateToDetails = MutableLiveData<MarsProperty>()

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    val response: LiveData<String>
        get() = _response
    val properties: LiveData<List<MarsProperty>>
        get() = _properties
    val status: LiveData<MarsApiStatus>
        get() = _status
    val navigateToDetails: LiveData<MarsProperty>
        get() = _navigateToDetails

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
                _status.value = MarsApiStatus.DONE
                _properties.value = actualList
            } catch (e: Exception) {
                _status.value = MarsApiStatus.ERROR
            }


        }
    }

    fun setResponse(message: String?) {
        println("debug: from@setResponse $message")
        _response.value = message
    }

    fun onPropertySelected(marsProperty: MarsProperty) {
        _navigateToDetails.value = marsProperty
    }

    fun onPropertySelectionComplete() {
        _navigateToDetails.value = null
    }

}