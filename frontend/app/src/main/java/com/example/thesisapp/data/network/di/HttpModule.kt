package com.example.thesisapp.data.network.di

import com.example.thesisapp.BuildConfig
import com.example.thesisapp.data.network.api.ThesisApi
import com.example.thesisapp.data.network.interceptor.TokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HttpModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class LoginClient

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class AuthenticatedClient

    @AuthenticatedClient
    @Provides
    @Singleton
    fun provideAuthenticatedOkHttpClient(tokenInterceptor: TokenInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(tokenInterceptor)
            .build()

    @AuthenticatedClient
    @Provides
    @Singleton
    fun provideAuthenticatedRetrofit(@AuthenticatedClient okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @LoginClient
    @Singleton
    @Provides
    fun provideLoginOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

    @LoginClient
    @Singleton
    @Provides
    fun provideLoginRetrofit(@LoginClient okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideThesisApi(retrofit: Retrofit): ThesisApi = retrofit.create()
}
