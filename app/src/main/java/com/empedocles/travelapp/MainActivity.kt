package com.empedocles.travelapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.empedocles.travelapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            val navHostFragment = supportFragmentManager
                .findFragmentById(fragmentContainerView.id) as NavHostFragment
            navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
                if (destination.id == R.id.detailFragment || destination.id == R.id.searchResultFragment) {
                    binding.bottomNav.visibility = View.GONE
                } else {
                    binding.bottomNav.visibility = View.VISIBLE
                }
            }
            NavigationUI.setupWithNavController(binding.bottomNav, navHostFragment.navController)
        }
    }
}