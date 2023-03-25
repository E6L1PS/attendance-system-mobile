package com.mirea.attsystem.ui.adapter.infotime

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.*
import com.mirea.attsystem.R
import com.mirea.attsystem.data.dto.DateDTO
import com.mirea.attsystem.databinding.ItemOuterBinding
import java.time.LocalTime

class OuterRecyclerViewAdapter : RecyclerView.Adapter<OuterRecyclerViewAdapter.OuterPersonVH>() {

    inner class OuterPersonVH(var binding: ItemOuterBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<DateDTO>() {
        override fun areItemsTheSame(oldItem: DateDTO, newItem: DateDTO): Boolean {
            return oldItem.date == newItem.date
        }

        override fun areContentsTheSame(oldItem: DateDTO, newItem: DateDTO): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OuterPersonVH =
        OuterPersonVH(
            ItemOuterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: OuterPersonVH, position: Int) {
        val dateDTO = differ.currentList[position]
        val sum = if (dateDTO.dateTimeList.isNotEmpty()) {
            dateDTO.dateTimeList
                .map { LocalTime.parse(it) }
                .reduce { acc, i ->
                    acc.plusHours(i.hour.toLong())
                        .plusMinutes(i.minute.toLong())
                        .plusSeconds(i.second.toLong())
                }.toString()
        } else {
            "00:00:00"
        }
        with(holder.binding) {
            tvDate.text = dateDTO.date
            tvCountTime.text = sum
            tvCountAtt.text = dateDTO.dateTimeList.size.toString()


            // Set up inner recycler view
            val innerAdapter = InfoTimePersonAdapter()
            innerAdapter.differ.submitList(dateDTO.dateTimeList)
            rvInner.apply {
                adapter = innerAdapter
                layoutManager = LinearLayoutManager(context)
            }

            clDate.setOnClickListener {
                if (rvInner.visibility == View.VISIBLE) {
                    val color = ContextCompat.getColor(clDate.context, R.color.main_medium)
                    Log.d("btnDateV", "GONE")
                    rvInner.visibility = View.GONE
                    ivMore.setImageResource(R.drawable._26716_less_unfold_icon)

                    tvCountAtt.setTextColor(color)
                    ivMore.setColorFilter(color)

                    clMoreLess.setBackgroundResource(R.drawable.rounded_border_less)
                    clDate.setBackgroundColor(
                        ContextCompat.getColor(
                            clDate.context,
                            R.color.main_medium
                        )
                    )
                } else {
                    val mainUp = ContextCompat.getColor(clDate.context, R.color.main_up)
                    val black = ContextCompat.getColor(clDate.context, R.color.main_black)
                    Log.d("btnDateV", "VISIBLE")
                    rvInner.visibility = View.VISIBLE
                    ivMore.setImageResource(R.drawable._26717_more_unfold_icon)
/*
                    tvDate.setTextColor(color)
                    tvCountTime.setTextColor(color)*/
                    tvCountAtt.setTextColor(black)
                    ivMore.setColorFilter(black)
/*
                    tvDate.setBackgroundResource(R.drawable.rounded_border_more)
                    tvCountTime.setBackgroundResource(R.drawable.rounded_border_more)*/
                    clMoreLess.setBackgroundResource(R.drawable.rounded_border_more)

                    clDate.setBackgroundColor(mainUp)
                }
            }
        }


    }

    override fun getItemCount(): Int = differ.currentList.size

    /*  private fun setupInnerRV(binding: ItemOuterBinding) {
         // val divider = DividerItemDecoration(context, RecyclerView.VERTICAL)
          val innerAdapter = InfoTimePersonAdapter()
          innerAdapter.differ.submitList(differ.currentList[position].list)
          binding.rvInner.apply {
              addItemDecoration(divider)
              layoutManager = LinearLayoutManager(activity)
              adapter = innerAdapter
      }*/
}