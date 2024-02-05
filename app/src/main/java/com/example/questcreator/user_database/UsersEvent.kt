package com.example.questcreator.user_database

sealed interface UsersEvent {
    object SortUsers: UsersEvent
    data class DeleteUser(val user: User): UsersEvent
    data class SaveUser (
        val username: String,
        val password: String,
    ): UsersEvent
}
