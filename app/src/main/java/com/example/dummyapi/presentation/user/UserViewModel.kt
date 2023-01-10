package com.example.dummyapi.presentation.user

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dummyapi.common.Resource
import com.example.dummyapi.domain.model.User
import com.example.dummyapi.domain.repository.UserRepository
import com.example.dummyapi.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val userRepository: UserRepository
) : ViewModel() {
    private var userList = listOf<User>()

    private val _state = mutableStateOf(UserState())
    val state: State<UserState> = _state

    init {
        getUsers()
    }

    fun getUsers() {
        getUserUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    result.data?.let {
                        _state.value = UserState(users = it)
                        userList = it
                    }
                }
                is Resource.Loading -> {
                    _state.value = UserState(loading = true)
                }
                is Resource.Error -> {
                    _state.value = UserState(error = result.errorMessage ?: "Unexpected Error")
                }
            }

        }.launchIn(viewModelScope)
    }

//    fun addUser(name: User) {
//        viewModelScope.launch {
//            getUserUseCase.addUser(name)
//        }
//    }

    fun addUser(name: User) {
        viewModelScope.launch {
            userRepository.addUser(name)
        }
    }

}