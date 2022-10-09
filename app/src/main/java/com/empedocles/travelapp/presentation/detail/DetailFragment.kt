package com.empedocles.travelapp.presentation.detail

import android.os.Bundle
import android.util.JsonWriter
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import com.empedocles.travelapp.R
import com.empedocles.travelapp.databinding.FragmentDetailBinding
import com.empedocles.travelapp.databinding.FragmentHomeBinding
import com.empedocles.travelapp.presentation.home.HomeViewModel
import com.empedocles.travelapp.util.circularProgressFactory
import com.empedocles.travelapp.util.downloadFromUrl
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ActivityContext

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private val viewModel by viewModels<DetailViewModel>()
    private lateinit var binding: FragmentDetailBinding
    private lateinit var selectedId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        arguments?.getString("id")?.let { id ->
            selectedId = id
            viewModel.loadSelectedItem(id)
        }
        binding = FragmentDetailBinding.inflate(inflater)
        binding.root.context as? LifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createView()
        observeLiveData()
    }


    private fun createView(){
        binding.addBookMark.setOnClickListener{
            viewModel.pageState.value?.selectedItem?.let { item ->
                val isBookmark = !item.isBookmark
                item.isBookmark = isBookmark
                viewModel.bookMarkHandler(selectedId, isBookmark)
            }
        }
    }


    private fun observeLiveData() {
        viewModel.pageState.observe(viewLifecycleOwner) {
            this.viewModel.pageState.value?.let { state ->
                if (state.isLoading) {
                    println("loading")
                }
                if (state.isError) {
                    println("there is a error")
                } else {
                    println(state.selectedItem?.description)
                    state.selectedItem?.images?.get(0)?.url?.let { image ->
                        println(state.selectedItem.toString())
                        binding.travelModel = state.selectedItem
                        println(state.selectedItem?.title)
                        binding.detailFragmentBanner.downloadFromUrl(
                            image,
                            circularProgressFactory(binding.root.context)
                        )
                    }
                }
            }
        }
    }

}