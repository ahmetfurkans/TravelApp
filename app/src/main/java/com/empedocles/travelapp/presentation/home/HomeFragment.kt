package com.empedocles.travelapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.empedocles.travelapp.databinding.FragmentHomeBinding
import com.empedocles.travelapp.domain.model.TravelModel
import com.google.android.material.tabs.TabLayoutMediator
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
        val tabTexts = arrayListOf<String>("All", "Hotels", "Flights", "Transportations")
        var viewPager = binding.viewPager
        val tabLayout = binding.tabLayout
        viewPager.isUserInputEnabled = false
        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "${tabTexts[position]}"
        }.attach()
    }

    private fun observeLiveData() {
        viewModel.loadAllTravelItem().observe(viewLifecycleOwner) {
            val hotels = it.filter { item -> item.category == "hotel" }
            val flights = it.filter { item -> item.category == "flight" }
            val transportations = it.filter { item -> item.category == "transportation" }
            val all = it.subList(5, 15)
            adapter.updateList(
                arrayListOf(
                    all, hotels, flights, transportations
                )
            )
        }
    }
}