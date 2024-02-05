package com.example.questcreator.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.questcreator.ui.theme.QuestCreatorTheme
import com.example.questcreator.user_database.UsersDatabase
import com.example.questcreator.user_database.UsersViewModel

class MainActivity : ComponentActivity() {
    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            UsersDatabase::class.java,
            "users.db"
        ).build()
    }

    private val viewModel by viewModels<UsersViewModel> (
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun<T: ViewModel> create(modelClass: Class<T>): T {
                    return UsersViewModel(database.dao) as T
                }
            }
        }
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuestCreatorTheme {
                val state by viewModel.state.collectAsState()
                Navigation(rememberNavController(),state)
            }
        }
    }
}