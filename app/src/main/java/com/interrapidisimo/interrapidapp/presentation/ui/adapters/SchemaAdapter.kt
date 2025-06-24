package com.interrapidisimo.interrapidapp.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.interrapidisimo.interrapidapp.R
import com.interrapidisimo.interrapidapp.data.database.entities.SchemaEntity

class SchemaAdapter : ListAdapter<SchemaEntity, SchemaAdapter.SchemaVH>(DIFF) {

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<SchemaEntity>() {
            override fun areItemsTheSame(a: SchemaEntity, b: SchemaEntity) = a.id == b.id
            override fun areContentsTheSame(a: SchemaEntity, b: SchemaEntity) = a == b
        }
    }

    inner class SchemaVH(item: View) : RecyclerView.ViewHolder(item) {
        private val name  = item.findViewById<TextView>(R.id.tv_table_name)
        private val count = item.findViewById<TextView>(R.id.tv_fields_count)
        fun bind(se: SchemaEntity) {
            name.text  = se.tableName
            count.text = "Campos: ${se.numberOfFields}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SchemaVH(
            LayoutInflater.from(parent.context)
            .inflate(R.layout.item_schema, parent, false))

    override fun onBindViewHolder(holder: SchemaVH, position: Int) =
        holder.bind(getItem(position))
}
