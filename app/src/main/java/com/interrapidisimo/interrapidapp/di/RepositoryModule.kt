package com.interrapidisimo.interrapidapp.di

import com.interrapidisimo.interrapidapp.data.database.daos.LocalityDao
import com.interrapidisimo.interrapidapp.data.database.daos.SchemaDao
import com.interrapidisimo.interrapidapp.data.database.daos.UserDao
import com.interrapidisimo.interrapidapp.data.remote.ApiClient
import com.interrapidisimo.interrapidapp.data.repository.DatabaseRepository
import com.interrapidisimo.interrapidapp.data.repository.ServiceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideServiceRepository(apiClient: ApiClient): ServiceRepository {
        return ServiceRepository(apiClient)
    }

    @Provides
    @Singleton
    fun provideDatabaseRepository(
        userDao: UserDao,
        schemaDao: SchemaDao,
        localityDao: LocalityDao
    ): DatabaseRepository =
        DatabaseRepository(userDao, schemaDao, localityDao)
}