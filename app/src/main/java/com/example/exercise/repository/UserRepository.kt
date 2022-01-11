package com.example.exercise.repository

import com.example.exercise.service.UserApiService


class UserRepository (private val userApiService: UserApiService) {
    suspend fun getUsers() = userApiService.getUsers()
    suspend fun getPostsByUser(userId: Int) = userApiService.getPostsByUser(userId)
}