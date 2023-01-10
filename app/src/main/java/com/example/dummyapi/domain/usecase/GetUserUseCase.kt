package com.example.dummyapi.domain.usecase

import com.example.dummyapi.common.Resource
import com.example.dummyapi.domain.model.User
import com.example.dummyapi.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(): Flow<Resource<List<User>>> = flow {
        try {
            emit(Resource.Loading())
            emit(Resource.Success(userRepository.getUserList().map { it.toUser()}))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage.orEmpty()))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage.orEmpty()))
        }
    }

}