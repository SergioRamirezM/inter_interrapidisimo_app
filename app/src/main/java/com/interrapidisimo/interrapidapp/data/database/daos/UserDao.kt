package com.interrapidisimo.interrapidapp.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.interrapidisimo.interrapidapp.data.database.entities.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(userEntity: UserEntity)

    @Query("SELECT * FROM user_table LIMIT 1")
    suspend fun getSingleUser(): UserEntity

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsers()
}