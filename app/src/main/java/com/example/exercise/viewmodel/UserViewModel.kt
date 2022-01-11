package com.example.exercise.viewmodel

import com.example.exercise.data.responses.User
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exercise.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {
    private val userLiveData = MutableLiveData<List<User>?>()
    private val postCountLiveData = MutableLiveData<List<Int>?>()

    fun getUsers() = userLiveData
    fun getPostCount() = postCountLiveData

    init {
        loadUsers()
        loadPost()
    }

    private fun loadUsers() {
        viewModelScope.launch {
            val users = userRepository.getUsers()
            when (users.isSuccessful) {
                true -> {
                    with(users.body().orEmpty()) {
                        var userList = listOf<User>()
                        forEach { (id, name, username, email, address, phone, website, company) ->
                            userList = userList + User(
                                id, name, username, email, address,
                                phone, website, company
                            )
                        }
                        userLiveData.postValue(userList)
                    }
                }
                else -> {
                    Timber.e(users.message())
                }
            }
        }
    }

        private fun loadPost(){
            viewModelScope.launch {
                val users = userRepository.getUsers()
                var postCount = mutableListOf<Int>()
                for(user in users.body()!!){
                    val posts = userRepository.getPostsByUser(user.id)
                    var count: Int = 0
                    for(post in posts.body() !!){
                        count++
                    }
                    postCount.add(count)
                }
                postCountLiveData.postValue(postCount)
        }
    }
}