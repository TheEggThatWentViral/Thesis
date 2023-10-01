package com.example.thesisapp.ui.registration

sealed class RegistrationEvent

data class UsernameValueChanged(
    val username: String
) : RegistrationEvent()

data class PasswordValueChanged(
    val password: String
) : RegistrationEvent()

data class PasswordAgainValueChanged(
    val passwordAgain: String
) : RegistrationEvent()

data class EmailValueChanged(
    val email: String
) : RegistrationEvent()

data class FirstnameValueChanged(
    val firstname: String
) : RegistrationEvent()

data class LastnameValueChanged(
    val lastname: String
) : RegistrationEvent()

data class SignUpBtnClicked(
    val username: String,
    val password: String,
    val email: String,
    val firstName: String,
    val lastName: String,
) : RegistrationEvent()