package com.empedocles.travelapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.empedocles.travelapp.presentation.detail.DetailViewModel
import com.empedocles.travelapp.presentation.guide.GuideViewModel
import com.empedocles.travelapp.presentation.home.HomeViewModel
import com.empedocles.travelapp.presentation.search.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        observeLiveData()
    }

    fun observeLiveData() {
        viewModel.pageState.observe(this) {
            this.viewModel.pageState.value?.let { state ->
                if (state.isLoading){
                    println("loading")
                }
                if (state.isError){
                    println("there is a error")
                }
                if (state.selectedItem != null){
                    println("not null")
                    println(state.selectedItem?.description)
                }else{
                    println("null")

                }
            }
        }
    }
}