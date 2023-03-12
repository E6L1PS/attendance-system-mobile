package com.mirea.attsystem.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mirea.attsystem.databinding.ItemAttendanceBinding
import com.mirea.attsystem.domain.model.Attendance

class InfoPersonsAdapter : RecyclerView.Adapter<InfoPersonsAdapter.InfoPersonsVH>() {

    inner class InfoPersonsVH(val binding: ItemAttendanceBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Attendance>() {
        override fun areItemsTheSame(oldItem: Attendance, newItem: Attendance): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Attendance, newItem: Attendance): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoPersonsVH {
        return InfoPersonsVH(
            ItemAttendanceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: InfoPersonsVH, position: Int) {
        TODO("Not yet implemented")
    }
}