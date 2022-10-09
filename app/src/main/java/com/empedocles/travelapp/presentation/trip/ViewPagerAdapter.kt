package com.empedocles.travelapp.presentation.trip

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.empedocles.travelapp.databinding.FragmentTripTabLayoutItemBinding
import com.empedocles.travelapp.domain.model.TravelModel
import com.empedocles.travelapp.presentation.search.NearbyAdapter

class ViewPagerAdapter(private val travelCategoryList: ArrayList<List<TravelModel>>) :
    RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {
    class ViewPagerViewHolder(
        private val binding: FragmentTripTabLayoutItemBinding,
        private val context: Context
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(list: List<TravelModel>) {
            val adapter = NearbyAdapter(ArrayList(list))
            binding.tripRecyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            binding.tripRecyclerView.adapter = adapter
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val binding =
            FragmentTripTabLayoutItemBinding.inflate(
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


    fun updateList(newEstateList: ArrayList<List<TravelModel>>) {
        travelCategoryList.clear()
        travelCategoryList.addAll(newEstateList)
        notifyDataSetChanged()
    }

}