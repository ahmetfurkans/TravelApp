package com.empedocles.travelapp.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.empedocles.travelapp.R
import com.empedocles.travelapp.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private var topDestinationAdapter = TopDestinationAdapter(arrayListOf())
    private val viewModel by viewModels<SearchViewModel>()
    private var nearbyAdapter = NearbyAdapter(arrayListOf())

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
        binding.searchFragmentSearch.setEndIconOnClickListener {
            val query = binding.searchField.text.toString()
            val bundle = bundleOf("query" to query)
            it.findNavController().navigate(R.id.action_global_searchResultFragment, bundle)
        }
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
        viewModel.loadAllTravelItem().observe(viewLifecycleOwner) {
            val topDestination = it.filter { item -> item.category == "topdestination" }
            val nearby = it.filter { item -> item.category == "nearby" }
            topDestinationAdapter.updateList(
                topDestination
            )
            nearbyAdapter.updateList(nearby)
        }
    }


}