package com.mirea.attsystem.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mirea.attsystem.R
import com.mirea.attsystem.databinding.FragmentEditPersonBinding
import com.mirea.attsystem.domain.model.Person
import com.mirea.attsystem.ui.view.PersonsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditPersonFragment : Fragment(R.layout.fragment_edit_person), MenuProvider {

    private val viewModel by viewModels<PersonsViewModel>()
    private val binding by viewBinding<FragmentEditPersonBinding>()
    private val args by navArgs<EditPersonFragmentArgs>()

  /*  override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        with(binding.tiEtUid) {
            setText(getPersonUid().toString())
            isEnabled = false
            setTextColor(Color.GRAY)
        }

        return binding.root
    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.addMenuProvider(this, viewLifecycleOwner)

    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.edit_person_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            android.R.id.home -> findNavController().navigate(R.id.action_navigation_edit_person_to_navigation_person)
            R.id.apply -> {
                with(binding) {
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
                    viewModel.updatePerson(person)
                }
                findNavController().navigate(R.id.action_navigation_edit_person_to_navigation_person)
            }
        }
        return true
    }

    private fun getPersonUid(): Long = args.uid


}