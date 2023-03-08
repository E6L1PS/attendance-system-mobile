package com.mirea.attsystem.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import com.mirea.attsystem.R
import com.mirea.attsystem.databinding.FragmentAddPersonBinding
import com.mirea.attsystem.model.Person
import com.mirea.attsystem.ui.view.PersonsViewModel
import com.mirea.attsystem.util.MAIN_ACTIVITY

class AddPersonFragment : Fragment(R.layout.fragment_add_person), MenuProvider {

    private lateinit var binding: FragmentAddPersonBinding
    private lateinit var viewModel: PersonsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddPersonBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.addMenuProvider(this, viewLifecycleOwner)
        viewModel = MAIN_ACTIVITY.personsVM

        //val tiEtUid = binding.tiEtUid

        /*tiEtUid.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("Not yet implemented")
            }

            override fun afterTextChanged(p0: Editable?) {
                TODO("Not yet implemented")
            }

        })*/

    }


    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.add_person_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            android.R.id.home -> MAIN_ACTIVITY.navController.navigate(R.id.action_navigation_add_person_to_navigation_person)
            R.id.apply -> {
                with(binding.addPerson) {
                    val person = Person(
                        tiEtUid.text.toString().toLong(),
                        tiEtName.text.toString(),
                        tiEtLastName.text.toString(),
                        tiEtJobTitle.text.toString(),
                        'M'
                    )
                    Log.d("bApplyLog", person.toString())
                    viewModel.addPerson(person)
                }

                MAIN_ACTIVITY.navController.navigate(R.id.action_navigation_add_person_to_navigation_person)
            }
        }
        return true
    }

}