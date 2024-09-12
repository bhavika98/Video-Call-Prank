package com.app.incroyable.videocall_prank.database.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.app.incroyable.videocall_prank.database.dao.UserDao
import com.app.incroyable.videocall_prank.database.model.User
import com.app.incroyable.videocall_prank.database.network.UserDatabase
import com.app.incroyable.videocall_prank.database.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(context: Context) : ViewModel() {
    private val userRepository: UserRepository

    private val _users: MutableLiveData<List<User>> = MutableLiveData()
    val users: LiveData<List<User>> = _users

    private val _user: MutableLiveData<User> = MutableLiveData()
    val user: LiveData<User> = _user

    init {
        val userDao: UserDao = UserDatabase.getDatabase(context).userDao()
        userRepository = UserRepository(userDao)
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            userRepository.getAllUsers().collect { userList ->
                _users.value = userList
            }
        }
    }

    fun getUser(id: Int) {
        viewModelScope.launch {
            userRepository.getUser(id).collect { user ->
                _user.value = user
            }
        }
    }

    fun addUser(user: User) {
        viewModelScope.launch {
            userRepository.insertUser(user)
        }
    }

    fun addMembers(users: List<User>) {
        viewModelScope.launch {
            userRepository.insertMembers(users)
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch {
            userRepository.updateUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            userRepository.deleteUser(user)
        }
    }
}

@Suppress("UNCHECKED_CAST")
class UserViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}