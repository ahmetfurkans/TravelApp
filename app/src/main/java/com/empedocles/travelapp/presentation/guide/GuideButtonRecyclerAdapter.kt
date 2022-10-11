package com.empedocles.travelapp.presentation.guide

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.empedocles.travelapp.databinding.FragmentGuideButtonRecyclerItemBinding
import com.empedocles.travelapp.presentation.home.ButtonModel


class GuideButtonRecyclerAdapter :
    RecyclerView.Adapter<GuideButtonRecyclerAdapter.ItemHolder>() {

    val buttonList = listOf(
        GuideButtonConstants.SIGHTSEEING,
        GuideButtonConstants.RESORT,
        GuideButtonConstants.RESTAURANT,
        GuideButtonConstants.SIGHTSEEING,
        GuideButtonConstants.RESORT
    )

    class ItemHolder(private val binding: FragmentGuideButtonRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(buttonModel: ButtonModel) {
            binding.buttonImage.setImageResource(buttonModel.id)
            binding.buttonText.text = buttonModel.text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemBinding = FragmentGuideButtonRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(buttonList[position])
    }

    override fun getItemCount(): Int {
        return buttonList.size
    }
}