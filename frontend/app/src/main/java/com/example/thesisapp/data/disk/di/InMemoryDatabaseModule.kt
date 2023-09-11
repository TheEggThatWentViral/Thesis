package com.example.thesisapp.data.disk.di

import android.content.Context
import androidx.room.Room
import com.example.thesisapp.data.disk.dao.AdvertisedJobDao
import com.example.thesisapp.data.disk.dao.UserDao
import com.example.thesisapp.data.disk.db.InMemoryDatabase
import com.example.thesisapp.data.disk.source.JobDiskSataSource
import com.example.thesisapp.data.disk.source.UserDiskDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class InMemoryDatabaseModule {

    @Provides
    @Singleton
    fun provideInMemoryDatabase(@ApplicationContext context: Context): InMemoryDatabase {
        return Room
            .inMemoryDatabaseBuilder(context, InMemoryDatabase::class.java)
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(inMemoryDatabase: InMemoryDatabase) = inMemoryDatabase.userDao()

    @Provides
    @Singleton
    fun provideAdvertisedJobDao(inMemoryDatabase: InMemoryDatabase) =
        inMemoryDatabase.advertisedJobDao()

    @Provides
    @Singleton
    fun provideUserDiskDataSource(userDao: UserDao) = UserDiskDataSource(userDao)

    @Provides
    @Singleton
    fun provideJobDiskDataSource(jobDao: AdvertisedJobDao) = JobDiskSataSource(jobDao)
}
