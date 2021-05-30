
package com.example.android.trackmysleepquality.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SleepDatabaseDao {
    /**
     * Database Manipulations
     */
    @Insert
    fun insert(night: SleepNight)

    @Update
    fun update(night: SleepNight)

    //get a specific sleepNight by key
    @Query("SELECT * from daily_sleep_quality_table WHERE nightID = :key")
    fun get(key: Long): SleepNight

    //clear table
    @Query("DELETE FROM daily_sleep_quality_table")
    fun clear()

    //get all nights
    @Query("SELECT * FROM daily_sleep_quality_table ORDER bY nightID DESC")
    fun getAllNights(): LiveData<List<SleepNight>>

    //get the recent night
    @Query("SELECT * FROM daily_sleep_quality_table ORDER BY nightID DESC LIMIT 1")
    fun getTonight():SleepNight?


}
