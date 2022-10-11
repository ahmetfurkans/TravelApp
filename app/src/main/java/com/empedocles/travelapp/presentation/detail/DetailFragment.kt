package com.empedocles.travelapp.presentation.detail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.empedocles.travelapp.databinding.FragmentDetailBinding
import com.empedocles.travelapp.util.circularProgressFactory
import com.empedocles.travelapp.util.downloadFromUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private val viewModel by viewModels<DetailViewModel>()
    private lateinit var binding: FragmentDetailBinding
    private lateinit var selectedId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

    private fun createView() {
        binding.addBookMark.setOnClickListener {
            viewModel.pageState.value?.selectedItem?.let { item ->
                val isBookmark = !item.isBookmark
                item.isBookmark = isBookmark
                viewModel.bookMarkHandler(selectedId, isBookmark)
                binding.addBookMark.text =
                    if (item.isBookmark) "Remove Bookmark" else "Add Bookmark"
            }
        }
        binding.backIcon.setOnClickListener{
            findNavController().navigateUp()
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
                        state.selectedItem?.isBookmark?.let { isBookmark ->
                            binding.addBookMark.text =
                                if (isBookmark) "Remove Bookmark" else "Add Bookmark"
                        }
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