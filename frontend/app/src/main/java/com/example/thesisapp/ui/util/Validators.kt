package com.example.thesisapp.ui.util

import java.util.regex.Pattern

private val EMAIL_ADDRESS = Pattern.compile(
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
)

val String.isValidInput: Boolean
    get() = isEmpty().not()

fun String.isSamePasswords(otherPassword: String): Boolean {
    return this == otherPassword
}

val String.isValidEmail: Boolean
    get() = isEmpty().not() && EMAIL_ADDRESS.matcher(this).matches()