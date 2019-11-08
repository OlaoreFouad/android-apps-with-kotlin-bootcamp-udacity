package dev.iamfoodie.sleeptracker2.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.iamfoodie.sleeptracker2.daos.SleepNightDao
import java.lang.IllegalArgumentException

class SleepTrackerViewModelFactory(var dataSource: SleepNightDao, var application: Application): ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SleepTrackerViewModel::class.java)) {
            return SleepTrackerViewModel(dataSource, application) as T
        }

        throw IllegalArgumentException("Illegal activity used!!!")
    }
}