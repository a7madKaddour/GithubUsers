package com.ahmadkadddour.githubuser.data.sorce.remote.api.service

import com.ahmadkadddour.githubuser.data.sorce.remote.api.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface RetrofitService {
    @GET("users")
    suspend fun getUsers(@Query("since") sinceId: Long?): Response<List<UserResponse>>

    @GET("users/{username}")
    suspend fun getUserByName(@Path("username") username: String): Response<UserResponse>

    @GET
    suspend fun getUserByUrl(@Url url: String): Response<UserResponse>
}