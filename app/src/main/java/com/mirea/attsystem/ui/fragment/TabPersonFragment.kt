package com.mirea.attsystem.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.tabs.TabLayout
import com.mirea.attsystem.R
import com.mirea.attsystem.databinding.FragmentTabLayoutBinding
import com.mirea.attsystem.ui.adapter.FragmentPageAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TabPersonFragment : Fragment(R.layout.fragment_tab_layout) {
    private lateinit var binding: FragmentTabLayoutBinding
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var adapter: FragmentPageAdapter
    private val args by navArgs<TabPersonFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTabLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            tabLayout = tlPerson
            viewPager2 = vpPerson
            tvName.text = args.person.name
            tvLastName.text = args.person.lastName
        }

        adapter =
            activity?.let { FragmentPageAdapter(it.supportFragmentManager, lifecycle, args) }!!

        tabLayout.addTab(tabLayout.newTab().setText("Посещения"))
        tabLayout.addTab(tabLayout.newTab().setText("Проработанное время"))
        viewPager2.adapter = adapter

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    viewPager2.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        viewPager2.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })
    }
}