package dev.olaore.realestateonmars.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.olaore.realestateonmars.models.MarsProperty

class DetailsViewModel(var marsProperty: MarsProperty, val app: Application)
    : AndroidViewModel(app) {

    private val _selectedProperty = MutableLiveData<MarsProperty>(marsProperty)

    val selectedProperty: LiveData<MarsProperty>
        get() = _selectedProperty

}