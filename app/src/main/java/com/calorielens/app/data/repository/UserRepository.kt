package com.calorielens.app.data.repository

import com.calorielens.app.data.dao.UserDao
import com.calorielens.app.data.model.User
import kotlinx.coroutines.flow.Flow

class UserRepository(
    private val userDao: UserDao
) {
    fun getCurrentUser(): Flow<User?> = userDao.getCurrentUser()

    suspend fun getUserById(userId: String): User? = userDao.getUserById(userId)

    suspend fun createUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    suspend fun deleteUser(userId: String) {
        userDao.deleteUser(userId)
    }
}

