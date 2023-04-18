package com.mirea.attsystem.ui.gate.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mirea.attsystem.R
import com.mirea.attsystem.databinding.DialogLayoutBinding
import com.mirea.attsystem.databinding.FragmentGateBinding
import com.mirea.attsystem.ui.adapter.GateActionListener
import com.mirea.attsystem.ui.adapter.GateAdapter
import com.mirea.attsystem.ui.gate.view.GatesViewModel
import com.mirea.attsystem.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GateFragment : Fragment(R.layout.fragment_gate) {

    private val viewModel by viewModels<GatesViewModel>()
    private val binding by viewBinding<FragmentGateBinding>()
    private lateinit var gateAdapter: GateAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView(view)

        val swipe = binding.srlGates
        swipe.setOnRefreshListener {
            viewModel.getGates()
            swipe.isRefreshing = false
        }


        val dialogBuilder = AlertDialog.Builder(requireContext())
        val dialogBinding = DialogLayoutBinding.inflate(layoutInflater)
        dialogBuilder.setView(dialogBinding.root)

        dialogBuilder
            .setTitle("Название проходной")
            .setPositiveButton("Принять") { dialog, _ ->
                val title = dialogBinding.editText.text.toString()

                if (title.isNotEmpty() && title.length < 50) {
                    viewModel.addGate(title)
                } else {
                    Toast.makeText(requireContext(), "Введите значение", Toast.LENGTH_SHORT).show()
                }

                dialog.dismiss()
            }
            .setNegativeButton("Отменить") { dialog, _ ->
                dialog.dismiss()
            }
        dialogBuilder.setOnDismissListener {
            dialogBinding.editText.setText("")
        }

        val dialog = dialogBuilder.create()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.gates.onEach {
                when (it) {
                    is Resource.Success -> {
                        gateAdapter.differ.submitList(it.data)
                        binding.pb.visibility = View.GONE
                    }
                    is Resource.Loading -> {
                        binding.pb.visibility = View.VISIBLE
                    }
                    is Resource.Error -> {
                        binding.pb.visibility = View.GONE
                    }
                }
            }.collect()
        }

        with(binding) {

            btnAddGate.setOnClickListener {
                dialog.show()
            }
        }

    }


    private fun setupRecyclerView(view: View) {
        gateAdapter = GateAdapter(object : GateActionListener {
            override fun onGateDelete(name: String) {
                viewModel.deleteGate(name)
            }
        })

        binding.rvGates.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = gateAdapter
        }
    }
}