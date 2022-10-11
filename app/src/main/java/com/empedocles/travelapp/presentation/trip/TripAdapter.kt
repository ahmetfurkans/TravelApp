package com.empedocles.travelapp.presentation.trip

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.empedocles.travelapp.R
import com.empedocles.travelapp.data.local.TripEntity
import com.empedocles.travelapp.databinding.FragmentTripTripRecyclerItemBinding
import com.empedocles.travelapp.util.circularProgressFactory
import com.empedocles.travelapp.util.downloadFromUrl

class TripAdapter(private val travelList: ArrayList<TripEntity>) :
    RecyclerView.Adapter<TripAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemBinding = FragmentTripTripRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(travelList.get(position))
    }

    override fun getItemCount(): Int {
        return travelList.size
    }

    class ItemHolder(private val binding: FragmentTripTripRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tripEntity: TripEntity) {
            binding.imageView.setOnClickListener {
                val bundle = bundleOf("id" to tripEntity.id)
                it.findNavController().navigate(R.id.action_global_detailFragment, bundle)
            }
            binding.root.context as? LifecycleOwner
            binding.tripEntity = tripEntity
            binding.imageView.downloadFromUrl(
                tripEntity.url, circularProgressFactory(binding.root.context)
            )
        }
    }
}