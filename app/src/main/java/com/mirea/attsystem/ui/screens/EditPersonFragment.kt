package com.mirea.attsystem.ui.screens

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import com.mirea.attsystem.R
import com.mirea.attsystem.databinding.FragmentEditPersonBinding
import com.mirea.attsystem.model.Person
import com.mirea.attsystem.util.MAIN_ACTIVITY

class EditPersonFragment : Fragment(R.layout.fragment_edit_person), MenuProvider {

    private lateinit var binding: FragmentEditPersonBinding
    private lateinit var viewModel: PersonsViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEditPersonBinding.inflate(inflater, container, false)
        viewModel = MAIN_ACTIVITY.personsVM

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.add_person_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            android.R.id.home -> MAIN_ACTIVITY.navController.navigate(R.id.action_navigation_edit_person_to_navigation_person)
            R.id.apply -> {
                val person = Person(
                    binding.tiEtUid.text.toString().toLong(),
                    binding.tiEtName.text.toString(),
                    binding.tiEtLastName.text.toString(),
                    binding.tiEtJobTitle.text.toString(),
                    'M'
                )
                Log.d("bApplyLog", person.toString())
                viewModel.updatePerson(person)
                MAIN_ACTIVITY.navController.navigate(R.id.action_navigation_edit_person_to_navigation_person)
            }
        }
        return true
    }
}