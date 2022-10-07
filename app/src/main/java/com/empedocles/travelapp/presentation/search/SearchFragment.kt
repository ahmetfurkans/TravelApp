package com.empedocles.travelapp.presentation.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.empedocles.travelapp.R
import com.empedocles.travelapp.databinding.FragmentSearchBinding
import com.empedocles.travelapp.presentation.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private var topDestinationAdapter = TopDestinationAdapter(arrayListOf())
    private var nearbyAdapter = NearbyAdapter(arrayListOf())

    private val viewModel by viewModels<SearchViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createUi()
        observeLiveData()
    }

    private fun createUi() {
        createTopDestinationRecycler()
        createNearbyRecycler()
    }

    private fun createTopDestinationRecycler() {
        binding.topDestinationRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.topDestinationRecycler.adapter = topDestinationAdapter
    }

    private fun createNearbyRecycler() {
        binding.nearbyRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.nearbyRecycler.adapter = nearbyAdapter
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
                    topDestinationAdapter.updateList(
                        state.topDestination
                    )
                    nearbyAdapter.updateList(
                        state.nearby
                    )
                }
            }
        }
    }


}