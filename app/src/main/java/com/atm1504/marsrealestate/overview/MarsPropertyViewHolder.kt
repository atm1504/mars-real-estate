package com.atm1504.marsrealestate.overview

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.atm1504.marsrealestate.databinding.GridViewItemBinding
import com.atm1504.marsrealestate.network.MarsProperty

class MarsPropertyViewHolder(private var binding: GridViewItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(marsProperty: MarsProperty) {
        binding.property = marsProperty
        binding.executePendingBindings()
    }
}

class MarsDiffCallback : DiffUtil.ItemCallback<MarsProperty>() {
    override fun areItemsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
        return oldItem == newItem
    }
}