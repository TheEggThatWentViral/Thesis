package com.example.thesisapp.config

import android.content.Context
import com.example.thesisapp.domain.model.LoginCredentials
import dagger.hilt.android.qualifiers.ApplicationContext
import hu.autsoft.krate.SimpleKrate
import hu.autsoft.krate.stringPref
import javax.inject.Inject

class ConfigurationProvider @Inject constructor(
    @ApplicationContext context: Context
) : SimpleKrate(context) {

    var username: String? by stringPref("USERNAME")
    var password: String? by stringPref("PASSWORD")
    var accessToken: String? by stringPref("ACCESS_TOKEN")
    var expiredAccessToken: String? by stringPref("EXPIRED_ACCESS_TOKEN")
    var refreshToken: String? by stringPref("REFRESH_TOKEN")
    var expiredRefreshToken: String? by stringPref("EXPIRED_REFRESH_TOKEN")

    fun getRememberedCredentials(): LoginCredentials? {
        val currentUsername = username
        val currentPassword = password

        if (username == null || currentPassword == null) {
            return null
        }

        return LoginCredentials(
            username = currentUsername ?: "",
            password = currentPassword
        )
    }

    fun clearSession() {
        username = null
        password = null
        accessToken = null
        expiredAccessToken = null
        refreshToken = null
        expiredRefreshToken = null
    }
}
