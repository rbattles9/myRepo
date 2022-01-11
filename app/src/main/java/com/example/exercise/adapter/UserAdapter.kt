package com.example.exercise.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise.data.responses.User
import com.example.exercise.databinding.UserTypeBinding
import javax.inject.Inject

class UserAdapter @Inject constructor() : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    var users: List<User> = emptyList()
    var postCount: List<Int> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            UserTypeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(users[position], postCount[position])

    override fun getItemCount(): Int = users.size

    inner class ViewHolder(private val binding: UserTypeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User, postCount: Int) {
            binding.apply {
                user.also { (id, name, username, email) ->
                    nameTextview.text = name
                    emailTextview.text = email
                }
                postCount.also {
                    postCountTextview.text = postCount.toString()
                }
            }
        }
    }
}