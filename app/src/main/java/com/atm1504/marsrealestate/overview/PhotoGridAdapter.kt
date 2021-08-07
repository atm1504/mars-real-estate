package com.atm1504.marsrealestate.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.atm1504.marsrealestate.databinding.GridViewItemBinding
import com.atm1504.marsrealestate.network.MarsProperty

class PhotoGridAdapter : ListAdapter<MarsProperty, MarsPropertyViewHolder>(MarsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsPropertyViewHolder {
        return MarsPropertyViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MarsPropertyViewHolder, position: Int) {
        val marsProperty = getItem(position)
        holder.bind(marsProperty)
    }
}
