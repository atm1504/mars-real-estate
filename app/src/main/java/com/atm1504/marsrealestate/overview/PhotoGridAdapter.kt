package com.atm1504.marsrealestate.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.atm1504.marsrealestate.databinding.GridViewItemBinding
import com.atm1504.marsrealestate.network.MarsProperty

class PhotoGridAdapter(val onClickListener: OnClickListener) : ListAdapter<MarsProperty, MarsPropertyViewHolder>(MarsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsPropertyViewHolder {
        return MarsPropertyViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MarsPropertyViewHolder, position: Int) {
        val marsProperty = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(marsProperty)
        }
        holder.bind(marsProperty)
    }
}

class OnClickListener(val clickListener: (marsProperty: MarsProperty) -> Unit) {
    fun onClick(marsProperty:MarsProperty) = clickListener(marsProperty)
}
