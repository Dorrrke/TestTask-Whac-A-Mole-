package com.example.whac_a_mole.di

import com.example.whac_a_mole.database.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule(private val appDatabase: AppDatabase) {
    @Provides
    internal fun providesDatabase(): AppDatabase {
        return appDatabase
    }
}