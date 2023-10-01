package com.example.thesisapp.data.network.di

import com.example.thesisapp.data.network.api.AuthenticationApi
import com.example.thesisapp.data.network.source.AuthenticationNetworkDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideAuthenticationNetworkDataSource(
        authenticationApi: AuthenticationApi
    ): AuthenticationNetworkDataSource = AuthenticationNetworkDataSource(authenticationApi)
}
