package com.empedocles.travelapp.presentation.guide

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.empedocles.travelapp.R
import com.empedocles.travelapp.databinding.FragmentGuideTopPickRecyclerItemBinding
import com.empedocles.travelapp.domain.model.TravelModel
import com.empedocles.travelapp.util.circularProgressFactory
import com.empedocles.travelapp.util.downloadFromUrl

class TopPickRecyclerAdapter(private val travelList: ArrayList<TravelModel>) :
    RecyclerView.Adapter<TopPickRecyclerAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemBinding = FragmentGuideTopPickRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(travelList[position])
    }

    override fun getItemCount(): Int {
        return travelList.size
    }

    class ItemHolder(private val binding: FragmentGuideTopPickRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(selectedTravelModel: TravelModel) {
            binding.apply {
                topImage.setOnClickListener {
                    val bundle = bundleOf("id" to selectedTravelModel.id)
                    it.findNavController().navigate(R.id.action_global_detailFragment, bundle)
                }
                val bookMarkDrawable =
                    if (selectedTravelModel.isBookmark) R.drawable.ic_bookmark_selected else R.drawable.ic_bookmark
                bookmarkButton.setImageResource(bookMarkDrawable)
                root.context as? LifecycleOwner
                travelModel = selectedTravelModel
                topImage.downloadFromUrl(
                    selectedTravelModel.images[1].url, circularProgressFactory(root.context)
                )
            }
        }
    }

    fun updateList(newList: List<TravelModel>) {
        travelList.clear()
        travelList.addAll(newList)
        notifyDataSetChanged()
    }
}