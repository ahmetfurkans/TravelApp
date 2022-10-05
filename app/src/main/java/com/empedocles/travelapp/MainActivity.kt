package com.empedocles.travelapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.empedocles.travelapp.presentation.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        observeLiveData()
    }

    fun observeLiveData() {
        viewModel.allTravelList.observe(this) {

            this.viewModel.allTravelList.value?.let { it ->
                it.forEach(){
                    println(it.category)
                }
            }
        }
    }
}