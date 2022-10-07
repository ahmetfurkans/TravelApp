package com.empedocles.travelapp.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.empedocles.travelapp.R
import com.empedocles.travelapp.databinding.FragmentSearchNearbyrecyclerItemBinding
import com.empedocles.travelapp.databinding.FragmentSearchToprecyclerItemBinding
import com.empedocles.travelapp.domain.model.TravelModel
import com.empedocles.travelapp.util.circularProgressFactory
import com.empedocles.travelapp.util.downloadFromUrl

class NearbyAdapter (private val travelList: ArrayList<TravelModel>) :
    RecyclerView.Adapter<NearbyAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemBinding = FragmentSearchNearbyrecyclerItemBinding.inflate(
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

    class ItemHolder(private val binding: FragmentSearchNearbyrecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(travelModel: TravelModel) {
            binding.imageView.setOnClickListener {
                val bundle = bundleOf("id" to travelModel.id)
                it.findNavController().navigate(R.id.action_global_detailFragment, bundle)
            }
            binding.root.context as? LifecycleOwner
            binding.travelModel = travelModel
            binding.imageView.downloadFromUrl(
                travelModel.images.get(0).url, circularProgressFactory(binding.root.context)
            )
        }
    }

    fun updateList(newEstateList: List<TravelModel>) {
        travelList.clear()
        travelList.addAll(newEstateList)
        notifyDataSetChanged()
    }
}