package com.example.kt_retrofit.data

import androidx.lifecycle.ViewModel
import com.example.kt_retrofit.data.api.Client

object GithubRepository : ViewModel() {

    suspend fun getUser() = Client.api.getUsers()

    suspend fun searchUser(name: String) = Client.api.searchUsers(name).body()?.items

    suspend fun getUser(id: String?) = id?.let { Client.api.getUser(it) }
}