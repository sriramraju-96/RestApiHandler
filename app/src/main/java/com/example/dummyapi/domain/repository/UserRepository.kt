package com.example.dummyapi.domain.repository

import com.example.dummyapi.data.model.UserDto
import com.example.dummyapi.domain.model.User


interface UserRepository {
    suspend fun getUserList() : List<UserDto>
    suspend fun addUser(username : User)
}