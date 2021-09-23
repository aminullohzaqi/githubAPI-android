package com.example.mygithubuser2

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mygithubuser2.databinding.UserItemBinding

class ListUserAdapter (private val listUserAdapter: ArrayList<User>): RecyclerView.Adapter<ListUserAdapter.ListViewHolder>() {

    private val mData = ArrayList<User>()

    fun setData(items: ArrayList<User>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = UserItemBinding.bind(itemView)
        fun bind(userItems : User){
            with(itemView){
                binding.itemName.text = userItems.username
                Glide.with(itemView.context)
                    .load(userItems.imageProfile)
                    .into(binding.itemImg)

                itemView.setOnClickListener{
                    val detailActivity = Intent(context, DetailActivity::class.java)
                    detailActivity.putExtra(DetailActivity.EXTRA_USER, "${userItems.username}")
                    detailActivity.putExtra(DetailActivity.EXTRA_URL, "${userItems.url}")
                    context.startActivity(detailActivity)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val mBind = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return ListViewHolder(mBind)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    override fun getItemCount(): Int {
        return mData.size
    }
}