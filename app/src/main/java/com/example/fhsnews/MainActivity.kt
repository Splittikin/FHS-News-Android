package com.example.fhsnews

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.fhsnews.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

private lateinit var navController: NavController
private lateinit var bottomNav: BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.navController


        bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    Log.d(TAG, "onCreate: bottom nav home clicked")
                    navController.navigate(R.id.newsScrollerFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.clubs -> {
                    Log.d(TAG, "onCreate: bottom nav club clicked")
                    navController.navigate(R.id.clubScrollerFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.events -> {
                    navController.navigate(R.id.eventsViewFragment)
                    return@setOnItemSelectedListener true
                }
                else -> {
                    return@setOnItemSelectedListener true
                }
            }
        }
    }
}