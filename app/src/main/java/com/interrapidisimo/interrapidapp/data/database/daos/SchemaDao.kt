package com.interrapidisimo.interrapidapp.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.interrapidisimo.interrapidapp.data.database.entities.SchemaEntity

@Dao
interface SchemaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(schemas: List<SchemaEntity>)

    @Query("DELETE FROM schema_table")
    suspend fun clearAll()

    @Query("SELECT * FROM schema_table")
    suspend fun getAll(): List<SchemaEntity>
}