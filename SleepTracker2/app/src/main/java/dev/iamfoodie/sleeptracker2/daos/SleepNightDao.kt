package dev.iamfoodie.sleeptracker2.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import dev.iamfoodie.sleeptracker2.models.SleepNight

@Dao
interface SleepNightDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(night: SleepNight)

    @Update
    fun update(night: SleepNight)

    @Query("SELECT * FROM sleep_night_tbl")
    fun getAllNights(): LiveData<List<SleepNight>>

    @Query("SELECT * FROM sleep_night_tbl WHERE nightId = :nightId")
    fun getNight(nightId: Long): SleepNight

    @Query("DELETE FROM sleep_night_tbl")
    fun deleteAllNights()

    @Query("SELECT * FROM sleep_night_tbl ORDER BY nightId DESC LIMIT 1")
    fun getTonight(): SleepNight

}