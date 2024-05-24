package com.eslam.domain.exceptions

sealed class AuthValidationException : Exception() {
    data object InvalidNameException : AuthValidationException()
    data object InvalidCountryCodeException : AuthValidationException()
    data object InvalidPhoneException : AuthValidationException()
    data object InvalidEmailException : AuthValidationException()
    data object InvalidPasswordException : AuthValidationException()
    data object InvalidConfirmPasswordException : AuthValidationException()
    data object InvalidConfirmationCodeException : AuthValidationException()
    data object InvalidAcceptTermsException : AuthValidationException()
}