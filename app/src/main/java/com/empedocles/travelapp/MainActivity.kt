package com.empedocles.travelapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.empedocles.travelapp.databinding.ActivityMainBinding
import com.empedocles.travelapp.presentation.detail.DetailViewModel
import com.empedocles.travelapp.presentation.guide.GuideViewModel
import com.empedocles.travelapp.presentation.home.HomeViewModel
import com.empedocles.travelapp.presentation.search.SearchViewModel
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