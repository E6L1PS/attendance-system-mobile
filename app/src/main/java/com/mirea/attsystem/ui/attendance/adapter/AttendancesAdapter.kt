package com.mirea.attsystem.ui.attendance.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mirea.attsystem.databinding.ItemAttendanceBinding
import com.mirea.attsystem.domain.model.Attendance
import java.text.SimpleDateFormat
import java.util.*


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


    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: AttendancesVH, position: Int) {
        val attendance = differ.currentList[position]
        with(holder.binding) {

            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd@HH:mm:ss").parse(attendance.date)

            tvDate.text = SimpleDateFormat("yyyy-MM-dd").format(simpleDateFormat)
            tvTime.text = SimpleDateFormat("HH:mm:ss").format(simpleDateFormat)
            tvLastName.text = attendance.person.lastName
            tvName.text = attendance.person.name
        }

        if (attendance.status) {
            holder.itemView.setBackgroundColor(Color.parseColor("#E9FF9B"))
            holder.binding.tvGate.text = "Проходная: ${attendance.gate.name}(вход)"
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFB2B2"))
            holder.binding.tvGate.text = "Проходная: ${attendance.gate.name}(выход)"
        }


    }




    override fun onClick(p0: View?) {

    }
}