package com.empedocles.travelapp.presentation.detail


import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.empedocles.travelapp.R
import com.empedocles.travelapp.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import me.relex.circleindicator.CircleIndicator

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private val viewModel by viewModels<DetailViewModel>()
    private lateinit var binding: FragmentDetailBinding
    private lateinit var selectedId: String
    private lateinit var imageSlideAdapter: ImageSlideAdapter
    private lateinit var indicator: CircleIndicator


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
        createOnClickFunctions()
        observeLiveData()
    }

    private fun createOnClickFunctions() {
        binding.addBookMark.setOnClickListener { addBookMark() }
        binding.backIcon.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun addBookMark() {
        viewModel.pageState.value?.selectedItem?.let { item ->
            val isBookmark = !item.isBookmark
            item.isBookmark = isBookmark
            viewModel.bookMarkHandler(selectedId, isBookmark)
            binding.addBookMark.text =
                if (item.isBookmark) "Remove Bookmark" else "Add Bookmark"
        }
    }

    private fun observeLiveData() {
        viewModel.pageState.observe(viewLifecycleOwner) {
            this.viewModel.pageState.value?.let { state ->
                if (state.isLoading) {
                    binding.feedProgressBar.visibility = View.VISIBLE
                    binding.error.visibility = View.GONE
                }
                if (state.isError) {
                    binding.feedProgressBar.visibility = View.GONE
                    binding.error.visibility = View.VISIBLE
                } else {
                    binding.feedProgressBar.visibility = View.GONE
                    binding.error.visibility = View.GONE
                    createDetailView(state)
                }
            }
        }
    }

    private fun createDetailView(state: DetailState) {
        state.selectedItem?.images?.get(0)?.url?.let { image ->
            binding.travelModel = state.selectedItem
            binding.fragmentDetailH3.movementMethod = ScrollingMovementMethod()
            state.selectedItem?.isBookmark?.let { isBookmark ->
                binding.addBookMark.text =
                    if (isBookmark) "Remove Bookmark" else "Add Bookmark"
            }
            state.selectedItem?.images?.let {
                val imageList: List<String> = it.map { it -> it.url }
                createImageSlider(imageList)
            }
        }
    }

    private fun createImageSlider(imageList: List<String>) {
        imageSlideAdapter =
            ImageSlideAdapter(requireContext(), ArrayList(imageList))
        binding.detailFragmentBanner.adapter = imageSlideAdapter
        indicator =
            requireView().findViewById(R.id.indicator) as CircleIndicator
        indicator.setViewPager(binding.detailFragmentBanner)
    }

}