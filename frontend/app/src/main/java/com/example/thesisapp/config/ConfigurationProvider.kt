package com.example.thesisapp.config

import android.content.Context
import com.example.thesisapp.data.network.model.LoginRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import hu.autsoft.krate.SimpleKrate
import hu.autsoft.krate.stringPref
import javax.inject.Inject

class ConfigurationProvider @Inject constructor(
    @ApplicationContext context: Context
) : SimpleKrate(context) {

    var username: String? by stringPref("USERNAME")
    var password: String? by stringPref("PASS")
    var token: String? by stringPref("TOKEN")

    fun getRememberedCredentials(): LoginRequest? {
        val currentUsername = username
        val currentPassword = password

        if (username == null || currentPassword == null) {
            return null
        }

        return LoginRequest(
            username = currentUsername,
            password = currentPassword
        )
    }
}
