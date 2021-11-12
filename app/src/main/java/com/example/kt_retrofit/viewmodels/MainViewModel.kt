package com.example.kt_retrofit.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kt_retrofit.data.models.SearchResponse
import com.example.kt_retrofit.data.models.User
import com.example.kt_retrofit.data.repository.GithubRepository
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel() {

    private val _user: MutableLiveData<List<User>> = MutableLiveData()
    val user: LiveData<List<User>>
        get() = _user

    private val _searchedUser: MutableLiveData<List<SearchResponse>> = MutableLiveData()
    val searchedUser: LiveData<List<SearchResponse>>
        get() = _searchedUser

    fun fetchUsers() {
        viewModelScope.launch {
            val fetchedUsers = GithubRepository.getUser()
            _user.value = fetchedUsers
        }
    }

    fun searchUsers(name: String) {
        viewModelScope.launch {
            val foundedUsers = GithubRepository.searchUser(name)
            _searchedUser.value = foundedUsers
        }
    }
}