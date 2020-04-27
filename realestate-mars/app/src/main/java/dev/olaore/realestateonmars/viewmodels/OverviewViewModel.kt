package dev.olaore.realestateonmars.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.olaore.realestateonmars.models.MarsProperty

class OverviewViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    init {
        getProperties()
    }

    private fun getProperties() {
        _response.value = "Set the response from the API here!"
    }

}