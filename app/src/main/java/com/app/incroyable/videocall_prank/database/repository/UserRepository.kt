package com.app.incroyable.videocall_prank.database.repository

import com.app.incroyable.videocall_prank.database.dao.UserDao
import com.app.incroyable.videocall_prank.database.model.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    fun getAllUsers(): Flow<List<User>> = userDao.getAllUsers()

    fun getUser(id: Int): Flow<User> = userDao.getUser(id)

    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun insertMembers(users: List<User>) {
        userDao.insertMembers(users)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }
}
