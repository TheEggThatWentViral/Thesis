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

    var email: String? by stringPref("EMAIL")
    var password: String? by stringPref("PASS")
    var token: String? by stringPref("TOKEN")

    fun getRememberedCredentials(): LoginCredentials? {
        val currentEmail = email
        val currentPassword = password

        if (currentEmail == null || currentPassword == null) {
            return null
        }

        return LoginCredentials(
            email = currentEmail,
            password = currentPassword,
        )
    }
}