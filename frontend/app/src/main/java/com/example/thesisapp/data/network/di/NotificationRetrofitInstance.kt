package com.example.thesisapp.data.network.di

import com.example.thesisapp.BuildConfig
import com.example.thesisapp.data.network.api.NotificationApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class NotificationRetrofitInstance {

    companion object {
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BuildConfig.FIREBASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        }

        val api by lazy {
            retrofit.create(NotificationApi::class.java)
        }
    }
}