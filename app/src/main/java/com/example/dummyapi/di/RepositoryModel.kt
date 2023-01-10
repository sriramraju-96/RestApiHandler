package com.example.dummyapi.di

import com.example.dummyapi.data.remote.UserService
import com.example.dummyapi.data.repository.UserRepositoryImpl
import com.example.dummyapi.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModel {

    @Provides
    @Singleton
    fun providesRepository(UserService : UserService) : UserRepository {
        return UserRepositoryImpl(UserService)
    }
}