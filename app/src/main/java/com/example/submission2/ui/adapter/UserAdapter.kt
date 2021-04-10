package com.example.submission2.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submission2.data.model.Users
import com.example.submission2.databinding.ItemUserBinding
import java.util.ArrayList

class UserAdapter:RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private var listData = ArrayList<Users>()

    var onItemClick: ((Users) -> Unit)? = null

    fun setData(newListData: ArrayList<Users>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class UserViewHolder(private val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Users){
            with(binding){
                Glide.with(itemView.context)
                        .load(data.avatar)
                        .into(circleImageView)
                tvName.text = data.username
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}
