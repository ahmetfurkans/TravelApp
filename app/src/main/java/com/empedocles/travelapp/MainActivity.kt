package com.empedocles.travelapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
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
    private val viewModel by viewModels<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.apply {
            val navHostFragment  = supportFragmentManager
                .findFragmentById(fragmentContainerView.id) as NavHostFragment
            NavigationUI.setupWithNavController(binding.bottomNav ,navHostFragment.navController)
        }
    }
}