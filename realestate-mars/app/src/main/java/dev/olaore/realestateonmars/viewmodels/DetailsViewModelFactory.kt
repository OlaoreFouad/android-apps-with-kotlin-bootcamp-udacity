package dev.olaore.realestateonmars.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dev.olaore.realestateonmars.models.MarsProperty
import kotlin.IllegalArgumentException

class DetailsViewModelFactory(var marsProperty: MarsProperty, val application: Application)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(marsProperty, application) as T
        }

        throw IllegalArgumentException("Something went wrong tho!")
    }

}