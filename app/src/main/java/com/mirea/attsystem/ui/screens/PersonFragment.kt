package com.mirea.attsystem.ui.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mirea.attsystem.MainActivity
import com.mirea.attsystem.R
import com.mirea.attsystem.adapters.PersonsAdapter
import com.mirea.attsystem.databinding.FragmentPersonBinding
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
        val view = binding.root
        viewModel = MAIN_ACTIVITY.personsVM
        setupRecyclerView()

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


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)/*
        MAIN_ACTIVITY.supportActionBar?.title = "Сотрудники"*/
        binding.button2.setOnClickListener {
            MAIN_ACTIVITY.navController.navigate(R.id.action_navigation_person_to_navigation_add_person)
        }
    }

    private fun hideProgressBar() {
        binding.progressBar2.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.progressBar2.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        personAdapter = PersonsAdapter()
        binding.rvPersons.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = personAdapter
        }
    }

}