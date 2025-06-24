package com.interrapidisimo.interrapidapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.interrapidisimo.interrapidapp.data.database.daos.LocalityDao
import com.interrapidisimo.interrapidapp.data.database.daos.SchemaDao
import com.interrapidisimo.interrapidapp.data.database.daos.UserDao
import com.interrapidisimo.interrapidapp.data.database.entities.LocalityEntity
import com.interrapidisimo.interrapidapp.data.database.entities.SchemaEntity
import com.interrapidisimo.interrapidapp.data.database.entities.UserEntity

@Database(
    entities = [
        UserEntity::class,
        SchemaEntity::class,
        LocalityEntity::class
    ],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun schemaDao(): SchemaDao
    abstract fun localityDao(): LocalityDao
}