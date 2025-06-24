package com.interrapidisimo.interrapidapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "locality_table",
    indices = [androidx.room.Index(value = ["FullName"], unique = true)]
)
data class LocalityEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "Abbreviation")
    val abbreviation: String,

    @ColumnInfo(name = "FullName")
    val fullName: String,
)