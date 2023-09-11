package com.example.thesisapp.data.network.di

import com.example.thesisapp.data.network.api.AuthenticationApi
import com.example.thesisapp.data.network.source.AuthenticationNetworkDataSource
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    fun provideAuthenticationNetworkDataSource(
        authenticationApi: AuthenticationApi
    ): AuthenticationNetworkDataSource = AuthenticationNetworkDataSource(authenticationApi)
}
