package com.example.thesisapp.ui.di

import com.example.thesisapp.ui.util.SnackbarManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UiModule {

    @Provides
    @Singleton
    fun provideSnackbarManager(): SnackbarManager = SnackbarManager
}