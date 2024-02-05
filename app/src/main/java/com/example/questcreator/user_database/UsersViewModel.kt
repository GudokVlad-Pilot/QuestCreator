package com.example.questcreator.user_database

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UsersViewModel(private val dao: UserDao): ViewModel() {
    private val isSortedByDateAdded = MutableStateFlow(true)

    private val users =
        isSortedByDateAdded.flatMapLatest { sort ->
            if (sort) {
                dao.getUsersOrderByDateAdded()
            } else {
                dao.getUsersOrderByUsername()
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val _state = MutableStateFlow(UserState())
    val state = combine(_state, isSortedByDateAdded, users) {state, isSortedByDateAdded, users ->
        state.copy(
            users = users
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UserState())
    fun onEvent(event: UsersEvent) {
        when (event) {
            is UsersEvent.DeleteUser -> {
                viewModelScope.launch {
                    dao.deleteUser(event.user)
                }
            }
            is UsersEvent.SaveUser -> {
                val user = User(
                        username = state.value.username.value,
                        password = state.value.password.value,
                        dateAdded = System.currentTimeMillis()
                )
                viewModelScope.launch {
                    dao.upsertUser(user)
                }

                _state.update {
                    it.copy(
                        username = mutableStateOf(""),
                        password = mutableStateOf("")
                    )
                }
            }
            UsersEvent.SortUsers -> {
                isSortedByDateAdded.value = !isSortedByDateAdded.value
            }
        }
    }

//    private val userDao = AppDatabase.getDatabase(application).userDao()
//
//    fun insertUser(user: User) {
//        viewModelScope.launch {
//            userDao.insertUser(user)
//        }
//    }
}