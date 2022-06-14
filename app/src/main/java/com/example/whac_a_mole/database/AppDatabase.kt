package com.example.whac_a_mole.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.whac_a_mole.database.dao.RecordDao
import com.example.whac_a_mole.database.entity.Record

@Database(entities = [Record::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun RecordDao(): RecordDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getAppDatabase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "room-kotlin-database"
                ).build()
            }
            return INSTANCE
        }
    }
}