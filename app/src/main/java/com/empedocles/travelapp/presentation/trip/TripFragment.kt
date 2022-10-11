package com.empedocles.travelapp.presentation.trip

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.empedocles.travelapp.R
import com.empedocles.travelapp.data.local.TripEntity
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
    private var tripList = listOf<TripEntity>()
    private var bookMarkList = listOf<TravelModel>()

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
        binding.addTripButton.setOnClickListener {
            val dialogFragment = AddTripFragment()
            dialogFragment.show(parentFragmentManager, "MyFragment")
        }
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
        viewModel.loadAllTravelItem().observe(viewLifecycleOwner) {
            val bookmark = it.filter { item -> item.isBookmark == true }
            val trip = it
            adapter.updateList(
                arrayListOf(
                    trip,
                    bookmark
                )
            )
        }
    }

    private fun observeTripRoomLiveData(){
        viewModel.loadAllTravelItemFromRoom().observe(viewLifecycleOwner){
            tripList = it
        }
    }
}