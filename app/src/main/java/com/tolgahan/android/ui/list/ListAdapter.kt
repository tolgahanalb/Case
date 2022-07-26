package com.tolgahan.android.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tolgahan.android.data.network.model.response.FollowersItem
import com.tolgahan.android.data.network.model.response.UserModel
import com.tolgahan.android.databinding.ItemListBinding

class ListAdapter(
    val itemClicked: (FollowersItem) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list = AsyncListDiffer(this, ProductDiffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Holder).bind(list.currentList[position])

    }

    override fun getItemCount() = list.currentList.size

    fun submitList(movieList: List<FollowersItem>) {
        this.list.submitList(movieList)
    }

    inner class Holder(val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FollowersItem) {
            binding.model = item
            binding.parentView.setOnClickListener {
                itemClicked(item)
            }
            binding.favIV.setOnClickListener{
                list.currentList[absoluteAdapterPosition].isClicked = !list.currentList[absoluteAdapterPosition].isClicked
                notifyItemChanged(absoluteAdapterPosition)
            }
        }
    }

    object ProductDiffCallback : DiffUtil.ItemCallback<FollowersItem>() {
        override fun areItemsTheSame(oldItem: FollowersItem, newItem: FollowersItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FollowersItem, newItem: FollowersItem): Boolean {
            return oldItem == newItem
        }
    }
}