package com.empedocles.travelapp.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.empedocles.travelapp.R
import com.empedocles.travelapp.databinding.FragmentHomeBinding
import com.empedocles.travelapp.domain.model.TravelModel
import com.empedocles.travelapp.presentation.detail.DetailViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private var buttonAdapter = HomeButtonRecyclerAdapter()
    private val viewModel by viewModels<HomeViewModel>()
    private val emptyList = ArrayList<List<TravelModel>>()
    private val adapter = ViewPagerAdapter(emptyList)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        observeLiveData()
    }

    private fun setupUi() {
        createButtonRecyclerView()
        createViewPager()
    }

    private fun createButtonRecyclerView() {
        binding.fragmentHomeButtonRecyclerView.layoutManager = object :
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false) {
            @Override
            override fun canScrollHorizontally(): Boolean {
                return false
            }
        }
        binding.fragmentHomeButtonRecyclerView.adapter = buttonAdapter
    }

    private fun createViewPager() {
        val tabTexts = arrayListOf<String>("All", "Flights", "Hotels", "Transportations")
        var viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        viewPager.isUserInputEnabled = false
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "${tabTexts[position]}"
        }.attach()
    }

    private fun observeLiveData() {
        viewModel.pageState.observe(viewLifecycleOwner) {
            this.viewModel.pageState.value?.let { state ->
                if (state.isLoading) {
                    println("loading")
                }
                if (state.isError) {
                    println("there is a error")
                }
                if (state.allTravelItem.isEmpty()) {
                    println("null")
                } else {
                    println("not null")
                    adapter.updateEstateList(
                        listOf(
                            state.allTravelItem,
                            state.hotels,
                            state.flights,
                            state.transportation
                        )
                    )
                    println(state.allTravelItem?.get(1)?.description)

                }
            }
        }
    }

}