package com.example.questcreator.user_database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UsersDatabase : RoomDatabase() {
    abstract val dao: UserDao

//    abstract fun userDao(): UserDao
//
//    companion object {
//        private const val DATABASE_NAME = "USER_DATABASE"
//
//        @Volatile
//        private var appDatabaseInstance: AppDatabase? = null
//
//        fun getDatabase(context: Context): AppDatabase? {
//            if (appDatabaseInstance == null) {
//                synchronized(AppDatabase::class.java) {
//                    if (appDatabaseInstance == null) {
//                        appDatabaseInstance = Room.databaseBuilder(
//                            context.applicationContext,
//                            AppDatabase::class.java, DATABASE_NAME
//                        )
//                            .build()
//                    }
//                }
//            }
//            return appDatabaseInstance
//        }
//    }
}