package com.interrapidisimo.interrapidapp.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.interrapidisimo.interrapidapp.data.database.entities.LocalityEntity

@Dao
interface LocalityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(localities: List<LocalityEntity>)

    @Query("DELETE FROM locality_table")
    suspend fun clearAll()

    @Query("SELECT * FROM locality_table")
    suspend fun getAll(): List<LocalityEntity>
}