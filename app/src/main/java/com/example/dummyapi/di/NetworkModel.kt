package com.example.dummyapi.di

import com.example.dummyapi.common.Constants.BASE_URL
import com.example.dummyapi.data.remote.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModel {
    var retrofit: Retrofit? = null

    @Provides
    @Singleton
//    fun providesUserService(): Retrofit? {
//        if (retrofit == null) {
//            val interceptor = HttpLoggingInterceptor()
//            interceptor.level = HttpLoggingInterceptor.Level.BODY
//            val client = OkHttpClient.Builder().apply {
//                readTimeout(20, TimeUnit.SECONDS)
//                writeTimeout(20, TimeUnit.SECONDS)
//                connectTimeout(20, TimeUnit.SECONDS)
//                addInterceptor(interceptor)
//                addInterceptor { chain ->
//                    var request = chain.request()
//                    request = request.newBuilder()
//                        .build()
//                    val response = chain.proceed(request)
//                    response
//                }
//            }
//            retrofit = Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .client(client.build())
//
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//
//        }
//        return retrofit
//    }
    fun providesUserService(): UserService {
        return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UserService::class.java)}


}