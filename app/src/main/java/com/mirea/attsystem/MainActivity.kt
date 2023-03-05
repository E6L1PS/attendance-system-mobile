package com.mirea.attsystem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mirea.attsystem.databinding.ActivityMainBinding
import com.mirea.attsystem.repository.AttendanceRepository
import com.mirea.attsystem.repository.PersonRepository
import com.mirea.attsystem.ui.screens.*
import com.mirea.attsystem.util.MAIN_ACTIVITY

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var personsVM: PersonsViewModel
    lateinit var attendancesVM: AttendancesViewModel

    lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MAIN_ACTIVITY = this
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val personRepository = PersonRepository()
        val attendanceRepository = AttendanceRepository()
        val personVMProviderFactory = VMProviderFactory(personRepository)
        val attendanceVMProviderFactory = VMProviderFactory(attendanceRepository)

        personsVM = ViewModelProvider(this, personVMProviderFactory)[PersonsViewModel::class.java]
        attendancesVM = ViewModelProvider(this, attendanceVMProviderFactory)[AttendancesViewModel::class.java]
/*
        replaceFragment(StartFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_start -> replaceFragment(StartFragment())
                R.id.navigation_person -> replaceFragment(PersonFragment())
                else -> {

                }
            }
            true
        }*/

        val navView: BottomNavigationView = binding.bottomNavigationView
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_fragment) as NavHostFragment
        navController = navHostFragment.navController
        //navController = Navigation.findNavController(this, R.id.nav_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_start,
                R.id.navigation_person
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    fun replaceFragment(fragment: Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nav_fragment, fragment)
        fragmentTransaction.commit()
    }


}