package dev.iamfoodie.sleeptracker2.databases

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.iamfoodie.sleeptracker2.daos.SleepNightDao
import dev.iamfoodie.sleeptracker2.models.SleepNight

@Database(
    entities = [SleepNight::class],
    exportSchema = false,
    version = 1
)
abstract class SleepNightDatabase : RoomDatabase() {

    abstract val sleepDatabaseDao: SleepNightDao

    companion object {

        @Volatile
        private var INSTANCE: SleepNightDatabase? = null

        fun getInstance(context: Context): SleepNightDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context, SleepNightDatabase::class.java, "sleep_night_db"
                    ).fallbackToDestructiveMigration().build()
                }

                return instance
            }
        }

    }

}