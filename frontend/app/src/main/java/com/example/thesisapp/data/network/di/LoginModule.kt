package com.example.thesisapp.data.network.di

import com.example.thesisapp.data.network.api.AuthenticationApi
import com.example.thesisapp.data.network.source.AuthenticationNetworkDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LoginModule {

    @Singleton
    @Provides
    fun provideAuthenticationApi(@HttpModule.LoginClient retrofit: Retrofit): AuthenticationApi {
        return retrofit.create(AuthenticationApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthenticationNetworkDataSource(
        authenticationApi: AuthenticationApi
    ): AuthenticationNetworkDataSource = AuthenticationNetworkDataSource(authenticationApi)
}
