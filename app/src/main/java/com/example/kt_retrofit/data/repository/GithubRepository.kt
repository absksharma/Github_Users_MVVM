package com.example.kt_retrofit.data.repository

import androidx.lifecycle.ViewModel
import com.example.kt_retrofit.data.api.Client

object GithubRepository : ViewModel() {

    suspend fun getUser() = Client.api.getUsers()

    suspend fun searchUser(name: String) = Client.api.searchUsers(name)
}