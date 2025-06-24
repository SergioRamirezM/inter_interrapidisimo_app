package com.interrapidisimo.interrapidapp.di

import android.content.Context
import androidx.room.Room
import com.interrapidisimo.interrapidapp.data.database.AppDatabase
import com.interrapidisimo.interrapidapp.data.database.daos.LocalityDao
import com.interrapidisimo.interrapidapp.data.database.daos.SchemaDao
import com.interrapidisimo.interrapidapp.data.database.daos.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext ctx: Context): AppDatabase {
        return Room.databaseBuilder(ctx, AppDatabase::class.java, "interrapidapp.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides fun provideUserDao(db: AppDatabase): UserDao = db.userDao()
    @Provides fun provideSchemaDao(db: AppDatabase): SchemaDao = db.schemaDao()
    @Provides fun provideLocalityDao(db: AppDatabase): LocalityDao = db.localityDao()
}