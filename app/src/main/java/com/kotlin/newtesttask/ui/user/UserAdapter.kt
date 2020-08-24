package com.kotlin.newtesttask.ui.user

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.kotlin.newtesttask.R
import com.kotlin.newtesttask.networking.model.UserModel
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter(
    private val listener: OnUserClickListener
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    interface OnUserClickListener {
        fun onUserClick(aUser: UserModel)
    }

    private val items: ArrayList<UserModel> = ArrayList()

    fun changeDataSet(users: List<UserModel>, isClear: Boolean = true) {
        if (isClear)
            items.clear()

        items.addAll(users)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(itemView)
    }

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int) {
        holder.bindUser(items[position])
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bindUser(aUser: UserModel) {
            with(itemView) {
                item_user_full_name.text = "${aUser.first_name} ${aUser.last_name}"

                val myOptions = RequestOptions()
                    .placeholder(R.drawable.loading_img)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                Glide.with(this)
                    .load(aUser.avatar)
                    .apply(myOptions)
                    .into(item_user_avatar_iv)

                setOnClickListener {
                    listener.onUserClick(aUser)
                }
            }

        }

    }
}