package com.empedocles.travelapp.presentation.trip

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.empedocles.travelapp.R
import com.empedocles.travelapp.databinding.FragmentTripBinding
import com.empedocles.travelapp.domain.model.TravelModel
import com.empedocles.travelapp.presentation.home.ButtonModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TripFragment : Fragment() {
    private lateinit var binding: FragmentTripBinding
    private val emptyList = ArrayList<List<TravelModel>>()
    private val viewModel by viewModels<TripViewModel>()

    private val adapter = ViewPagerAdapter(emptyList)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTripBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createViewPager()
        observeLiveData()
    }

    private fun createViewPager() {
        val tabItems = arrayListOf<ButtonModel>(
            ButtonModel(R.drawable.ic_trip, "Trips"),
            ButtonModel(R.drawable.ic_bookmark, "Bookmark")
            )

        val viewpager = binding.viewPager
        val tabLayout = binding.tabLayout

        viewpager.adapter = adapter
        viewpager.isUserInputEnabled = false

        TabLayoutMediator(tabLayout, viewpager) { tab, position ->
            tab.text = "${tabItems.get(position).text}"
            tab.setIcon(tabItems[position].id)
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
                if (state.trip.isEmpty()) {
                    println("null")
                } else {
                    println("not null")
                    println(viewModel.pageState.value?.trip?.get(1)?.description)
                    adapter.updateList(
                        arrayListOf(
                            state.trip,
                            state.bookmark
                        )
                    )
                }
            }
        }
    }
}