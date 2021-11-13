package com.example.kt_retrofit.ui.detailedActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kt_retrofit.data.GithubRepository
import com.example.kt_retrofit.data.models.User
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {

    private val _user: MutableLiveData<User> = MutableLiveData()
    val user: LiveData<User> = _user

    fun getUser(id: String?) {
        viewModelScope.launch {
            GithubRepository.getUser(id).let {
                _user.postValue(it)
            }
        }
    }
}