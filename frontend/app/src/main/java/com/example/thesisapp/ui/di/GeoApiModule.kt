package com.example.thesisapp.ui.di

import com.example.thesisapp.BuildConfig.GOOGLE_MAPS_API_KEY
import com.google.maps.GeoApiContext
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class GeoApiModule {

    @Provides
    @Singleton
    fun provideGeoApiContext(): GeoApiContext = GeoApiContext()
        .setApiKey(GOOGLE_MAPS_API_KEY)
        .setQueryRateLimit(3)
        .setConnectTimeout(1, TimeUnit.SECONDS)
        .setReadTimeout(1, TimeUnit.SECONDS)
        .setWriteTimeout(1, TimeUnit.SECONDS)
}