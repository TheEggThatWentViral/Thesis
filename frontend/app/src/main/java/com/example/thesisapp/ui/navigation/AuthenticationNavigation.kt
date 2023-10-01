package com.example.thesisapp.ui.navigation

import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.thesisapp.ui.login.LoginPage
import com.example.thesisapp.ui.login.LoginViewModel
import com.example.thesisapp.ui.registration.RegistrationPage
import com.example.thesisapp.ui.registration.RegistrationViewModel

fun NavGraphBuilder.addAuthenticationGraph(
    onNavigateToRoute: (String) -> Unit
) {
    composable(AuthenticationSections.LOGIN.route) {
        val loginViewModel: LoginViewModel = hiltViewModel()
        val state = loginViewModel.stateStream.collectAsState().value

        LoginPage(
            onNavigateToRoute = onNavigateToRoute,
            state = state,
            onUsernameValueChanged = loginViewModel::setUsername,
            onPasswordValueChanged = loginViewModel::setPassword,
            onLoginBtnClicked = loginViewModel::loginManually,
            onRememberMeToggled = loginViewModel::rememberMeToggled,
            onConsumedLoginError = loginViewModel::onConsumedLoginError,
            onConsumedLoginSuccess = loginViewModel::onConsumedLoginSuccess
        )
    }

    composable(AuthenticationSections.REGISTRATION.route) {
        val registrationViewModel: RegistrationViewModel = hiltViewModel()
        val state = registrationViewModel.stateStream.collectAsState().value

        RegistrationPage(
            onNavigateToRoute = onNavigateToRoute,
            state = state,
            onUsernameValueChanged = registrationViewModel::setUsername,
            onPasswordValueChanged = registrationViewModel::setPassword,
            onPasswordAgainValueChanged = registrationViewModel::setPasswordAgain,
            onEmailValueChanged = registrationViewModel::setEmail,
            onFirstNameValueChanged = registrationViewModel::setFirstname,
            onLastNameValueChanged = registrationViewModel::setLastname,
            onRegisterBtnClicked = registrationViewModel::register
        )
    }
}

enum class AuthenticationSections(
    val route: String
) {
    LOGIN("authentication/login"),
    REGISTRATION("authentication/registration")
}