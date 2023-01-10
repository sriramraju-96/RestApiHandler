package com.example.dummyapi.presentation.user

import com.example.dummyapi.domain.model.User

data class UserState(
    val loading : Boolean = false,
    val error : String = "",
    val users :List<User> = emptyList()
)