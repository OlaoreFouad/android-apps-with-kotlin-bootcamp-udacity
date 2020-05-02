package dev.olaore.realestateonmars.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import dev.olaore.realestateonmars.R
import dev.olaore.realestateonmars.models.MarsProperty

class DetailsViewModel(var marsProperty: MarsProperty, val app: Application)
    : AndroidViewModel(app) {

    private val _selectedProperty = MutableLiveData<MarsProperty>(marsProperty)
    private var _selectedPropertyType = MutableLiveData<String>()
    private var _selectedPropertyPrice = MutableLiveData<String>()

    val selectedProperty: LiveData<MarsProperty>
        get() = _selectedProperty
    val selectedPropertyType: LiveData<String>
        get() = _selectedPropertyType
    val selectedPropertyPrice: LiveData<String>
        get() = _selectedPropertyPrice

    init {

        _selectedPropertyType = Transformations.map(selectedProperty) {

            app.resources.getString(R.string.display_type,
                app.resources.getString(
                    if (marsProperty.isRental) R.string.show_rent else R.string.show_buy
                ))

        } as MutableLiveData<String>

        _selectedPropertyPrice = Transformations.map(selectedProperty) {

            app.resources.getString(when(marsProperty.isRental) {
                true -> R.string.display_price_monthly_rental
                false -> R.string.display_price
            }, marsProperty.price)

        } as MutableLiveData<String>

    }

}