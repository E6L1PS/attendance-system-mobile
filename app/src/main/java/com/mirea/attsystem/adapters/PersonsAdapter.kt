package com.mirea.attsystem.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mirea.attsystem.databinding.ItemPersonBinding
import com.mirea.attsystem.model.Person

class PersonsAdapter : RecyclerView.Adapter<PersonsAdapter.PersonsVH>() {

    inner class PersonsVH(val binding: ItemPersonBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Person>() {
        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem.uid == newItem.uid
        }

        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonsVH {
        return PersonsVH(
            ItemPersonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: PersonsVH, position: Int) {
        val person = differ.currentList[position]
        with(holder.binding) {
            tvUid.text = person.uid.toString()
            tvName.text = person.name
            tvGender.text = person.gender.toString()

        }
        holder.itemView.setOnClickListener {

        }
    }

}