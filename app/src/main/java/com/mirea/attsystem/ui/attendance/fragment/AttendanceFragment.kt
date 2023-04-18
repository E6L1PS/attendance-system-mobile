package com.mirea.attsystem.ui.attendance.fragment

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.MenuItem.OnMenuItemClickListener
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mirea.attsystem.R
import com.mirea.attsystem.databinding.FragmentAttendanceBinding
import com.mirea.attsystem.ui.attendance.adapter.AttendancesAdapter
import com.mirea.attsystem.ui.attendance.view.AttendancesViewModel
import com.mirea.attsystem.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AttendanceFragment : Fragment(R.layout.fragment_attendance), MenuProvider {

    private lateinit var attendancesAdapter: AttendancesAdapter
    private val viewModel by viewModels<AttendancesViewModel>()
    private val binding by viewBinding<FragmentAttendanceBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().addMenuProvider(this, viewLifecycleOwner)

        setupRecyclerView()

        val swipe = binding.srlAttendances
        swipe.setOnRefreshListener {
            viewModel.getAttendances()
            swipe.isRefreshing = false
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.attendances.onEach {
                when (it) {
                    is Resource.Success -> {
                        val data = it.data
                        attendancesAdapter.differ.submitList(data)
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
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        val divider = DividerItemDecoration(context, RecyclerView.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider, null)?.let {
            divider.setDrawable(it)
        }
        attendancesAdapter = AttendancesAdapter()
        binding.rvAttendances.apply {
            addItemDecoration(divider)
            layoutManager = LinearLayoutManager(activity)
            adapter = attendancesAdapter
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.attendance_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.ic_clear -> {
               viewModel.deleteAll()
            }
            else -> {

            }
        }
        return true
    }
}