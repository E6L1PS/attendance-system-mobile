package com.mirea.attsystem.ui.person.adapter.infotime

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mirea.attsystem.databinding.ItemTimeAttendanceBinding

class InfoTimePersonAdapter : RecyclerView.Adapter<InfoTimePersonAdapter.InfoTimePersonVH>() {

    inner class InfoTimePersonVH(var binding: ItemTimeAttendanceBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoTimePersonVH =
        InfoTimePersonVH(
            ItemTimeAttendanceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: InfoTimePersonVH, position: Int) {
        val duration = differ.currentList[position]
        with(holder.binding) {
            tvTime.text = "${position + 1}-е посещение: $duration"
        }
    }
}