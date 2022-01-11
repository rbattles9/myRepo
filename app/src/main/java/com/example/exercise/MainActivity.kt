package com.example.exercise

import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exercise.adapter.UserAdapter
import com.example.exercise.data.responses.User
import com.example.exercise.databinding.ActivityMainBinding
import com.example.exercise.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity  : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private val userViewModel: UserViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUI()
        setUpObservers()
        }

    private fun setUpUI() {
        binding.userRecyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = UserAdapter()
            addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayout.VERTICAL))
        }
    }

    private fun setUpObservers() {
        userViewModel.getUsers().observe(this, { userList ->
            userList?.let {
                binding.userRecyclerview.apply {
                    with(adapter as UserAdapter) {
                        users = it
                        notifyDataSetChanged()
                    }
                }
            }
        })
        userViewModel.getPostCount().observe(this, {postCounts ->
            postCounts?.let {
                binding.userRecyclerview.apply {
                    with(adapter as UserAdapter){
                        postCount = it
                        notifyDataSetChanged()
                    }
                }
            }
        })
    }
}
