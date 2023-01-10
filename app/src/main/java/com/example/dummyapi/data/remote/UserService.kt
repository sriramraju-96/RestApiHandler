package com.example.dummyapi.data.remote

import com.example.dummyapi.data.model.UserDto
import com.example.dummyapi.data.model.UserResponse
import com.example.dummyapi.domain.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {
    @GET("/userList")
    suspend fun getUserList() : List<UserDto>

    @POST("/")
    suspend fun addUser(@Body username : User)

}