package dev.iamfoodie.sleeptracker2.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dev.iamfoodie.sleeptracker2.daos.SleepNightDao

class SleepTrackerViewModel(dataSource: SleepNightDao, application: Application): AndroidViewModel(application)