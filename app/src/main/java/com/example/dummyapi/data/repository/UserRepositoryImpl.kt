package com.example.dummyapi.data.repository

import com.example.dummyapi.data.model.UserDto
import com.example.dummyapi.data.model.UserResponse
import com.example.dummyapi.data.remote.UserService
import com.example.dummyapi.domain.model.User
import com.example.dummyapi.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userService: UserService) : UserRepository {
    override suspend fun getUserList(): List<UserDto> {
        return userService.getUserList()
    }

    override suspend fun addUser(name: User) {
        return userService.addUser(username = name)
    }

}