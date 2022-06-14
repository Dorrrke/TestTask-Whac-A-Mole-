package com.example.whac_a_mole.database.dao

import androidx.room.*
import com.example.whac_a_mole.database.entity.Record

@Dao
interface RecordDao {
    @Query("SELECT*FROM Record")
    fun getRecord(): Record

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(record: Record)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(record: Record)
}