package com.mirea.attsystem.ui.fragment

import android.os.Bundle
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
import com.mirea.attsystem.databinding.FragmentOuterRvBinding
import com.mirea.attsystem.ui.adapter.infotime.OuterRecyclerViewAdapter
import com.mirea.attsystem.ui.view.AttendancesViewModel
import com.mirea.attsystem.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InfoTimePersonFragment : Fragment(R.layout.fragment_outer_rv) {

    private lateinit var outerRecyclerViewAdapter: OuterRecyclerViewAdapter
    private val viewModel by viewModels<AttendancesViewModel>()
    private val binding by viewBinding<FragmentOuterRvBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel.getAllDatesByUid(this.requireArguments().getLong("arg"))

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.dates.onEach {
                when (it) {
                    is Resource.Success -> {
                        outerRecyclerViewAdapter.differ.submitList(it.data)
                    }

                    is Resource.Loading -> {

                    }

                    is Resource.Error -> {

                    }
                }
            }.collect()
        }
    }

    private fun setupRecyclerView() {
        val divider = DividerItemDecoration(context, RecyclerView.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider_att_date, null)?.let {
            divider.setDrawable(it)
        }
        outerRecyclerViewAdapter = OuterRecyclerViewAdapter()
        binding.rvOuter.apply {
            addItemDecoration(divider)
            layoutManager = LinearLayoutManager(activity)
            adapter = outerRecyclerViewAdapter
        }
    }

    companion object {
        fun newInstance(arg: Long): InfoTimePersonFragment {
            val args = Bundle()
            args.putLong("arg", arg)
            val fragment = InfoTimePersonFragment()
            fragment.arguments = args
            return fragment
        }
    }


}