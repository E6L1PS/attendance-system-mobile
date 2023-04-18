package com.mirea.attsystem.ui.person.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mirea.attsystem.ui.person.fragment.InfoPersonFragment
import com.mirea.attsystem.ui.person.fragment.InfoTimePersonFragment
import com.mirea.attsystem.ui.person.fragment.TabPersonFragmentArgs

class FragmentPageAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val args: TabPersonFragmentArgs
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> InfoPersonFragment.newInstance(args.person.uid)
        1 -> InfoTimePersonFragment.newInstance(args.person.uid)
        else -> throw IllegalArgumentException("TODO")
    }


}