package com.mirea.attsystem.ui.person.fragment

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mirea.attsystem.R
import com.mirea.attsystem.databinding.FragmentAddPersonBinding
import com.mirea.attsystem.domain.model.Person
import com.mirea.attsystem.ui.person.view.PersonsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddPersonFragment : Fragment(R.layout.fragment_add_person), MenuProvider {

    private val viewModel by viewModels<PersonsViewModel>()
    private val binding by viewBinding<FragmentAddPersonBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.addMenuProvider(this, viewLifecycleOwner)

        val tiEtUid = binding.addPerson.tiEtUid
        val tiEtNumber = binding.addPerson.tiEtNumber

    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.add_person_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            android.R.id.home -> findNavController().navigate(R.id.action_navigation_add_person_to_navigation_person)
            R.id.apply -> {
                with(binding.addPerson) {
                    val person = Person(
                        uid = tiEtUid.text.toString().toLong(),
                        name = tiEtName.text.toString(),
                        lastName = tiEtLastName.text.toString(),
                        jobTitle = tiEtJobTitle.text.toString(),
                        number = tiEtNumber.text.toString(),
                        gender = when (rgGender.checkedRadioButtonId) {
                            R.id.rb_man -> 'M'
                            R.id.rb_woman -> 'W'
                            else -> 'M'
                        }
                    )
                    Log.d("bApplyLog", person.toString())
                    viewModel.addPerson(person)
                }

                findNavController().navigate(R.id.action_navigation_add_person_to_navigation_person)
            }
        }
        return true
    }
}