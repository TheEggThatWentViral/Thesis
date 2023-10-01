package com.example.thesisapp.ui.registration

import de.palm.composestateevents.StateEvent
import de.palm.composestateevents.StateEventWithContent
import de.palm.composestateevents.consumed

data class RegistrationViewState(
    val username: String = "",
    val usernameError: Boolean = false,
    val password: String = "",
    val passwordAgain: String = "",
    val passwordError: Boolean = false,
    val email: String = "",
    val emailError: Boolean = false,
    val firstname: String = "",
    val firstnameError: Boolean = false,
    val lastname: String = "",
    val lastnameError: Boolean = false,
    val registrationSuccess: StateEvent = consumed,
    val registrationError: StateEventWithContent<String> = consumed()
)
