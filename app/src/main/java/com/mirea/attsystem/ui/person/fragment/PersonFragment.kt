package com.mirea.attsystem.ui.person.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mirea.attsystem.R
import com.mirea.attsystem.ui.person.adapter.PersonActionListener
import com.mirea.attsystem.ui.person.adapter.PersonsAdapter
import com.mirea.attsystem.databinding.FragmentPersonBinding
import com.mirea.attsystem.domain.model.Person
import com.mirea.attsystem.ui.person.view.PersonsViewModel
import com.mirea.attsystem.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PersonFragment : Fragment(R.layout.fragment_person) {

    private lateinit var personAdapter: PersonsAdapter
    private val viewModel by viewModels<PersonsViewModel>()
    private val binding by viewBinding<FragmentPersonBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bAdd.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_navigation_person_to_navigation_add_person)
        }

        setupRecyclerView(view)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.persons.onEach {
                when (it) {
                    is Resource.Success -> {
                        personAdapter.differ.submitList(it.data)
                        hideProgressBar()
                    }

                    is Resource.Loading -> {
                        showProgressBar()
                    }

                    is Resource.Error -> {
                        hideProgressBar()
                    }
                }
            }.collect()
        }

        val swipe = binding.srlPersons
        swipe.setOnRefreshListener {
            viewModel.getPersons()
            swipe.isRefreshing = false
        }

    }



    private fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView(view: View) {
        personAdapter = PersonsAdapter(object : PersonActionListener {

            override fun onPersonDelete(uid: Long) {
                viewModel.deletePerson(uid)
            }

            override fun onPersonUpdate(uid: Long) {
                val direction =
                    PersonFragmentDirections.actionNavigationPersonToEditPersonFragment(uid)

                Navigation.findNavController(view)
                    .navigate(direction)
            }

            override fun onPersonInfo(person: Person) {
               // Navigation.findNavController(view).navigate(R.id.action_navigation_person_to_tabPersonFragment)

                val direction =
                    PersonFragmentDirections.actionNavigationPersonToTabPersonFragment(person)
                Navigation.findNavController(view)
                    .navigate(direction)
            }


        })
        binding.rvPersons.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = personAdapter
        }
    }

}