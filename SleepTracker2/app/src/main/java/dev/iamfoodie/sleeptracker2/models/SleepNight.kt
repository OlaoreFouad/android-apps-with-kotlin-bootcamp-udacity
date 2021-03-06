package dev.iamfoodie.sleeptracker2.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sleep_night_tbl")
data class SleepNight(
    @PrimaryKey(autoGenerate = true)
    var nightId: Long = 0L,

    var startTimeMilli: Long = System.currentTimeMillis(),

    var endTimeMilli: Long = startTimeMilli,

    var sleepQuality: Int = -1
)