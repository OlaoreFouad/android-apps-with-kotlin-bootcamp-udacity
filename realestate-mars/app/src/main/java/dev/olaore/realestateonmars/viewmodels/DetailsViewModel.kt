package dev.olaore.realestateonmars.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dev.olaore.realestateonmars.models.MarsProperty

class DetailsViewModel(var marsProperty: MarsProperty, val app: Application)
    : AndroidViewModel(app) {
}