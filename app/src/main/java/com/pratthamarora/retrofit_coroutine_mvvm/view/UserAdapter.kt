package com.pratthamarora.retrofit_coroutine_mvvm.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pratthamarora.retrofit_coroutine_mvvm.R
import com.pratthamarora.retrofit_coroutine_mvvm.model.Users
import kotlinx.android.synthetic.main.user_item.view.*

class UserAdapter(private var users: ArrayList<Users>) : RecyclerView.Adapter<UserAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
    )

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(users[position])
    }

    fun updateUsers(newUsers: List<Users>) {
        users.clear()
        users.addAll(newUsers)
        notifyDataSetChanged()

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(users: Users) {
            with(itemView) {
                userAge.text = users.age
                userName.text = users.name
                userLocation.text = users.location

            }
            val options = RequestOptions()
                .error(R.mipmap.ic_launcher_round)
            Glide.with(itemView.profile_image.context).setDefaultRequestOptions(options)
                .load(users.url)
                .into(itemView.profile_image)

        }

    }
}