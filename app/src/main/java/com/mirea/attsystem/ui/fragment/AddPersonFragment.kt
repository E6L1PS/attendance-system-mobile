package com.mirea.attsystem.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mirea.attsystem.R
import com.mirea.attsystem.databinding.FragmentAddPersonBinding
import com.mirea.attsystem.domain.model.Person
import com.mirea.attsystem.ui.view.PersonsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddPersonFragment : Fragment(R.layout.fragment_add_person), MenuProvider {

    private lateinit var binding: FragmentAddPersonBinding
    private val viewModel by viewModels<PersonsViewModel>()

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
            android.R.id.home -> findNavController().navigate(R.id.action_navigation_add_person_to_navigation_person)
            R.id.apply -> {
                with(binding.addPerson) {
                    val person = Person(
                        tiEtUid.text.toString().toLong(),
                        tiEtName.text.toString(),
                        tiEtLastName.text.toString(),
                        tiEtJobTitle.text.toString(),
                        when (rgGender.checkedRadioButtonId) {
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