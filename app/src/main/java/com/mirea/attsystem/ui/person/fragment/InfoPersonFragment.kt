package com.mirea.attsystem.ui.person.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mirea.attsystem.R
import com.mirea.attsystem.databinding.FragmentInfoPersonBinding
import com.mirea.attsystem.ui.attendance.adapter.AttendancesAdapter
import com.mirea.attsystem.ui.attendance.view.AttendancesViewModel
import com.mirea.attsystem.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InfoPersonFragment : Fragment(R.layout.fragment_info_person) {

    private lateinit var attendancesAdapter: AttendancesAdapter
    private val viewModel by viewModels<AttendancesViewModel>()
    private val binding by viewBinding<FragmentInfoPersonBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        viewModel.getAttendancesByUid(getPersonUid())
        val swipe = binding.srlAttendances

        swipe.setOnRefreshListener {
            viewModel.getAttendancesByUid(getPersonUid())
            swipe.isRefreshing = false
        }


        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.attendancesByUid.onEach {
                when (it) {
                    is Resource.Success -> {
                        attendancesAdapter.differ.submitList(it.data)
                    }

                    is Resource.Loading -> {

                        Log.d("dadasda", "LOAD")
                    }

                    is Resource.Error -> {

                        Log.d("dadasda", it.message)
                    }
                }
            }.collect()
        }


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

    private fun getPersonUid(): Long = this.arguments?.getLong("arg") ?: throw IllegalAccessException("")

    companion object {
        fun newInstance(arg: Long): InfoPersonFragment {
            val args = Bundle()
            args.putLong("arg", arg)
            val fragment = InfoPersonFragment()
            fragment.arguments = args
            return fragment
        }
    }

}