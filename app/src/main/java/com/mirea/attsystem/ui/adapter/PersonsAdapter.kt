package com.mirea.attsystem.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mirea.attsystem.R
import com.mirea.attsystem.databinding.ItemPersonBinding
import com.mirea.attsystem.model.Person
import com.mirea.attsystem.util.MAIN_ACTIVITY


interface PersonActionListener {
    fun onPersonDelete(uid: Long)

    fun onPersonUpdate(uid: Long)

    fun onPersonInfo(person: Person)

}

class PersonsAdapter(private val personActionListener: PersonActionListener) :
    RecyclerView.Adapter<PersonsAdapter.PersonsVH>(),
    View.OnClickListener {

    inner class PersonsVH(val binding: ItemPersonBinding) : RecyclerView.ViewHolder(binding.root) {

    }

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
        val binding = ItemPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        binding.root.setOnClickListener(this)
        binding.icMore.setOnClickListener(this)

        return PersonsVH(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: PersonsVH, position: Int) {
        val person = differ.currentList[position]
        with(holder.binding) {
            holder.itemView.tag = person
            icMore.tag = person
            tvUid.text = person.uid.toString()
            tvName.text = person.name
            tvLastName.text = person.lastName
            tvJobTitle.text = person.jobTitle
            tvGender.text = person.gender.toString()
        }
    }

    override fun onClick(v: View) {
        val person = v.tag as Person

        when (v.id) {
            R.id.ic_more -> {
                Log.d("showPopupMenu", person.uid.toString())
                showPopupMenu(v)

            }
            else -> {
                Log.d("onPersonInfo", person.toString())
                personActionListener.onPersonInfo(person)

            }
        }
    }

    private fun showPopupMenu(view: View) {

        val person = view.tag as Person
        val popupMenu = PopupMenu(view.context, view)
        popupMenu.inflate(R.menu.pop_up_menu)
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.navigation_edit_person -> personActionListener.onPersonUpdate(person.uid)
                R.id.navigation_delete -> personActionListener.onPersonDelete(person.uid)
            }
            true
        }
        popupMenu.show()
    }



}