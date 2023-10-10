package com.example.thesisapp.data.network.di

import com.example.thesisapp.data.network.api.ThesisApi
import com.example.thesisapp.data.network.source.JobNetworkDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthenticatedModule {

    @Singleton
    @Provides
    fun provideThesisApi(@HttpModule.AuthenticatedClient retrofit: Retrofit): ThesisApi {
        return retrofit.create(ThesisApi::class.java)
    }

    @Provides
    @Singleton
    fun provideJobNetworkDataSource(
        thesisApi: ThesisApi
    ): JobNetworkDataSource = JobNetworkDataSource(thesisApi)
}
