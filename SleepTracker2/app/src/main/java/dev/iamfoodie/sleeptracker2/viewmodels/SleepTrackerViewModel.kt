package dev.iamfoodie.sleeptracker2.viewmodels

import android.app.Application
import android.provider.SyncStateContract.Helpers.insert
import android.provider.SyncStateContract.Helpers.update
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.android.trackmysleepquality.formatNights
import dev.iamfoodie.sleeptracker2.daos.SleepNightDao
import dev.iamfoodie.sleeptracker2.models.SleepNight
import kotlinx.coroutines.*

class SleepTrackerViewModel(private val dataSource: SleepNightDao, application: Application): AndroidViewModel(application) {

    private val TAG = "SleepTrackerViewModel"

    private val tonight = MutableLiveData<SleepNight?>()
    var allNights = dataSource.getAllNights()

    // viewmodel job
    private val viewModelJob = Job()

    // scope for running coroutines!
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    lateinit var nightString: Any

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    init {
        initializeTonight()
    }


    fun initializeTonight() {
        uiScope.launch {
            tonight.value = getTonightFromDatabase()
        }
    }

    suspend fun getTonightFromDatabase(): SleepNight? {
        return withContext(Dispatchers.IO) {
            var night = dataSource.getTonight()
            if (night?.endTimeMilli != night?.startTimeMilli) {
                night = null
            }
            night
        }
    }

    fun onStartTracking() {
        Log.d(TAG, "tracking started")
        var night = SleepNight()
        uiScope.launch {
            insert(night)
            tonight.value = getTonightFromDatabase()
            getNights()
        }
//        Log.d(TAG, "Length: ${ nightString?.size }")
    }

    suspend fun getNights() {
        withContext(Dispatchers.IO) {
            val night = dataSource.getTonight()
            Log.d(TAG, tonight.value.toString())
        }
    }

    fun onStopTracking() {
        Log.d(TAG, "tracking stopped!")
        uiScope.launch {
            val oldNight = tonight.value ?: return@launch
            oldNight.endTimeMilli = System.currentTimeMillis()
            update(oldNight)
        }
    }

    suspend fun update(night: SleepNight) {
        withContext(Dispatchers.IO) {
            dataSource.update(night)
        }
    }

    suspend fun clear() {
        withContext(Dispatchers.IO) {
            Log.d(TAG, dataSource.getAllNights().value?.size.toString())
            dataSource.deleteAllNights()
        }
    }

    fun onClear() {
        uiScope.launch {
            clear()
            tonight.value = null
        }
    }

    suspend fun insert(night: SleepNight) {
        withContext(Dispatchers.IO) {
            dataSource.insert(night)
        }
    }


}