package com.example.questcreator.user_database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
//    @PrimaryKey val uid: Int,
//    @ColumnInfo(name = "username") val username: String,
//    @ColumnInfo(name = "password") val password: String
    val username: String,
    val password: String,
    val dateAdded: Long,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)