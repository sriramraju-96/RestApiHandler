package com.example.dummyapi.data.model

import com.example.dummyapi.domain.model.User

data class UserDto(
    val id : String,
    val user_name: String,
    val v : Int
){
    fun toUser() : User{
        return User(user_name)
    }
}
