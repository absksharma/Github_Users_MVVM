package com.example.kt_retrofit.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kt_retrofit.data.GithubRepository
import com.example.kt_retrofit.data.models.User
import kotlinx.coroutines.launch

private const val TAG = "MainViewModel"

class MainViewModel() : ViewModel() {

    private val _user: MutableLiveData<List<User>> = MutableLiveData()
    val user: LiveData<List<User>>
        get() = _user


    private var _searchedUser: MutableLiveData<List<User>> = MutableLiveData()
    val searchedUser: LiveData<List<User>> = _searchedUser


    fun searchUsers(name: String) = viewModelScope.launch {
        GithubRepository.searchUser(name).let {
            _searchedUser.postValue(it)
        }
    }

    fun fetchUsers() {
        viewModelScope.launch {
            val fetchedUsers = GithubRepository.getUser()
            _user.value = fetchedUsers
        }
    }
}
