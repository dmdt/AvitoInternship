package ru.avitointernship.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.avitointernship.MainActivity
import ru.avitointernship.R
import ru.avitointernship.data.ListItem

class Adapter(private val itemRemovedCallback: MainActivity.ItemRemovedCallback) :
    ListAdapter<ListItem, Adapter.ListItemViewHolder>(ListItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        return ListItemViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_plate, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.bDelete.setOnClickListener {
            it.isEnabled = false // Preventing double-click on button
            itemRemovedCallback.removeItem(holder.adapterPosition)
        }
    }

    class ListItemViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val tvId: TextView = v.findViewById(R.id.tvItemId)
        val bDelete: Button = v.findViewById(R.id.bDelete)

        fun bind(item: ListItem) {
            bDelete.isEnabled = true
            tvId.text = item.id.toString()
        }
    }
}

class ListItemDiffCallback : DiffUtil.ItemCallback<ListItem>() {
    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
        oldItem == newItem
}
