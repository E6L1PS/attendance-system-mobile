package com.mirea.attsystem.ui.adapter.infotime

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mirea.attsystem.data.dto.DateDTO
import com.mirea.attsystem.databinding.ItemOuterBinding

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

    override fun onBindViewHolder(holder: OuterPersonVH, position: Int) {
        with(holder.binding) {
            tvDate.text = differ.currentList[position].date
            tvCount.text = differ.currentList[position].date

            // Set up inner recycler view
            val innerAdapter = InfoTimePersonAdapter()
            innerAdapter.differ.submitList(differ.currentList[position].list)
            rvInner.apply {
                adapter = innerAdapter
                layoutManager = LinearLayoutManager(context)
            }

            btnDate.setOnClickListener {
                if (rvInner.visibility == View.VISIBLE) {
                    rvInner.visibility = View.GONE
                } else {
                    rvInner.visibility = View.VISIBLE
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