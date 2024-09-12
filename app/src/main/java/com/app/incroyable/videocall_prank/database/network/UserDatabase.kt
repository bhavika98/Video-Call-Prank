package com.app.incroyable.videocall_prank.database.network

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.incroyable.videocall_prank.database.dao.UserDao
import com.app.incroyable.videocall_prank.database.model.User

const val USER_TABLE = "user_table"
const val USER_DATABASE = "user_database"
const val USER_DATA = "user_data"
const val USER_ID = "user_id"
const val UPDATED_USER_DATA = "updated_user_data"

const val KEY_ID = "data_id"
const val KEY_TYPE = "data_type"
const val KEY_NAME = "data_name"
const val KEY_CONTACT = "data_contact"
const val KEY_IMAGE = "data_image"
const val KEY_VIDEO = "data_video"
const val KEY_EMOJI = "data_emoji"

const val CURRENT_CALL = "current_call"

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    USER_DATABASE
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
