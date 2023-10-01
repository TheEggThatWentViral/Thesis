package com.example.thesisapp.ui.login

sealed class LoginEvent

data class SignInBtnClicked(
    val username: String,
    val password: String,
    val rememberMe: Boolean
) : LoginEvent()

data class UsernameValueChanged(
    val username: String
) : LoginEvent()

data class PasswordValueChanged(
    val password: String
) : LoginEvent()


