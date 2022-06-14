package com.example.whac_a_mole

import android.app.Application
import androidx.room.Room
import com.example.whac_a_mole.database.AppDatabase
import com.example.whac_a_mole.di.DaggerMainComponent
import com.example.whac_a_mole.di.MainComponent
import com.example.whac_a_mole.di.DatabaseModule

class MainApp: Application(){

    lateinit var appComponent: MainComponent
    private var database: AppDatabase? = null

    override fun onCreate() {
        super.onCreate()
        initRoom()
        initDagger()
    }

    private fun initDagger() {
        appComponent = DaggerMainComponent.builder()
            .databaseModule(DatabaseModule(this!!.database!!))
            .build()
    }

    private fun initRoom() {
        database = Room.databaseBuilder(this, AppDatabase::class.java, "local_db")
            .allowMainThreadQueries()
            .build()
    }
}