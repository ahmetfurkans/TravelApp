package com.empedocles.travelapp.presentation.search_result

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.empedocles.travelapp.R
import com.empedocles.travelapp.databinding.FragmentSearchResultBinding
import com.empedocles.travelapp.presentation.detail.DetailViewModel
import com.empedocles.travelapp.presentation.search.NearbyAdapter
import com.empedocles.travelapp.util.circularProgressFactory
import com.empedocles.travelapp.util.downloadFromUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchResultFragment : Fragment() {
    private val viewModel by viewModels<SearchResultViewModel>()
    private lateinit var query: String
    private var adapter = NearbyAdapter(arrayListOf())
    private lateinit var binding : FragmentSearchResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        arguments?.getString("query")?.let { queryArgs ->
            query = queryArgs
            viewModel.loadAllTravelItem(query)
        }
        binding = FragmentSearchResultBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createUi()
        observeLiveData()
    }

    private fun createUi(){
        binding.searchQuery.text = query
        createNearbyRecycler()
        binding.backIcon.setOnClickListener{
            findNavController().navigateUp()
        }
    }

    private fun createNearbyRecycler() {
        binding.searchResultRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.searchResultRecycler.adapter = adapter
    }

    private fun observeLiveData() {
        viewModel.pageState.observe(viewLifecycleOwner) {
            this.viewModel.pageState.value?.let { state ->
                if (state.isLoading) {
                    binding.feedProgressBar.visibility = View.VISIBLE
                }
                if (state.isError) {
                    binding.feedProgressBar.visibility = View.GONE
                    println("Error")
                } else {
                    binding.feedProgressBar.visibility = View.GONE
                    if (state.searchResults.isEmpty() && !state.isLoading){
                        binding.noResult.visibility = View.VISIBLE
                        binding.searchResultRecycler.visibility = View.GONE
                    }else{
                        binding.noResult.visibility = View.GONE
                        binding.searchResultRecycler.visibility = View.VISIBLE
                        adapter.updateList(state.searchResults)

                    }
                }
            }
        }
    }
}

