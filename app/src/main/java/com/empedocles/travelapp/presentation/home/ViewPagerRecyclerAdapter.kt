package com.empedocles.travelapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.empedocles.travelapp.R
import com.empedocles.travelapp.databinding.FragmentHomeTabLayoutRecyclerItemBinding
import com.empedocles.travelapp.domain.model.TravelModel
import com.empedocles.travelapp.util.circularProgressFactory
import com.empedocles.travelapp.util.downloadFromUrl

class ViewPagerRecyclerAdapter(private val travelList: List<TravelModel>)
    : RecyclerView.Adapter<ViewPagerRecyclerAdapter.ItemHolder>() {
    class ItemHolder(private val binding: FragmentHomeTabLayoutRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(travelModel: TravelModel) {
            binding.imageView.setOnClickListener {
                val bundle = bundleOf("id" to travelModel.id)
                it.findNavController().navigate(R.id.action_global_detailFragment, bundle)
            }
            binding.imageView.downloadFromUrl(
                travelModel.images[0].url, circularProgressFactory(binding.root.context)
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemBinding = FragmentHomeTabLayoutRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ItemHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(travelList[position])
    }

    override fun getItemCount(): Int {
        return travelList.size
    }
}
