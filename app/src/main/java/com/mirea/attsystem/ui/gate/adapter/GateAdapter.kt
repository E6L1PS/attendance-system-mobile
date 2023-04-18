package com.mirea.attsystem.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mirea.attsystem.databinding.ItemGateBinding
import com.mirea.attsystem.domain.model.Gate

interface GateActionListener {

    fun onGateDelete(name: String)

}

class GateAdapter(private val gateActionListener: GateActionListener) : RecyclerView.Adapter<GateAdapter.GateVH>() {


    inner class GateVH(val binding: ItemGateBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Gate>() {
        override fun areItemsTheSame(oldItem: Gate, newItem: Gate): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Gate, newItem: Gate): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GateVH {
        return GateVH(
            ItemGateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = differ.currentList.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: GateVH, position: Int) {
        val gate = differ.currentList[position]
        holder.binding.tvGateName.text = "Проходная: ${gate.name}"

        holder.binding.cvGate.setOnLongClickListener {
            gateActionListener.onGateDelete(gate.name)
            true
        }
    }

}