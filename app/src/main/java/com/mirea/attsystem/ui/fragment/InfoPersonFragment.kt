package com.mirea.attsystem.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.mirea.attsystem.R
import com.mirea.attsystem.databinding.FragmentInfoPersonBinding
import com.mirea.attsystem.ui.adapter.AttendancesAdapter
import com.mirea.attsystem.ui.view.AttendancesViewModel
import com.mirea.attsystem.util.MAIN_ACTIVITY
import com.mirea.attsystem.util.Resource

class InfoPersonFragment : Fragment(R.layout.fragment_info_person) {

    private lateinit var binding: FragmentInfoPersonBinding
    private lateinit var attendancesAdapter: AttendancesAdapter
    private lateinit var viewModel: AttendancesViewModel
    private val args by navArgs<InfoPersonFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoPersonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = MAIN_ACTIVITY.attendancesVM

        setupRecyclerView()
        viewModel.getAttendancesByUid(getPersonUid())
        val swipe = binding.srlAttendances

        swipe.setOnRefreshListener {
            viewModel.getAttendancesByUid(getPersonUid())
            swipe.isRefreshing = false
        }


        viewModel.attendancesByUid.observe(viewLifecycleOwner, Observer { response ->

            when (response) {
                is Resource.Success -> {
                    //hideProgressBar()
                    response.data?.let { persons ->
                        attendancesAdapter.differ.submitList(persons)
                    }
                }
                is Resource.Error -> {
                    // hideProgressBar()
                    response.message?.let { message ->
                        Log.e("PERSON_MESSAGE", message)
                    }
                }
                is Resource.Loading -> {
                    // showProgressBar()
                }
            }
        })


    }

    private fun setupRecyclerView() {
        attendancesAdapter = AttendancesAdapter()
        binding.rvInfoPersons.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = attendancesAdapter
        }
    }

    private fun getPersonUid(): Long = args.uid

}