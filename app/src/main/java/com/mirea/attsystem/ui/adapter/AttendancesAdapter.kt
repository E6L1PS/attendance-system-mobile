package com.mirea.attsystem.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mirea.attsystem.databinding.ItemAttendanceBinding
import com.mirea.attsystem.domain.model.Attendance


class AttendancesAdapter : RecyclerView.Adapter<AttendancesAdapter.AttendancesVH>(), View.OnClickListener {

    inner class AttendancesVH(val binding: ItemAttendanceBinding) :
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttendancesVH {
        return AttendancesVH(
            ItemAttendanceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = differ.currentList.size


    override fun onBindViewHolder(holder: AttendancesVH, position: Int) {
        val attendance = differ.currentList[position]
        with(holder.binding) {
            tvDate.text = attendance.date
            tvLastName.text = attendance.person.lastName
            tvName.text = attendance.person.name
        }

        if (attendance.status) {
            holder.itemView.setBackgroundColor(Color.parseColor("#E9FF9B"))
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFB2B2"))
        }


    }




    override fun onClick(p0: View?) {

    }
}