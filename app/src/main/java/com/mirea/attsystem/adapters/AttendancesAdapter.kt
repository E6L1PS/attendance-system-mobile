package com.mirea.attsystem.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mirea.attsystem.databinding.FragmentStartBinding
import com.mirea.attsystem.databinding.ItemAttendanceBinding
import com.mirea.attsystem.model.Attendance

class AttendancesAdapter : RecyclerView.Adapter<AttendancesAdapter.AttendancesVH>() {

    inner class AttendancesVH(val binding: ItemAttendanceBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Attendance>() {
        override fun areItemsTheSame(oldItem: Attendance, newItem: Attendance): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Attendance, newItem: Attendance): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttendancesAdapter.AttendancesVH {
        return AttendancesVH(
            ItemAttendanceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: AttendancesAdapter.AttendancesVH, position: Int) {
        val attendance = differ.currentList[position]
        with(holder.binding) {
            tvDate.text = attendance.date
            tvPerson.text = attendance.person.name
        }
    }
}