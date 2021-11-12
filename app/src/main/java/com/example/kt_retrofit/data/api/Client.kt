package com.example.kt_retrofit.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.github.com/"

object Client {

    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: GitHubService by lazy {
        retrofit.create(GitHubService::class.java)
    }


}