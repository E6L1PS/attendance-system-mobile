package com.mirea.attsystem.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.mirea.attsystem.R
import com.mirea.attsystem.ui.adapter.PersonActionListener
import com.mirea.attsystem.ui.adapter.PersonsAdapter
import com.mirea.attsystem.databinding.FragmentPersonBinding
import com.mirea.attsystem.ui.view.PersonsViewModel
import com.mirea.attsystem.util.MAIN_ACTIVITY
import com.mirea.attsystem.util.Resource

class PersonFragment : Fragment(R.layout.fragment_person) {

    private lateinit var binding: FragmentPersonBinding
    private lateinit var viewModel: PersonsViewModel
    private lateinit var personAdapter: PersonsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPersonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bAdd.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_navigation_person_to_navigation_add_person)
        }


        viewModel = MAIN_ACTIVITY.personsVM
        setupRecyclerView(view)

        call()

        val swipe = binding.srlPersons
        swipe.setOnRefreshListener {
            viewModel.getPersons()
            swipe.isRefreshing = false
        }

        /* binding.button2.setOnClickListener {
             MAIN_ACTIVITY.replaceFragment(AddPersonFragment())
            // MAIN_ACTIVITY.navController.navigate(R.id.action_navigation_person_to_navigation_add_person)
         }*/
    }

    fun call() {
        viewModel.persons.observe(viewLifecycleOwner, Observer { response ->

            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { persons ->
                        personAdapter.differ.submitList(persons)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e("PERSON_MESSAGE", message)
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
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

            override fun onPersonUpdate() {
                Navigation.findNavController(view)
                    .navigate(R.id.navigation_edit_person)
            }

        })
        binding.rvPersons.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = personAdapter
        }
    }

}