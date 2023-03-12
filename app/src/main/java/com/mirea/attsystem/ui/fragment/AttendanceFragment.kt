package com.mirea.attsystem.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mirea.attsystem.R
import com.mirea.attsystem.databinding.FragmentAttendanceBinding
import com.mirea.attsystem.ui.adapter.AttendancesAdapter
import com.mirea.attsystem.ui.view.AttendancesViewModel
import com.mirea.attsystem.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AttendanceFragment : Fragment(R.layout.fragment_attendance) {

    private val viewModel by viewModels<AttendancesViewModel>()
    private lateinit var binding: FragmentAttendanceBinding

    private lateinit var attendancesAdapter: AttendancesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAttendanceBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        val swipe = binding.srlAttendances
        swipe.setOnRefreshListener {
            viewModel.getAttendances()
            swipe.isRefreshing = false
        }


        viewModel.attendances.observe(viewLifecycleOwner, Observer { response ->

            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { persons ->
                        attendancesAdapter.differ.submitList(persons)
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

    private fun setupRecyclerView() {
        attendancesAdapter = AttendancesAdapter()
        binding.rvAttendances.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = attendancesAdapter
        }
    }
}