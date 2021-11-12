package com.example.kt_retrofit.data.api

import com.example.kt_retrofit.data.models.SearchResponse
import com.example.kt_retrofit.data.models.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubService {

    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("search/users")
    suspend fun searchUsers(@Query("q") name: String): Response<SearchResponse>
}
