package com.app.incroyable.videocall_prank.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.app.incroyable.videocall_prank.database.model.User
import com.app.incroyable.videocall_prank.database.network.USER_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM $USER_TABLE")
    fun getAllUsers(): Flow<List<User>>

    @Query("SELECT * FROM $USER_TABLE WHERE id = :id")
    fun getUser(id: Int): Flow<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Insert
    suspend fun insertMembers(users: List<User>)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)
}
