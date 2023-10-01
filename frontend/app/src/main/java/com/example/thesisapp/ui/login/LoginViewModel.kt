package com.example.thesisapp.ui.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thesisapp.R
import com.example.thesisapp.domain.model.LoginCredentials
import com.example.thesisapp.ui.util.PresentationHttpError
import com.example.thesisapp.ui.util.PresentationResponse
import com.example.thesisapp.ui.util.PresentationResult
import com.example.thesisapp.ui.util.SnackbarManager
import com.example.thesisapp.ui.util.isValidInput
import dagger.hilt.android.lifecycle.HiltViewModel
import de.palm.composestateevents.consumed
import de.palm.composestateevents.triggered
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val snackbarManager: SnackbarManager,
    private val loginPresenter: LoginPresenter
) : ViewModel() {

    private val _stateStream: MutableStateFlow<LoginViewState> = MutableStateFlow(LoginViewState())
    val stateStream = _stateStream.asStateFlow()

    private var state: LoginViewState
        get() = _stateStream.value
        set(newState) {
            _stateStream.update { newState }
        }

    init {
        viewModelScope.launch {
            loginPresenter.loginWithSavedCredentials()
        }
    }

    fun onConsumedLoginSuccess() {
        state = state.copy(loginSuccess = consumed)
    }

    fun onConsumedLoginError() {
        state = state.copy(loginError = consumed())
    }

    fun setUsername(username: String) {
        state = state.copy(username = username)
    }

    fun setPassword(password: String) {
        state = state.copy(password = password)
    }

    fun rememberMeToggled(toggled: Boolean) {
        state = state.copy(rememberMe = !toggled)
    }

    fun loginManually(context: Context) {
        viewModelScope.launch {
            state = validateInputs(state.username, state.password)

            if (state.usernameError || state.passwordError) {
                return@launch
            }

            val loginResponse = loginPresenter.loginWithCredentials(
                credentials = LoginCredentials(
                    username = state.username,
                    password = state.password
                ),
                rememberMe = state.rememberMe
            )

            handleLoginResponse(loginResponse, context)
        }
    }

    private fun validateInputs(
        username: String,
        password: String
    ) = LoginViewState(
        loginSuccess = state.loginSuccess,
        loginError = state.loginError,
        username = state.username,
        password = state.password,
        usernameError = !username.isValidInput,
        passwordError = !password.isValidInput
    )

    private fun handleLoginResponse(
        loginResponse: PresentationResponse<Boolean>,
        context: Context
    ) {
        return when (loginResponse) {
            is PresentationResult -> {
                if (loginResponse.result) {
                    Timber.i("Login is successful")
                    state = state.copy(loginSuccess = triggered)
                } else {
                    state = state.copy(
                        loginError = triggered(context.getString(R.string.unknown_error_msg))
                    )
                }
            }

            is PresentationHttpError -> {
                Timber.i("Login http error")
                state = state.copy(
                    loginError = triggered(
                        loginResponse.message ?: context.getString(R.string.unknown_error_msg)
                    )
                )
            }

            else -> {
                Timber.i("Login network error")
                state = state.copy(
                    loginError = triggered(context.getString(R.string.unknown_error_msg))
                )
            }
        }
    }
}
