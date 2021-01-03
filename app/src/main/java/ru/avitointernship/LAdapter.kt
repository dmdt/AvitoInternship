package ru.avitointernship

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class LAdapter: ListAdapter<ListItem, LViewHolder>(ListItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LViewHolder {
        return LViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_plate, parent, false)
        )
    }

    override fun onBindViewHolder(holder: LViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ListItemDiffCallback: DiffUtil.ItemCallback<ListItem>() {
    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
        oldItem == newItem
}

class LViewHolder(v: View): RecyclerView.ViewHolder(v) {
    private val tvId: TextView = v.findViewById(R.id.tvItemId)
    private val bDelete: Button = v.findViewById(R.id.bDelete)

    fun bind(item: ListItem) {
        tvId.text = item.id.toString()
    }
}
