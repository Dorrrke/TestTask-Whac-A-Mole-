package com.example.whac_a_mole.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class Record(
    @PrimaryKey(autoGenerate = true) val RecordId: Int,
    @ColumnInfo(name = "RecordValue") val record: Int
)