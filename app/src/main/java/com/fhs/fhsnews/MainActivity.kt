package com.fhs.fhsnews

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.fhs.fhsnews.databinding.ActivityMainBinding
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


        bottomNav = findViewById(R.id.bottomNavigationView)
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    navController.navigate(R.id.newsScrollerFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.clubs -> {
                    navController.navigate(R.id.clubScrollerFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.events -> {
                    navController.navigate(R.id.eventsViewFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.search -> {
                    navController.navigate(R.id.searchMenuFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.settings -> {
                    navController.navigate(R.id.settingsMenuFragment)
                    return@setOnItemSelectedListener true
                }
                else -> {
                    return@setOnItemSelectedListener true
                }
            }
        }
    }
    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.radio_system ->
                    if (checked) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    }
                R.id.radio_light ->
                    if (checked) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    }
                R.id.radio_dark ->
                    if (checked) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    }
            }
        }
    }
}