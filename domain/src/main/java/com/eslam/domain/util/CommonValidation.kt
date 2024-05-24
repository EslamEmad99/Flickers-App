package com.eslam.domain.util

import java.util.regex.Pattern

object CommonValidation {

    fun String.isValidConfirmationCode() = this.isNotBlank() && this.trim().length == 4

    fun String.isValidPhone() = this.isNotBlank() && this.trim().length in 9..11

    fun String.isValidName() = this.isNotBlank() && this.trim().length >= 3

    fun String.isValidText() = this.isNotBlank() && this.trim().length >= 3

    fun String.isValidPassword() = this.isNotBlank() && this.trim().length >= 6

    fun String.isValidPrice() =
        this.isNotBlank() && this.toDoubleOrNull() != null && this.toDouble() > 0

    fun String.isValidQuantity() = this.isNotBlank() && this.toInt() > 0

    fun String.isValidDiscount() =
        this.isNotBlank() && this.toDoubleOrNull() != null && this.toDouble() in 1.0..100.0

    fun String.isValidConfirmPassword(password: String) =
        this.isNotBlank() && this == password

    fun String.isValidEmailAddress(): Boolean {
        val regex: Pattern = Pattern.compile(
            "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+",
        )
        return regex.matcher(this).matches()
    }

    fun String.isValidTaxNumber() = this.trim().length == 15
    fun String.isValidBankAccountNumber() = this.trim().length in 9..18

    fun String.isValidCardHolderName(): Boolean {
        val length = this.split(" ").size
        return this.isAllUpperCase() && length in 2..5
    }

    fun String.isAllUpperCase(): Boolean {
        this.forEach {
            if (!it.isWhitespace()) {
                if (it.isLowerCase() || it.isDigit()) {
                    return false
                }
            }
        }
        return true
    }

    fun String.isValidIbanNumber() = this.trim().length in 12..24

    fun String.isValidCountryCode() = this.isNotBlank()

    fun String?.isValidDeviceId() = !this.isNullOrEmpty()

    fun String.isValidLink(): Boolean {
        val regex: Pattern =
            Pattern.compile(
                "^((ftp|http|https):\\/\\/)?(www.)?(?!.*(ftp|http|https|www.))[a-zA-Z0-9_-]+(\\.[a-zA-Z]+)+((\\/)[\\w#]+)*(\\/\\w+\\?[a-zA-Z0-9_]+=\\w+(&[a-zA-Z0-9_]+=\\w+)*)?$",
                Pattern.CASE_INSENSITIVE
            )
        return regex.matcher(this).matches()
    }
}