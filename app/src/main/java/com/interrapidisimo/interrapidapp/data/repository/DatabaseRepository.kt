package com.interrapidisimo.interrapidapp.data.repository

import com.interrapidisimo.interrapidapp.data.database.daos.LocalityDao
import com.interrapidisimo.interrapidapp.data.database.daos.SchemaDao
import com.interrapidisimo.interrapidapp.data.database.daos.UserDao
import com.interrapidisimo.interrapidapp.data.database.entities.LocalityEntity
import com.interrapidisimo.interrapidapp.data.database.entities.SchemaEntity
import com.interrapidisimo.interrapidapp.data.database.entities.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseRepository @Inject constructor(
    private val userDao: UserDao,
    private val schemaDao: SchemaDao,
    private val localityDao: LocalityDao
) {

    suspend fun saveUser(user: UserEntity) = withContext(Dispatchers.IO) {
        userDao.saveUser(user)
    }

    suspend fun getUser(): UserEntity = withContext(Dispatchers.IO) {
        userDao.getSingleUser()
    }

    suspend fun deleteUser() = withContext(Dispatchers.IO) {
        userDao.deleteAllUsers()
    }

    suspend fun saveSchemas(schemas: List<SchemaEntity>) = withContext(Dispatchers.IO) {
        schemaDao.clearAll()
        schemaDao.insertAll(schemas)
    }

    suspend fun loadSchemas(): List<SchemaEntity> = withContext(Dispatchers.IO) {
        schemaDao.getAll()
    }

    suspend fun saveLocalities(localities: List<LocalityEntity>) = withContext(Dispatchers.IO) {
        localityDao.clearAll()
        localityDao.insertAll(localities)
    }

    suspend fun loadLocalities(): List<LocalityEntity> = withContext(Dispatchers.IO) {
        localityDao.getAll()
    }

}