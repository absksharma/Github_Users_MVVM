package com.example.kt_retrofit.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kt_retrofit.R
import com.example.kt_retrofit.data.models.User

class UserAdapter(
    private val context: Context,
    private val user: List<User>,
    val itemClickLister: ItemClickListener
) : RecyclerView.Adapter<UserAdapter.UserViewModel>() {

    interface ItemClickListener {
        fun onItemClick(users: User)
    }

    inner class UserViewModel(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userName: TextView = itemView.findViewById(R.id.tvName)
        private val userId: TextView = itemView.findViewById(R.id.tvId)
        private val image: ImageView = itemView.findViewById(R.id.imageView)

        fun bind(item: User) = with(itemView) {
            userId.text = item.id.toString()
            userName.text = item.login
            com.squareup.picasso.Picasso.get().load(item.avatarUrl).into(image)
            itemView.setOnClickListener {
                itemClickLister.onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserViewModel(
            LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)
        )

    override fun getItemCount() = user.size

    override fun onBindViewHolder(holder: UserViewModel, position: Int) {
        holder.bind(user[position])
    }

}