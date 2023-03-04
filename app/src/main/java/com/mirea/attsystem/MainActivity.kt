package com.mirea.attsystem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mirea.attsystem.databinding.ActivityMainBinding
import com.mirea.attsystem.repository.AttendanceRepository
import com.mirea.attsystem.repository.PersonRepository
import com.mirea.attsystem.ui.screens.AttendancesViewModel
import com.mirea.attsystem.ui.screens.PersonsViewModel
import com.mirea.attsystem.ui.screens.VMProviderFactory
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
        val personPF = VMProviderFactory(personRepository)
        val attendancePF = VMProviderFactory(attendanceRepository)
        personsVM = ViewModelProvider(this, personPF)[PersonsViewModel::class.java]
        attendancesVM = ViewModelProvider(this, attendancePF)[AttendancesViewModel::class.java]

        val navView: BottomNavigationView = binding.bottomNavigationView
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_start,
                R.id.navigation_person
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}