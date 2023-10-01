package com.example.thesisapp.ui.registration

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thesisapp.R
import com.example.thesisapp.domain.model.RegistrationCredentials
import com.example.thesisapp.ui.util.PresentationHttpError
import com.example.thesisapp.ui.util.PresentationResponse
import com.example.thesisapp.ui.util.PresentationResult
import com.example.thesisapp.ui.util.isSamePasswords
import com.example.thesisapp.ui.util.isValidEmail
import com.example.thesisapp.ui.util.isValidInput
import dagger.hilt.android.lifecycle.HiltViewModel
import de.palm.composestateevents.triggered
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registrationPresenter: RegistrationPresenter
) : ViewModel() {

    private val _stateStream: MutableStateFlow<RegistrationViewState> =
        MutableStateFlow(RegistrationViewState())
    val stateStream = _stateStream.asStateFlow()

    private var state: RegistrationViewState
        get() = _stateStream.value
        set(newState) {
            _stateStream.update { newState }
        }


    fun setUsername(username: String) {
        state = state.copy(username = username)
    }

    fun setPassword(password: String) {
        state = state.copy(password = password)
    }

    fun setPasswordAgain(passwordAgain: String) {
        state = state.copy(passwordAgain = passwordAgain)
    }

    fun setEmail(email: String) {
        state = state.copy(email = email)
    }

    fun setFirstname(firstname: String) {
        state = state.copy(firstname = firstname)
    }

    fun setLastname(lastname: String) {
        state = state.copy(lastname = lastname)
    }

    fun register(context: Context) {
        viewModelScope.launch {
            state = validateInputs(
                state.username,
                state.password,
                state.passwordAgain,
                state.email,
                state.firstname,
                state.lastname
            )

            if (state.usernameError || state.passwordError || state.emailError ||
                state.firstnameError || state.lastnameError
            ) {
                return@launch
            }

            val registrationResponse = registrationPresenter.register(
                RegistrationCredentials(
                    firstName = state.firstname,
                    lastName = state.lastname,
                    email = state.email,
                    username = state.username,
                    password = state.password
                )
            )

            handleRegistrationResponse(registrationResponse, context)
        }
    }

    private fun handleRegistrationResponse(
        registrationResponse: PresentationResponse<Boolean>,
        context: Context
    ) {
        return when (registrationResponse) {
            is PresentationResult -> {
                if (registrationResponse.result) {
                    Timber.i("Registration is successful")
                    state = state.copy(registrationSuccess = triggered)
                } else {
                    state = state.copy(
                        registrationError = triggered(
                            context.getString(R.string.unknown_error_msg)
                        )
                    )
                }
            }

            is PresentationHttpError -> {
                Timber.i("Login http error")
                state = state.copy(
                    registrationError = triggered(
                        registrationResponse.message
                            ?: context.getString(R.string.unknown_error_msg)
                    )
                )
            }

            else -> {
                Timber.i("Login network error")
                state = state.copy(
                    registrationError = triggered(context.getString(R.string.unknown_error_msg))
                )
            }
        }
    }

    private fun validateInputs(
        username: String,
        password: String,
        passwordAgain: String,
        email: String,
        firstname: String,
        lastname: String
    ) = RegistrationViewState(
        username = username,
        usernameError = username.isValidInput,
        password = password,
        passwordAgain = passwordAgain,
        passwordError = password.isSamePasswords(passwordAgain),
        email = email,
        emailError = email.isValidEmail,
        firstname = firstname,
        firstnameError = firstname.isValidInput,
        lastname = lastname,
        lastnameError = lastname.isValidInput
    )
}