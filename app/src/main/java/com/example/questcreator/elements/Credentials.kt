package com.example.questcreator.elements

data class Credentials(
    var login: String = "",
    var password: String = "",
    var remember: Boolean = false
) {
    fun isNotEmpty(): Boolean {
        return login.isNotEmpty() && password.isNotEmpty()
    }
}