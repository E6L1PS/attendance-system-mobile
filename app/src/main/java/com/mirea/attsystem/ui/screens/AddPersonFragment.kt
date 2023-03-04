package com.mirea.attsystem.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mirea.attsystem.R
import com.mirea.attsystem.databinding.FragmentAddPersonBinding
import com.mirea.attsystem.util.MAIN_ACTIVITY

class AddPersonFragment : Fragment(R.layout.fragment_add_person) {

    private lateinit var binding: FragmentAddPersonBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAddPersonBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      /*  MAIN_ACTIVITY.supportActionBar?.title = "Добавить сотрудника"*/

    }

}