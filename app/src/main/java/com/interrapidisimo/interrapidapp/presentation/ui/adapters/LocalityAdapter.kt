package com.interrapidisimo.interrapidapp.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.interrapidisimo.interrapidapp.R
import com.interrapidisimo.interrapidapp.data.database.entities.LocalityEntity

class LocalityAdapter :
    ListAdapter<LocalityEntity, LocalityAdapter.LocalityVH>(DIFF) {

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<LocalityEntity>() {
            override fun areItemsTheSame(a: LocalityEntity, b: LocalityEntity) =
                a.id == b.id

            override fun areContentsTheSame(a: LocalityEntity, b: LocalityEntity) =
                a == b
        }
    }

    inner class LocalityVH(item: View) : RecyclerView.ViewHolder(item) {
        private val abbr = item.findViewById<TextView>(R.id.tv_abbreviation)
        private val full = item.findViewById<TextView>(R.id.tv_full_name)
        fun bind(le: LocalityEntity) {
            abbr.text = le.abbreviation
            full.text = le.fullName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LocalityVH(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_locality, parent, false)
        )

    override fun onBindViewHolder(holder: LocalityVH, position: Int) =
        holder.bind(getItem(position))
}
