package com.example.questcreator.user_database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Upsert //New version supports inserting and updating in one function
    suspend fun upsertUser(user: User)

//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun addUser(user: User)

    @Query("SELECT * FROM users ORDER BY dateAdded ")
    fun getUsersOrderByDateAdded(): Flow<List<User>>
    @Query("SELECT * FROM users ORDER BY username ASC")
    fun getUsersOrderByUsername(): Flow<List<User>>
    @Query("SELECT * FROM users WHERE username = :username")
    fun getUserByUsername(username: String): User

    @Query("SELECT * FROM users")
    fun getAllUsers(): List<User>

//    @Update
//    suspend fun updateUserDetails(user: User)

    @Delete
    suspend fun deleteUser(user: User)
}