package com.empedocles.travelapp.presentation.trip

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.empedocles.travelapp.data.local.TripEntity
import com.empedocles.travelapp.databinding.FragmentTripTabLayoutItemBinding
import com.empedocles.travelapp.domain.model.TravelModel
import com.empedocles.travelapp.presentation.search.NearbyAdapter

class ViewPagerAdapter(
    private val tripList: ArrayList<TripEntity>,
    private val bookMarkList: ArrayList<TravelModel>
) :
    RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

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
        holder.bind(position, tripList, bookMarkList)
    }

    override fun getItemCount(): Int {
        return 2
    }

    class ViewPagerViewHolder(
        private val binding: FragmentTripTabLayoutItemBinding,
        private val context: Context
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            position: Int,
            tripList: ArrayList<TripEntity>,
            bookMarkList: ArrayList<TravelModel>
        ) {
            when (position) {
                0 -> {
                    val adapter = TripAdapter(tripList)
                    binding.tripRecyclerView.layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    binding.tripRecyclerView.adapter = adapter
                }

                1 -> {
                    println("2")
                    val adapter = NearbyAdapter(bookMarkList)
                    binding.tripRecyclerView.layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    binding.tripRecyclerView.adapter = adapter
                }
            }
        }

    }


    fun updateList(
        newTripList: List<TripEntity> = tripList,
        newBookMarkList: List<TravelModel> = bookMarkList
    ) {
        tripList.clear()
        tripList.addAll(newTripList)
        bookMarkList.clear()
        bookMarkList.addAll(newBookMarkList)
        notifyDataSetChanged()
    }

}