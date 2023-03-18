package com.mirea.attsystem.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mirea.attsystem.R
import com.mirea.attsystem.databinding.FragmentInfoPersonBinding
import com.mirea.attsystem.ui.adapter.AttendancesAdapter
import com.mirea.attsystem.ui.view.AttendancesViewModel
import com.mirea.attsystem.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoPersonFragment : Fragment(R.layout.fragment_info_person) {

    private lateinit var binding: FragmentInfoPersonBinding
    private lateinit var attendancesAdapter: AttendancesAdapter
    private val viewModel by viewModels<AttendancesViewModel>()
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
        val divider = DividerItemDecoration(context, RecyclerView.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider, null)?.let {
            divider.setDrawable(it)
        }
        attendancesAdapter = AttendancesAdapter()
        binding.rvInfoPersons.apply {
            addItemDecoration(divider)
            layoutManager = LinearLayoutManager(activity)
            adapter = attendancesAdapter
        }
    }

    private fun getPersonUid(): Long = args.uid

}