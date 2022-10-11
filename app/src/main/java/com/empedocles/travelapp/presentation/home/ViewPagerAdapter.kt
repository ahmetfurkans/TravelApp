package com.empedocles.travelapp.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.empedocles.travelapp.databinding.FragmentHomeTabLayoutItemBinding
import com.empedocles.travelapp.domain.model.TravelModel

class ViewPagerAdapter(private val travelCategoryList: ArrayList<List<TravelModel>>) :
    RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    class ViewPagerViewHolder(
        private val binding: FragmentHomeTabLayoutItemBinding,
        private val context: Context
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(list: List<TravelModel>) {
            val adapter = ViewPagerRecyclerAdapter(list)
            binding.homeRecyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            binding.homeRecyclerView.adapter = adapter
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val binding =
            FragmentHomeTabLayoutItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewPagerViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        holder.bind(travelCategoryList[position])
    }

    override fun getItemCount(): Int {
        return travelCategoryList.size
    }


    fun updateList(newEstateList: List<List<TravelModel>>) {
        travelCategoryList.clear()
        travelCategoryList.addAll(newEstateList)
        notifyDataSetChanged()
    }

}