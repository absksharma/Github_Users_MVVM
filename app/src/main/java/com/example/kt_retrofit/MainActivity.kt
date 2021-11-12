package com.example.kt_retrofit

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kt_retrofit.data.models.User
import com.example.kt_retrofit.databinding.ActivityMainBinding
import com.example.kt_retrofit.ui.adapter.UserAdapter
import com.example.kt_retrofit.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private val list = arrayListOf<User>()
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userAdapter = UserAdapter(this, list)

        binding.userRv.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        binding.searchView.isSubmitButtonEnabled = true


        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    findUser(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return true
            }
        })

        viewModel.fetchUsers()

        viewModel.user.observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                list.addAll(it)
                userAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun findUser(it: String) {
        viewModel.searchUsers(it)
        viewModel.searchedUser.observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                list.addAll(it)
                userAdapter.notifyDataSetChanged()
            }
        })

    }
}