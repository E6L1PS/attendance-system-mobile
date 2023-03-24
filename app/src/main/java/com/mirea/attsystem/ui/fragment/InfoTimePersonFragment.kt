package com.mirea.attsystem.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mirea.attsystem.R
import com.mirea.attsystem.databinding.FragmentInfoTimePersonBinding
import com.mirea.attsystem.databinding.FragmentOuterRvBinding
import com.mirea.attsystem.ui.adapter.infotime.InfoTimePersonAdapter
import com.mirea.attsystem.ui.adapter.infotime.OuterRecyclerViewAdapter
import com.mirea.attsystem.ui.view.AttendancesViewModel
import com.mirea.attsystem.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoTimePersonFragment : Fragment(R.layout.fragment_outer_rv) {

    private lateinit var binding: FragmentOuterRvBinding
    private lateinit var outerRecyclerViewAdapter: OuterRecyclerViewAdapter
    private val viewModel by viewModels<AttendancesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOuterRvBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel.getAllDatesByUid(this.requireArguments().getLong("arg"))

        viewModel.dates.observe(viewLifecycleOwner, Observer { response ->

            when (response) {
                is Resource.Success -> {
                    //hideProgressBar()
                    response.data?.let { times ->
                        outerRecyclerViewAdapter.differ.submitList(times)
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