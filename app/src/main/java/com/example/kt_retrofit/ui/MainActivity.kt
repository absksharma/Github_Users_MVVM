package com.example.kt_retrofit.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kt_retrofit.R
import com.example.kt_retrofit.databinding.ActivityMainBinding
import com.example.kt_retrofit.data.models.User
import com.example.kt_retrofit.ui.adapter.UserAdapter
import com.example.kt_retrofit.ui.detailedActivity.DetailedActivity


const val EXTRA_USER_ID = "EXTRA_USER_ID"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private val list = arrayListOf<User>()
    private val originalList = arrayListOf<User>()

    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val searchView: SearchView = findViewById(R.id.searchView)

        userAdapter = UserAdapter(this, list, object : UserAdapter.ItemClickListener {
            override fun onItemClick(user: User) {
                val intent = Intent(this@MainActivity, DetailedActivity::class.java)
                intent.putExtra(EXTRA_USER_ID, user.login)
                startActivity(intent)
            }
        })

        binding.userRv.apply {
            hasFixedSize()
            adapter = userAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        searchView.isSubmitButtonEnabled = true

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    findUser(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    findUser(it)
                }
                return true
            }
        })
        searchView.setOnCloseListener {
            list.clear()
            list.addAll(originalList)
            userAdapter.notifyDataSetChanged()
            return@setOnCloseListener true
        }

        viewModel.fetchUsers()

        viewModel.user.observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                list.addAll(it)
                userAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun findUser(query: String) {
        viewModel.searchUsers(query)
        viewModel.searchedUser.observe(this@MainActivity, Observer {
            if (!it.isNullOrEmpty()) {
                binding.userRv.scrollToPosition(0)
                list.clear()
                list.addAll(it)
                originalList.addAll(it)
                userAdapter.notifyDataSetChanged()
            }
        })
    }
}
