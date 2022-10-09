package com.empedocles.travelapp.presentation.guide

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.empedocles.travelapp.databinding.FragmentGuideBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GuideFragment : Fragment() {
    private lateinit var binding: FragmentGuideBinding
    private var topPickRecyclerAdapter = TopPickRecyclerAdapter(arrayListOf())
    private var mightNeedAdapter = MightNeedRecyclerAdapter(arrayListOf())
    private val viewModel by viewModels<GuideViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGuideBinding.inflate(inflater)
        createUi()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeLiveData()
    }


    private fun createUi() {
        createMightRecycler()
        createTopPickRecycler()
        createButtonRecycler()
    }

    private fun createMightRecycler() {
        binding.mightNeedRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.mightNeedRecycler.adapter = mightNeedAdapter
    }

    private fun createTopPickRecycler() {
        binding.toppickRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.toppickRecycler.adapter = topPickRecyclerAdapter
    }

    private fun createButtonRecycler(){
        binding.buttonRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.buttonRecycler.adapter = GuideButtonRecyclerAdapter()
    }

    private fun observeLiveData() {
        viewModel.loadAllTravelItem().observe(viewLifecycleOwner) {
            val topPicks = it.filter { item -> item.category == "toppick" }
            val mightneed = it.filter { item -> item.category == "mightneed" }
            topPickRecyclerAdapter.updateList(
                topPicks
            )
            mightNeedAdapter.updateList(mightneed)
        }
    }
}