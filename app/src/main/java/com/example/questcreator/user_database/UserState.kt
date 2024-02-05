package com.example.questcreator.user_database

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class UserState(
    val users: List<User> = emptyList(),
    val username: MutableState<String> = mutableStateOf(""),
    val password: MutableState<String> = mutableStateOf("")
)
