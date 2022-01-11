package com.example.exercise.service

import com.example.exercise.data.responses.Post
import com.example.exercise.data.responses.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApiService {
    @GET("users")
    suspend fun getUsers(): Response<List<User>>

    @GET("users/{userId}/posts")
    suspend fun getPostsByUser(@Path("userId") userId: Int): Response<List<Post>>
}