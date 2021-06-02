
package com.example.android.trackmysleepquality.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SleepNight::class],version = 1,exportSchema = false )
abstract class SleepDatabase: RoomDatabase(){
    abstract val sleepDatabaseDao: SleepDatabaseDao

    companion object{
        @Volatile // ensures value of instance is always up to data
        private var INSTANCE: SleepDatabase? = null //keeps reference to the database

        fun getInstance(context: Context): SleepDatabase {
            //synchronized ensures only one thread of execution at a time
            synchronized(this){
                var instance = INSTANCE //kotlin smart cast

                if(instance == null){
                    instance = Room.databaseBuilder(context.applicationContext,
                    SleepDatabase::class.java,"sleep_history_database")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
