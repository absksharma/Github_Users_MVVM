package com.example.kt_retrofit.ui.detailedActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kt_retrofit.databinding.ActivityDetailedBinding
import com.example.kt_retrofit.ui.EXTRA_USER_ID
import com.squareup.picasso.Picasso

class DetailedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailedBinding
    private val viewModel by lazy {
        ViewModelProvider(this)[DetailsViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailedBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val id = intent.getStringExtra(EXTRA_USER_ID)

        viewModel.user.observe(this, Observer {
            binding.apply {
                userid.text = it.login
                url.text = it.url
                bio.text = it.bio
                name.text = it.name
                Picasso.get().load(it.avatarUrl).into(image)
            }
        })
        viewModel.getUser(id)
    }
}